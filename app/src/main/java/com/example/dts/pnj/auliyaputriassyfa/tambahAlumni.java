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

;import com.example.dts.pnj.auliyaputriassyfa.repository.userRepository;

public class tambahAlumni extends AppCompatActivity {

    private EditText editNama, editAlamat, editNpm, editTempatLahir, editTglLahir, editThnMasuk, editThnLulus, editPekerjaan, editJabatan, editNoHP;
    private Button btnSubmit, btnCancel;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah_alumni);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initalizeViews();
        initializeOnClickListeners();
    }

    void initializeOnClickListeners() {
        btnSubmit.setOnClickListener(v -> {
            String nama = editNama.getText().toString();
            String alamat = editAlamat.getText().toString();
            String npm = editNpm.getText().toString().trim();
            String tempatlahir = editTempatLahir.getText().toString().trim();
            String tglLahir = editTglLahir.getText().toString().trim();
            String thnmasuk = editThnMasuk.getText().toString().trim();
            String thnlulus = editThnLulus.getText().toString().trim();
            String pekerjaan = editPekerjaan.getText().toString().trim();
            String jabatan = editJabatan.getText().toString().trim();
            String noHP = editNoHP.getText().toString().trim();

            if (nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(tambahAlumni.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                insertDataToSQLite(nama, npm, tempatlahir, tglLahir, alamat, noHP, thnmasuk, thnlulus, pekerjaan, jabatan);
                Toast.makeText(tambahAlumni.this, "User data submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }

    void initalizeViews() {
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
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);
    }

    void insertDataToSQLite(String nama, String npm, String tempatLahir, String tanggalLahir, String alamat, String noHP, String tahunMasuk, String tahunLulus, String pekerjaan, String jabatan) {
        userRepository userRepository = new userRepository(this);
        userRepository.open();
        userRepository.createUser(nama, npm, tempatLahir, tanggalLahir, alamat, noHP, tahunMasuk, tahunLulus, pekerjaan, jabatan);
        userRepository.close();
    }
}