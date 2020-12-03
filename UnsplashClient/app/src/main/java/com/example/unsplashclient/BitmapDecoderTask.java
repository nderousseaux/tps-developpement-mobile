package com.example.unsplashclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BitmapDecoderTask extends AsyncTask<String, Void, Bitmap> {

    WeakReference<MainActivity> mainActivity;
    BitmapDecoderTask(MainActivity mainActivity){
        this.mainActivity=new WeakReference<>(mainActivity);
    }

    @Override
    protected Bitmap doInBackground(String[] urls) {
        String stringUrl= urls[0];
        Bitmap bm=null;

        URL url= null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(url != null) {
            try {
                bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        MainActivity myActivity=mainActivity.get();
        if(!this.isCancelled() && myActivity != null) {
            myActivity.updateBitmap(bm);
        }

    }
}
