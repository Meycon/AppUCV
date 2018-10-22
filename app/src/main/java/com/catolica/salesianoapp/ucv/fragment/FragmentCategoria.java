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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategoria extends Fragment {

    private RecyclerView recyclerView;
    private List<Categoria> listaCategoria;
    private CategoriaAdapter categoriaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categoria, container, false);

      //  setHasOptionsMenu(true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);

        listaCategoria = new ArrayList<Categoria>();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Categoria categoria = new Categoria();

        listaCategoria.add(new Categoria("Notas"));
        listaCategoria.add(new Categoria("Financeiro"));
        listaCategoria.add(new Categoria("Frequência"));
        listaCategoria.add(new Categoria("Comunicado"));
        listaCategoria.add(new Categoria("Horários"));
        listaCategoria.add(new Categoria("Histórico Escolar"));

        categoriaAdapter = new CategoriaAdapter(getActivity(), listaCategoria);
        recyclerView.setAdapter(categoriaAdapter);

        return v;
    }

    //Mostra o Toast Passando a mensagem
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        final MenuItem searchMenuItem = menu.findItem(R.id.search);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean id) {
                if (!id) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
