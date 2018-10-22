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

public class TabFragmentDesempenho extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int num_itens = 3;

    private int[] tabIcons = {
            R.drawable.chart_donut,
            R.drawable.chart_bar,
            R.drawable.graphql,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_tab_desempenho, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabsDesempenho);
        viewPager = (ViewPager) v.findViewById(R.id.viewpagerDesempenho);

        viewPager.setAdapter(new MeuAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                setupTabIcons();
            }
        });

        return v;
    }

    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    class MeuAdapter extends FragmentPagerAdapter{

        public MeuAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new FragmentCR();
                case 1:
                    return new FragmentNotaFinal();
                case 2:
                    return new FragmentRadar();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "CR Geral";
                case 1: return "Desempenho Semestral";
                case 2: return "Atributos/Competência";
            }
            return null;
        }

        @Override
        public int getCount() {
            return num_itens;
        }
    }

}
