package com.catolica.salesianoapp.ucv.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.NoticiasPorCategoriaAdapter;
import com.catolica.salesianoapp.ucv.json.JsonUtils;
import com.catolica.salesianoapp.ucv.model.Noticia;
import com.catolica.salesianoapp.ucv.util.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticiaPorCategoriaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Noticia> listaNoticia;
    private NoticiasPorCategoriaAdapter noticiasPorCategoria;

    private ProgressBar progressBar;

    SwipeRefreshLayout swipeRefreshLayout = null;

//Pegar essa Activity para fazer as outras
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_por_categoria);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview_ListaCategoria);
        progressBar = (ProgressBar) findViewById(R.id.progressbarListaCategoria);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_listaCategoria);
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.red, R.color.red); //Color

        listaNoticia = new ArrayList<Noticia>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(Config.TITULO_CATEGORIA);
        }


        if (JsonUtils.estaConectado(getApplicationContext())) {
            new NoticiaTask().execute(Config.URL_SERVIDOR + "api.php?id_categoria=" + Config.ID_CATEGORIA);
        } else {
            Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Nada Depois do Refrest
                        swipeRefreshLayout.setRefreshing(false);
                        limpa();
                        if (JsonUtils.estaConectado(getApplicationContext())) {
                            new NoticiaTask().execute(Config.URL_SERVIDOR + "api.php?id_categoria=" + Config.ID_CATEGORIA);
                        } else {
                            new AwesomeErrorDialog(NoticiaPorCategoriaActivity.this)
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
                                            onBackPressed();
                                        }
                                    })
                                    .show();
                             }
                    }
                }, 500);
            }
        });
    }

    public void limpa() {
        int tamanho = this.listaNoticia.size();
        if (tamanho > 0) {
            for (int i = 0; i < tamanho; i++) {
                this.listaNoticia.remove(0);
            }
            noticiasPorCategoria.notifyItemRangeRemoved(0, tamanho);
        }
    }

    public class NoticiaTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return JsonUtils.retornaJsonDeGet(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray jsonArray = obj.getJSONArray("UCVApp");
                JSONObject objJson = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    objJson = jsonArray.getJSONObject(i);
                    //  nome += objJson.getString("categoria")+"\n";
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

                //   txt.setText(obj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            noticiasPorCategoria = new NoticiasPorCategoriaAdapter(getApplicationContext(), listaNoticia);
            recyclerView.setAdapter(noticiasPorCategoria);
        }
    }

    //Botão voltar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
