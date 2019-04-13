package com.example.ganesh.movieapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.activity.GenreDetailsActivity;
import com.example.ganesh.movieapplication.model.Genre;

import java.util.List;


public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genres;
    private Context context;
    private int rowLayout;



    public class GenreViewHolder extends RecyclerView.ViewHolder{
        private TextView genreTitle;

        public GenreViewHolder(View v) {
            super(v);

            genreTitle = (TextView) v.findViewById(R.id.genre_title);


        }


    }
    public GenreAdapter(List<Genre> genres, int rowLayout, Context context) {
        this.genres = genres;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.genreTitle.setText(genres.get(position).getName());
        holder.genreTitle.setTag(holder);

        holder.genreTitle.setOnClickListener(clicklistener);


    }
    View.OnClickListener clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GenreViewHolder vholder = (GenreViewHolder) v.getTag();
            int position = vholder.getPosition();

            //Toast.makeText(context, "You click on  "+genres.get(position).getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, GenreDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("genreName", genres.get(position).getName());
            context.startActivity(intent);
        }
    };



    @Override
    public int getItemCount() {
       return genres.size();
    }


}
