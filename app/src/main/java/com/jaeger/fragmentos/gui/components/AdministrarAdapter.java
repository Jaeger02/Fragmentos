package com.jaeger.fragmentos.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.jaeger.fragmentos.R;
import com.jaeger.fragmentos.model.AdministrarJuego;
import com.jaeger.fragmentos.model.MiJuego;

import java.util.List;

public class AdministrarAdapter extends RecyclerView.Adapter<AdministrarAdapter.ViewHolder>{

    private List<AdministrarJuego> administrarJuegos;
    private Context context;

    public AdministrarAdapter(List<AdministrarJuego> administrarJuegos) {
        this.administrarJuegos = administrarJuegos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_administrar,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdministrarJuego administrarJuego = administrarJuegos.get(position);
        String imgUri = "@drawable/"+ administrarJuego.getImagen();
        int imgResource = context.getResources().getIdentifier
                (imgUri,null,context.getPackageName());
        holder.imgAdministrar.setImageResource(imgResource);
        holder.txtTitulo.setText(administrarJuego.getTitulo());
        holder.rbClasificacion.setRating(administrarJuego.getClasificacion());
        holder.txtDescripcion.setText(administrarJuego.getDescripcion());

    }


    @Override
    public int getItemCount() {
        return administrarJuegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private AppCompatImageView imgAdministrar;
        private TextView txtTitulo;
        private AppCompatRatingBar rbClasificacion;
        private  TextView txtDescripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAdministrar= itemView.findViewById(R.id.imgAdministrar);
            txtTitulo = itemView.findViewById(R.id.txtTituloAd);
            rbClasificacion = itemView.findViewById(R.id.rbClasificacion);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            this.view= itemView;
        }
    }
}
