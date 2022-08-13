package com.example.bucksbunnyyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtZaman;
    TextView txtSkor;
    int skor;
    ImageView imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    //Bir resim arrayi olusturduk
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtZaman=findViewById(R.id.txtZaman);
        txtSkor=findViewById(R.id.txtSkor);

        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);

        //tanımlanan imageleri diziye ekledik
        imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        imagesSakla();

        skor=0;
       //Oyunda zamanı CountDownTimer ile takip ettik
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                //Zaman bitene kadar burdaki işlemi yaptı
                txtZaman.setText("Zaman: "+l/1000);
            }

            @Override
            public void onFinish() {
                //Zaman bittiğinde bu işlemleri yaptı
                txtZaman.setText("Zaman Bitti");
                handler.removeCallbacks(runnable);
                //Oyun bitince resimlere tıklanmaması için görünmez yaptık
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                //AlertDialog ile tekrar oyun oynayıp oynamayacagı kullanıcıya soruldu
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar Oyna");
                alert.setMessage("Tekrar Oynamak ister misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Main activitye tekrar bitirip dönmesini sagladık
                        //Intent olusturup ıntentı getirdik
                        Intent intent=getIntent();
                        //önceki intenti kapattık
                        finish();
                        //intenti calıstırdık tekrar
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Tekrar oynamak istemezse oyunu bitirdik
                        Toast.makeText(MainActivity.this,"Oyun Bitti",Toast.LENGTH_LONG).show();
                    }
                });
                //Mesajı ekranda gösterdik
                alert.show();
            }
        }.start();
    }

    public void skoruArttir(View view){
        //Image lere onclıck özelliği ile bu metodu ekledik
        //her resim görünür oldugunda tıklarsa skor artar ekrana yazar
        skor++;
        txtSkor.setText("Skor: "+skor);
    }

    public void imagesSakla(){
        //Resimlerin görünmez olup random yerlerde görünür olmalarını sagladıgımız fonksıyon
        //belli aralıklarla resimler görünüp kaybolacagı için runnable kullandık
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                //dizideki tüm resimleri görünmez yapan for döngüsü
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                //Random rastgele 0-9 arasında sayılar üretir.
                Random random=new Random();
                int j=random.nextInt(9);
                //üretilen sayıdaki image görünür yapılır
                imageArray[j].setVisibility(View.VISIBLE);

                //0.5 saniyede bir yeni bir image görünür olur
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}