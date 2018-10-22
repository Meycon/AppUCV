package com.catolica.salesianoapp.ucv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.activity.AboutActivity;
import com.catolica.salesianoapp.ucv.activity.CarteirinhaActivity;
import com.catolica.salesianoapp.ucv.activity.ComunicadoActivity;
import com.catolica.salesianoapp.ucv.activity.DesempenhoActivity;
import com.catolica.salesianoapp.ucv.activity.DocumentosActivity;
import com.catolica.salesianoapp.ucv.activity.EstagioActivity;
import com.catolica.salesianoapp.ucv.activity.FaleConoscoActivity;
import com.catolica.salesianoapp.ucv.activity.FinanceiroActivity;
import com.catolica.salesianoapp.ucv.activity.FrequenciaActivity;
import com.catolica.salesianoapp.ucv.activity.HistoricoEscolarActivity;
import com.catolica.salesianoapp.ucv.activity.NotasActivity;
import com.catolica.salesianoapp.ucv.activity.QuadroDeHorarioActivity;
import com.catolica.salesianoapp.ucv.adapter.FrequenciaAdapter;
import com.catolica.salesianoapp.ucv.adapter.NotaAdapter;
import com.catolica.salesianoapp.ucv.dialog.DialogSair;
import com.catolica.salesianoapp.ucv.dialog.RadioDialog;
import com.catolica.salesianoapp.ucv.fragment.TabFragment;
import com.catolica.salesianoapp.ucv.model.Aluno;
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


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vincula o Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbar);

        toolbar.setContentInsetStartWithNavigation(0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.abs_layout, null),
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.LEFT
                )
        );

        //Fragment para Mudança do TabFragment.
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainerPrincipal, new TabFragment()).commit();

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
                        KeyboardUtils.hideKeyboard(MainActivity.this);
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
                                new AwesomeSuccessDialog(MainActivity.this)
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
                                                //click
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

        // ----------------------------------------
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notificacao, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_toolbarNotificacao:
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
