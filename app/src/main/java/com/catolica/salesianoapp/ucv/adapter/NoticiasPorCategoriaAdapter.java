package com.catolica.salesianoapp.ucv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.catolica.salesianoapp.ucv.R;
import com.catolica.salesianoapp.ucv.activity.DetalhesActivity;
import com.catolica.salesianoapp.ucv.model.Noticia;
import com.catolica.salesianoapp.ucv.util.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Meycon Augusto on 13/01/2018.
 */

public class NoticiasPorCategoriaAdapter extends RecyclerView.Adapter<NoticiasPorCategoriaAdapter.ViewHolder> {

    private List<Noticia> itens;
    private Context context;
    private Noticia noticia;

    public NoticiasPorCategoriaAdapter(Context context, List<Noticia> itens){
        this.itens = itens;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagem;
        public TextView titulo;
        public TextView data;
        public LinearLayout linearLayout;


        public ViewHolder(View view) {
            super(view);

            titulo = (TextView) view.findViewById(R.id.txtTituloNoticia);
            data = (TextView) view.findViewById(R.id.txtDataNoticia);
            imagem = (ImageView) view.findViewById(R.id.img_noticias);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_noticias);
        }
    }

    @Override
    public NoticiasPorCategoriaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_itens_recentes,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticiasPorCategoriaAdapter.ViewHolder holder, final int position) {
      //Método responsável por montar a lista
        Picasso.with(context).load(Config.URL_SERVIDOR + "upload/thumbs/" + itens.get(position).getImgNoticia()).placeholder(R.drawable.ic_image).into(holder.imagem);
        holder.titulo.setText(itens.get(position).getTituloNoticia());
        holder.data.setText(itens.get(position).getDataNoticia());

        noticia = itens.get(position);

        //Clique no Layout para abrir a informação
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Config.ID_NOTICIA = noticia.getIdNoticia();
                Intent intent = new Intent(context, DetalhesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

}
