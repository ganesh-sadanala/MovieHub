package com.example.ganesh.movieapplication.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.adapter.MovieAdapter;
import com.example.ganesh.movieapplication.model.Movie;
import com.example.ganesh.movieapplication.model.MovieResponse;
import com.example.ganesh.movieapplication.rest.ApiClient;
import com.example.ganesh.movieapplication.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMovieFragment extends Fragment {
    private RecyclerView recyclerView;


    public UpcomingMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_upcoming_movie, container, false);
        View view = inflater.inflate(R.layout.recycler_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getUpcomingMovieList(getResources().getString(R.string.api_key));


        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();

                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(movies,R.layout.list_item,getActivity().getApplicationContext()));



                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });





    }
}
