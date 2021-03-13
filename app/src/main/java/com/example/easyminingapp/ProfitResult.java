package com.example.easyminingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProfitResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_result);
        double mhs;
        double watts;
        double cost;
        double poolFee;
        double rewardPerMhs;

        Bundle extras = getIntent().getExtras();
        mhs = Double.parseDouble(extras.getString("mhs"));
        watts = Double.parseDouble(extras.getString("watts"));
        cost = Double.parseDouble(extras.getString("cost"));
        poolFee = Double.parseDouble(extras.getString("poolFee"));
        rewardPerMhs = Double.parseDouble(extras.getString("rewardPerMhs"));

        double electricCostPerDay = ((watts*24)/1000)*cost;
        double rewardPerDay = (mhs*rewardPerMhs);
        double poolFeePerDay = (poolFee/100)*rewardPerDay;
        double resultPerDay = rewardPerDay-electricCostPerDay-poolFeePerDay;
        double resultPerWeek = resultPerDay*7;
        double resultPerMonth = resultPerWeek*4;
        LocalTime now = LocalTime.now();

        ProfitObject nObject = new ProfitObject(now, resultPerDay, resultPerWeek, resultPerMonth);
        nObject.setData(mhs, watts, cost, poolFee, rewardPerMhs);
        MainActivity.profitList.add(nObject);

        TextView perDayEditText = findViewById(R.id.resultPerDay);
        TextView perWeekEditText = findViewById(R.id.resultPerWeek);
        TextView perMonthEditText = findViewById(R.id.resultPerMonth);

        if(resultPerDay > 0) {
            perDayEditText.setBackgroundResource(R.drawable.item_background_positive);
            perWeekEditText.setBackgroundResource(R.drawable.item_background_positive);
            perMonthEditText.setBackgroundResource(R.drawable.item_background_positive);
        }
        else {
            perDayEditText.setBackgroundResource(R.drawable.item_background_negative);
            perWeekEditText.setBackgroundResource(R.drawable.item_background_negative);
            perMonthEditText.setBackgroundResource(R.drawable.item_background_negative);
        }

        perDayEditText.setText(String.format("%.2f€", resultPerDay));
        perWeekEditText.setText(String.format("%.2f€", resultPerWeek));
        perMonthEditText.setText(String.format("%.2f€", resultPerMonth));
    }
}