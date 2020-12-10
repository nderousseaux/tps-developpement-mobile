package com.example.tp34;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.c;


import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    List<Photo> listPhoto;

    //Objet définissant un item de la liste
    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtAuteur;
        TextView txtDescription;
        ImageView img;

        public PhotoViewHolder(@NonNull View itemView){
            super(itemView);
            txtAuteur = itemView.findViewById(R.id.txtAuteur);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            img = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        void setDisplay(Photo photo){
            txtAuteur.setText(photo.get_auteur());
            txtDescription.setText(photo.get_description());
            Picasso.get().load(photo.get_url()).into(img);
        }

        //Lors du click sur l'item, on le définit en fond d'écran
        @Override
        public void onClick(View v){
            //On récupère l'image et on en fait un fond d'écran
            Bitmap image = ((BitmapDrawable)img.getDrawable()).getBitmap();
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(v.getContext());
            try {
                wallpaperManager.setBitmap(image);

                //On met au courrant l'utilisateur
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setMessage("Image mise en fond d'écran");
                dialog.setCancelable(true);
                AlertDialog alertBlock = dialog.create();
                alertBlock.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public PhotoAdapter(@NonNull List<Photo> listPhoto){
        this.listPhoto = listPhoto;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        PhotoViewHolder holder = new PhotoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.setDisplay(listPhoto.get(position));
    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }

}

