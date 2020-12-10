package com.example.tp34;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    //On definit la variables ""globales""
    TextView txtError;
    String token = "JVeAv1qO78UbaWrrhZ6ttZgKNCk-k6mCDRF0t1BZ0vY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtError = findViewById(R.id.txtError);

        //Lors du click sur le boutton, on appelle l'api
        findViewById(R.id.btnRecherche).setOnClickListener(view -> getData());
    }

    public void getData() {

        TextView loading = findViewById(R.id.txtLoading);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.unsplash.com/search/photos?client_id="+token+"&query="+ ((EditText)findViewById(R.id.edtRecherche)).getText().toString();
        JsonObjectRequest data = new JsonObjectRequest(Request.Method.GET, url, null, data1 -> {
            printData(data1);
            loading.setText("");
        }, error -> txtError.setText(error.getMessage()));

        loading.setText("Loading...");
        txtError.setText("");

        queue.add(data);
    }

    void printData(JSONObject data){
        try{
            //Données générales
            ((TextView)findViewById(R.id.txtNbElements)).setText("Nb Elements :" + data.getString("total"));
            ((TextView)findViewById(R.id.txtNbPages)).setText("Nb Pages :" + data.getString("total_pages"));


            //Première image
            JSONObject firstPic = data.getJSONArray("results").getJSONObject(0);

            ((TextView)findViewById(R.id.txtDescription)).setText("Description :" + firstPic.getString("description"));
            ((TextView)findViewById(R.id.txtAuteur)).setText("Auteur :" + firstPic.getJSONObject("user").getString("username"));
            System.out.println(firstPic.getJSONObject("urls").getString("full"));
            requestImage(firstPic.getJSONObject("urls").getString("full"));

        } catch (JSONException e){
            txtError.setText("Aucun résultat");
        } catch (Exception e) {
            txtError.setText(e.getMessage());
        }
    }
    public void requestImage(String url) {
        ImageView imageView = findViewById(R.id.img);

        imageView.setVisibility(View.INVISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(url, response -> {
            imageView.setImageBitmap(response);
            imageView.setVisibility(View.VISIBLE);
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, error -> txtError.setText(error.getMessage()));
        requestQueue.add(imageRequest);
    }
}
