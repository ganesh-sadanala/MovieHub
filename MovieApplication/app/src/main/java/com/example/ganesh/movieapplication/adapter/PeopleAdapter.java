package com.example.ganesh.movieapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.model.People;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    List<People> peoples;
    int rowLayout;
    Context context;


    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        private ImageView peopleImage;
        private TextView peopleName;
        public PeopleViewHolder(View v) {
            super(v);
            peopleImage = (ImageView) v.findViewById(R.id.people_image_view);
            peopleName = (TextView) v.findViewById(R.id.people_name);
        }
    }

    public PeopleAdapter(List<People> peoples,int rowLayout,Context context){
        this.peoples = peoples;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

        holder.peopleName.setText(peoples.get(position).getName());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185"+peoples.get(position).getProfilePath()).resize(170,170).into(holder.peopleImage);

    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }
}
