package com.example.sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;
//Geriye dogru CountDownTimer ile sayma işlemi yapan program.

//ContDownTimer kullanıcının belli süre içerisinde bir eylemi yapmasını istiyorsak kullanılır.
public class MainActivity extends AppCompatActivity {
    TextView say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        say=findViewById(R.id.textView);

        // App daha açıldığı anda on create altında bu şekilde kullanılabilir (new CountDownTimer diyerek),
        // nesne oluşturarakta kullanılabilir.
        //milisaniye olarak kaç saniye sayacagını , nasıl bir aralıkta sayacagını belirtiriz
        new CountDownTimer(1000, 100) {
            @Override
            public void onTick(long l) {
                // sayarken ne yapacagını yazdıgımız fonksiyon
                say.setText("Sayaç: "+l/1000);
            }

            @Override
            public void onFinish() {
                //bitince ne yapacegını yazdıgımız fonksiyon
                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG);
                say.setText("Tamamlandı");
            }
        }.start();//coutDownTimer ın parantezine yazılır
    }
}