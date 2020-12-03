package com.example.unsplashclient2;

import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RequestTask extends AsyncTask<String, Void, String> {

    WeakReference<com.example.unsplashclient2.MainActivity> mainActivity;
    RequestTask(com.example.unsplashclient2.MainActivity mainActivity){
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
                URL req = new URL(com.example.unsplashclient2.MainActivity.api_prefix+"?client_id=" + com.example.unsplashclient2.MainActivity.access_key + "&query=" + query);
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
        com.example.unsplashclient2.MainActivity myActivity=mainActivity.get();
        if(!this.isCancelled() && myActivity != null) {
            myActivity.updateUI(s);
        }

    }
}
