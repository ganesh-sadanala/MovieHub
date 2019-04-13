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
import com.example.ganesh.movieapplication.adapter.GenreAdapter;
import com.example.ganesh.movieapplication.model.Genre;
import com.example.ganesh.movieapplication.model.GenreResponse;
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
public class GenreFragment extends Fragment{


    public static GenreFragment newInstance() {

        GenreFragment genreFragment = new GenreFragment();
        return genreFragment;
    }

    public GenreFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_layout,container,false);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<GenreResponse> call = apiService.getGenreList(getResources().getString(R.string.api_key));
        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, final Response<GenreResponse> response) {
                List<Genre> genres = response.body().getGenres();
                

                recyclerView.setAdapter(new GenreAdapter(genres,R.layout.genre_list,getActivity().getApplicationContext()));


                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
            }

        }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });



    }

}
