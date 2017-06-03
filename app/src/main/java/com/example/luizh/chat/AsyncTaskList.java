package com.example.luizh.chat;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * Created by luizh on 26/05/2017.
 */

public class AsyncTaskList extends AsyncTask<String, String, String> {

    private Activity activity;
    //private AdapterCustom arrayAdapterChat;
    private ArrayAdapter<Chat> arrayAdapterChat;

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        String result = "";

        try {

            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpGet httpGet = new HttpGet("http://10.30.199.240:8080/chatrest/rest/servicorest");

            HttpResponse httpResponse = httpclient.execute(httpGet);

            result = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonRootObject = new JSONObject(result);

            JSONArray jsonArray = jsonRootObject.optJSONArray("chat");
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //arrayAdapterChat.getChats().clear();
                    arrayAdapterChat.clear();
                }
            });

            for (int i = 0; i < jsonArray.length(); i++) {

                final JSONObject jsonObject = jsonArray.getJSONObject(i);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Chat c = new Chat();
                        c.setName(jsonObject.optString("nome").toString());
                        c.setMessage(jsonObject.optString("mensagem").toString());

                        //arrayAdapterChat.getChats().add(c);
                        //arrayAdapterChat.notifyDataSetChanged();
                        arrayAdapterChat.add(c);

                    }
                });
            }

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return null;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //public AdapterCustom getArrayAdapterChat() {
    public ArrayAdapter<Chat> getArrayAdapterChat() {
        return arrayAdapterChat;
    }

    //public void setArrayAdapterChat(AdapterCustom arrayAdapterChat) {
    public void setArrayAdapterChat(ArrayAdapter<Chat> arrayAdapterChat) {
        this.arrayAdapterChat = arrayAdapterChat;
    }
}