package com.catolica.salesianoapp.ucv.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Meycon Augusto on 11/02/2018.
 */

public class DiasPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList timetable;
    public static int num_itens = 5;

    public DiasPagerAdapter(ArrayList timetable, FragmentManager fm) {
        super(fm);
        this.timetable = timetable;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) DiaFragment.Companion.Companion(position+1,this.timetable);
    }

    @Override
    public int getCount() {
        return num_itens;
    }

    public final ArrayList getTimetable(){
        return this.timetable;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return "Seg";
            case 1: return "Ter";
            case 2: return "Qua";
            case 3: return "Qui";
            case 4: return "Sex";
        }
        return null;
    }
}
