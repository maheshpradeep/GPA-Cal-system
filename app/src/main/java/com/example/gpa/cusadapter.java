package com.example.gpa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cusadapter extends RecyclerView.Adapter<cusadapter.subViewHolder> {
    private Context context;
    private ArrayList<String> subcode, credits, results;

    public cusadapter(Context context, ArrayList<String> subcode, ArrayList<String> credits,
                         ArrayList<String> results) {
        this.context = context;
        this.subcode = subcode;
        this.credits = credits;
        this.results = results;

    }

    @NonNull
    @Override
    public subViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new subViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.subcodeTxt.setText(subcode.get(position));
        holder.creditTxt.setText(credits.get(position));
        holder.resultTxt.setText(results.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,update.class);
                intent.putExtra("cardNo", subcode.get(position));
                intent.putExtra("firstName", credits.get(position));
                intent.putExtra("lastName", results.get(position));
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return subcode.size();
    }

    public class subViewHolder extends RecyclerView.ViewHolder {
        TextView subcodeTxt, creditTxt,resultTxt;

        public subViewHolder(@NonNull View itemView) {
            super(itemView);
            subcodeTxt = itemView.findViewById(R.id.subcode_text);
            creditTxt = itemView.findViewById(R.id.credits_text);
            resultTxt =itemView.findViewById(R.id.result_text);


            // Set click listener
        }


    }

    // Method to delete a member
    void deleteMember(int position) {
        database myDB = new database(context);
        myDB.deleteMember(subcode.get(position));
        subcode.remove(position);
        credits.remove(position);
        results.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, subcode.size());
    }
}
