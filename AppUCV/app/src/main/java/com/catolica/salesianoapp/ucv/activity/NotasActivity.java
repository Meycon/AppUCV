package com.catolica.salesianoapp.ucv.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.FrequenciaAdapter;
import com.catolica.salesianoapp.ucv.adapter.HistoricoAdapter;
import com.catolica.salesianoapp.ucv.adapter.NotaAdapter;
import com.catolica.salesianoapp.ucv.dialog.DialogSair;
import com.catolica.salesianoapp.ucv.dialog.RadioDialog;
import com.catolica.salesianoapp.ucv.json.JsonUtils;
import com.catolica.salesianoapp.ucv.model.Aluno;
import com.catolica.salesianoapp.ucv.model.Nota;
import com.catolica.salesianoapp.ucv.util.Config;
import com.catolica.salesianoapp.ucv.util.KeyboardUtils;
import com.catolica.salesianoapp.ucv.util.SharedPrefManager;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotasActivity extends AppCompatActivity {

    NotaAdapter notaAdapter;
    List<Nota> notaList;
    SwipeRefreshLayout swipeRefreshLayout = null;
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        //Vincula o Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarNotas);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notas de Provas");

        //Pega o objeto Aluno logado.
        Aluno aluno = SharedPrefManager.getInstance(this).getAluno();

        //URL Imagem
        String userPic = aluno.getImagem();

        //Pega o Nome e Sobrenome
        String nomeCompleto = aluno.getNome();
        String[] arr = nomeCompleto.split(" ");
        String fname = arr[0];
        String lname = arr[1];

        //Cria um Perfil colocando os valores.
        IProfile profile = new ProfileDrawerItem().withName(fname + " " + lname).withEmail(aluno.getMatricula()).withIcon(userPic);//.withIdentifier(2); //.withIcon(R.drawable.perfil)

        //Inicializa e cria a Imagem para Carregar
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }
        });

        //Cria o Cabeçalho do Drawer
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.color.white)
                .withTextColorRes(R.color.colorBlueGrey600)
                .withProfileImagesClickable(true)
                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile profile) {
                        startActivity(new Intent(getApplicationContext(), CarteirinhaActivity.class));
                        result.closeDrawer();
                        return false;
                    }
                })
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        startActivity(new Intent(getApplicationContext(), CarteirinhaActivity.class));
                        return false;
                    }
                })
                .addProfiles(profile).build();


        //Cria o Drawer Lateral.
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true) //Embaixo da status bar
                .withActionBarDrawerToggle(true)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        KeyboardUtils.hideKeyboard(NotasActivity.this);
                    }
                })
                .withActionBarDrawerToggleAnimated(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_inicio).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(GoogleMaterial.Icon.gmd_dashboard),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_notas).withIconColorRes(R.color.colorNotas).withSelectedIconColorRes(R.color.colorNotas).withIcon(R.drawable.file_check_menu),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_faltas).withIconColorRes(R.color.colorFrequencia).withSelectedIconColorRes(R.color.colorFrequencia).withIcon(GoogleMaterial.Icon.gmd_event_busy),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_datas_prova).withIconColorRes(R.color.colorDatasProvas).withSelectedIconColorRes(R.color.colorDatasProvas).withIcon(GoogleMaterial.Icon.gmd_insert_invitation),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_financeiro).withIconColorRes(R.color.colorFinanceiro).withSelectedIconColorRes(R.color.colorFinanceiro).withIcon(GoogleMaterial.Icon.gmd_attach_money),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_quadro_horario).withIconColorRes(R.color.colorHorarios).withSelectedIconColorRes(R.color.colorHorarios).withIcon(R.drawable.calendar_clock_menu),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_historico_escolar).withIconColorRes(R.color.colorHistoricoEscolar).withSelectedIconColorRes(R.color.colorHistoricoEscolar).withIcon(GoogleMaterial.Icon.gmd_library_books),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_documentos).withIconColorRes(R.color.colorPDF).withSelectedIconColorRes(R.color.colorPDF).withIcon(R.drawable.file_pdf),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_indicadores).withIconColorRes(R.color.colorIndicadores).withSelectedIconColorRes(R.color.colorIndicadores).withIcon(R.drawable.finance),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_estagios).withIconColorRes(R.color.colorEstagio).withSelectedIconColorRes(R.color.colorEstagio).withIcon(R.drawable.briefcase),

                        new SectionDrawerItem().withName(R.string.drawer_item_sessao_outros),

                        new SecondaryDrawerItem().withName(R.string.drawer_item_ajuda).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(FontAwesome.Icon.faw_question).withTextColorRes(R.color.black),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contexto).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(GoogleMaterial.Icon.gmd_sync).withTextColorRes(R.color.black),
                        new SecondaryDrawerItem().withName(R.string.nome_toolbar_about).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(GoogleMaterial.Icon.gmd_error).withTextColorRes(R.color.black),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_sair).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withTextColorRes(R.color.black)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                break;
                            case 2:
                                NotaAdapter.posicaoAtual = -1;
                                startActivity(new Intent(getApplicationContext(), NotasActivity.class));
                                break;
                            case 3:
                                FrequenciaAdapter.posicaoAtual = -1;
                                startActivity(new Intent(getApplicationContext(), FrequenciaActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(getApplicationContext(), ComunicadoActivity.class));
                                break;
                            case 5:
                                startActivity(new Intent(getApplicationContext(), FinanceiroActivity.class));
                                break;
                            case 6:
                                startActivity(new Intent(getApplicationContext(), QuadroDeHorarioActivity.class));
                                break;
                            case 7:
                                startActivity(new Intent(getApplicationContext(), HistoricoEscolarActivity.class));
                                break;
                            case 8:
                                startActivity(new Intent(getApplicationContext(), DocumentosActivity.class));
                                break;
                            case 9:
                                startActivity(new Intent(getApplicationContext(), DesempenhoActivity.class));
                                break;
                            case 10:
                                startActivity(new Intent(getApplicationContext(), EstagioActivity.class));
                                break;
                            case 11:
                                /*Outros*/
                            case 12:
                                startActivity(new Intent(getApplicationContext(), FaleConoscoActivity.class));
                                break;
                            case 13:
                                RadioDialog radioDialog = new RadioDialog();
                                radioDialog.show(getFragmentManager(), "dialogContexto");
                                break;
                            case 14:
                                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                                break;
                            case 15:
                                new AwesomeSuccessDialog(NotasActivity.this)
                                        .setTitle("Aviso")
                                        .setMessage("Deseja mesmo sair?")
                                        .setColoredCircle(R.color.dialogInfoBackgroundColor)
                                        .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                        .setCancelable(true)
                                        .setPositiveButtonText("Sim")
                                        .setPositiveButtonbackgroundColor(R.color.dialogInfoBackgroundColor)
                                        .setPositiveButtonTextColor(R.color.white)
                                        .setNegativeButtonText("Não")
                                        .setNegativeButtonbackgroundColor(R.color.dialogInfoBackgroundColor)
                                        .setNegativeButtonTextColor(R.color.white)
                                        .setPositiveButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                SharedPrefManager.getInstance(getApplicationContext()).logout();
                                            }
                                        })
                                        .setNegativeButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                            }
                                        })
                                        .show();
                                break;
                        }

                        result.closeDrawer();
                        return true;
                        //  return false;
                    }
                })
                .build();

        //Mostra o icone do Hamburguer
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_notas);
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.red, R.color.red); //Color

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVerticalScrollBarEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notaList = new ArrayList<Nota>();

        // Verifica se esta conectado e da um GET JSON
        if (JsonUtils.estaConectado(this)) {
            new NotasTask().execute(Config.URL_NOTAS_GET);
        } else {
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Handle para usar a Thread
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        limpa();
                        if (JsonUtils.estaConectado(getApplicationContext())) {
                            new NotasTask().execute(Config.URL_NOTAS_GET);
                        } else {
                            new AwesomeErrorDialog(NotasActivity.this)
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
                }, 400);
            }
        });

    }

    //Método para limpar a Lista
    public void limpa() {
        int tamanho = this.notaList.size();
        notaAdapter.posicaoAtual = -1;
        if (tamanho > 0) {
            for (int i = 0; i < tamanho; i++) {
                this.notaList.remove(0);
            }
            notaAdapter.notifyItemRangeRemoved(0, tamanho);
        }
    }

    //Mostra o Toast Passando a mensagem
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //Botão voltar do Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                notaAdapter.posicaoAtual = -1;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //Back press fecha o Drawer
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public class NotasTask extends AsyncTask<String, Void, String> {

        // ProgressBar progressBarNotas;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NotasActivity.this);
            pDialog.setMessage("Carregando...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

             return JsonUtils.retornaJsonDeGet(strings[0]); //POST
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (null == resultado || resultado.length() == 0) {
                new AwesomeErrorDialog(NotasActivity.this)
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
                    JSONObject jsonObject = new JSONObject(resultado);

                    JSONArray jsonArray = jsonObject.getJSONArray("Notas");
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        Nota nota = new Nota(
                                objJson.getString("disciplina"),
                                objJson.getString("teste"),
                                objJson.getString("p1"),
                                objJson.getString("td1"),
                                objJson.getString("td2"),
                                objJson.getString("p2"),
                                objJson.getString("td3"),
                                objJson.getString("td4"),
                                objJson.getString("aco")

                        );
                        notaList.add(nota);
                    }
                    notaAdapter = new NotaAdapter(notaList, getApplicationContext());
                    recyclerView.setAdapter(notaAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
