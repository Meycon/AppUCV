package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.activity.DetalhesEstagioActivity;
import com.catolica.salesianoapp.ucv.activity.DetalhesPdfActivity;
import com.catolica.salesianoapp.ucv.model.Documentos;
import com.catolica.salesianoapp.ucv.model.Estagio;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class EstagioAdapter extends RecyclerView.Adapter<EstagioAdapter.EstagioViewHolder> {

    public static int posicaoAtual = -1;
    private List<Estagio> EstagioList;
    private Context context;

    public EstagioAdapter(List<Estagio> EstagioList, Context context) {
        this.EstagioList = EstagioList;
        this.context = context;
    }

    @Override
    public EstagioAdapter.EstagioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_estagio, parent, false);
        return new EstagioAdapter.EstagioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EstagioAdapter.EstagioViewHolder holder, final int position) {

        final Estagio estagio = EstagioList.get(position);

        holder.codVaga.setText(String.valueOf(estagio.getCodEstagio()));
        holder.tpVaga.setText(estagio.getVaga());
        holder.nmCurso.setText(estagio.getCurso());

        holder.linearLayoutEstagio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesEstagioActivity.class);
                intent.putExtra("tpVaga",estagio.getVaga());
                intent.putExtra("urlImagem",estagio.getImagem());
                context.startActivity(intent);
            }
        });
        holder.linearLayoutEstagio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesEstagioActivity.class);
                intent.putExtra("tpVaga",estagio.getVaga());
                intent.putExtra("urlImagem",estagio.getImagem());
                context.startActivity(intent);
            }
        });
        holder.linearLayoutEstagio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesEstagioActivity.class);
                intent.putExtra("tpVaga",estagio.getVaga());
                intent.putExtra("urlImagem",estagio.getImagem());
                context.startActivity(intent);
            }
        });
        holder.linearLayoutEstagio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesEstagioActivity.class);
                intent.putExtra("tpVaga",estagio.getVaga());
                intent.putExtra("urlImagem",estagio.getImagem());
                context.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return EstagioList.size();
    }

    class EstagioViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewEstagio;
        TextView codVaga, tpVaga, nmCurso;
        LinearLayout linearLayoutEstagio1, linearLayoutEstagio2, linearLayoutEstagio3, linearLayoutEstagio4;

        EstagioViewHolder(View itemView) {
            super(itemView);

            cardViewEstagio = (CardView) itemView.findViewById(R.id.cardViewEstagio);
            codVaga = (TextView) itemView.findViewById(R.id.codVaga);
            tpVaga = (TextView) itemView.findViewById(R.id.tpVaga);
            nmCurso = (TextView) itemView.findViewById(R.id.nmCurso);
            linearLayoutEstagio1 = (LinearLayout) itemView.findViewById(R.id.linearLayoutEstagio1);
            linearLayoutEstagio2 = (LinearLayout) itemView.findViewById(R.id.linearLayoutEstagio2);
            linearLayoutEstagio3 = (LinearLayout) itemView.findViewById(R.id.linearLayoutEstagio3);
            linearLayoutEstagio4 = (LinearLayout) itemView.findViewById(R.id.linearLayoutEstagio4);

        }
    }
}
