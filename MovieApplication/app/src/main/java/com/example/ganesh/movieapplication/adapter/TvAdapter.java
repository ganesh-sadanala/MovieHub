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
import com.example.ganesh.movieapplication.model.Tv;
import com.squareup.picasso.Picasso;

import java.util.List;



public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private List<Tv> discoverTvs;
    private Context context;
    private int rowLayout;
    public class TvViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,rating,releaseDate;
        public TvViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_view);
            title = (TextView) v.findViewById(R.id.name);
            rating = (TextView) v.findViewById(R.id.rating);
            releaseDate = (TextView) v.findViewById(R.id.releaseDate);
        }
    }
    public TvAdapter(List<Tv> discoverTvs, int rowLayout, Context context){
        this.discoverTvs = discoverTvs;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);

        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        holder.title.setText("Title: "+discoverTvs.get(position).getName());
        Log.d("tag"," Title : "+discoverTvs.get(position).getName());
        holder.releaseDate.setText("Release Date: "+discoverTvs.get(position).getFirstAirDate());
        holder.rating.setText("Rating: "+discoverTvs.get(position).getVoteAverage().toString()+"/10");
//   http://image.tmdb.org/t/p/w185+"The Big Bang Theory"
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+discoverTvs.get(position).
                getPosterPath())
                .resize(120,152).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return discoverTvs.size();
    }
}
