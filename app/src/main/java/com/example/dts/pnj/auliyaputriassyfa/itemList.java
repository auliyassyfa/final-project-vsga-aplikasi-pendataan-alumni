package com.example.dts.pnj.auliyaputriassyfa;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dts.pnj.auliyaputriassyfa.model.user;

import java.util.List;

public class itemList extends RecyclerView.Adapter<itemList.ItemViewHolder> {

    private List<user> userList;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(user user);
    }

    public itemList(List<user> userList, OnItemLongClickListener listener) {
        this.userList = userList;
        this.onItemLongClickListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        user user = userList.get(position);
        holder.tvName.setText(user.getNama());
        holder.tvNim.setText(user.getNpm());

        holder.itemView.setOnLongClickListener(v -> {
            Log.d("ItemListAct", "Item long clicked: " +  user.getNama());
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(user);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateUserList(List<user> userList) {
        this.userList = userList;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNim;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNama);
            tvNim = itemView.findViewById(R.id.tvNpm);
        }
    }
}