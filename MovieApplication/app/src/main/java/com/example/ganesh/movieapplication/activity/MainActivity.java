package com.example.ganesh.movieapplication.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ganesh.movieapplication.R;
import com.example.ganesh.movieapplication.fragment.DiscoverFragment;
import com.example.ganesh.movieapplication.fragment.GenreFragment;
import com.example.ganesh.movieapplication.fragment.MovieFragment;
import com.example.ganesh.movieapplication.fragment.PeopleFragment;
import com.example.ganesh.movieapplication.fragment.TvFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  NavigationView navigationView;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getResources().getString(R.string.api_key).isEmpty()){
            Toast.makeText(this, "Please obtain your API_KEY from themoviedb.org", Toast.LENGTH_SHORT).show();
            return;
        }
        if (savedInstanceState == null) {
            MenuItem item =  navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id){
            case R.id.nav_discover:
                navDiscover();
                break;
            case R.id.nav_movie:
                navMovie();
                break;
            case R.id.nav_tv:
                navTv();
                break;
            case R.id.nav_people:
                navPeople();
                break;
            case R.id.nav_genre:
                navGenre();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void navDiscover(){
        setTitle(getResources().getString(R.string.home));
        DiscoverFragment discoverFragment = DiscoverFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, discoverFragment).commit();
    }
    public void navMovie(){
        setTitle(getResources().getString(R.string.movie));
        MovieFragment movie = MovieFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, movie).commit();

    }
    public void navTv(){
        setTitle(getResources().getString(R.string.tv));
        TvFragment tvFragment = TvFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, tvFragment).commit();

    }
    public void navPeople(){
        setTitle(getResources().getString(R.string.people));
        PeopleFragment peopleFragment = PeopleFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, peopleFragment).commit();

    }
    public void navGenre(){
        setTitle(getResources().getString(R.string.genre));
        GenreFragment genreFragment = GenreFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, genreFragment).commit();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MenuItem item =  navigationView.getMenu().getItem(0);
        onNavigationItemSelected(item);
    }
}
