package com.catolica.salesianoapp.ucv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.catolica.salesianoapp.ucv.R;
import com.github.chrisbanes.photoview.OnOutsidePhotoTapListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;


public class DetalhesEstagioActivity extends AppCompatActivity implements OnPhotoTapListener, OnOutsidePhotoTapListener {

    String tpVaga;
    String urlImagem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_estagio);

        //Criação do Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetalhesEstagio);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tpVaga = bundle.getString("tpVaga").toString();
        urlImagem = bundle.getString("urlImagem").toString();

        //Botão Voltar do Toolbar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(tpVaga);
        }

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setMaximumScale(5);
        //photoView.setZoomable(true);
        photoView.setOnPhotoTapListener(this);
        photoView.setOnOutsidePhotoTapListener(this);
        Picasso.with(this).load(urlImagem).placeholder(R.drawable.ic_image).error(R.drawable.ic_error_red_24dp).into(photoView);

    }

    @Override
    public void onPhotoTap(ImageView view, float x, float y) {
       // onBackPressed();
    }


    @Override
    public void onOutsidePhotoTap(ImageView imageView) {
        onBackPressed();
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
