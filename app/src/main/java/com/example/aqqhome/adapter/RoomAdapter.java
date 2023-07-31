package com.example.aqqhome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aqqhome.R;
import com.example.aqqhome.model.roommodel2;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<roommodel2> roomList;
    private OnRoomClickListener listener;

    public RoomAdapter(List<roommodel2> roomList, OnRoomClickListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roomIdView;
        public TextView roomNameView;

        public ViewHolder(View itemView) {
            super(itemView);
            roomIdView = itemView.findViewById(R.id.tvTenCanHo);
            roomNameView = itemView.findViewById(R.id.tvMaCanHo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_building, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        roommodel2 room = roomList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRoomClick(room);
            }
        });
        holder.roomIdView.setText(room.getRoomID());
        holder.roomNameView.setText(room.getRoomName());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }
}