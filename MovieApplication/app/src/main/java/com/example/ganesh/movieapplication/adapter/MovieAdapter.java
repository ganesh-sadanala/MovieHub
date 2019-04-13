package com.example.ganesh.movieapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviewViewHolder> {
    private List<Movie> movies;
    private Context context;
    private int rowLayout;


    public class MoviewViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,releaseDate,rating;
        public MoviewViewHolder(View v) {
            super(v);

            imageView = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.name);
            releaseDate = (TextView) v.findViewById(R.id.releaseDate);

            rating = (TextView) v.findViewById(R.id.rating);

        }
    }
    public MovieAdapter(List<Movie> movies, int rowLayout, Context context){
        this.movies = movies;
        this.context = context;
        this.rowLayout = rowLayout;
    }


    @Override
    public MoviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);

        return new MoviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviewViewHolder holder, int position) {

        holder.title.setText("Title: "+movies.get(position).getTitle());
        Log.d("tag"," movie Title : "+movies.get(position).getTitle());

        holder.releaseDate.setText("Release Date: "+movies.get(position).getReleaseDate(""));
        holder.rating.setText("Rating: "+movies.get(position).getVoteAverage().toString()+"/10");

        // got it from the following link
        // https://developers.themoviedb.org/3/getting-started/images
        // https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

        Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+movies.get(position).
                getPosterPath())
                .resize(120,152).into(holder.imageView);

//        MoviewViewHolder viewHolder = (MoviewViewHolder)
//        holder.getPosition();
//        http://image.tmdb.org/t/p/w185/Fifty Shades Freed

        // https://api.themoviedb.org/3/discover/movie?api_key=5579cf2006dc3815560a4551e7b3ccf9&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1

        // 5579cf2006dc3815560a4551e7b3ccf9
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
