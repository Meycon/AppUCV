package com.catolica.salesianoapp.ucv.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.catolica.salesianoapp.ucv.R;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class DetalhesPdfActivity extends AppCompatActivity implements DownloadFile.Listener {

    RemotePDFViewPager remotePDFViewPager;
    String url;
    PDFPagerAdapter adapter;
    PDFViewPager pdfViewPager;
    String NomeDocumento, UrlDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pdf);

        //Criação do Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDocumentos);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        NomeDocumento =  bundle.getString("NomeDocumento").toString();
        UrlDocumento = bundle.getString("UrlDocumento").toString();

        //Botão Voltar do Toolbar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(NomeDocumento);
        }


        pdfViewPager = (PDFViewPager) findViewById(R.id.pdfViewPager);

        DownloadFile.Listener listener = this;
        remotePDFViewPager = new RemotePDFViewPager(this, UrlDocumento, listener);
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

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        pdfViewPager.setAdapter(adapter);
    }

    @Override
    public void onFailure(Exception e) {
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.close();
        }
    }

}
