package com.example.luizh.chat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AsyncTaskSendMessage extends AsyncTask<String,
        String, String> {

    private Chat c;

    public AsyncTaskSendMessage(Chat c){
        this.c = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("nome", c.getName());
            jsonobj.put("mensagem", c.getMessage());

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppostreq = new HttpPost("http://10.30.199.240:8080/chatrest/rest/servicorest/post");

            StringEntity se = new StringEntity(jsonobj.toString());
            se.setContentType("application/json;charset=UTF-8");
            httppostreq.setEntity(se);

            HttpResponse httpresponse = httpclient.execute(httppostreq);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}