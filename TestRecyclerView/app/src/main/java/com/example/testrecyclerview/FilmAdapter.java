package com.example.testrecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    List<Film> listeFilms;

    public static class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView yearTextView;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=(TextView)itemView.findViewById(R.id.tvTitle);
            yearTextView=(TextView)itemView.findViewById(R.id.tvYear);
            itemView.setOnClickListener(this);
        }

        void setDisplay(Film film){
            titleTextView.setText(film.getTitle());
            yearTextView.setText(""+film.getYear());
        }

        @Override
        public void onClick(View v) {
            Log.d("onClick",""+getAdapterPosition());

        }
    }

    public FilmAdapter(@NonNull List<Film> listeFilms) {
        this.listeFilms = listeFilms;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item_layout,parent,false);
        FilmViewHolder holder=new FilmViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.setDisplay(listeFilms.get(position));
    }

    @Override
    public int getItemCount() {
        return listeFilms.size();
    }

}
