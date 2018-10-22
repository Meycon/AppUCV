package com.catolica.salesianoapp.ucv.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.catolica.salesianoapp.ucv.activity.LoginActivity;
import com.catolica.salesianoapp.ucv.model.Aluno;

/**
 * Created by Meycon Augusto on 01/02/2018.
 */

public class SharedPrefManager {

    //As constantes
    private static final String SHARED_PREF_NAME = "pref_aluno";
    private static final String KEY_ID = "key_id";
    private static final String KEY_MATRICULA = "key_matricula";
    private static final String KEY_NOME = "key_nome";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_GENERO = "key_genero";
    private static final String KEY_IMAGEM = "key_imagem";
    private static final String KEY_CPF = "key_cpf";
    private static final String KEY_CURSO = "key_curso";
    private static final String KEY_VALIDADE = "key_validade";
    private static final String KEY_SEMESTRE = "key_semestre";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //Esse método guarda as informações do usuario
    public void userLogin(Aluno aluno) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, aluno.getIdaluno());
        editor.putString(KEY_MATRICULA, aluno.getMatricula());
        editor.putString(KEY_NOME, aluno.getNome());
        editor.putString(KEY_EMAIL, aluno.getEmail());
        editor.putString(KEY_GENERO, aluno.getGenero());
        editor.putString(KEY_IMAGEM, aluno.getImagem());
        editor.putString(KEY_CPF, aluno.getCpf());
        editor.putString(KEY_SEMESTRE, aluno.getSemestre());
        editor.apply();
    }

    //Chega se o usuario esta logado
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NOME, null) != null;
    }

    //Esse método pega o usuario logado
    public Aluno getAluno() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Aluno(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_MATRICULA, null),
                sharedPreferences.getString(KEY_NOME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENERO, null),
                sharedPreferences.getString(KEY_IMAGEM, null),
                sharedPreferences.getString(KEY_CPF, null),
                sharedPreferences.getString(KEY_SEMESTRE, null)
        );
    }

    //Desloga o usuario
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
