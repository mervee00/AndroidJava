package com.example.sharedpraferances;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//SharedPreference ufak çaplı bilgileri tutmak saklamak için kullanılan bir yöntemdir
// Uyarı mesajlarından olan AlertDialog ve Toast Messagelarda kullanıldı
public class MainActivity extends AppCompatActivity {

    TextView txtText;
    EditText txtName;
    String userName,storedName,storedData;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtText=(TextView) findViewById(R.id.textView);
        txtName= (EditText) findViewById(R.id.editTextTextPersonName);
        //shared preferance hangi paketten ve hangi modda erişileceğini belirledik.
        //Mode_private-sadece benim cihazımda bilgiye erişebilmemi sağlar.
        sharedPreferences=this.getSharedPreferences("com.example.sharedpraferances", Context.MODE_PRIVATE);

        //storedName varsa getirecek yoksa bosluk oluyor
        storedName=sharedPreferences.getString("storedName","");

        //ismi ekrana yazdırmak için
        if(storedName==""){
            txtText.setText("Hoşgeldiniz");
        }else{
            txtText.setText("Hoşgeldin "+storedName);
        }
    }
    public void save(View view){
        if(!txtName.getText().toString().matches("")){
            userName=txtName.getText().toString();
            txtText.setText("Hoşgeldin "+ userName);
            //veriyi sharedpreferances a put ile ekleriz ve apply ile kaydederiz
            sharedPreferences.edit().putString("storedName",userName).apply();
        }
    }
    public void delete(View view){
        if(storedData!=""){
            //ALERTDİALOG kullanıcıya islemin kesinliğini sormak için kullanılır
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("Sil");
            alertDialog.setMessage("Emin misiniz?");
            //positive butona tıklayınca ne yapacagını yazarız
            alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //sil
                    sharedPreferences.edit().remove("storedName").apply();
                    txtText.setText("Hoşgeldiniz");
                    //toast mesajı kullanıcıya bilgi vermek için
                    Toast.makeText(MainActivity.this,"Silindi",Toast.LENGTH_LONG).show();
                }
            });
            alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"Silinmedi",Toast.LENGTH_LONG).show();
                }
            });
            alertDialog.show();

        }
    }
}