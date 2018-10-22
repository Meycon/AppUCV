package com.catolica.salesianoapp.ucv.adapter;

import android.annotation.SuppressLint;
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

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.activity.ComunicadoActivity;
import com.catolica.salesianoapp.ucv.activity.FinanceiroActivity;
import com.catolica.salesianoapp.ucv.activity.FrequenciaActivity;
import com.catolica.salesianoapp.ucv.activity.HistoricoEscolarActivity;
import com.catolica.salesianoapp.ucv.activity.NotasActivity;
import com.catolica.salesianoapp.ucv.activity.QuadroDeHorarioActivity;
import com.catolica.salesianoapp.ucv.model.Categoria;

import java.util.List;

/**
 * Created by Meycon Augusto on 13/01/2018.
 */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private List<Categoria> itens;
    private Context context;
    private Categoria categoria;

    private int colorNotas = R.color.md_red_400;
    private int colorFrequencia = R.color.md_green_400;
    private int colorFinanceiro = R.color.md_purple_400;
    private int colorDatasProvas = R.color.md_orange_400;
    private int colorHorarios = R.color.md_blue_700;
    private int colorHistoricoEscolar = R.color.md_pink_400;
    private int colorGeral = R.color.white;

    public CategoriaAdapter(Context context, List<Categoria> itens) {
        this.itens = itens;
        this.context = context;
    }

    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_categoria, viewGroup, false);
        return new ViewHolder(v);

    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onBindViewHolder(final CategoriaAdapter.ViewHolder holder, final int position) {
        holder.titulo.setText(itens.get(position).getNomeCategoria());

        //Notas de provas
        if (position == 0) {
            holder.imagem.setImageResource(R.drawable.file_check);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#1976D2"));
        }
        //Financeiro
        if (position == 1) {
            holder.imagem.setImageResource(R.drawable.ic_attach_money_black_24dp);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#66BB6A"));
        }
        //Frequência
        if (position == 2) {
            holder.imagem.setImageResource(R.drawable.ic_event_busy_black_24dp);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#EF5350"));
        }
        //Comunicado
        if (position == 3) {
            holder.imagem.setImageResource(R.drawable.bullhorn);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFA726"));
        }
        //Horários
        if (position == 4) {
            holder.imagem.setImageResource(R.drawable.calendar_clock);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#AB47BC"));
        }
        //Histórico Escolar
        if (position == 5) {
            holder.imagem.setImageResource(R.drawable.ic_library_books_black_24dp);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#EC407A"));
        }
        holder.view2.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.titulo.setTextColor(Color.parseColor("#ffffff"));

        //Clique no Layout para abrir a informação
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Direcionar para tela - Notas de provas
                if (position == 0) {
                    categoria = itens.get(position);
                    NotaAdapter.posicaoAtual = -1;
                    Intent intent = new Intent(context, NotasActivity.class);
                    context.startActivity(intent);
                }
                //Direcionar para tela - Financeiro
                if (position == 1) {
                    categoria = itens.get(position);
                    Intent intent = new Intent(context, FinanceiroActivity.class);
                    context.startActivity(intent);
                }
                //Direcionar para tela - Frequencia
                if (position == 2) {
                    categoria = itens.get(position);
                    FrequenciaAdapter.posicaoAtual = -1;
                    Intent intent = new Intent(context, FrequenciaActivity.class);
                    context.startActivity(intent);
                }
                //Direcionar para tela - Datas de provas
                if (position == 3) {
                    categoria = itens.get(position);
                    Intent intent = new Intent(context, ComunicadoActivity.class);
                    context.startActivity(intent);
                }
                //Direcionar para tela - Horários
                if (position == 4) {
                    categoria = itens.get(position);
                    Intent intent = new Intent(context, QuadroDeHorarioActivity.class);
                    context.startActivity(intent);
                }
                //Direcionar para tela - Histórico Escolar
                if (position == 5) {
                    categoria = itens.get(position);
                    HistoricoAdapter.posicaoAtual = -1;
                    Intent intent = new Intent(context, HistoricoEscolarActivity.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagem;
        public TextView titulo;
        public LinearLayout linearLayout;
        public View view2;

        public ViewHolder(View view) {
            super(view);

            titulo = (TextView) view.findViewById(R.id.tituloCategoria);
            imagem = (ImageView) view.findViewById(R.id.imgCategoria);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_categoria);
            view2 = (View) view.findViewById(R.id.viewcategoria);
        }
    }

}
