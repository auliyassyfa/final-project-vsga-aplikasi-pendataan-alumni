package com.example.dts.pnj.auliyaputriassyfa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.dts.pnj.auliyaputriassyfa.model.user;
import com.example.dts.pnj.auliyaputriassyfa.repository.userRepository;

public class dataAlumni extends AppCompatActivity {
    private RecyclerView recyclerView;
    private itemList itemListNameAdapter;
    private userRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_alumni);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initalizeViews();
    }
    private void initalizeViews() {
        recyclerView = findViewById(R.id.recyclerViewItemName);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        userRepository = new userRepository(this);
        userRepository.open();

        List<user> userList = userRepository.getAllUsers();
        if (itemListNameAdapter == null) {
            itemListNameAdapter = new itemList(userList, this::showOptionsDialog);
            recyclerView.setAdapter(itemListNameAdapter);
        } else {
            itemListNameAdapter.updateUserList(userList);
            itemListNameAdapter.notifyDataSetChanged();
        }

        userRepository.close();
    }

    private void showOptionsDialog(user user) {
        Log.d("MainActivity", "showOptionsDialog called for user: " + user.getNama());
        new AlertDialog.Builder(this)
                .setItems(new String[]{"Update", "Delete"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            Intent intent = new Intent(dataAlumni.this, editData.class);
                            intent.putExtra("USER_ID", user.getId());
                            startActivity(intent);
                            break;
                        case 1:
                            deleteUser(user);
                            break;
                    }
                })
                .show();
    }

    private void deleteUser(user user) {
        userRepository.open();
        userRepository.deleteUser(user.getId());
        userRepository.close();
        loadUserData();
        Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
    }
}