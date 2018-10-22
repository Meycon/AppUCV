package com.catolica.salesianoapp.ucv.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.MainActivity;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.dialog.DialogEntrarEmContato;
import com.catolica.salesianoapp.ucv.model.Aluno;
import com.catolica.salesianoapp.ucv.util.Config;
import com.catolica.salesianoapp.ucv.util.KeyboardUtils;
import com.catolica.salesianoapp.ucv.util.RequestHandler;
import com.catolica.salesianoapp.ucv.util.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    Button btnLogon;
    TextView tvEsqueceuSenha;
    LinearLayout linearLayout, linearLayout2;
    EditText editTextMatricula, editTextSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Se o usuário estiver logado então irá redirecionar para o MainActivity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
          //  startActivity(new Intent(this, TutorialActivity.class));
            return;
        }

        /*NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();*/

        editTextMatricula = (EditText) findViewById(R.id.etMatriculaouEmail);
        editTextSenha = (EditText) findViewById(R.id.etSenha);
        btnLogon = (Button) findViewById(R.id.btnLogon);

        //Esconder o teclado ao clicar em qualquer lugar da tela em volta da logo
        linearLayout = (LinearLayout) findViewById(R.id.lnlogin2);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(LoginActivity.this);
            }
        });

        //Esconder o teclado ao clicar em qualquer lugar proximo a logo da tela
        linearLayout2 = (LinearLayout) findViewById(R.id.lnlogin3);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(LoginActivity.this);
            }
        });

        tvEsqueceuSenha = (TextView) findViewById(R.id.tvEsqueceuSenha);
        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /* // Dialogo
                DialogEntrarEmContato dialogEntrarEmContato = new DialogEntrarEmContato();
                dialogEntrarEmContato.show(getFragmentManager(), "dialog");
             */
                String url = "http://portal.ucv.edu.br/Corpore.Net/SharedServices/LibPages/RecoverPassConfirmation.aspx?UserCaption=5LK%5c9F%5c3D%5c023%5c5B&ConfirmationCaption=%5c7B%5cFAbP%5c06%5c11Q%5c7C&RecoverContainerClassName=ASP.login_aspx,%20App_Web_cc5b4u3u,%20Version=0.0.0.0,%20Culture=neutral,%20PublicKeyToken=null&RecoverInitializeMethodName=GetRecoverPassServer&ServiceAlias=CorporeRM";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        btnLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(LoginActivity.this);
                userLogin();
            }
        });


    }

    private void userLogin() {
        //Pegar os valores
        final String matricula = editTextMatricula.getText().toString();
        final String senha = editTextSenha.getText().toString();

        //Validar as entrada
        if (TextUtils.isEmpty(matricula)) {
            editTextMatricula.setError("Favor entrar com a Matricula/Email");
            editTextMatricula.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            editTextSenha.setError("Favor entrar com a Senha");
            editTextSenha.requestFocus();
            return;
        }

        //Se os valores estiverem corretos.
        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.INVISIBLE);

                if (s == "") {
                   // Toast.makeText(getApplicationContext(), "Falha ao se comunicar com o servidor, favor tente mais tarde", Toast.LENGTH_SHORT).show();
                    Toasty.error(getApplicationContext(), "Falha ao se comunicar com o servidor, favor tente mais tarde.", Toast.LENGTH_SHORT, true).show();
                } else {
                    try {
                        //Converter a resposta em um objeto
                        JSONObject obj = new JSONObject(s);

                        //Se estiver erro na resposta
                        if (!obj.getBoolean("error")) {
                          //  Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            if(obj.getString("message").equals("Login bem-sucedido")) {
                                Toasty.success(getApplicationContext(), "Login bem-sucedido", Toast.LENGTH_SHORT, true).show();
                            }else{
                            Toasty.error(getApplicationContext(), "Nome ou senha inválidos", Toast.LENGTH_SHORT, true).show();
                            }
                            //Pega as informações do Aluno em Json
                            JSONObject alunoJson = obj.getJSONObject("aluno");

                            //Cria um objeto Aluno
                            Aluno aluno = new Aluno();
                            aluno.setIdaluno(alunoJson.getInt("idaluno"));
                            aluno.setMatricula(alunoJson.getString("matricula"));
                            aluno.setNome(alunoJson.getString("nome"));
                            aluno.setEmail(alunoJson.getString("email"));
                            aluno.setGenero(alunoJson.getString("genero"));
                            aluno.setImagem(alunoJson.getString("imagem"));
                            aluno.setCpf(alunoJson.getString("cpf"));
                        //    aluno.setCurso(alunoJson.getString("curso"));
                        //    aluno.setValidade(alunoJson.getString("validade"));

                            //Guarda as informações do Aluno
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(aluno);

                            //Inicia a Activity
                          //  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                            finish();
                        } else {
                         //   Toast.makeText(getApplicationContext(), "Senha ou Matricula inválido", Toast.LENGTH_SHORT).show();
                            Toasty.error(getApplicationContext(), "Falha ao se comunicar com o servidor, favor tente mais tarde.", Toast.LENGTH_SHORT, true).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                //Criando a requisição do Handler
                RequestHandler requestHandler = new RequestHandler();

                //Criando a requisição do parâmetros
                HashMap<String, String> params = new HashMap<>();
                params.put("matricula", matricula);
                params.put("senha", senha);


                //retornando a resposta
                return requestHandler.sendPostRequest(Config.URL_LOGIN, params);
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
    }
}
