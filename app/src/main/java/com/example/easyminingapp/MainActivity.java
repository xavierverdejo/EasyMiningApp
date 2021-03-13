package com.example.easyminingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.easyminingapp.db.DBInterface;
import com.example.easyminingapp.objects.Grafica;
import com.example.easyminingapp.objects.Rig;
import com.example.easyminingapp.preferences.Preferences;
import com.example.easyminingapp.rigs.RigList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<ProfitObject> profitList = new ArrayList<ProfitObject>();
    public static Preferences preferencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencies = new Preferences(this);
        DBInterface db = new DBInterface(this);

        db.obre();
        // BUIDAM I AFEGIM LES GRAFIQUES
        db.buidaGrafiques();
        Drawable d = getDrawable(R.drawable.rx470);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        Drawable d2 = getDrawable(R.drawable.rx570);
        Bitmap bitmap1 = ((BitmapDrawable)d2).getBitmap();
        Drawable d3 = getDrawable(R.drawable.rx580);
        Bitmap bitmap2 = ((BitmapDrawable)d3).getBitmap();
        db.createGrafica(new Grafica("AMD RX470", (float)28.9, getBitmapAsByteArray(bitmap)));
        db.createGrafica(new Grafica("AMD RX570", (float)31.0, getBitmapAsByteArray(bitmap1)));
        db.createGrafica(new Grafica("AMD RX580", (float)33.6, getBitmapAsByteArray(bitmap2)));
        db.tanca();

        Switch ding = findViewById(R.id.darkSwitch);
        ding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //commit prefs on change
                preferencies.setDarkmode(isChecked);
                checkDark();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        checkDark();
    }

    public void checkDark()
    {
        Switch ding = findViewById(R.id.darkSwitch);
        ding.setChecked(preferencies.getDarkmode());
        LinearLayout fondo = findViewById(R.id.linearFondo);
        if(preferencies.getDarkmode()) {
            fondo.setBackgroundColor(0x292929);
        }
        else {
            fondo.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void openEasyminingWeb(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://easymining.es"));
        startActivity(i);
    }

    public void openCalculator(View view){
        Intent profitCalc = new Intent(this, ProfitCalculator.class);
        startActivity(profitCalc);
    }

    public void electricityCalc(View view){
        Intent ElectricityCalc = new Intent(this, ElectricityCostCalculator.class);
        startActivity(ElectricityCalc);
    }

    public void openList(View view){
        Intent ProfitListAct = new Intent(this, ProfitList.class);
        startActivity(ProfitListAct);
    }

    public void openRigList(View view){
        Intent ProfitListAct = new Intent(this, RigList.class);
        startActivity(ProfitListAct);
    }
}