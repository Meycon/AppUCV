package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.model.Comunicado;
import com.catolica.salesianoapp.ucv.model.Financeiro;
import com.catolica.salesianoapp.ucv.util.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class ComunicadoAdapter extends RecyclerView.Adapter<ComunicadoAdapter.ComunicadoViewHolder> {

    private List<Comunicado> ComunicadoList;
    private Context context;

    public ComunicadoAdapter(List<Comunicado> ComunicadoList, Context context) {
        this.ComunicadoList = ComunicadoList;
        this.context = context;
    }

    @Override
    public ComunicadoAdapter.ComunicadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_comunicado, parent, false);
        return new ComunicadoAdapter.ComunicadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ComunicadoAdapter.ComunicadoViewHolder holder, final int position) {

        Comunicado comunicado = ComunicadoList.get(position);

        holder.txtNomeCurso.setText(comunicado.getMateria());
        holder.txtNomeProfessor.setText(comunicado.getProfessor());
        holder.txtDataComunicado.setText(comunicado.getData());
        holder.txtTituloComunicado.setText(comunicado.getTitulo_descricao());
        holder.txtDescricaoComunicado.setText(comunicado.getDescricao());

        Picasso.with(context).load(comunicado.getImagem()).placeholder(R.drawable.ic_image).into(holder.img_professor);

    }


    @Override
    public int getItemCount() {
        return ComunicadoList.size();
    }

    class ComunicadoViewHolder extends RecyclerView.ViewHolder {


        TextView txtNomeCurso, txtNomeProfessor, txtDataComunicado, txtTituloComunicado, txtDescricaoComunicado;
        ImageView img_professor;


        ComunicadoViewHolder(View itemView) {
            super(itemView);

        txtNomeCurso = (TextView) itemView.findViewById(R.id.txtNomeCurso);
        txtNomeProfessor = (TextView) itemView.findViewById(R.id.txtNomeProfessor);
        txtDataComunicado = (TextView) itemView.findViewById(R.id.txtDataComunicado);
        txtTituloComunicado = (TextView) itemView.findViewById(R.id.txtTituloComunicado);
        txtDescricaoComunicado = (TextView) itemView.findViewById(R.id.txtDescricaoComunicado);
        img_professor = (ImageView) itemView.findViewById(R.id.img_professsor);

        }
    }
}
