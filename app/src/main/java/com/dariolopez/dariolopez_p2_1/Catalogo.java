package com.dariolopez.dariolopez_p2_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import java.util.Locale;

public class Catalogo extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT=100;
    private TextView mEntradaVoz,mEntradaVoz1;
    private ImageButton mButtonSpeesh;


    //RecyclerView rvProductList;

    //inicio realtime
    private TextView tvCodigo,tvNombre,tvPrecio,tvDescripcion;
    private DatabaseReference mDatabase;
    //fin realtime
    //inicio Storage
    private StorageReference mDataStorage;
    ImageView imgProducto;
    ProgressDialog progressDialog;
    LinearLayout linearContainer;
    //fin Stogare

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        //rvProductList = findViewById(R.id.rvProductList);
        //rvProductList.setLayoutManager(new LinearLayoutManager(this));

        mEntradaVoz=findViewById(R.id.tvSpeechText1);
        mButtonSpeesh=findViewById(R.id.btnSpeech);
        mEntradaVoz1=findViewById(R.id.tvSpeechText1);

        mButtonSpeesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarEntradaVoz();
            }
        });


        tvCodigo = findViewById(R.id.stvCodigo);
        tvNombre = findViewById(R.id.stvNombre);
        tvPrecio = findViewById(R.id.stvPrecio);
        tvDescripcion = findViewById(R.id.stvDescripcion);
        //inicio realtime

        tvCodigo=findViewById(R.id.stvCodigo);
        mDatabase = FirebaseDatabase.getInstance().getReference("productos");
/*
        //inicio Storage
        //imgProducto = findViewById(R.id.imageView);

        String imgCode = "productos/"+tvCodigo.getText().toString()+".jpeg";
Toast.makeText(this,""+imgCode,Toast.LENGTH_SHORT).show();
        mDataStorage = FirebaseStorage.getInstance().getReference("productos/0039")/*.child("gs://dariolopez-p2-1.appspot.com/productos/")*;


        progressDialog = new ProgressDialog(this);
        //fin Storage

        String audioText = mEntradaVoz.getText().toString();
        Toast.makeText(this,""+audioText,Toast.LENGTH_SHORT).show();


Toast.makeText(this,""+audioText,Toast.LENGTH_SHORT).show();

            mDatabase.child("productos/Mouse gamer"/*+audioText*).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String codigo = snapshot.child("codigo").getValue().toString();
                    tvCodigo.setText("Código: "+codigo);

                    String nombre = snapshot.child("nombre").getValue().toString();
                    tvNombre.setText(nombre);

                    String precio = snapshot.child("precio").getValue().toString();
                    tvPrecio.setText("$/."+precio);

                    String descripcion = snapshot.child("descripcion").getValue().toString();
                    tvDescripcion.setText("Descripción: "+descripcion);

                    mostrarImagen();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
Toast.makeText(Catalogo.this,"Error: "+error,Toast.LENGTH_SHORT).show();
            }
        });





        //fin realtime
*/

    }


    @Override
    protected void onResume() {
        super.onResume();



        //inicio Storage
        //imgProducto = findViewById(R.id.imageView);



        progressDialog = new ProgressDialog(this);
        //fin Storage

        String audioText = mEntradaVoz.getText().toString();
        linearContainer = findViewById(R.id.linearContainerProducto);
        if (audioText.isEmpty()){
            Toast.makeText(this,"busque algun producto",Toast.LENGTH_SHORT).show();

            linearContainer.setEnabled(false);

        }else {

linearContainer.setEnabled(true);

            Toast.makeText(this, "" + audioText, Toast.LENGTH_SHORT).show();

            mDatabase.child("productos/" + audioText/*"productos/Mouse gamer"*//*+audioText*/).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        String codigo = snapshot.child("codigo").getValue().toString();
                        tvCodigo.setText("Código: " + codigo);

                        String nombre = snapshot.child("nombre").getValue().toString();
                        tvNombre.setText(nombre);

                        String precio = snapshot.child("precio").getValue().toString();
                        tvPrecio.setText("$/." + precio);

                        String descripcion = snapshot.child("descripcion").getValue().toString();
                        tvDescripcion.setText("" + descripcion);


                        mDataStorage = FirebaseStorage.getInstance().getReference("productos/" + codigo)/*.child("gs://dariolopez-p2-1.appspot.com/productos/")*/;

//inicio storage
                        try {
                            final File localFile = File.createTempFile(/*"0039"*/"" + codigo, "png"/*"jpeg"*/);
                            mDataStorage.getFile(localFile)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(Catalogo.this, "Picture Retrieved", Toast.LENGTH_SHORT).show();
                                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            ((ImageView) findViewById(R.id.imgProduct)).setImageBitmap(bitmap);

                                            // imgProducto.setImageBitmap(bitmap);
                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Catalogo.this, "Error Ocurred" + e, Toast.LENGTH_SHORT).show();
                                }

                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//fin Storage
                        // mostrarImagen();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Catalogo.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            });


            //fin realtime
        }



    }
/*
    //inicio Storage
private void mostrarImagen(){
    try {
        final File localFile = File.createTempFile("0039","jpeg");
        mDataStorage.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>(){
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot){
                        Toast.makeText(Catalogo.this,"Picture Retrieved",Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView)findViewById(R.id.imgProduct)).setImageBitmap(bitmap);

                        // imgProducto.setImageBitmap(bitmap);
                    }

                }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(Catalogo.this,"Error Ocurred"+e,Toast.LENGTH_SHORT).show();
            }

        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
//fin Storage
*/
    private void iniciarEntradaVoz(){
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Dígame cual producto desea...");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        }catch (ActivityNotFoundException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null!=data){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mEntradaVoz.setText(result.get(0));
                    mEntradaVoz1.setText(result.get(0));
                } break;
            }

        }

    }


}