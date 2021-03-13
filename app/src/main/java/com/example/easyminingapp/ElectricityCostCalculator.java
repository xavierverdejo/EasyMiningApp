package com.example.easyminingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ElectricityCostCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_cost_calculator);
        EditText watts = findViewById(R.id.watts);
        EditText pricekwh = findViewById(R.id.pricekwh);
        EditText usage = findViewById(R.id.usage);
        watts.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                showResult();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        pricekwh.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                showResult();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        usage.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                showResult();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
    public void openResult(View view){
        EditText watts = findViewById(R.id.watts);
        EditText pricekwh = findViewById(R.id.pricekwh);
        EditText usage = findViewById(R.id.usage);

        if(watts.getText().toString().equals("") || watts.getText().toString().equals("") || pricekwh.getText().toString().equals("") || usage.getText().toString().equals(""))
        {
            Toast.makeText(this, "There can't be empty fields", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void showResult(){
        TextView kwh = findViewById(R.id.powerConsumed);
        TextView finalcost = findViewById(R.id.cost);
        EditText watts = findViewById(R.id.watts);
        EditText pricekwh = findViewById(R.id.pricekwh);
        EditText usage = findViewById(R.id.usage);
        if(!watts.getText().toString().equals("") && !pricekwh.getText().toString().equals("") && !usage.getText().toString().equals(""))
        {
            double dWatts = Double.parseDouble(watts.getText().toString());
            double dPricekwh = Double.parseDouble(pricekwh.getText().toString());
            double dUsage = Double.parseDouble(usage.getText().toString());
            double dkWh = (dWatts * dUsage) / 1000 ;
            double dFinalCost = (dkWh * dPricekwh)*30;
            kwh.setText(dkWh + " kWh");
            finalcost.setText(dFinalCost + " €");
            return;
        }
    }
}