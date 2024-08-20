package com.example.dts.pnj.auliyaputriassyfa;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dts.pnj.auliyaputriassyfa.model.User;

import java.util.List;

public class itemList extends RecyclerView.Adapter<itemList.ItemViewHolder> {

    private List<User> userList;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(User user);
    }

    public itemList(List<User> userList, OnItemLongClickListener listener) {
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
        User user = userList.get(position);
        holder.tvName.setText(user.getName());
        holder.tvNim.setText(user.getNim());

        holder.itemView.setOnLongClickListener(v -> {
            Log.d("ItemListAct", "Item long clicked: " +  user.getName());
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

    public void updateUserList(List<User> userList) {
        this.userList = userList;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNim;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNim = itemView.findViewById(R.id.tvNim);
        }
    }
}