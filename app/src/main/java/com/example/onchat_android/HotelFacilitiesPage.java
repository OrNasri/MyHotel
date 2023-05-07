package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HotelFacilitiesPage extends AppCompatActivity {

//        public class DownloadFilesTask extends AsyncTask {
//            public Map<String, QueryResult> detectIntentTexts(
//                    HotelFacilitiesPage hfp, String projectId, List<String> texts, String sessionId, String languageCode)
//                    throws IOException, ApiException {
//                Map<String, QueryResult> queryResults = Maps.newHashMap();
//
//                GoogleCredentials credentials = GoogleCredentials.fromStream(hfp.getResources().openRawResource(R.raw.c));
//                SessionsSettings.Builder settingsBuilder = SessionsSettings.newBuilder();
//                SessionsSettings sessionsSettings = settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build();
//
//                // Instantiates a client
//                try (SessionsClient sessionsClient = SessionsClient.create(sessionsSettings)) {
//                    // Set the session name using the sessionId (UUID) and projectID (my-project-id)
//                    SessionName session = SessionName.of(projectId, sessionId);
//                    System.out.println("Session Path: " + session.toString());
//
//                    // Detect intents for each text input
//                    for (String text : texts) {
//                        // Set the text (hello) and language code (en-US) for the query
//                        TextInput.Builder textInput =
//                                TextInput.newBuilder().setText(text).setLanguageCode(languageCode);
//
//                        // Build the query with the TextInput
//                        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
//
//                        // Performs the detect intent request
//                        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
//
//                        // Display the query result
//                        QueryResult queryResult = response.getQueryResult();
//// queryResult.getFulfillmentMessagesCount() - output
//                        System.out.println("====================");
//                        System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
//                        System.out.format(
//                                "Detected Intent: %s (confidence: %f)\n",
//                                queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
//                        System.out.format(
//                                "Fulfillment Text: '%s'\n",
//                                queryResult.getFulfillmentMessagesCount() > 0
//                                        ? queryResult.getFulfillmentMessages(0).getText()
//                                        : "Triggered Default Fallback Intent");
//
//                        queryResults.put(text, queryResult);
//                    }
//                }
//                return queryResults;
//            }
//
//            @Override
//            protected Object doInBackground(Object[] objects) {
//                List<String> ls = new ArrayList<>();
//                ls.add("hello");
//                try {
//                    detectIntentTexts(HotelFacilitiesPage.this,"onchat-android-33122", ls, "1","en-US");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                return null;
//            }
//        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hotel_facilities_page);


////          call to doInBackground
//        new DownloadFilesTask().execute();



        Button buttonBack = findViewById(R.id.backToMenuFacilities);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            startActivity(i);
        });

        List<String> facilities = new ArrayList<>();
        facilities.add("gym");
        facilities.add("Self");
        facilities.add("bar");
        facilities.add("Luggage");


        ListView lvProgram = findViewById(R.id.lvProgramFacilities);

        List<String> contactsNamesList = new ArrayList<>();
        contactsNamesList.add("Gym");
        contactsNamesList.add("Self-service laundry");
        contactsNamesList.add("Bar");
        contactsNamesList.add("Luggage room at Front desk");

        List<String> contactsMessagesList = new ArrayList<>();
        contactsMessagesList.add("Daily: 06:00 - 22:00");
        contactsMessagesList.add("Daily: 06:00 - 22:00");
        contactsMessagesList.add("Daily: 07:00 - 24:00");
        contactsMessagesList.add("Daily: 24/7");


        List<Integer> contactsImagesList = new ArrayList<>();
        contactsImagesList.add(R.drawable.gym);
        contactsImagesList.add(R.drawable.laundry);
        contactsImagesList.add(R.drawable.bar);
        contactsImagesList.add(R.drawable.luggage);


        FacilitiesAdapter adapter = new FacilitiesAdapter(this, contactsNamesList,
                contactsMessagesList, contactsImagesList);

        lvProgram.setAdapter(adapter);

    }
}