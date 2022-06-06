package com.dariolopez.dariolopez_p2_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class Agregar extends AppCompatActivity {


    //inicio firebase
    private DatabaseReference productos;

    //fin firebase

    //inicio storage
    private static final int File = 1;
    DatabaseReference myRef;

    @SuppressLint("NonConstantResourceId")
    ImageView mUploadImageView;

    //fin storage


    EditText codigo, nombre, precio, descripcion;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);


        //inicio firebase

        productos = FirebaseDatabase.getInstance().getReference("productos");

        //fin firebase

        //inicio storage
        mUploadImageView = findViewById(R.id.ivUpload);
/*
        if(codigo.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()||precio.getText().toString().isEmpty()||descripcion.getText().toString().isEmpty()) {
            Toast.makeText(this,"Rellene todos los campos...",Toast.LENGTH_SHORT).show();
        }else{
            mUploadImageView.setOnClickListener(v -> fileUpload());
        }
*/
        //fin storage

        codigo = findViewById(R.id.etCode);
        nombre = findViewById(R.id.etName);
        precio = findViewById(R.id.etPrice);
        descripcion = findViewById(R.id.etDescription);
        btnAgregar = findViewById(R.id.btnAgregar);



    }


    //inicio storage

    public void fileUpload(){
        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,File);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
try {


        if(requestCode == File){
            if(resultCode == RESULT_OK){
                Uri FileUri = data.getData();
                StorageReference Folder = FirebaseStorage.getInstance().getReference("productos");
                final StorageReference file_name = Folder.child(codigo.getText().toString()/*"file"+FileUri.getLastPathSegment()*/);

                file_name .putFile(FileUri).addOnSuccessListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {

                    HashMap<String,String>hashMap = new HashMap<>();
                    hashMap.put("link",String.valueOf(uri));
                    myRef.setValue(hashMap);
                    Log.d("Mensaje", "Se subió correctamente");
                    Toast.makeText(this,"se subio correctamente",Toast.LENGTH_SHORT).show();

                }));


            }
        }



}catch (Exception e){
    Toast.makeText(this,"Error: "+ e,Toast.LENGTH_SHORT).show();
}








    }










    //fin storage


    public void RegistrarProducto(View view){
        String aCodigo = codigo.getText().toString();
        String aNombre = nombre.getText().toString();
        String aPrecio = precio.getText().toString();
        String aDescripcion = descripcion.getText().toString();

        if(aCodigo.isEmpty()||aNombre.isEmpty()||aPrecio.isEmpty()||aDescripcion.isEmpty()){
            Toast.makeText(this, "Rellene todos los campos...", Toast.LENGTH_SHORT).show();
        }else{
            //String id = productos.push().getKey();
            Productos producto = new Productos(/*id,*/aCodigo,aNombre,aPrecio,aDescripcion);
            productos.child("productos").child(nombre.getText().toString()/*id*//*aCodigo*/).setValue(producto);
            Toast.makeText(this, "Registrado...", Toast.LENGTH_SHORT).show();
            try {
                fileUpload();
            }catch (Exception e){
                Toast.makeText(this,"Error: "+ e,Toast.LENGTH_SHORT).show();
            }

        }

    }





}