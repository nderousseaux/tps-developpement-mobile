package com.example.testrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Film> listeFilms=new ArrayList<>();
        listeFilms.add(new Film("Star Wars",1977));
        listeFilms.add(new Film("Contagion",2014));
        listeFilms.add(new Film("Les frères sisters",2017));
        listeFilms.add(new Film("Dead man",2001));
        listeFilms.add(new Film("Welcome",2004));
        listeFilms.add(new Film("La tour sombre",2018));
        listeFilms.add(new Film("Shaun le mouton",2016));
        listeFilms.add(new Film("La Strada",1954));
        listeFilms.add(new Film("La Chèvre",1981));
        listeFilms.add(new Film("Corporate",2017));
        listeFilms.add(new Film("Colonia",2015));
        listeFilms.add(new Film("Comancheria",2016));
        listeFilms.add(new Film("Welcome",2004));
        listeFilms.add(new Film("La tour sombre",2018));
        listeFilms.add(new Film("Shaun le mouton",2016));
        listeFilms.add(new Film("La Strada",1954));
        listeFilms.add(new Film("Les frères sisters",2017));
        listeFilms.add(new Film("Dead man",2001));
        listeFilms.add(new Film("Welcome",2004));
        listeFilms.add(new Film("La tour sombre",2018));
        listeFilms.add(new Film("Shaun le mouton",2016));
        listeFilms.add(new Film("La Strada",1954));
        listeFilms.add(new Film("La Chèvre",1981));
        listeFilms.add(new Film("Corporate",2017));

        filmAdapter=new FilmAdapter(listeFilms);

        recyclerView=(RecyclerView)findViewById(R.id.rv);

        layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(filmAdapter);

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}