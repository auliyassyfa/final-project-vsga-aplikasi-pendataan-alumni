package com.example.dts.pnj.auliyaputriassyfa;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dts.pnj.auliyaputriassyfa.model.user;
import com.example.dts.pnj.auliyaputriassyfa.repository.userRepository;

public class editData extends AppCompatActivity {
    private EditText editNama, editAlamat, editJabatan, editNpm, editTempatLahir, editTglLahir, editNoHP, editPekerjaan, editThnMasuk, editThnLulus;
    private ImageButton btnBack;
    private Button btnSubmit, btnDelete;
    private userRepository userRepository;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userRepository = new userRepository(this);
        userId = getIntent().getLongExtra("USER_ID", -1);
        if (userId == -1) {
            Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initalizeViews();
        initializeOnClickListeners();
        loadUserData();
    }

    private void initalizeViews() {
        editNama = findViewById(R.id.editNama);
        editAlamat = findViewById(R.id.editAlamat);
        editNpm = findViewById(R.id.editNpm);
        editTempatLahir = findViewById(R.id.editTempatLahir);
        editTglLahir = findViewById(R.id.editTglLahir);
        editThnMasuk = findViewById(R.id.editThnMasuk);
        editThnLulus = findViewById(R.id.editThnLulus);
        editPekerjaan = findViewById(R.id.editPekerjaan);
        editJabatan = findViewById(R.id.editJabatan);
        editNoHP = findViewById(R.id.editNoHP);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initializeOnClickListeners() {
        btnSubmit.setOnClickListener(v -> {
            String nama = editNama.getText().toString().trim();
            String alamat = editAlamat.getText().toString().trim();
            String npm = editNpm.getText().toString().trim();
            String tempatlahir = editTempatLahir.getText().toString().trim();
            String tglLahir = editTglLahir.getText().toString().trim();
            String thnmasuk = editThnMasuk.getText().toString().trim();
            String thnlulus = editThnLulus.getText().toString().trim();
            String pekerjaan = editPekerjaan.getText().toString().trim();
            String jabatan = editJabatan.getText().toString().trim();
            String noHP = editNoHP.getText().toString().trim();

            if (nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                updateDataToSQLite(userId, nama, alamat, npm, tempatlahir, tglLahir, thnmasuk, thnmasuk, thnlulus, pekerjaan, jabatan, noHP);
                Toast.makeText(this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> {
            finish();
        } );

        btnDelete.setOnClickListener(v -> deleteUser());
    }

    private void deleteUser() {
        userRepository.open();
        userRepository.deleteUser(userId);
        userRepository.close();
        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void loadUserData() {
        userRepository.open();
        user user = userRepository.getUserById(userId);
        userRepository.close();

        if (user != null) {
            editNama.setText(user.getNama());
            editAlamat.setText(user.getAlamat());
            editNpm.setText(user.getNpm());
            editThnMasuk.setText(user.getThnMasuk());
            editThnLulus.setText(user.getThnLulus());
            editTempatLahir.setText(user.getTempatLahir());
            editTglLahir.setText(user.getTglLahir());
            editPekerjaan.setText(user.getPekerjaan());
            editJabatan.setText(user.getJabatan());
            editNoHP.setText(user.getNoHP());
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void updateDataToSQLite(long id, String nama, String alamat, String nim, String tempatlahir, String tglLahir, String thnmasuk, String  thnlulus, String  pekerjaan, String jabatan, String noHp, String s) {
        userRepository.open();
        userRepository.updateUser(id, nama, alamat, nim, tempatlahir, tglLahir, thnmasuk, thnmasuk, thnlulus, pekerjaan, jabatan, noHp);
        userRepository.close();
    }
}