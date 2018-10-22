package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.model.Frequencia;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class FrequenciaAdapter extends RecyclerView.Adapter<FrequenciaAdapter.FrequenciaViewHolder> {

    public static int posicaoAtual = -1;
    private List<Frequencia> FrequenciaList;
    private Context context;

    public FrequenciaAdapter(List<Frequencia> FrequenciaList, Context context) {
        this.FrequenciaList = FrequenciaList;
        this.context = context;
    }

    @Override
    public FrequenciaAdapter.FrequenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_frequencia, parent, false);
        return new FrequenciaAdapter.FrequenciaViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final FrequenciaAdapter.FrequenciaViewHolder holder, final int position) {

        Frequencia frequencia = FrequenciaList.get(position);

        holder.txtDisciplina.setText(frequencia.getDisciplina());
        holder.SOMA1 = 0;

        //Data1
        holder.txtData1.setText(frequencia.getData1());
        holder.txtQuantidade1.setText(frequencia.getQuantidade1());
        holder.SOMA1 = holder.SOMA1 + Integer.parseInt(frequencia.getQuantidade1());

        //Data2
        holder.txtData2.setText(frequencia.getData2());
        holder.txtQuantidade2.setText(frequencia.getQuantidade2());
        holder.SOMA1 = holder.SOMA1 + Integer.parseInt(frequencia.getQuantidade2());


        //Data3
        holder.txtData3.setText(frequencia.getData3());
        holder.txtQuantidade3.setText(frequencia.getQuantidade3());
        holder.SOMA1 = holder.SOMA1 + Integer.parseInt(frequencia.getQuantidade3());


        //Total
        holder.txtTotal.setText(String.valueOf(holder.SOMA1));
        holder.txtTotalPermitida.setText("20");

        //Barra Fora
        holder.bootstrapProgressBarFora.setProgress(holder.SOMA1 * 5);
        if (holder.bootstrapProgressBarFora.getProgress() <= 50) {
            holder.bootstrapProgressBarFora.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        } else if (holder.bootstrapProgressBarFora.getProgress() < 70) {
            holder.bootstrapProgressBarFora.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        } else {
            holder.bootstrapProgressBarFora.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }

        //Barra Dentro
        holder.bootstrapProgressBarDentro.setProgress(holder.SOMA1 * 5);
        if (holder.bootstrapProgressBarDentro.getProgress() <= 50) {
            holder.bootstrapProgressBarDentro.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        } else if (holder.bootstrapProgressBarDentro.getProgress() < 70) {
            holder.bootstrapProgressBarDentro.setBootstrapBrand(DefaultBootstrapBrand.WARNING);
        } else {
            holder.bootstrapProgressBarDentro.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }

        //Voltar o item ao clicar
        collapse(holder.linearLayout);
        holder.cardView.setCardBackgroundColor(Color.parseColor("#EF5350"));
        holder.bootstrapProgressBarFora.setVisibility(View.VISIBLE);

        //Se a posição atual for igual a posicao clicada irá usar a animação
        if (posicaoAtual == position) {

            //Toogle Visible e Nao Visible
            if (holder.linearLayout.getVisibility() == View.GONE) {
                expand(holder.linearLayout);
                holder.bootstrapProgressBarFora.setVisibility(View.GONE);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#607d8b"));
            } else {
                collapse(holder.linearLayout);
                holder.cardView.setCardBackgroundColor(Color.parseColor("#EF5350"));
                holder.bootstrapProgressBarFora.setVisibility(View.VISIBLE);

            }

        }

        holder.txtDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pega a posição atual
                posicaoAtual = position;

                //Carrega a lista
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return FrequenciaList.size();
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

    class FrequenciaViewHolder extends RecyclerView.ViewHolder {
        TextView txtDisciplina, txtData1, txtData2, txtData3, txtQuantidade1, txtQuantidade2, txtQuantidade3;
        TextView txtTotal, txtTotalPermitida;

        CardView cardView;
        LinearLayout linearLayout;

        BootstrapProgressBar bootstrapProgressBarDentro, bootstrapProgressBarFora;

        int SOMA1;

        FrequenciaViewHolder(View itemView) {
            super(itemView);

            txtDisciplina = (TextView) itemView.findViewById(R.id.txtDisciplinaFrequencia);
            txtData1 = (TextView) itemView.findViewById(R.id.txtData1);
            txtData2 = (TextView) itemView.findViewById(R.id.txtData2);
            txtData3 = (TextView) itemView.findViewById(R.id.txtData3);
            txtQuantidade1 = (TextView) itemView.findViewById(R.id.txtQuantidade1);
            txtQuantidade2 = (TextView) itemView.findViewById(R.id.txtQuantidade2);
            txtQuantidade3 = (TextView) itemView.findViewById(R.id.txtQuantidade3);

            txtTotal = (TextView) itemView.findViewById(R.id.txtTotal);
            txtTotalPermitida = (TextView) itemView.findViewById(R.id.txtTotalPermitida);

            cardView = (CardView) itemView.findViewById(R.id.cardviewFrequencia);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutFrequencia);

            bootstrapProgressBarDentro = (BootstrapProgressBar) itemView.findViewById(R.id.bootstrapProgressBarDisciplinaDentro);
            bootstrapProgressBarFora = (BootstrapProgressBar) itemView.findViewById(R.id.bootstrapProgressBarDisciplinaFora);


        }
    }
}
