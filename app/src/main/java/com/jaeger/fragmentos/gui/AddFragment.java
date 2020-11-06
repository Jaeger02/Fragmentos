package com.jaeger.fragmentos.gui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaeger.fragmentos.R;
import com.jaeger.fragmentos.databinding.FragmentAddBinding;
import com.jaeger.fragmentos.databinding.FragmentMisJuegosBinding;
import com.jaeger.fragmentos.gui.components.NavigationHost;
import com.jaeger.fragmentos.gui.components.NavigationIconClickListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class AddFragment extends Fragment {
    private static final int RC_GALLERY = 21;
    private static final int RP_STORAGE = 122;
    private static final String IMAGE_DIRECTORY = "/MyPhotoApp";
    private static final String MY_PHOTO = "my_photo";
    private static String PATH_PROFILE = "profile";
    private static String PATH_PHOTO_URI = "photoUri";

    private TextView lblMessage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    private DatabaseReference newJuego;
    private String currentPhotoPath;
    private Uri photoSelectedUri;
    ImageView foto_gallery;
    private ConstraintLayout ctn;

    private FragmentAddBinding binding;
    private  View view;
    private Context context;

    private ImageView img;
    private EditText edit1;
    private EditText edit2;
    private Button enviar;
    private Button cancelar;
    private AppCompatRatingBar clasi;
    public int cont=1;
    private String my_photo="";
    private FirebaseStorage storage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add,container,false);

        img = (ImageView) root.findViewById(R.id.imgJuegoEdit);
        edit1= (EditText) root.findViewById(R.id.txtTituloAdd);
        edit2= (EditText) root.findViewById(R.id.txtDescripcion);
        enviar = (Button) root.findViewById(R.id.btnGuardar);
        cancelar = (Button) root.findViewById(R.id.btnCancelar);
        clasi = (AppCompatRatingBar)root.findViewById(R.id.rbClasificacion);
       //ctn = view.findViewById(R.id.container);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromGallery();

            }
        });


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storage=FirebaseStorage.getInstance();
                StorageReference stotageRef = storage.getReference();
                //Uri file = Uri.fromFile(new File(photoSelectedUri.getPath()));
                StorageReference ref = stotageRef.child("images/" + UUID.randomUUID().toString()); // Referencia a donde queres subir el archivo
                UploadTask uploadTask = ref.putFile(photoSelectedUri);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });


                Map<String, Object> userData = new HashMap<String, Object>();
                databaseReference = FirebaseDatabase.getInstance().getReference("Administrar");
                storageReference = FirebaseStorage.getInstance().getReference();
                newJuego = databaseReference.push();
                String camp1 = edit1.getText().toString();
                String camp2 = edit2.getText().toString();
                float camp3 = clasi.getRating();
                String camp4=ref.getDownloadUrl().toString();


                //databaseReference.child("Titulo").setValue(camp1);
                //atabaseReference.child("Descripcion").setValue(camp2);
                //databaseReference.child("Clasificacion").setValue(camp3);
                cont = cont+1;
                //my_photo=newJuego.getKey();
                //UploadImage();

                userData.put("titulo", camp1);
                userData.put("descripcion",camp2);
                userData.put("clasificacion",camp3);
                userData.put("imagen",camp4);
                databaseReference.child("Administrar").child("titulo");
                databaseReference.push().setValue(userData);


                ((NavigationHost)getActivity()).navigateTo(new Administrar(), true);
            }

        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost)getActivity()).navigateTo(new Administrar(), true);
            }
        });


        return root;
    }

    private void fromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RC_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_GALLERY:
                    if (data != null) {
                        photoSelectedUri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), photoSelectedUri);
                            img.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }

    }

    private void UploadImage() {
        if (photoSelectedUri == null) {
            Snackbar.make(ctn, R.string.main_message_uri_null, BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }
        StorageReference profileReference = storageReference.child(PATH_PROFILE);
        StorageReference photoReference = profileReference.child(my_photo);
        StorageReference urlReference = photoReference.child("imagen");
        urlReference.putFile(photoSelectedUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Snackbar.make(ctn, R.string.main_message_upload_success, BaseTransientBottomBar.LENGTH_LONG).show();
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        savePhotoUri(task.getResult());
                    }
                });

                //binding.btnDelete.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               // Snackbar.make(ctn, R.string.main_message_upload_error, BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }

    private void savePhotoUri(Uri downloadUri) {
        newJuego.child("imagen").setValue(downloadUri.toString());
    }

    private void configFirebase() {
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child(PATH_PROFILE).child(PATH_PHOTO_URI);
    }

    private void configToolbar() {


    }

    private void configUI() {

    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAddBinding.inflate(inflater,container,false);

        view = binding.getRoot();
        context = container.getContext();
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("addFragment",this);

    }



    private void configPhotoProfile() {
        storageReference.child(PATH_PROFILE).child(MY_PHOTO).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        RequestOptions options = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .centerCrop();
                        Glide.with(AddFragment.this)
                                .load(uri)
                                .placeholder(R.drawable.ic_placeholder)
                                .error(R.drawable.ic_error)
                                .apply(options)
                                .into(binding.imgJuegoEdit);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Snackbar.make(null, R.string.main_message_error_notfound, BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                });
    }


}