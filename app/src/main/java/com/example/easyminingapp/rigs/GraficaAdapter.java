package com.example.easyminingapp.rigs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.easyminingapp.R;
import com.example.easyminingapp.objects.Grafica;
import com.example.easyminingapp.objects.Rig;
import com.example.easyminingapp.objects.Rig_Grafica;

import java.util.ArrayList;

public class GraficaAdapter extends RecyclerView.Adapter<GraficaAdapter.ViewHolder> {

    private ArrayList<Rig_Grafica> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    GraficaAdapter(Context context, ArrayList<Rig_Grafica> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grafica_row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rig_Grafica objecte = mData.get(position);
        Log.d("GRAFICA_ADAPTER", objecte.getGrafica().getNom());
        holder.nomGrafica.setText(objecte.getGrafica().getNom());
        holder.hashRate.setText(objecte.getHashrate_real()+" MH/s");
        }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomGrafica, hashRate;
        ViewHolder(View itemView) {
            super(itemView);

            nomGrafica = itemView.findViewById(R.id.nomGraficaTextView);
            hashRate = itemView.findViewById(R.id.hashrateTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "PICOLOLOLOLO", Toast.LENGTH_LONG).show();

            Rig_Grafica graf = mData.get(getAdapterPosition());
            Intent profitCalc = new Intent(context, ShowGrafica.class);
            profitCalc.putExtra("id", graf.getId_relacio());
            context.startActivity(profitCalc);
        }

    }

    // convenience method for getting data at click position
    Rig_Grafica getItem(int id) {
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
