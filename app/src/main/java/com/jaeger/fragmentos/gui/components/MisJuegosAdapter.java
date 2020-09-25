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
import com.jaeger.fragmentos.model.MiJuego;

import java.util.List;

public class MisJuegosAdapter extends RecyclerView.Adapter<MisJuegosAdapter.ViewHolder>{

    private List<MiJuego> misJuegos;
    private Context context;

    public MisJuegosAdapter(List<MiJuego> misJuegos) {
        this.misJuegos = misJuegos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_mis_juegos,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MiJuego miJuego = misJuegos.get(position);
        String imgUri = "@drawable/"+ miJuego.getImagen();
        int imgResource = context.getResources().getIdentifier
                (imgUri,null,context.getPackageName());
        holder.imgMisjuegos.setImageResource(imgResource);
        holder.txtTitulo.setText(miJuego.getTitulo());
        holder.rbClasificacion.setRating(miJuego.getClasificacion());
        holder.txtDescripcion.setText(miJuego.getDescripcion());


    }

    @Override
    public int getItemCount() {
        return misJuegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private AppCompatImageView imgMisjuegos;
        private TextView txtTitulo;
        private AppCompatRatingBar rbClasificacion;
        private  TextView txtDescripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMisjuegos= itemView.findViewById(R.id.imgMiJuego);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            rbClasificacion = itemView.findViewById(R.id.rbClasificacion);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            this.view= itemView;
        }
    }
}
