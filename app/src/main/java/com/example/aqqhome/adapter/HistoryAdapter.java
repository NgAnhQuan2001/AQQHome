package com.example.aqqhome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aqqhome.R;
import com.example.aqqhome.model.bankmodel;
import com.example.aqqhome.model.roommodel2;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<bankmodel> transactions;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView amountTextView;
        public TextView timeTextView;

        public ViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.transaction_id);
            amountTextView = view.findViewById(R.id.transaction_amount);
            timeTextView = view.findViewById(R.id.transaction_time);
        }
    }

    public HistoryAdapter(List<bankmodel> transactions) {
        this.transactions = transactions;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lichsunap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        bankmodel transaction = transactions.get(position);
        holder.idTextView.setText(transaction.getMoneyID());
        holder.amountTextView.setText(transaction.getSotiennap());
        holder.timeTextView.setText(transaction.getThoigiannap());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}