package com.example.easyminingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShowAllProfitObject extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_calculator);

        Bundle extras = getIntent().getExtras();
        ProfitObject objecte = extras.getParcelable("object");

        EditText mhs = findViewById(R.id.hashEditText);
        mhs.setText(objecte.getMhs()+"");
        mhs.setEnabled(false);
        EditText watts = findViewById(R.id.powerEditText);
        watts.setText(objecte.getWatts()+"");
        watts.setEnabled(false);
        EditText cost = findViewById(R.id.costPerKwEditText);
        cost.setText(objecte.getCost()+"");
        cost.setEnabled(false);
        EditText poolFee = findViewById(R.id.poolFeeEditText);
        poolFee.setText(objecte.getPoolFee()+"");
        poolFee.setEnabled(false);
        EditText rewardPerMhs = findViewById(R.id.coinPerMhEditText);
        rewardPerMhs.setText(objecte.getRewardPerMhs()+"");
        rewardPerMhs.setEnabled(false);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setEnabled(false);
    }
}
