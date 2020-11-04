package com.jaeger.fragmentos.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaeger.fragmentos.R;
import com.jaeger.fragmentos.databinding.FragmentAdministrarBinding;
import com.jaeger.fragmentos.databinding.FragmentMisJuegosBinding;
import com.jaeger.fragmentos.gui.components.AdministrarAdapter;
import com.jaeger.fragmentos.gui.components.MisJuegosAdapter;
import com.jaeger.fragmentos.gui.components.NavigationHost;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;
import com.jaeger.fragmentos.model.AdministrarJuego;
import com.jaeger.fragmentos.model.MiJuego;

import java.util.ArrayList;
import java.util.List;

public class Administrar extends Fragment {

    private FragmentAdministrarBinding binding;

    private View view;
    private Context context;
    private List<AdministrarJuego> administrarJuegos = new ArrayList<>();

    private static  final String PATH_TOP="Administrar";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentAdministrarBinding.inflate(getLayoutInflater());
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();

        binding.fabCartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost)getActivity()).navigateTo(new AddFragment(), true);
            }
        });
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("administrarFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAdministrarBinding.inflate(inflater,container,false);

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
                view.findViewById(R.id.gridAdministrar),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));

    }

    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            view.findViewById(R.id.gridAdministrar).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {
        //administrarJuegos.add(new AdministrarJuego(1,"pokemongo","Pokemon Go",4,"Sal de tu casa"));
        //administrarJuegos.add(new AdministrarJuego(2,"dragonballfighterz","DB Fighter Z",4,"El kokun peleas"));
        //dministrarJuegos.add(new AdministrarJuego(3,"supermariosunshine","Mario Sunshine",5,"Otro buen Mario"));


        binding.rclvAdministrar.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
       // layoutManager.isAutoMeasureEnabled();
        binding.rclvAdministrar.setLayoutManager(layoutManager);
        binding.rclvAdministrar.setAdapter(new AdministrarAdapter(administrarJuegos));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_TOP);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AdministrarJuego administrarJuego = dataSnapshot.getValue(AdministrarJuego.class);
                if(!administrarJuegos.contains(administrarJuego)){
                    administrarJuegos.add(administrarJuego);
                }
                binding.rclvAdministrar.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AdministrarJuego administrarJuego = dataSnapshot.getValue(AdministrarJuego.class);
                administrarJuegos.set(administrarJuegos.indexOf(administrarJuego),administrarJuego);
                binding.rclvAdministrar.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                AdministrarJuego administrarJuego = dataSnapshot.getValue(AdministrarJuego.class);

                administrarJuegos.remove(administrarJuego);
                binding.rclvAdministrar.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
