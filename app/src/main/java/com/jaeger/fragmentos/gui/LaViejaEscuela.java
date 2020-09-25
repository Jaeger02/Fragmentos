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
import com.jaeger.fragmentos.databinding.FragmentLaViejaEscuelaBinding;
import com.jaeger.fragmentos.gui.components.JuegosAdapter;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.Juego;

import java.util.ArrayList;
import java.util.List;

public class LaViejaEscuela extends Fragment {

    private FragmentLaViejaEscuelaBinding binding;
    private View view;
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
        MainActivity.GLOBALS.put("laViejaEscuelaFragment", this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLaViejaEscuelaBinding.inflate(inflater,container,false);
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
                view.findViewById(R.id.gridLaViejaEscuela),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.findViewById(R.id.gridLaViejaEscuela).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        juegos.add(new Juego(1, "thekingoffighters", "KOF 2002", 4, "Street fighter chido"));
        juegos.add(new Juego(2, "supermarioseiscuatro", "Super Mario 64", 5, "Un clásico"));
        juegos.add(new Juego(3, "contra", "Contra", 4, "Rambo vs aliens"));
        juegos.add(new Juego(4, "silenthill", "Silent Hill", 4, "Un pueblo peculiar"));
        juegos.add(new Juego(5, "metalslug", "Metal Slug", 5, "Liberen al prisionero"));
        binding.rclvLaViejaEscuela.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvLaViejaEscuela.setLayoutManager(layoutManager);
        binding.rclvLaViejaEscuela.setAdapter(new JuegosAdapter(juegos));
    }
}
