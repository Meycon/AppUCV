package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.activity.DetalhesActivity;
import com.catolica.salesianoapp.ucv.activity.DetalhesPdfActivity;
import com.catolica.salesianoapp.ucv.activity.DocumentosActivity;
import com.catolica.salesianoapp.ucv.model.Comunicado;
import com.catolica.salesianoapp.ucv.model.Documentos;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class DocumentosAdapter extends RecyclerView.Adapter<DocumentosAdapter.DocumentosViewHolder> {

    private List<Documentos> DocumentosList;
    private Context context;
    public static int posicaoAtual = -1;

    public DocumentosAdapter(List<Documentos> DocumentosList, Context context) {
        this.DocumentosList = DocumentosList;
        this.context = context;
    }

    @Override
    public DocumentosAdapter.DocumentosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_documentos, parent, false);
        return new DocumentosAdapter.DocumentosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DocumentosAdapter.DocumentosViewHolder holder, final int position) {

        final Documentos documento = DocumentosList.get(position);

        holder.txtNomeDocumento.setText(documento.getNome());
        holder.linearLayoutDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetalhesPdfActivity.class);
                intent.putExtra("NomeDocumento",documento.getNome()); //Criado a classe Config para utilizar variável global.
                intent.putExtra("UrlDocumento",documento.getUrl()); //Criado a classe Config para utilizar variável global.
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return DocumentosList.size();
    }

    class DocumentosViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeDocumento;
        ImageView img_pdf;
        LinearLayout linearLayoutDocumentos;

        DocumentosViewHolder(View itemView) {
            super(itemView);

            txtNomeDocumento = (TextView) itemView.findViewById(R.id.txtnomeDocumento);
            img_pdf = (ImageView) itemView.findViewById(R.id.img_pdf);
            linearLayoutDocumentos = (LinearLayout) itemView.findViewById(R.id.lineLayoutDocumentos);

        }
    }
}
