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
import com.jaeger.fragmentos.databinding.FragmentMisJuegosBinding;
import com.jaeger.fragmentos.gui.components.MisJuegosAdapter;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.MiJuego;

import java.util.ArrayList;
import java.util.List;

public class MisJuegos extends Fragment {

    private FragmentMisJuegosBinding binding;
    private View view;
    private Context context;
    private List<MiJuego> misJuegos = new ArrayList<>();


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
        MainActivity.GLOBALS.put("misJuegosFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMisJuegosBinding.inflate(inflater,container,false);

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
                view.findViewById(R.id.gridMisJuegos),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));

    }

    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridMisJuegos).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        misJuegos.add(new MiJuego(1,"pokemongo","Pokemon Go",4,"Sal de tu casa"));
        misJuegos.add(new MiJuego(2,"dragonballfighterz","DB Fighter Z",4,"El kokun peleas"));
        misJuegos.add(new MiJuego(3,"supermariosunshine","Mario Sunshine",5,"Otro buen Mario"));


        binding.rclvMisJuegos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        layoutManager.isAutoMeasureEnabled();
        binding.rclvMisJuegos.setLayoutManager(layoutManager);
        binding.rclvMisJuegos.setAdapter(new MisJuegosAdapter(misJuegos));
    }
}
