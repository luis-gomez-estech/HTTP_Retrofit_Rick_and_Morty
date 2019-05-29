package com.luisgomez.http_retrofit_rick_and_morty;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RMAdapter extends RecyclerView.Adapter<RMAdapter.RMViewHolder> {

    // RM mayusculas = Ricky and morty


    List<Result> listaRM;
    private Context context;


    public RMAdapter(Context context, List<Result> listaRM) {
        this.listaRM = listaRM;
        this.context = context;
    }


    @Override
    public RMViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
        return new RMViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RMViewHolder RMViewHolder, int i) {
        RMViewHolder.rmBind(listaRM.get(i));
    }

    @Override
    public int getItemCount() {
        return listaRM.size();
    }

    public class RMViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;
        TextView tvStatus;
        TextView tvSpecies; // esta no puede ser private
        TextView tvType; // esta no puede ser private
        TextView tvGender; // esta no puede ser private
        ImageView imgRM;
        // private Origin origin;
        //private Location location;

        //private List<String> episode = null;
        //private String url;
        //private String created;
        // private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public RMViewHolder(@NonNull View itemView) {
        super(itemView);
         tvId = itemView.findViewById(R.id.tvId);
         tvName = itemView.findViewById(R.id.tvName);
         tvStatus = itemView.findViewById(R.id.tvStatus);
         tvSpecies = itemView.findViewById(R.id.tvSpecies);
         // tvType = itemView.findViewById(R.id.tvType); // Desactivo pq no tiene datos en la api
         tvGender = itemView.findViewById(R.id.tvGender);
         imgRM = itemView.findViewById(R.id.ivImage);

    }

    public void rmBind(Result rm) {

        tvId.setText(String.valueOf(rm.getId()));

        String name = rm.getName();
        tvName.setText(name);

        String status = rm.getStatus();
        tvStatus.setText(status);

        String species = rm.getSpecies();
        tvSpecies.setText(species);

        //String type = rm.getType();
        //tvType.setText(type);

        String gender = rm.getGender();
        tvGender.setText(gender);

        // La imagen se obtiene con Glide de esta forma

        Glide.with(context).load(rm.getImage()).into(imgRM);

        }
    }



}
