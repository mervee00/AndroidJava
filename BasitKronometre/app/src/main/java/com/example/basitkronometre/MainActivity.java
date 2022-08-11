package com.example.basitkronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Runabble kullanarak basit kronometre yapımı

//Runaneble bir işlemi birden fazla kez belirttiğimiz priyotta ve belirttigimiz sürede yapmamızı saglar.
//Arka planda işlemi yapabiliriz kullanıcı arayüzünü meşgul etmeyiz.
//Handler runnable ı ele aldıgımız arayüz
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button basla;
    int number;
    Runnable runnable;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        basla=(Button) findViewById(R.id.button);
        number=0;
    }
    public void start(View view){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                //run methodunda yaptığımız herşey belirlediğimiz aralıklarla olur.
                textView.setText("Zaman: "+number);
                number++;
                textView.setText("Zaman: "+number);
                //handler kullanarak kac saniyede runnable çalıştıracagını söyleriz.
                handler.postDelayed(runnable,1000);
            }
        };
        //runnable baslatırız.
        handler.post(runnable);
        //saymaya başalyınca başla butonunu pasif hale getirerek tıklanmasını engelleriz.
        basla.setEnabled(false);
    }
    public void stop(View view){
        //buton aktif
        basla.setEnabled(true);
        //handler tekrar cagırıp runable durdururuz
        handler.removeCallbacks(runnable);
        number=0;
        textView.setText("Zaman: "+number);
    }
}