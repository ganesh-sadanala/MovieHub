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
import com.example.ganesh.movieapplication.adapter.TvAdapter;
import com.example.ganesh.movieapplication.model.Tv;
import com.example.ganesh.movieapplication.model.TvResponse;
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
public class TvDiscoverFragment extends Fragment {


    public TvDiscoverFragment() {
        // Required empty public constructor
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
        Call<TvResponse> call = apiService.getTvDiscoverList(getResources().getString(R.string.api_key));



        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                List<Tv> discoverTvs = response.body().getResults();
                recyclerView.setAdapter(new TvAdapter(discoverTvs,R.layout.list_item,getActivity().getApplicationContext()));

                if (mProgressDialog.isShowing()){

                    mProgressDialog.dismiss();
                }
            }



            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });
    }
}
