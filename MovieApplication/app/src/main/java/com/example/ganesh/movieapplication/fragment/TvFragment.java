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
public class TvFragment extends Fragment {
    private ViewPager viewPager;


    public static TvFragment newInstance() {
        TvFragment tvFragment = new TvFragment();
        return tvFragment;
    }


    public TvFragment() {
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
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.popular_tv));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top_rated));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.on_tv));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        TvPager tvPager = new TvPager(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(tvPager);
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
    public class TvPager extends FragmentStatePagerAdapter {
        int tabCount;
        public TvPager(FragmentManager fm,int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    PopularTvShowsFragment popularTvShowsFragment = new PopularTvShowsFragment();
                    return popularTvShowsFragment;
                case 1:
                    TopRatedTvShowsFragment topRatedTvShowsFragment = new TopRatedTvShowsFragment();
                    return topRatedTvShowsFragment;
                case 2:
                    OnTvShowsFragment onTvShowsFragment = new OnTvShowsFragment();
                    return onTvShowsFragment;
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
