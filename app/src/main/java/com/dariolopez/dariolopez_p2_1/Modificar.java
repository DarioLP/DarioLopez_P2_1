package com.dariolopez.dariolopez_p2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Modificar extends AppCompatActivity {

    TextView mtvNombreProducto,mtvMostrarCodigo;
    Spinner spinnerProductos;
    EditText etPrice,etDescripcion;

    DatabaseReference mDatabase;
    String mProductoSeleccionado ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

    mtvNombreProducto = findViewById(R.id.mtvNombreProducto);
    spinnerProductos = findViewById(R.id.spinnerProductos);
    mtvMostrarCodigo = findViewById(R.id.mtvMostrarCodigo);
    mDatabase = FirebaseDatabase.getInstance().getReference();
    etPrice = findViewById(R.id.etPrice);
    etDescripcion = findViewById(R.id.etDescription);

    }


    @Override
    protected void onResume() {
        super.onResume();
        cargarProducto();
    }

    public void cargarProducto(){



        final List<modProducto> arrProductos = new ArrayList<>();
        mDatabase.child("productos/productos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren() ){
                        String codigo = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        arrProductos.add(new modProducto(codigo,nombre));
                    }

                    ArrayAdapter<modProducto> arrayAdapter = new ArrayAdapter<>(Modificar.this, android.R.layout.simple_dropdown_item_1line,arrProductos);
                    spinnerProductos.setAdapter(arrayAdapter);

                    spinnerProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            mProductoSeleccionado = adapterView.getItemAtPosition(i).toString();
                            //mtvCodigoProducto.setText(mProductoSeleccionado);

                            String Nombrecodigo = arrProductos.get(i).getCodigo();

                            //String codigo = mtvCodigoProducto.child("codigo").getValue().toString();
                            mtvNombreProducto.setText("" + Nombrecodigo);
                            llamadoDeProducto(Nombrecodigo);
                        }

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



    public void RegistrarProducto(View view){
        String aCodigo = mtvMostrarCodigo.getText().toString();
        String aNombre = mtvNombreProducto.getText().toString();
        String aPrecio = etPrice.getText().toString();
        String aDescripcion = etDescripcion.getText().toString();

        if(aCodigo.isEmpty()||aNombre.isEmpty()||aPrecio.isEmpty()||aDescripcion.isEmpty()){
            Toast.makeText(this, "Rellene todos los campos...", Toast.LENGTH_SHORT).show();
        }else{

            AlertDialog.Builder exit = new AlertDialog.Builder(Modificar.this);
            exit.setMessage("Desea modificar la información de este producto?")
                    .setCancelable(false)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //String id = productos.push().getKey();
                            Productos producto = new Productos(/*id,*/aCodigo,aNombre,aPrecio,aDescripcion);
                            mDatabase.child("productos/productos").child(mtvNombreProducto.getText().toString()/*id*//*aCodigo*/).setValue(producto);
                            Toast.makeText(Modificar.this, "Modificando...", Toast.LENGTH_SHORT).show();



                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog titulo = exit.create();
            titulo.setTitle("Modificar Producto");
            titulo.show();




        }

    }


    public void llamadoDeProducto(String NombreCodigo){

        mDatabase.child("productos/productos/" + NombreCodigo/*"productos/Mouse gamer"*//*+audioText*/).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String codigo = snapshot.child("codigo").getValue().toString();
                    mtvMostrarCodigo.setText("" + codigo);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Modificar.this, "Error: " + error, Toast.LENGTH_SHORT).show();
               // desLinearActTextView();
            }
        });
    }

}