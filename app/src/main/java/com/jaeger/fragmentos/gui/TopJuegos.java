package com.jaeger.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.jaeger.fragmentos.R;
import com.jaeger.fragmentos.databinding.FragmentTopJuegosBinding;
import com.jaeger.fragmentos.gui.components.JuegosAdapter;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.Juego;

import java.util.ArrayList;
import java.util.List;


public class TopJuegos extends Fragment {

    private FragmentTopJuegosBinding binding;
    private  View view;
    private Context context;
    private List<Juego> juegos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater, container);
        configToolbar();
        configUI();
        configRecycler();

        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("topJuegosFragment", this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentTopJuegosBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity != null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridTopGames),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.findViewById(R.id.gridTopGames).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        juegos.add(new Juego(1, "halo", "Halo", 5, "Master chief es la onda"));
        juegos.add(new Juego(2, "call", "Call of Duty", 2, "Free fire para fresas"));
        juegos.add(new Juego(3, "mario", "Mario Kart", 5, "Un clásico"));
        juegos.add(new Juego(4, "maincra", "Maincra", 1, "Sin comentarios"));
        juegos.add(new Juego(5, "destiny", "Destiny 2", 5, "El legado de Halo"));
        binding.rclvTopJuegos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvTopJuegos.setLayoutManager(layoutManager);
        binding.rclvTopJuegos.setAdapter(new JuegosAdapter(juegos));
    }
}