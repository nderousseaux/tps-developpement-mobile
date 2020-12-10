package com.example.tp34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //On definit la variables ""globales""
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PhotoAdapter photoAdapter;
    TextView txtMessage;
    TextView txtPage;
    String query;
    int page = 1;
    int nbPages = 1;
    Button btnPrev;
    Button btnNext;
    String token = "JVeAv1qO78UbaWrrhZ6ttZgKNCk-k6mCDRF0t1BZ0vY";
    List<Photo> listePhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On récupère les items
        txtMessage = findViewById(R.id.txtMessage);
        txtPage = findViewById(R.id.txtPage);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        //Au début, les bouttons ne se voient pas
        btnPrev.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);

        //Lors du click sur le boutton, on appelle l'api avec le texte retrouvé dans l'editText
        findViewById(R.id.btnRecherche).setOnClickListener(view -> {
            query = ((EditText)findViewById(R.id.edtRecherche)).getText().toString();
            if (query.length() == 0) {
                txtMessage.setText("Champ de recherche vide");
            } else {
                btnPrev.setVisibility(view.INVISIBLE);
                btnNext.setVisibility(view.VISIBLE);
                page = 1;
                txtPage.setText(""+page);
                getData(query);
            }
        });


        //On définit la logique des boutons
        btnPrev.setOnClickListener(view -> {
            page -= 1;
            txtPage.setText(""+page);

            if(page == 1){
                btnPrev.setVisibility(view.INVISIBLE);
            }
            else{
                btnPrev.setVisibility(view.VISIBLE);
            }

            if(page == nbPages){
                btnNext.setVisibility(view.INVISIBLE);
            } else {
                btnNext.setVisibility(view.VISIBLE);
            }
            getData(query);
        });

        btnNext.setOnClickListener(view -> {
            page += 1;
            txtPage.setText("" + page);
            if(page == 1){
                btnPrev.setVisibility(view.INVISIBLE);
            }
            else{
                btnPrev.setVisibility(view.VISIBLE);
            }
            if(page == nbPages){
                btnNext.setVisibility(view.INVISIBLE);
            } else {
                btnNext.setVisibility(view.VISIBLE);
            }

            getData(query);
        });
    }

    //Intèroge l'api pour trouver les images
    public void getData(String query) {
        listePhotos.clear();
        txtMessage.setText("Loading...");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.unsplash.com/search/photos?client_id="+token+"&query="+ query + "&page=" + page;

        //On construit la requète. Si tout vas bien, on affiche les données
        JsonObjectRequest data = new JsonObjectRequest(Request.Method.GET, url, null, data1 -> {
            printData(data1);
        }, error -> txtMessage.setText(error.getMessage()));
        queue.add(data);
    }

    //Affiche les données à l'écran
    void printData(JSONObject data){
        try{
            int nbElements = data.getInt("total");
            nbPages = data.getInt("total_pages");

            //Si il n'y a aucun résultat, on l'affiche
            if(nbElements == 0){
                ((RecyclerView)findViewById(R.id.recyclerview)).setVisibility(View.INVISIBLE);
                throw new Resources.NotFoundException();
            }
            ((RecyclerView)findViewById(R.id.recyclerview)).setVisibility(View.VISIBLE);

            //Données générales
            ((TextView)findViewById(R.id.txtNbElements)).setText("Nb Elements :" + nbElements);
            ((TextView)findViewById(R.id.txtNbPages)).setText("Nb Pages :" + nbPages);

            //On affiche la liste
            printList(data.getJSONArray("results"));

            txtMessage.setText("");

        } catch (Resources.NotFoundException e){
            txtMessage.setText("Aucun résultat");
        } catch (Exception e) {
            txtMessage.setText(e.getMessage());
        }
    }

    //Affiche la liste à l'écran
    void printList(JSONArray data) throws JSONException {


        int nbImg = data.length();

        for(int i = 0; i < nbImg; i++){
            JSONObject object = data.getJSONObject(i);
            String auteur = object.getJSONObject("user").getString("name");
            String description = object.getString("description");
            String url = object.getJSONObject("urls").getString("small");

            Photo photo = new Photo(auteur, description, url);

            listePhotos.add(photo);
        }

        //On crée le recycler view avec la liste des photos récupérée
        photoAdapter = new PhotoAdapter(listePhotos);
        recyclerView= findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoAdapter);
    }
}
