package com.catolica.salesianoapp.ucv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.AulasAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meycon Augusto on 11/02/2018.
 */

public class DiaFragment extends Fragment{
    private static final String ARG_DAY = "day";
    private static final String ARG_TIMETABLE = "timetable";
    public static final DiaFragment.Companion Companion = new DiaFragment.Companion();

    RecyclerView recyclerView;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quadro_de_horario,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        Integer day = bundle !=null ? Integer.valueOf(bundle.getInt(Companion.getARG_DAY())):null;
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutEmpty);

        Serializable timetable = bundle != null? bundle.getSerializable(Companion.getARG_TIMETABLE()):null;
        if(day != null && timetable != null) {
            recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(new AulasAdapter(day.intValue(),(List)timetable,this.getContext()));
        }
        if(recyclerView.getAdapter().getItemCount()==0){
            linearLayout.setVisibility(View.VISIBLE);
        }


    }

    public static final class Companion{
        public final String getARG_DAY(){
            return DiaFragment.ARG_DAY;
        }
        public final String getARG_TIMETABLE(){
            return DiaFragment.ARG_TIMETABLE;
        }

        public Fragment Companion(int day, ArrayList timetable){

            DiaFragment fragment = new DiaFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(((DiaFragment.Companion)this).getARG_DAY(), day);
            arguments.putSerializable(((DiaFragment.Companion)this).getARG_TIMETABLE(), (Serializable)timetable);
            fragment.setArguments(arguments);
            return fragment;
        }

        private Companion(){

        }

    }



}
