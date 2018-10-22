package com.catolica.salesianoapp.ucv.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.json.JsonUtils;
import com.catolica.salesianoapp.ucv.model.Noticia;
import com.catolica.salesianoapp.ucv.util.Config;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetalhesActivity extends AppCompatActivity {

    Noticia noticia;
    WebView webDescricao;
    List<Noticia> listaNoticia;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imgPrincipal, imgFavorito;
    private TextView txtTitulo, txtData;
    private LinearLayout linearLayout;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean estaMostrando = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(noticia.getCategoria());
                    estaMostrando = true;
                } else {
                    collapsingToolbarLayout.setTitle("");
                    estaMostrando = false;
                }
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar2);
        collapsingToolbarLayout.setTitle("");

        imgPrincipal = (ImageView) findViewById(R.id.image2);
        imgFavorito = (FloatingActionButton) findViewById(R.id.imgFavorito);

        txtTitulo = (TextView) findViewById(R.id.title2);
        txtData = (TextView) findViewById(R.id.txtData);
        webDescricao = (WebView) findViewById(R.id.webview);


        listaNoticia = new ArrayList<Noticia>();

        if (JsonUtils.estaConectado(getApplicationContext())) {
            new NoticiaTask().execute(Config.URL_SERVIDOR + "api.php?id_noticia=" + Config.ID_NOTICIA);
        } else {
           // Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
            new AwesomeErrorDialog(this)
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

    //Mostra o Toast Passando a mensagem
    public void showToast(String msg) {
        Toast.makeText(DetalhesActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    public void populaDados() {
        try {
            noticia = listaNoticia.get(0);
            txtTitulo.setText(noticia.getTituloNoticia());
            txtData.setText(noticia.getDataNoticia());
            Picasso.with(this).load(Config.URL_SERVIDOR + "upload/" + noticia.getImgNoticia()).into(imgPrincipal);

            WebSettings webSettings = webDescricao.getSettings();
            Resources res = getResources();
            int tamFont = 20;
            webSettings.setJavaScriptEnabled(true);
            String mimeType = "text/html; charset=UTF8";
            String encoding = "utf-8";
            String conteudo = noticia.getDescricaoNoticia();
            String estrutura = "<html><head>"
                    + "<style type=\"text/css\">body{color: #525252;}"
                    + "</style></head>"
                    + "<body>"
                    + conteudo
                    + "<body></html>";
            webDescricao.loadData(estrutura, mimeType, encoding);
        }catch(Exception e){
            Toast.makeText(this,"Erro ao obter a notícia: "+e.getMessage(),Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
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

    public class NoticiaTask extends AsyncTask<String, Void, String> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DetalhesActivity.this);
            pDialog.setMessage("Carregando...");
            pDialog.setCancelable(false);
            pDialog.show();
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
                new AwesomeErrorDialog(DetalhesActivity.this)
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
                populaDados();
            }
        }

    }
}
