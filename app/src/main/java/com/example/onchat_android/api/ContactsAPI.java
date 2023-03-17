package com.example.onchat_android.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactsAPI {
    private String BaseUrl;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public void setWebServiceAPI(WebServiceAPI webServiceAPI) {
        this.webServiceAPI = webServiceAPI;
    }

    public WebServiceAPI getWebServiceAPI() {
        return this.webServiceAPI;
    }

    public ContactsAPI(String server) {
        BaseUrl = "http://" + server + "/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
}
