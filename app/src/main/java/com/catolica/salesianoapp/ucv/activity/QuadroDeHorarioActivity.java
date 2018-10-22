package com.catolica.salesianoapp.ucv.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.FrequenciaAdapter;
import com.catolica.salesianoapp.ucv.adapter.HistoricoAdapter;
import com.catolica.salesianoapp.ucv.adapter.NotaAdapter;
import com.catolica.salesianoapp.ucv.dialog.DialogSair;
import com.catolica.salesianoapp.ucv.dialog.RadioDialog;
import com.catolica.salesianoapp.ucv.fragment.DiasPagerAdapter;
import com.catolica.salesianoapp.ucv.model.Aluno;
import com.catolica.salesianoapp.ucv.model.Aula;
import com.catolica.salesianoapp.ucv.util.KeyboardUtils;
import com.catolica.salesianoapp.ucv.util.SharedPrefManager;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class QuadroDeHorarioActivity extends AppCompatActivity {

    Toolbar toolbar;
    android.support.v4.app.FragmentManager fragmentManager;
    ProgressDialog progressDialog;
    private TextView test;
    private ArrayList<Aula> timetable;
    private StringBuffer postList;
    int today, aux;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadro_de_horario);

        //Vincula o Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarQuadroDeHorario);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quadro de Horário");


        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsPrincipal);

        Type listType = new TypeToken<ArrayList<Aula>>() {}.getType();
        timetable = new GsonBuilder().create().fromJson(loadJSONFromAsset(), listType);
        postList=new StringBuffer();
        for(Aula post: timetable){
            postList.append("\n name: "+post.getName()+"\n prof: "+post.getProf()+"\n place: "+post.getPlace()+"\n day: "+post.getDay()+"\n\n");
        }

        fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter((PagerAdapter) new DiasPagerAdapter(timetable,fragmentManager));

        tabLayout.setupWithViewPager(viewPager);

        today = Calendar.getInstance().get(7) - 2;
        escopo:
        {
            if (0 <= today) {
                if (4 >= today) {
                    aux = today;
                }
            }
            aux = 0;
        }
        viewPager.setCurrentItem(aux);


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
                        KeyboardUtils.hideKeyboard(QuadroDeHorarioActivity.this);
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
                                new AwesomeSuccessDialog(QuadroDeHorarioActivity.this)
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
                    }
                })
                .build();

        //Mostra o icone do Hamburguer
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("timetable.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //Botão Voltar do celular - Irá voltar o drawer
    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    //Botão voltar do Toolbar
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
