package com.dariolopez.dariolopez_p2_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Eliminar extends AppCompatActivity {


    Button btnEliminar;
    EditText etCodigo;

    private DatabaseReference productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        btnEliminar = findViewById(R.id.btnEliminar);
        etCodigo = findViewById(R.id.etCodigo);

        productos = FirebaseDatabase.getInstance().getReference("productos");

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
    public void eliminarDatos(View view){
        productos.child("productos").child(etCodigo.getText().toString()).removeValue();
        //productos.child("productos").removeValue();
    }
*/
}