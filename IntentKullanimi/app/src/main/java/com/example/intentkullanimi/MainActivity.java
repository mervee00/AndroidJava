package com.example.intentkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//İki activity arasında veri aktarımı ve activityler arası geçiş yapılmıstır.

//Activityler arası geçiş yaparken Intent kullanılır.

public class MainActivity extends AppCompatActivity {
    EditText txtName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName= findViewById(R.id.editTextTextPersonName);

    }
    public void activityGecis(View view){
        name=txtName.getText().toString();
        //yeni ıntent olusturulur hangi activityden hangi activitye geçilecegi belirtilir.
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        //diğer activity açılmadan onde ıntente put yapılarak veri anahtar kelime ile gönderilir.
        intent.putExtra("userName",name);
        //ıntent baslatılır.
        startActivity(intent);


    }
}