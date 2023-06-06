package com.example.onchat_android.chat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.onchat_android.MenuPage;
import com.example.onchat_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyAgent extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    private List<String> bag;
    String GptAnswer;
    Boolean gpt = false;
    private SessionsClient sessionsClient = null;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    public void InitCred() throws IOException {
        if (sessionsClient == null) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(this.getResources().openRawResource(R.raw.service_account));
            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();

            // Instantiates a client
            sessionsClient = SessionsClient.create(sessionsSettings);
        }
    }
    public class DownloadFilesTask extends AsyncTask {
        private String answer = null;
        private SessionsClient sessionsClient = null;
        public DownloadFilesTask(SessionsClient sessionsClient) {
            this.sessionsClient = sessionsClient;
        }
        public void setAnswer(String answer){
            this.answer = answer;
        }
        public String getAnswer(){
            return answer;
        }
        public Map<String, QueryResult> detectIntentTexts(
                MyAgent hfp, String projectId, List<String> texts, String sessionId, String languageCode)
                throws IOException, ApiException, InterruptedException {
                Map<String, QueryResult> queryResults = Maps.newHashMap();
                ArrayList<String> temp = new ArrayList<>();
                temp.add(texts.get(texts.size() -1));
                // Set the session name using the sessionId (UUID) and projectID (my-project-id)
                SessionName session = SessionName.of(projectId, sessionId);
                System.out.println("Session Path: " + session.toString());

                // Detect intents for each text input
                for (String t : temp) {
                    // Set the text (hello) and language code (en-US) for the query
                    TextInput.Builder textInput =
                            TextInput.newBuilder().setText(t).setLanguageCode(languageCode);

                    // Build the query with the TextInput
                    QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

                    // Performs the detect intent request
                    DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

                    // Display the query result
                    QueryResult queryResult = response.getQueryResult();

                    System.out.println("====================");
                    System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
                    System.out.format(
                            "Detected Intent: %s (confidence: %f)\n",
                            queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
                    System.out.format(
                            "Fulfillment Text: '%s'\n",
                            queryResult.getFulfillmentMessagesCount() > 0
                                    ? queryResult.getFulfillmentMessages(0).getText()
                                    : "Triggered Default Fallback Intent");

                    if (queryResult.getFulfillmentMessagesCount() > 0) {
                        String txt = queryResult.getFulfillmentMessages(0).getText().toString();
                        txt = txt.replace("\\\'", "'");
                        String[] temp1 = txt.split("text:");
                        String[] temp2 = temp1[1].split("\"");
                        setAnswer(null);
                        if(Objects.equals(temp2[1], "None")){
//                            call to chatGPT
                            gpt = true;
                        }
                        else {
                            setAnswer(temp2[1]);
                            queryResults.put(t, queryResult);
                        }
                    }
                }
            texts.clear();
            return queryResults;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            List<String> ls = new ArrayList<>();
            for(int i=0; i<messageList.size();i++){
                String txt = messageList.get(i).getMessage();
                if(!txt.equals("Typing..."))
                    ls.add(txt);
            }
            try {
                detectIntentTexts(MyAgent.this,"onchat-android-33122",ls,
                        "1","en-US");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }

    void callAPI(String question){
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 4000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
                System.out.println("Failed to load response due to "+e.getMessage());
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (gpt) {
                    String responseBodyString = "";
                    try {
                        responseBodyString = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(responseBodyString);
                            JSONArray jsonArray = jsonObject.getJSONArray("choices");
                            String result = jsonArray.getJSONObject(0).getString("text");
                            addResponse(result.trim());
                            GptAnswer = result.trim();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        addResponse("Failed to load response due to " + responseBodyString);
                        System.out.println("Failed to load response due to " + responseBodyString);
                    }

                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_agent);
        getSupportActionBar().hide();
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }


        FloatingActionButton buttonBack = findViewById(R.id.backToMenuAgent);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        messageList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        welcomeTextView = findViewById(R.id.welcome_text);
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_btn);

//        setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);


        sendButton.setOnClickListener((v)->{
            String question = messageEditText.getText().toString().trim();
            addToChat(question,Message.SENT_BY_ME);
            messageEditText.setText("");
            try {
                String res = callAgent(question);
                if (res == "None"){
                    callAPI(question);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            welcomeTextView.setVisibility(View.GONE);
        });
    }

    void addToChat(String message, String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response){
        messageList.remove(messageList.size()-1);
        addToChat(response, Message.SENT_BY_BOT);
    }

    String callAgent(String question) throws InterruptedException, ExecutionException {
        messageList.add(new Message("Typing...", Message.SENT_BY_BOT));
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 4000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try
        {
            InitCred();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        DownloadFilesTask dft = new DownloadFilesTask(sessionsClient);

        // Call to doInBackground
        // Waiting for the thread finished his job.
        String str_result = (String) dft.execute().get();

        String answer = dft.getAnswer();
        if (answer != null){
            addResponse(answer.trim());
            dft.setAnswer(null);
            return answer.trim();
        } else {
            return "None";
        }
    }
}

