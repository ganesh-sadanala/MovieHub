package com.example.ganesh.movieapplication.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.adapter.PeopleAdapter;
import com.example.ganesh.movieapplication.model.People;
import com.example.ganesh.movieapplication.model.PeopleResponse;
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
public class PeopleFragment extends Fragment {
    private RecyclerView recyclerView;


    public static PeopleFragment newInstance() {
        PeopleFragment peopleFragment = new PeopleFragment();
        return peopleFragment;
    }


    public PeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.recycler_layout, container, false);

        View view = inflater.inflate(R.layout.recycler_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false));

        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<PeopleResponse> call = apiService.getPopularPeopleList(getResources().getString(R.string.api_key));



        final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {

                int statuscode = response.code();
                List<People> peoples = response.body().getResults();

                recyclerView.setAdapter(new PeopleAdapter(peoples,R.layout.people_list,getActivity().getApplicationContext()));

                if (mProgressDialog.isShowing())
                {
                    mProgressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {

                Log.e(TAG,t.toString());
            }
        });


    }
}
