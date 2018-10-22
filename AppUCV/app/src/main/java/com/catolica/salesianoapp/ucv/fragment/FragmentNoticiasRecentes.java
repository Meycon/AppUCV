package com.catolica.salesianoapp.ucv.fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.NoticiasAdapter;
import com.catolica.salesianoapp.ucv.json.JsonUtils;
import com.catolica.salesianoapp.ucv.model.Noticia;
import com.catolica.salesianoapp.ucv.util.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiasRecentes extends Fragment {

    private RecyclerView recyclerView;
    private List<Noticia> listaNoticia;
    private NoticiasAdapter noticiasAdapter;
    private ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout = null;
/*
    //Salvar Pesquisa
    Noticia noticia;
    int tamTexto = 0; */


/*
    //Salvar Pesquisa
    ArrayList<String> array_noticia, array_id_noticia, array_id_categoria, array_nome_categoria, array_titulo, array_imagem, array_descricao, array_data;
    String[] str_noticia, str_nome_categoria, str_id_noticia, str_id_categoria, str_titulo, str_imagem, str_descricao, str_data;
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_noticias_recentes, container, false);

        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_noticias);
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.red, R.color.red); //Color

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listaNoticia = new ArrayList<Noticia>();
/*
      //Salvar Pesquisa
        array_noticia = new ArrayList<String>();
        array_nome_categoria = new ArrayList<String>();
        array_id_noticia = new ArrayList<String>();
        array_id_categoria = new ArrayList<String>();
        array_titulo = new ArrayList<String>();
        array_imagem = new ArrayList<String>();
        array_descricao = new ArrayList<String>();
        array_data = new ArrayList<String>();

        str_noticia = new String[array_noticia.size()];
        str_id_noticia = new String[array_id_noticia.size()];
        str_id_categoria = new String[array_id_categoria.size()];
        str_nome_categoria = new String[array_nome_categoria.size()];
        str_titulo = new String[array_titulo.size()];
        str_imagem = new String[array_imagem.size()];
        str_descricao = new String[array_descricao.size()];
        str_data = new String[array_data.size()];
*/
        // Verifica se esta conectado e da um GET JSON
        if (JsonUtils.estaConectado(getContext())) {
            new NoticiaTask().execute(Config.URL_SERVIDOR + "api.php?qtde=10");
        } else {
            new AwesomeErrorDialog(getContext())
                    .setTitle("Erro")
                    .setMessage("Falha na conexão com a internet.")
                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                    .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                    .setButtonText(getString(R.string.dialog_ok_button))
                    .setErrorButtonClick(new Closure() {
                        @Override
                        public void exec() {
                        //    onBackPressed();
                        }
                    })
                    .show();
        }

        //Swipe de refrest noticia
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Nada Depois do Refrest
                        swipeRefreshLayout.setRefreshing(false);
                        limpa();
                        if (JsonUtils.estaConectado(getContext())) {
                            new NoticiaTask().execute(Config.URL_SERVIDOR + "api.php?qtde=10"); //8
                        } else {
                            Toasty.error(getContext(), "Sem conexão com a internet.", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }, 400);
            }
        });

        return v;
    }

    //Método para limpar a Lista
    public void limpa() {
        int tamanho = this.listaNoticia.size();
        if (tamanho > 0) {
            for (int i = 0; i < tamanho; i++) {
                this.listaNoticia.remove(0);
            }
            noticiasAdapter.notifyItemRangeRemoved(0, tamanho);
        }
    }

    //Mostra o Toast Passando a mensagem
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }


    //Noticia Task (Thread) - Rodar em segundo plano
    public class NoticiaTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Carregando...");
            pDialog.setCancelable(false);
            pDialog.show();
          //  pDialog.getWindow().setGravity(Gravity.BOTTOM);
        }

        @Override
        protected String doInBackground(String... strings) {
            return JsonUtils.retornaJsonDeGet(strings[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == resultado || resultado.length() == 0) {
           /*     showToast("Erro de conexão do servidor");
                 alert.showAlertDialog(getActivity(),
                "Erro de conexão do servidor",
                "Em manutenção, tente novamente mais tarde!", false);*/
                new AwesomeErrorDialog(getContext())
                        .setTitle("Erro")
                        .setMessage("Falha na conexão com a internet.")
                        .setColoredCircle(R.color.dialogErrorBackgroundColor)
                        .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                        .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                        .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                        .setButtonText(getString(R.string.dialog_ok_button))
                        .setErrorButtonClick(new Closure() {
                            @Override
                            public void exec() {
                             //   onBackPressed();
                            }
                        })
                        .show();
             //   getActivity().finish();
            } else {

                try {
                    JSONObject obj = new JSONObject(resultado);
                    JSONArray jsonArray = obj.getJSONArray("UCVApp");
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        Noticia noticia = new Noticia();

                        noticia.setIdCategoria(objJson.getString("id_categoria"));
                        noticia.setCategoria(objJson.getString("categoria"));
                        noticia.setIdNoticia(objJson.getString("id_noticia"));
                        noticia.setTituloNoticia(objJson.getString("titulo"));
                        noticia.setDescricaoNoticia(objJson.getString("descricao"));
                        noticia.setDataNoticia(objJson.getString("data"));
                        noticia.setImgNoticia(objJson.getString("imagem"));
                        noticia.setImgCategoria(objJson.getString("imagem_categoria"));

                        listaNoticia.add(noticia);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

         /*   //Salvar Pesquisa
            for (int j = 0; j < listaNoticia.size(); j++) {

                noticia = listaNoticia.get(j);

                array_id_categoria.add(noticia.getIdCategoria());
                str_id_categoria = array_id_categoria.toArray(str_id_categoria);
                array_nome_categoria.add(noticia.getCategoria());
                str_nome_categoria = array_nome_categoria.toArray(str_nome_categoria);
                array_id_noticia.add(String.valueOf(noticia.getIdNoticia()));
                str_id_noticia = array_id_noticia.toArray(str_id_noticia);
                array_imagem.add(String.valueOf(noticia.getImgNoticia()));
                str_imagem = array_imagem.toArray(str_imagem);
                array_titulo.add(String.valueOf(noticia.getTituloNoticia()));
                str_titulo = array_titulo.toArray(str_titulo);
                array_descricao.add(String.valueOf(noticia.getDescricaoNoticia()));
                str_descricao = array_descricao.toArray(str_descricao);
                array_data.add(String.valueOf(noticia.getDataNoticia()));
                str_data = array_data.toArray(str_data);
            } */
                noticiasAdapter = new NoticiasAdapter(getActivity(), listaNoticia);
                recyclerView.setAdapter(noticiasAdapter);
            }
        }
    }
/*
    //Cria o Menu de Opções e infla a Busca
    @SuppressLint("ResourceAsColor")
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView)

        MenuItemCompat.getActionView(menu.findItem(R.id.search));

        final MenuItem searchMenuItem = menu.findItem(R.id.search);

       // searchView.setBackgroundColor(R.color.hamburgerBlack);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            searchMenuItem.collapseActionView();
                            searchView.setQuery("", false);
                        }
                    }
                });

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                tamTexto = newText.length();
                listaNoticia.clear();

                for (int i = 0; i < str_titulo.length; i++) {
                    if (tamTexto <= str_titulo[i].length()) {
                        if (str_titulo[i].toLowerCase().contains(newText.toLowerCase())) {
                        //if (newText.toString().equalsIgnoreCase((String) str_titulo[i].subSequence(0,tamTexto))) { Para teste
                            Noticia objItem = new Noticia();

                            objItem.setCategoria((str_nome_categoria[i]));
                            objItem.setIdCategoria((str_id_categoria[i]));
                            objItem.setIdNoticia((str_id_noticia[i]));
                            objItem.setDataNoticia((str_data[i]));
                            objItem.setDescricaoNoticia((str_descricao[i]));
                            objItem.setTituloNoticia((str_titulo[i]));
                            objItem.setImgNoticia((str_imagem[i]));

                            listaNoticia.add(objItem);
                        }
                    }
                }
                noticiasAdapter = new NoticiasAdapter(getActivity(), listaNoticia);
                recyclerView.setAdapter(noticiasAdapter);
                return false; //Or true?
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do something
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    } */
}
