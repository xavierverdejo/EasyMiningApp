package com.example.easyminingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfitCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_calculator);
    }
    public void openResult(View view){
        EditText mhs = findViewById(R.id.hashEditText);
        EditText watts = findViewById(R.id.powerEditText);
        EditText cost = findViewById(R.id.costPerKwEditText);
        EditText poolFee = findViewById(R.id.poolFeeEditText);
        EditText rewardPerMhs = findViewById(R.id.coinPerMhEditText);

        if(mhs.getText().toString().equals("") || watts.getText().toString().equals("") || cost.getText().toString().equals("") || poolFee.getText().toString().equals("") || rewardPerMhs.getText().toString().equals(""))
        {
            Toast.makeText(this, "There can't be empty fields", Toast.LENGTH_LONG).show();
            return;
        }

        Intent profitCalc = new Intent(this, ProfitResult.class);
        profitCalc.putExtra("mhs", mhs.getText().toString());
        profitCalc.putExtra("watts", watts.getText().toString());
        profitCalc.putExtra("cost", cost.getText().toString());
        profitCalc.putExtra("poolFee", poolFee.getText().toString());
        profitCalc.putExtra("rewardPerMhs", rewardPerMhs.getText().toString());
        startActivity(profitCalc);
    }
}