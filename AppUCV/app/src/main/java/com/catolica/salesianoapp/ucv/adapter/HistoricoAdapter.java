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
import com.catolica.salesianoapp.ucv.model.Historico;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder> {

    public static int posicaoAtual = -1;
    private List<Historico> HistoricoList;
    private Context context;

    public HistoricoAdapter(List<Historico> HistoricoList, Context context) {
        this.HistoricoList = HistoricoList;
        this.context = context;
    }

    @Override
    public HistoricoAdapter.HistoricoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_historico, parent, false);
        return new HistoricoAdapter.HistoricoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final HistoricoAdapter.HistoricoViewHolder holder, final int position) {

        Historico historico = HistoricoList.get(position);

        holder.txtSemestre.setText(historico.getSemestre());

        holder.txtMat1.setText(historico.getMat1());
        holder.txtNota1.setText(historico.getNota1());
        holder.txtMat2.setText(historico.getMat2());
        holder.txtNota2.setText(historico.getNota2());
        holder.txtMat3.setText(historico.getMat3());
        holder.txtNota3.setText(historico.getNota3());
        holder.txtMat4.setText(historico.getMat4());
        holder.txtNota4.setText(historico.getNota4());
        holder.txtMat5.setText(historico.getMat5());
        holder.txtNota5.setText(historico.getNota5());
        holder.txtMat6.setText(historico.getMat6());
        holder.txtNota6.setText(historico.getNota6());
        holder.txtMat7.setText(historico.getMat7());
        holder.txtNota7.setText(historico.getNota7());

        holder.cardviewHistorico.setCardBackgroundColor(Color.parseColor("#EC407A"));

        holder.txtCR.setText(historico.getMediasemestre());
    }


    @Override
    public int getItemCount() {
        return HistoricoList.size();
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

    class HistoricoViewHolder extends RecyclerView.ViewHolder {

        TextView txtSemestre, txtMat1, txtMat2, txtMat3, txtMat4, txtMat5, txtMat6, txtMat7;
        TextView txtNota1, txtNota2, txtNota3, txtNota4, txtNota5, txtNota6, txtNota7;

        CardView cardviewHistorico;
        LinearLayout linearLayoutHistorico;
        TextView txtCR;

        HistoricoViewHolder(View itemView) {
            super(itemView);

            txtSemestre = (TextView) itemView.findViewById(R.id.txtSemestre);
            txtMat1 = (TextView) itemView.findViewById(R.id.txtMat1);
            txtMat2 = (TextView) itemView.findViewById(R.id.txtMat2);
            txtMat3 = (TextView) itemView.findViewById(R.id.txtMat3);
            txtMat4 = (TextView) itemView.findViewById(R.id.txtMat4);
            txtMat5 = (TextView) itemView.findViewById(R.id.txtMat5);
            txtMat6 = (TextView) itemView.findViewById(R.id.txtMat6);
            txtMat7 = (TextView) itemView.findViewById(R.id.txtMat7);

            txtNota1 = (TextView) itemView.findViewById(R.id.txtNota1);
            txtNota2 = (TextView) itemView.findViewById(R.id.txtNota2);
            txtNota3 = (TextView) itemView.findViewById(R.id.txtNota3);
            txtNota4 = (TextView) itemView.findViewById(R.id.txtNota4);
            txtNota5 = (TextView) itemView.findViewById(R.id.txtNota5);
            txtNota6 = (TextView) itemView.findViewById(R.id.txtNota6);
            txtNota7 = (TextView) itemView.findViewById(R.id.txtNota7);

            cardviewHistorico = (CardView) itemView.findViewById(R.id.cardviewHistorico);
            linearLayoutHistorico = (LinearLayout) itemView.findViewById(R.id.linearLayoutHistorico);

            txtCR = (TextView) itemView.findViewById(R.id.txtCR);

        }
    }
}
