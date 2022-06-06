package com.dariolopez.dariolopez_p2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnManager(View view){

        switch (view.getId()){
            case R.id.btnCatalogo:
                Intent goCatalogo = new Intent(MainActivity.this,Catalogo.class);
                startActivity(goCatalogo);
                break;

            case R.id.btnLogIn:
                Intent goLogIn = new Intent(MainActivity.this,LogIn.class);
                startActivity(goLogIn);

                break;
        }

    }

}