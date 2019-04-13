package com.example.ganesh.movieapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ganesh.movieapplication.R;


public class GenreDetailsActivity extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_details);

        final TextView textView = (TextView) findViewById(R.id.textview);

        final String genreName = getIntent().getStringExtra("genreName");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(genreName);

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
//        Call<GenreResponse> call = apiService.getGenreMovieList(id,getResources().getString(R.string.api_key));
//        call.enqueue(new Callback<GenreResponse>() {
//            @Override
   //      public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
////                List<Movie> movies =  response.body().getResults();
////                textView.setText((CharSequence) movies);
//                //Movie movie = response.body();
//
//                List<Genre> genres = response.body().getGenres();
//                textView.setText(genreName);
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<GenreResponse> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//        });





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
