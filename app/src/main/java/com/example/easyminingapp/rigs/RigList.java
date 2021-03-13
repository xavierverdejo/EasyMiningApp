package com.example.easyminingapp.rigs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyminingapp.MainActivity;
import com.example.easyminingapp.R;
import com.example.easyminingapp.db.DBInterface;
import com.example.easyminingapp.objects.Rig;

public class RigList extends AppCompatActivity {
    RigAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rig_list);
        Log.d("RigList", "onCreate");
    }

    public void checkDark()
    {
        LinearLayout fondo = findViewById(R.id.linearRigList);
        if(MainActivity.preferencies.getDarkmode()) {
            fondo.setBackgroundColor(0x292929);
        }
        else {
            fondo.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("RigList", "onResume");
        updateList();
        checkDark();
    }

    void updateList(){
        DBInterface db = new DBInterface(this);
        db.obre();

        RecyclerView recyclerView = findViewById(R.id.recyclerRigs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RigAdapter(this, db.getAllRigs());
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        db.tanca();
    }
    public void addNewRig(View view){
        finish();
        Intent newRig = new Intent(this, NewRig.class);
        startActivity(newRig);
    }
}