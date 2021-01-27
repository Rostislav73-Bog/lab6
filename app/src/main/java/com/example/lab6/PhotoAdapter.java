package com.example.lab6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6.db.PhotoDB;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private PhotoDB db;

    private final List<Photo> myPhoto;

    public PhotoAdapter(List<Photo> photo ) {
        myPhoto = photo;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoAdapter.ViewHolder holder, final int position) {
        db = PhotoDB.getDatabase(PhotoGallery.getAppContext());
        Picasso.with(PhotoGallery.getAppContext()).
                load(myPhoto.get(position).
                        getUrlS()).
                into(holder.imPhoto);
        holder.imPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.photoDao().insertPhoto(myPhoto.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPhoto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imPhoto = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}
