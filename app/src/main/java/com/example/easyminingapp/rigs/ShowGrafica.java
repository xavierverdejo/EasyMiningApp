package com.example.easyminingapp.rigs;

import androidx.appcompat.app.AppCompatActivity;
import com.example.easyminingapp.R;
import com.example.easyminingapp.db.DBInterface;
import com.example.easyminingapp.objects.Rig_Grafica;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowGrafica extends AppCompatActivity {
    Rig_Grafica relacio;
    int click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grafica);

        Bundle extras = getIntent().getExtras();

        DBInterface db = new DBInterface(this);
        db.obre();
        long graficaId = extras.getLong("id");
        relacio = db.getRelacioById(graficaId);
        db.tanca();

        TextView name = findViewById(R.id.nameGrafica);
        name.setText(""+relacio.getGrafica().getNom());

        TextView hashrate = findViewById(R.id.hashRateGrafica);
        hashrate.setText(relacio.getGrafica().getHashrate()+"MH/s");

        ImageView grafica = findViewById(R.id.imatgeGrafica);
        grafica.setImageBitmap(BitmapFactory.decodeByteArray(relacio.getGrafica().getImagte(), 0, relacio.getGrafica().getImagte().length));
        click = 0;
    }

    public void changeHashrate(View v){
        EditText hash = findViewById(R.id.editTextHashrateGrafica);
        if(click == 0) {

            hash.setVisibility(View.VISIBLE);
            Button ok = findViewById(R.id.changeHashrateButton);
            ok.setText("SAVE");
            click = 1;
        }
        else
        {
            if(hash.getText().toString() != "") {
                // TODO: save
                DBInterface db = new DBInterface(this);
                db.obre();
                db.updateHashrate(relacio.getId_relacio(), Float.parseFloat(hash.getText().toString()));
                db.tanca();
                finish();
            }
            else
            {
                //TODO: toast
            }
        }
    }
    public void deleteRelacio(View v){
        DBInterface db = new DBInterface(this);
        db.obre();
        db.deleteGraficaByRig(relacio.getId_relacio());
        db.tanca();
        finish();
    }
}