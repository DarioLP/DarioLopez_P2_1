package com.dariolopez.dariolopez_p2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Eliminar extends AppCompatActivity {


    Button btnEliminar;
    EditText etCodigo;
    Spinner spinnerEliminar;
    private DatabaseReference productos;

    //inicio storage
    //private StorageReference storage;
    //inicio Storage
    private StorageReference mDataStorage;
    ImageView imgProducto;
    ProgressDialog progressDialog;
    LinearLayout linearContainer;
    //fin Stogare
    //fin Storage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        btnEliminar = findViewById(R.id.btnEliminar);
        etCodigo = findViewById(R.id.etCodigo);
   // spinnerEliminar= findViewById(R.id.spinnerEliminar);
        productos = FirebaseDatabase.getInstance().getReference("productos");

        //inicio storage

        FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference deser
        StorageReference storageRef = storage.getReference("productos");

        //fin storage
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = etCodigo.getText().toString();


                if (codigo.isEmpty()) {

                    Toast.makeText(Eliminar.this, "Rellene todos los campos...", Toast.LENGTH_SHORT).show();

                }else{

                    AlertDialog.Builder exit = new AlertDialog.Builder(Eliminar.this);
                    exit.setMessage("Desea eliminar el producto?")
                            .setCancelable(false)
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    productos.child("productos").child(etCodigo.getText().toString()).removeValue();
                                    Toast.makeText(Eliminar.this,"Producto eliminado...",Toast.LENGTH_SHORT).show();
                                    //etCodigo.setText("");





                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = exit.create();
                    titulo.setTitle("Eliminar Producto");
                    titulo.show();

                }

            }
        });




    }

/*

    public void spinnerEliminar(){
        final List<modProducto> arrProductos = new ArrayList<>();
        productos.child("productos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren() ){
                        String codigo = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        arrProductos.add(new modProducto(codigo,nombre));
                    }

                    ArrayAdapter<modProducto> arrayAdapter = new ArrayAdapter<>(Eliminar.this, android.R.layout.simple_dropdown_item_1line,arrProductos);
                    spinnerEliminar.setAdapter(arrayAdapter);

                    spinnerEliminar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String nombreDeProductoSeleccionado = adapterView.getItemAtPosition(i).toString();
                            /*mProductoSeleccionado = adapterView.getItemAtPosition(i).toString();
                            //mtvCodigoProducto.setText(mProductoSeleccionado);

                            String Nombrecodigo = arrProductos.get(i).getCodigo();

                            //String codigo = mtvCodigoProducto.child("codigo").getValue().toString();
                            mtvNombreProducto.setText("" + Nombrecodigo);
                            llamadoDeProducto(Nombrecodigo);*
                            op
                        }z

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void opecacionEliminar(String codigo){

        // String codigo = etCodigo.getText().toString();


        if (codigo.isEmpty()) {

            Toast.makeText(Eliminar.this, "Rellene todos los campos...", Toast.LENGTH_SHORT).show();

        }else{

            AlertDialog.Builder exit = new AlertDialog.Builder(Eliminar.this);
            exit.setMessage("Desea eliminar el producto?")
                    .setCancelable(false)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            productos.child("productos").child(codigo).removeValue();
                            Toast.makeText(Eliminar.this,"Producto eliminado...",Toast.LENGTH_SHORT).show();
                            //etCodigo.setText("");





                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog titulo = exit.create();
            titulo.setTitle("Eliminar Producto");
            titulo.show();

        }

    }
    */

/*
    public void eliminarDatos(View view){
        productos.child("productos").child(etCodigo.getText().toString()).removeValue();
        //productos.child("productos").removeValue();
    }
*/
}