package com.example.easyminingapp.rigs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.easyminingapp.R;
import com.example.easyminingapp.objects.Rig;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RigAdapter extends RecyclerView.Adapter<RigAdapter.ViewHolder> {

    private ArrayList<Rig> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    RigAdapter(Context context, ArrayList<Rig> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rig_row_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rig objecte = mData.get(position);

        holder.nomRig.setText(objecte.getNom());
        holder.descRig.setText(objecte.getDescripcio());
        }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomRig, descRig;
        ViewHolder(View itemView) {
            super(itemView);

            nomRig = itemView.findViewById(R.id.nomText);
            descRig = itemView.findViewById(R.id.descTexts);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "PICOLOLOLOLO", Toast.LENGTH_LONG).show();

            Rig rig = mData.get(getAdapterPosition());
            Intent profitCalc = new Intent(context, ShowRig.class);
            profitCalc.putExtra("id", rig.getRig_id());

            context.startActivity(profitCalc);
        }

    }

    // convenience method for getting data at click position
    Rig getItem(int id) {
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
