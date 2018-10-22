package com.catolica.salesianoapp.ucv.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.CategoriaAdapter;
import com.catolica.salesianoapp.ucv.model.Categoria;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCR extends Fragment {

    CircleProgressView mCircleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cr, container, false);

        mCircleView = (CircleProgressView) v.findViewById(R.id.circleView);

        mCircleView.setMaxValue(100);
        mCircleView.setValue(0);
        mCircleView.setValueAnimated(70,4000);


        return v;
    }

}
