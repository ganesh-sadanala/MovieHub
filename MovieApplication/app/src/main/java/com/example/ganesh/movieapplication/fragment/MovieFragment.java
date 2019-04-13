package com.example.ganesh.movieapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesh.movieapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static MovieFragment newInstance() {

        MovieFragment moviefragment = new MovieFragment();
        return moviefragment;
    }


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.tab_layout,container,false);
        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.popular_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.now_playing_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.top_rated_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.upcoming_movie)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        MoviePager adapter = new MoviePager(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    public class MoviePager extends FragmentStatePagerAdapter {
        int tabCount;
        public MoviePager(FragmentManager fm, int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    PopularMovieFragment popularMovieFragment = new PopularMovieFragment();
                    return popularMovieFragment;

                case 1:
                    NowPlayingMovieFragment nowPlayingMovieFragment = new NowPlayingMovieFragment();
                    return nowPlayingMovieFragment;
                case 2:
                    TopRatedMovieFragment topRatedMovieFragment = new TopRatedMovieFragment();
                    return topRatedMovieFragment;
                case 3:
                    UpcomingMovieFragment upcomingMovieFragment = new UpcomingMovieFragment();
                    return upcomingMovieFragment;
                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }


}
