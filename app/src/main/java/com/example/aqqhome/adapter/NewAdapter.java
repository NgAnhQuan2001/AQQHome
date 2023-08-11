package com.example.aqqhome.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aqqhome.R;
import com.example.aqqhome.model.newfeedmodel;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    private List<newfeedmodel> newfeed;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameview;
        public TextView timeview;
        public TextView textview;

        public ViewHolder(View view) {
            super(view);
            usernameview = view.findViewById(R.id.usernameTextView);

            timeview = view.findViewById(R.id.timeTextView);
            textview = view.findViewById(R.id.statusTextView);
        }
    }

    public NewAdapter(List<newfeedmodel> newfeed) {
        this.newfeed = newfeed;
    }

    @Override
    public NewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemnew, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewAdapter.ViewHolder holder, int position) {
        newfeedmodel neww = newfeed.get(position);
        holder.usernameview.setText(neww.getName());
        holder.timeview.setText(neww.getNewTime());
        holder.textview.setText(neww.getText());
    }

    @Override
    public int getItemCount() {
        return (newfeed != null) ? newfeed.size() : 0;

    }
}
