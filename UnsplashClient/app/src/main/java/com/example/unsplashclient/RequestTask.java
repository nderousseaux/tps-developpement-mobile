package com.example.unsplashclient;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RequestTask extends AsyncTask<String, Void, String> {

    WeakReference<MainActivity> mainActivity;
    RequestTask(MainActivity mainActivity){
        this.mainActivity=new WeakReference<>(mainActivity);
    }

    @Override
    protected String doInBackground(String[] query_tab) {
        String result="";
        String query="";
        if(query_tab!=null)
            query=query_tab[0];
        HttpURLConnection urlConnection = null;
        if(!this.isCancelled()) {
            try {
                URL req = new URL(MainActivity.api_prefix+"?client_id=" + MainActivity.access_key + "&query=" + query);
                urlConnection = (HttpURLConnection) req.openConnection();
                Scanner scanner = new Scanner(urlConnection.getInputStream()).useDelimiter("\n");
                result = result + scanner.next();
            } catch (MalformedURLException exception) {
                return null;
            } catch (IOException exception) {
                return null;
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity myActivity=mainActivity.get();
        if(!this.isCancelled() && myActivity != null) {
            myActivity.updateUI(s);
        }

    }
}
