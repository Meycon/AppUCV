package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.model.Nota;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    public static int posicaoAtual = -1;
    private List<Nota> NotaList;
    private Context context;

    public NotaAdapter(List<Nota> NotaList, Context context) {
        this.NotaList = NotaList;
        this.context = context;
    }

    @Override
    public NotaAdapter.NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_notas, parent, false);
        return new NotaAdapter.NotaViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final NotaAdapter.NotaViewHolder holder, final int position) {

        Nota nota = NotaList.get(position);
        holder.txtDisciplina.setText(nota.getDisciplina());
        //Zera os valores
        holder.SOMA1 = 0;
        holder.SOMA2 = 0;
        holder.MF = 0;

        //1º Bimestre - Verificar CAMPO NULO
        //Teste
        if (nota.getTeste().equals("null")) {
            holder.txtTeste.setText("-");
            holder.SOMA1 = holder.SOMA1 + 0;
        } else {
            holder.txtTeste.setText(nota.getTeste()+context.getString(R.string.valorMAXTESTE));
            holder.SOMA1 = holder.SOMA1 + Double.parseDouble(nota.getTeste());
            if (Double.parseDouble(nota.getTeste()) < 6) {
                holder.txtTeste.setTextColor(Color.parseColor("#E82125"));
            }
        }
        //P1
        if (nota.getP1().equals("null")) {
            holder.txtP1.setText("-");
            holder.SOMA1 = holder.SOMA1 + 0;
        } else {
            holder.txtP1.setText(nota.getP1()+context.getString(R.string.valorMAXP1));
            holder.SOMA1 = holder.SOMA1 + Double.parseDouble(nota.getP1());
            if (Double.parseDouble(nota.getP1()) < 15) {
                holder.txtP1.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //TD1
        if (nota.getTd1().equals("null")) {
            holder.txtTD1.setText("-");
            holder.SOMA1 = holder.SOMA1 + 0;
        } else {
            holder.txtTD1.setText(nota.getTd1()+context.getString(R.string.valorMAXTD1));
            holder.SOMA1 = holder.SOMA1 + Double.parseDouble(nota.getTd1());
            if (Double.parseDouble(nota.getTd1()) < 5) {
                holder.txtTD1.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //TD2
        if (nota.getTd2().equals("null")) {
            holder.txtTD2.setText("-");
            holder.SOMA1 = holder.SOMA1 + 0;
        } else {
            holder.txtTD2.setText(nota.getTd2()+context.getString(R.string.valorMAXTD2));
            holder.SOMA1 = holder.SOMA1 + Double.parseDouble(nota.getTd2());
            if (Double.parseDouble(nota.getTd2()) < 5) {
                holder.txtTD2.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //2º Bimestre - VERIFICAR CAMPO NULO
        //P2
        if (nota.getP2().equals("null")) {
            holder.txtP2.setText("-");
            holder.SOMA2 = holder.SOMA2 + 0;
        } else {
            holder.txtP2.setText(nota.getP2()+context.getString(R.string.valorMAXP2));
            holder.SOMA2 = holder.SOMA2 + Double.parseDouble(nota.getP2());
            if (Double.parseDouble(nota.getP2()) < 15) {
                holder.txtP2.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //TD3
        if (nota.getTd3().equals("null")) {
            holder.txtTD3.setText("-");
            holder.SOMA2 = holder.SOMA2 + 0;
        } else {
            holder.txtTD3.setText(nota.getTd3()+context.getString(R.string.valorMAXTD3));
            holder.SOMA2 = holder.SOMA2 + Double.parseDouble(nota.getTd3());
            if (Double.parseDouble(nota.getTd3()) < 5) {
                holder.txtTD3.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //TD4
        if (nota.getTd4().equals("null")) {
            holder.txtTD4.setText("-");
            holder.SOMA2 = holder.SOMA2 + 0;
        } else {
            holder.txtTD4.setText(nota.getTd4()+context.getString(R.string.valorMAXTD4));
            holder.SOMA2 = holder.SOMA2 + Double.parseDouble(nota.getTd4());
            if (Double.parseDouble(nota.getTd4()) < 5) {
                holder.txtTD4.setTextColor(Color.parseColor("#E82125"));
            }
        }

        //ACO
        if (nota.getAco().equals("null")) {
            holder.txtACO.setText("-");
            holder.SOMA2 = holder.SOMA2 + 0;
        } else {
            holder.txtACO.setText(nota.getAco()+context.getString(R.string.valorMAXACO));
            holder.SOMA2 = holder.SOMA2 + Double.parseDouble(nota.getAco());
        }

        //Glide.with(context).load(hero.getImageUrl()).into(holder.imageView);
        //LayoutVisible
        //holder.linearLayout.setVisibility(View.GONE);

        //Soma 1º Bimestre
        holder.txtSOMAB1.setText(String.valueOf(holder.SOMA1)+context.getString(R.string.valorMAXSOMA1));

        //Soma 2º Bimestre
        holder.txtSOMAB2.setText(String.valueOf(holder.SOMA2)+context.getString(R.string.valorMAXSOMA2));

        //Soma da Média Final
        holder.MF = holder.SOMA1 + holder.SOMA2;

        //Soma Media Final SEMESTRE
        holder.txtSOMAMF.setText(String.valueOf(holder.MF)+context.getString(R.string.valorMAXSOMATOTAL));

        //Se a posição atual for igual a posicao clicada irá usar a animação
        if (posicaoAtual == position) {

            //Toogle Visible
            if (holder.linearLayout.getVisibility() == View.GONE) {
                expand(holder.linearLayout);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#607d8b"));
            } else {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#1976D2"));
                collapse(holder.linearLayout);
            }

        }

        holder.txtDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pega a posição atual
                posicaoAtual = position;

             //Toast.makeText(context,"Posição: "+ posicaoAtual,Toast.LENGTH_LONG).show();

                //Carrega a lista
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return NotaList.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView txtDisciplina, txtTeste, txtP1, txtTD1, txtTD2, txtP2, txtTD3, txtTD4, txtACO;
        TextView txtSOMAB1, txtSOMAB2, txtSOMAMF;
        CardView cardView;
        double SOMA1, SOMA2, MF;
        // ImageView imageView;
        LinearLayout linearLayout;

        NotaViewHolder(View itemView) {
            super(itemView);

            txtDisciplina = (TextView) itemView.findViewById(R.id.txtDisciplina);
            txtTeste = (TextView) itemView.findViewById(R.id.txtTeste);
            txtP1 = (TextView) itemView.findViewById(R.id.txtP1);
            txtTD1 = (TextView) itemView.findViewById(R.id.txtTD1);
            txtTD2 = (TextView) itemView.findViewById(R.id.txtTD2);
            txtP2 = (TextView) itemView.findViewById(R.id.txtP2);
            txtTD3 = (TextView) itemView.findViewById(R.id.txtTD3);
            txtTD4 = (TextView) itemView.findViewById(R.id.txtTD4);
            txtACO = (TextView) itemView.findViewById(R.id.txtACO);
            txtSOMAB1 = (TextView) itemView.findViewById(R.id.txtSOMAB1);
            txtSOMAB2 = (TextView) itemView.findViewById(R.id.txtSOMAB2);
            txtSOMAMF = (TextView) itemView.findViewById(R.id.txtSOMAMF);
            cardView = (CardView) itemView.findViewById(R.id.cardview1);

            //imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutNotas);

        }
    }


    //Método para separar
    public void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
        if (v.isShown()) {
            collapse(v);
        } else {
            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1 ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int) (targtetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            a.setDuration((int) (targtetHeight + 100));
            v.startAnimation(a);
        }

    }

    //Método para juntar
    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight
                            - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (v.getLayoutParams().height + 200));
        v.startAnimation(a);
    }
}
