package com.catolica.salesianoapp.ucv.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.adapter.FrequenciaAdapter;
import com.catolica.salesianoapp.ucv.adapter.HistoricoAdapter;
import com.catolica.salesianoapp.ucv.adapter.NotaAdapter;
import com.catolica.salesianoapp.ucv.dialog.DialogSair;
import com.catolica.salesianoapp.ucv.dialog.RadioDialog;
import com.catolica.salesianoapp.ucv.model.Aluno;
import com.catolica.salesianoapp.ucv.util.KeyboardUtils;
import com.catolica.salesianoapp.ucv.util.SendMail;
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

public class FaleConoscoActivity extends AppCompatActivity implements View.OnClickListener{

    //Declarando os EditText
    private EditText editTextEmail;
    private EditText editTextAssunto;
    private EditText editTextMensagem;

    private Button buttonEnviar;
    LinearLayout linearLayout;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco);

        //Vincula o Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarFaleConosco);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fale Conosco");

        //Inicializando as view
        editTextEmail = (EditText) findViewById(R.id.txtPara);
        editTextAssunto = (EditText) findViewById(R.id.txtAssunto);
        editTextMensagem = (EditText) findViewById(R.id.txtMensagem);

        buttonEnviar = (Button) findViewById(R.id.btnOK);

        //Adicionando o evento ao click
        buttonEnviar.setOnClickListener(this);

        linearLayout = findViewById(R.id.lnfaleconosco);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(FaleConoscoActivity.this);
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(FaleConoscoActivity.this);
            }
        });

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
                //.withHeaderBackground(R.color.colorPrimary)
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
                        KeyboardUtils.hideKeyboard(FaleConoscoActivity.this);
                    }
                })
                .withActionBarDrawerToggleAnimated(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                // .withTranslucentStatusBar(true)
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

                        //    new SecondaryDrawerItem().withName(R.string.drawer_item_configuracoes).withIconColorRes(R.color.black).withSelectedIconColorRes(R.color.black).withIcon(FontAwesome.Icon.faw_cog).withTextColorRes(R.color.black),
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
                                new AwesomeSuccessDialog(FaleConoscoActivity.this)
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
                        //  return false;
                    }
                })
                .build();

        //Mostra o icone do Hamburguer
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        //Cursor começando pelo segundo EditText
        editTextAssunto.requestFocus();

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

    private void sendEmail() {
        //Pegando informações que o usuário digitou
        String email = editTextEmail.getText().toString().trim();
        String assunto = editTextAssunto.getText().toString().trim();
        String mensagem = editTextMensagem.getText().toString().trim();

        //Criando o email objeto
        SendMail sm = new SendMail(this, email, assunto, mensagem);

        //Executando o envio do email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}

