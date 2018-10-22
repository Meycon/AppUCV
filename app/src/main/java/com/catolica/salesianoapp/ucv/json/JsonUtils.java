package com.catolica.salesianoapp.ucv.json;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Meycon Augusto on 14/01/2018.
 */

public class JsonUtils {
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    Context contex;

    //Passar o Contexto atual
    public JsonUtils(Context context) {
        this.contex = context;
    }

    //Método para Retornar o Json Via GET // Obs.: Dar permissão no AndroidManifest para acesso a INTERNET
    public static String retornaJsonDeGet(String url) {
        HttpGet cliente = new HttpGet(url);
        cliente.addHeader("Conteent-Type", "application/json");

        HttpResponse response = null;
        String json = null;
        try {
            response = httpClient.execute(cliente);
            json = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static boolean estaConectado(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if ((networkInfo != null) && (networkInfo.isConnected()))
            return true;

        return false;

    }

}