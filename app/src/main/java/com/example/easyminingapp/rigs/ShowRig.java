package com.example.easyminingapp.rigs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyminingapp.R;
import com.example.easyminingapp.db.DBInterface;
import com.example.easyminingapp.objects.Grafica;
import com.example.easyminingapp.objects.Rig;
import com.example.easyminingapp.objects.Rig_Grafica;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowRig extends AppCompatActivity {

    private long rigId;
    private Rig thisRig;
    private List<Grafica> llistaGrafiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        DBInterface db = new DBInterface(this);
        db.obre();
        rigId = extras.getLong("id");
        thisRig = db.getRigById(rigId);
        db.tanca();

        setContentView(R.layout.activity_show_rig);

    }

    @Override
    public void onResume() {
        super.onResume();
        setRig();
        ompleSpinner();
    }

    private void ompleSpinner() {
        DBInterface db = new DBInterface(this);
        db.obre();
        llistaGrafiques = db.getAllGrafiques();
        db.tanca();
        // Spinner Drop down elements
        List<String> stringGrafiques = new ArrayList<String>();
        stringGrafiques.add("Add a GPU...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            for (int i = 0; i < llistaGrafiques.size(); i++) {
                stringGrafiques.add(llistaGrafiques.get(i).getNom());
            }
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.text_spinner, stringGrafiques);

        Spinner spinner = findViewById(R.id.Grafiques);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);
    }

    public void clickAddGrafica(View v) {
        DBInterface db = new DBInterface(this);
        Spinner spinner = findViewById(R.id.Grafiques);
        Grafica graficaSeleccionada = llistaGrafiques.get(spinner.getSelectedItemPosition()-1);
        db.obre();
        //db.vinculaUsuariAula(peli);
        db.relacionaGraficaRig(rigId, graficaSeleccionada);
        db.tanca();
        Toast.makeText(this, "GPU added", Toast.LENGTH_SHORT).show();
        setRig();
        //ompleSpinner();
    }

    protected void setRig() {
        DBInterface db = new DBInterface(this);
        db.obre();
        TextView name = findViewById(R.id.textViewNameRig);
        name.setText(thisRig.getNom());
        TextView desc = findViewById(R.id.textViewDescRig);
        desc.setText(thisRig.getDescripcio());

        RecyclerView recyclerView = findViewById(R.id.llistaGrafiquesRig);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GraficaAdapter adapter = new GraficaAdapter(this, db.getGrafiquesByRigId(rigId));
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,R.id.textView,);
        db.tanca();
    }

    public void deleteThisRig(View view) {
        DBInterface db = new DBInterface(this);
        db.obre();
        db.deleteRig(rigId);
        db.tanca();
        finish();
    }
}