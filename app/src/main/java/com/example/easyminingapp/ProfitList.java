package com.example.easyminingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class ProfitList extends AppCompatActivity {
    ResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerProfit);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultAdapter(this, MainActivity.profitList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}