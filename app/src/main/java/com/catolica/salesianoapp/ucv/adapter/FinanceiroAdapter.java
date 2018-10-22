package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.model.Financeiro;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class FinanceiroAdapter extends RecyclerView.Adapter<FinanceiroAdapter.FinanceiroViewHolder> {

    private List<Financeiro> FinanceiroList;
    private Context context;

    public FinanceiroAdapter(List<Financeiro> FinanceiroList, Context context) {
        this.FinanceiroList = FinanceiroList;
        this.context = context;
    }

    @Override
    public FinanceiroAdapter.FinanceiroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_financeiro, parent, false);
        return new FinanceiroAdapter.FinanceiroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FinanceiroAdapter.FinanceiroViewHolder holder, final int position) {

        Financeiro financeiro = FinanceiroList.get(position);

        holder.txtVencimento.setText(financeiro.getVencimento());
        holder.txtValor.setText("R$: "+financeiro.getValor_bruto());

        if(financeiro.getSituacao().equals("P")){
            holder.txtSituacao.setText("Pago");
        }else{
            holder.txtSituacao.setText("Em aberto");
        }

        if(financeiro.getSituacao().equals("P")){
            holder.img_situacao.setImageResource(R.drawable.ic_check_circle_green_24dp);
        }else{
            holder.img_situacao.setImageResource(R.drawable.timer_sand);
        }

    }


    @Override
    public int getItemCount() {
        return FinanceiroList.size();
    }

    class FinanceiroViewHolder extends RecyclerView.ViewHolder {

        TextView txtVencimento, txtValor, txtSituacao;
        ImageView img_situacao;


        FinanceiroViewHolder(View itemView) {
            super(itemView);

        txtVencimento = (TextView) itemView.findViewById(R.id.txtVencimento);
        txtValor = (TextView) itemView.findViewById(R.id.txtValor);
        txtSituacao = (TextView) itemView.findViewById(R.id.txtSituacao);

        img_situacao = (ImageView) itemView.findViewById(R.id.img_situacao);


        }
    }
}
