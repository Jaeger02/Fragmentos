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
import com.jaeger.fragmentos.databinding.FragmentTopRankedBinding;
import com.jaeger.fragmentos.gui.components.JuegosAdapter;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.Juego;

import java.util.ArrayList;
import java.util.List;

public class TopRanked extends Fragment {

    private FragmentTopRankedBinding binding;
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
        MainActivity.GLOBALS.put("topRankedFragment", this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentTopRankedBinding.inflate(inflater,container,false);
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
                view.findViewById(R.id.gridTopRanked),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            view.findViewById(R.id.gridTopRanked).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        juegos.add(new Juego(1, "iconopeleas", "Smash", 5, "El Mario peleas"));
        juegos.add(new Juego(2, "amongus", "Among Us", 5, "Rompe amistades"));
        juegos.add(new Juego(3, "halo", "Halo", 5, "Master Chief es la onda!"));
        juegos.add(new Juego(4, "metalslug", "Metal Slug", 1, "Liberen al prisionero"));
        juegos.add(new Juego(5, "residentevildos", "Resident Evil 2", 5, "Un buen remake"));
        binding.rclvTopRanked.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        binding.rclvTopRanked.setLayoutManager(layoutManager);
        binding.rclvTopRanked.setAdapter(new JuegosAdapter(juegos));
    }
}
