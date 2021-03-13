package com.example.easyminingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<ProfitObject> mData;
    private LayoutInflater mInflater;
    public ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    ResultAdapter(Context context, ArrayList<ProfitObject> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.result_row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProfitObject objecte = mData.get(position);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        holder.date.setText(objecte.getDate().format(dtf));
        holder.resultPerDay.setText(objecte.getResultPerDay()+"€");
        holder.resultPerWeek.setText(objecte.getResultPerWeek()+"€");
        holder.resultPerMonth.setText(objecte.getResultPerMonth()+"€");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date, resultPerDay, resultPerWeek, resultPerMonth;
        ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.dateRow);
            resultPerDay = itemView.findViewById(R.id.resultRowPerDay);
            resultPerWeek = itemView.findViewById(R.id.resultRowPerWeek);
            resultPerMonth = itemView.findViewById(R.id.resultRowPerMonth);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "PICOLOLOLOLO", Toast.LENGTH_LONG).show();
            ProfitObject objecte = mData.get(getAdapterPosition());
            Intent profitCalc = new Intent(context, ShowAllProfitObject.class);
            profitCalc.putExtra("object", objecte);
            context.startActivity(profitCalc);
        }

    }

    // convenience method for getting data at click position
    ProfitObject getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
