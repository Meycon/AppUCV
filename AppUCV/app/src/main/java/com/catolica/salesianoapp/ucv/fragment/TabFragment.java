package com.catolica.salesianoapp.ucv.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catolica.salesianoapp.ucv.R;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int num_itens = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_tab, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MeuAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return v;
    }

    class MeuAdapter extends FragmentPagerAdapter{

        public MeuAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new FragmentCategoria();
                case 1:
                    return new FragmentNoticiasRecentes();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Acesso Rápido";
                case 1: return "Notícias Recentes";
            }
            return null;
        }

        @Override
        public int getCount() {
            return num_itens;
        }
    }

}
