package com.example.easyminingapp.rigs;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyminingapp.MainActivity;
import com.example.easyminingapp.R;
import com.example.easyminingapp.db.DBInterface;
import com.example.easyminingapp.objects.Rig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewRig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rig);
    }

    public void saveRig(View view)
    {
        EditText nom = findViewById(R.id.newRigName);
        EditText desc = findViewById(R.id.newRigDesc);

        DBInterface db = new DBInterface(this);
        db.obre();
        db.createRig(new Rig(nom.getText().toString(), desc.getText().toString()));
        db.tanca();

        finish();

        Intent rigList = new Intent(this, RigList.class);
        startActivity(rigList);
    }
}