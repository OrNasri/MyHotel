package com.example.onchat_android;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onchat_android.api.PostAPI;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private SessionsClient sessionsClient = null;

//    public static final MediaType JSON
//            = MediaType.get("application/json; charset=utf-8");

    public void InitCred() throws IOException {
        if (sessionsClient == null) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(this.getResources().openRawResource(R.raw.c));
            SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
            SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();

            // Instantiates a client
            sessionsClient = SessionsClient.create(sessionsSettings);
        }
    }
    public class DownloadFilesTask extends AsyncTask {
        private String answer;
        private SessionsClient sessionsClient = null;
        public DownloadFilesTask(SessionsClient sessionsClient) {
            this.sessionsClient = sessionsClient;
        }

        private void setAnswer(String answer){
            this.answer = answer;
        }
        public String getAnswer(){
            return answer;
        }
        public Map<String, QueryResult> detectIntentTexts(
                MyAgent hfp, String projectId, List<String> texts, String sessionId, String languageCode)
                throws IOException, ApiException {
            Map<String, QueryResult> queryResults = Maps.newHashMap();

//            if (sessionsClient == null) {
//                GoogleCredentials credentials = GoogleCredentials.fromStream(hfp.getResources().openRawResource(R.raw.c));
//                SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
//                SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
//
//                // Instantiates a client
//                sessionsClient = SessionsClient.create(sessionsSettings);
//            }
                // Set the session name using the sessionId (UUID) and projectID (my-project-id)
                SessionName session = SessionName.of(projectId, sessionId);
                System.out.println("Session Path: " + session.toString());

                // Detect intents for each text input
                for (String text : texts) {
                    // Set the text (hello) and language code (en-US) for the query
                    TextInput.Builder textInput =
                            TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

                    // Build the query with the TextInput
                    QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

                    // Performs the detect intent request
                    DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

                    // Display the query result
                    QueryResult queryResult = response.getQueryResult();
// queryResult.getFulfillmentMessagesCount() - output
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
                        String[] temp1 = txt.split("text:");
                        String[] temp2 = temp1[1].split("\"");
                        setAnswer(temp2[1]);
                    }


                    queryResults.put(text, queryResult);
//                    queryResult.getFulfillmentMessages(0);

                }
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_agent);
        getSupportActionBar().hide();

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
                callAgent(question);
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

    void callAgent(String question) throws InterruptedException, ExecutionException {
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
        {InitCred();}
        catch (Exception e)
        {}
        DownloadFilesTask dft = new DownloadFilesTask(sessionsClient);

        // call to doInBackground
        String str_result = (String) dft.execute().get();

        String answer = dft.getAnswer();
        addResponse(answer.trim());

    }


}


