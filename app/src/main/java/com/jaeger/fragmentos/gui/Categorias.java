package com.jaeger.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaeger.fragmentos.R;
import com.jaeger.fragmentos.databinding.FragmentCategoriasBinding;
import com.jaeger.fragmentos.gui.components.CategoriasAdapter;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.CategoriaJuego;


import java.util.ArrayList;
import java.util.List;

public class Categorias extends Fragment {

    private FragmentCategoriasBinding binding;
    private View view;
    private Context context;
    private List<CategoriaJuego> categorias = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("categoriasFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentCategoriasBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridCategorias),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridCategorias).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        categorias.add(new CategoriaJuego(1, "iconoaccion", "Acción"));
        categorias.add(new CategoriaJuego(2, "iconoaventura", "Aventura"));
        categorias.add(new CategoriaJuego(3, "iconodeportes", "Deportes"));
        categorias.add(new CategoriaJuego(4, "iconoestrategia", "Estrategia"));
        categorias.add(new CategoriaJuego(5, "iconofamiliar", "Familiar"));
        categorias.add(new CategoriaJuego(6, "iconopeleas", "Peleas"));
        categorias.add(new CategoriaJuego(7, "iconorol", "Rol"));
        categorias.add(new CategoriaJuego(8, "iconorol", "Rol"));
        categorias.add(new CategoriaJuego(9, "iconorol", "Rol"));
        categorias.add(new CategoriaJuego(10, "iconorol", "Rol"));

        binding.rclvCategorias.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        binding.rclvCategorias.setLayoutManager(layoutManager);

        binding.rclvCategorias.setAdapter(new CategoriasAdapter(categorias));
    }
}
