package com.dariolopez.dariolopez_p2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }

    public void btnManager(View view){

        switch (view.getId()){
            case R.id.btnAgregar:
                Intent goAgregar = new Intent(MenuAdmin.this,Agregar.class);
                startActivity(goAgregar);
                break;

            case R.id.btnModificar:
                Intent goModificar = new Intent(MenuAdmin.this,Modificar.class);
                startActivity(goModificar);
                break;

            case R.id.btnEliminar:
                Intent goEliminar = new Intent(MenuAdmin.this,Eliminar.class);
                startActivity(goEliminar);
                break;
        }

    }
}