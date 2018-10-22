package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.model.Aula;
import com.catolica.salesianoapp.ucv.util.Ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Meycon Augusto on 11/02/2018.
 */

public class AulasAdapter extends Adapter{

    private final List<Aula> dataset;
    private Context context;
    private final int day;
    public static int posicaoAtual = -1;


    public final List getDataset(){
        return this.dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = Ext.getLayoutInflater((View)parent).inflate(R.layout.lista_item_quadro_de_horario,parent,false);
    return new AulasAdapter.ViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
        this.onBindViewHolder((AulasAdapter.ViewHolder)var1, var2);
    }

    public void onBindViewHolder(AulasAdapter.ViewHolder holder, final int position) {
        Aula item = dataset.get(position);

        holder.name.setText(item.getName());
        holder.prof.setText(item.getProf());
        holder.place.setText(item.getPlace());
        holder.startTime.setText(item.getStartTime());
        holder.endTime.setText(item.getEndTime());

            if(item.getLength()>1){
                holder.itemView.getLayoutParams().height = (int) (holder.itemView.getContext().getResources().getDimension(R.dimen.class_item_height) * (float)2);
            }


        if (posicaoAtual == position) {
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pega a posição atual
                posicaoAtual = position;

                //Carrega a lista
                notifyDataSetChanged();
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public final int getDay(){
        return this.day;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, prof, place, startTime, endTime, empty_view;
        RecyclerView recyclerView;



        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            prof = (TextView) itemView.findViewById(R.id.prof);
            place = (TextView) itemView.findViewById(R.id.place);
            startTime = (TextView) itemView.findViewById(R.id.startTime);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            empty_view = (TextView) itemView.findViewById(R.id.empty_view);

        }
    }

    public AulasAdapter(int day, List<Aula> classes, Context context){
        super();
        this.day = day;
        this.context = context;

        Iterable $receiver$iv = (Iterable)classes;
        Collection destination$iv$iv = (Collection)(new ArrayList());
        Iterator iterator = $receiver$iv.iterator();

        while(iterator.hasNext()) {
            Object object;
            boolean aux;
            label20: {
                object = iterator.next();
                Aula aula = (Aula) object;
                Integer diaClasse = aula.getDay();
                int dia = this.day;
                if(diaClasse != null) {
                    if(diaClasse.intValue() == dia) {
                        aux = true;
                        break label20;
                    }
                }
                aux = false;
            }

            if(aux) {
                destination$iv$iv.add(object);
            }
        }

        List var14 = (List)destination$iv$iv;
        this.dataset = var14;

    }
}
