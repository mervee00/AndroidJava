package com.example.intentkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView nameYaz;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameYaz=findViewById(R.id.textView);

        //yeni intent olusturup gelen ıntent alınır
        Intent intent=getIntent();
        //bir degişkene anahtar kelime ile veri alınır.
        name=intent.getStringExtra("userName");
        //text e isim yazılır.
        nameYaz.setText(name);
    }
}