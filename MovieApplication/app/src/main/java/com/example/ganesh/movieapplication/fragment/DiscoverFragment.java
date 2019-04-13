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
public class DiscoverFragment extends Fragment {
    private ViewPager viewPager;


    public static DiscoverFragment newInstance() {
        DiscoverFragment discoverFragment = new DiscoverFragment();
        return discoverFragment;
    }


    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_discover, container, false);
        View view = inflater.inflate(R.layout.tab_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.discover_movie)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.discover_tv)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        DiscoverPager discoverPager = new DiscoverPager(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(discoverPager);

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
    public class DiscoverPager extends FragmentStatePagerAdapter {
        int tabcount;
        public DiscoverPager(FragmentManager fm,int tabcount) {
            super(fm);
            this.tabcount = tabcount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    MovieDiscoverFragment movieDiscoverFragment = new MovieDiscoverFragment();
                    return movieDiscoverFragment;
                case 1:
                    TvDiscoverFragment tvDiscoverFragment = new TvDiscoverFragment();
                    return tvDiscoverFragment;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return tabcount;
        }
    }
}
