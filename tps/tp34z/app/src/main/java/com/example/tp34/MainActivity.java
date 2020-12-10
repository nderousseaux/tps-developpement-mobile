package com.example.tp34;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {
    String client_id = "JVeAv1qO78UbaWrrhZ6ttZgKNCk-k6mCDRF0t1BZ0vY";
    String query;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = findViewById(R.id.buttonSearch);
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        image = findViewById(R.id.imageView);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = editTextSearch.getText().toString();
                System.out.println("Prochaine ligne :");
                System.out.println(query);
                if (query.length() != 0) {
                    try {
                        volleyFetchData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Entrez une valeur dans le champ de recherche", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void updateView(JSONObject result){
        TextView textResult = findViewById(R.id.textViewResult);
        String total = "0";
        String total_pages = "0";
        String picture_url = "none";
        String user = "unknown";
        String description = "no description";

        try {
            total = result.getString("total");
            total_pages = result.getString("total_pages");
            user = result.getJSONArray("results").getJSONObject(0).getJSONObject("user").getString("username");
            picture_url = result.getJSONArray("results").getJSONObject(0).getJSONObject("urls").getString("full");
            description = result.getJSONArray("results").getJSONObject(0).getString("description");
            // Download image fromm picture_url
            requestImage(picture_url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String res = "Elements trouvés : "+total+"\n"+"Nombre de pages : "+total_pages+"\n\n"+"Description : "+description+"\n"+"Auteur : "+user;
        textResult.setText(res);
    }

    public void volleyFetchData() {
        //On instancie la queue avec toutes les requêtes
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.unsplash.com/search/photos?client_id="+client_id+"&query="+query;
        JsonObjectRequest data = new JsonObjectRequest(Request.Method.GET, url, null, data1 -> {
            System.out.println("Salut");
            updateView(data1);
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // updateError(error);
            }
        });
        queue.add(data);
    }

    public void requestImage(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap> () {
            @Override
            public void onResponse(Bitmap response) {
                image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(imageRequest);
    }
}
