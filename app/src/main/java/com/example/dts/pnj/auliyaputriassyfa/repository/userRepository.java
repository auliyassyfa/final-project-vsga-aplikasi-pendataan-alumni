package com.example.dts.pnj.auliyaputriassyfa.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.dts.pnj.auliyaputriassyfa.database.DatabaseHelper;
import com.example.dts.pnj.auliyaputriassyfa.model.user;

import java.util.ArrayList;
import java.util.List;

public class userRepository {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public userRepository(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        if (database != null && database.isOpen()) {
            Log.d("Database Connected", "open: Successfully to connected database");
        } else {
            Toast.makeText(context, "Failed to connect to the database", Toast.LENGTH_SHORT).show();
        }
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }

    public long createUser(String nama, String npm, String tempatLahir, String tglLahir, String alamat, String noHP, String thnMasuk, String thnLulus, String pekerjaan, String jabatan) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_NPM, npm);
        values.put(DatabaseHelper.COLUMN_TEMPATLAHIR, tempatLahir);
        values.put(DatabaseHelper.COLUMN_TGL_LAHIR, tglLahir);
        values.put(DatabaseHelper.COLUMN_ALAMAT, alamat);
        values.put(DatabaseHelper.COLUMN_NOHP, noHP);
        values.put(DatabaseHelper.COLUMN_THNMASUK, thnMasuk);
        values.put(DatabaseHelper.COLUMN_THNLULUS, thnLulus);
        values.put(DatabaseHelper.COLUMN_PEKERJAAN, pekerjaan);
        values.put(DatabaseHelper.COLUMN_JABATAN, jabatan);

        return database.insert(DatabaseHelper.TABLE_USER, null, values);
    }

    public List<user> getAllUsers() {
        List<user> users = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.query(DatabaseHelper.TABLE_USER,
                    new String[]{
                            DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAMA,
                            DatabaseHelper.COLUMN_NPM,
                            DatabaseHelper.COLUMN_TEMPATLAHIR,
                            DatabaseHelper.COLUMN_TGL_LAHIR,
                            DatabaseHelper.COLUMN_ALAMAT,
                            DatabaseHelper.COLUMN_NOHP,
                            DatabaseHelper.COLUMN_THNMASUK,
                            DatabaseHelper.COLUMN_THNLULUS,
                            DatabaseHelper.COLUMN_PEKERJAAN,
                            DatabaseHelper.COLUMN_JABATAN
                    },
                    null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int namaIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA);
                int npmIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NPM);
                int tempatLahirIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEMPATLAHIR);
                int tglLahirIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TGL_LAHIR);
                int alamatIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ALAMAT);
                int noHpIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOHP);
                int thnMasukIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNMASUK);
                int thnLulusIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNLULUS);
                int pekerjaanIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PEKERJAAN);
                int jabatanIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JABATAN);

                cursor.moveToFirst();
                do {
                    long id = cursor.getLong(idIndex);
                    String nama = cursor.getString(namaIndex);
                    String npm = cursor.getString(npmIndex);
                    String tempatLahir = cursor.getString(tempatLahirIndex);
                    String tglLahir = cursor.getString(tglLahirIndex);
                    String alamat = cursor.getString(alamatIndex);
                    String noHP = cursor.getString(noHpIndex);
                    String tHNMasuk = cursor.getString(thnMasukIndex);
                    String thnLulus = cursor.getString(thnLulusIndex);
                    String pekerjaan = cursor.getString(pekerjaanIndex);
                    String jabatan = cursor.getString(jabatanIndex);

                    users.add(new user(id, nama, npm, tempatLahir, tglLahir, alamat, noHP, tHNMasuk, thnLulus, pekerjaan, jabatan));
                } while (cursor.moveToNext());
            } else {
                Log.e("userRepository", "Cursor is null or empty");
            }
        } catch (Exception e) {
            Log.e("userRepository", "Error while fetching users: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return users;
    }

    public user getUserById(long id) {
        user user = null;
        Cursor cursor = null;
        try {
            cursor = database.query(DatabaseHelper.TABLE_USER,
                    new String[]{DatabaseHelper.COLUMN_ID,
                            DatabaseHelper.COLUMN_NAMA,
                            DatabaseHelper.COLUMN_NPM,
                            DatabaseHelper.COLUMN_TEMPATLAHIR,
                            DatabaseHelper.COLUMN_TGL_LAHIR,
                            DatabaseHelper.COLUMN_ALAMAT,
                            DatabaseHelper.COLUMN_NOHP,
                            DatabaseHelper.COLUMN_THNMASUK,
                            DatabaseHelper.COLUMN_THNLULUS,
                            DatabaseHelper.COLUMN_PEKERJAAN,
                            DatabaseHelper.COLUMN_JABATAN},
                    DatabaseHelper.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int namaIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAMA);
                int npmIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NPM);
                int tempatLahirIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEMPATLAHIR);
                int tglLahirIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TGL_LAHIR);
                int alamatIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ALAMAT);
                int noHPIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOHP);
                int thnMasukIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNMASUK);
                int thnLulusIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_THNLULUS);
                int pekerjaanIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PEKERJAAN);
                int jabatanIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_JABATAN);

                if (idIndex != -1 && namaIndex != -1 && alamatIndex != -1) {
                    long userId = cursor.getLong(idIndex);
                    String nama = cursor.getString(namaIndex);
                    String npm = cursor.getString(npmIndex);
                    String tempatLahir = cursor.getString(tempatLahirIndex);
                    String tanggalLahir = cursor.getString(tglLahirIndex);
                    String alamat = cursor.getString(alamatIndex);
                    String telepon = cursor.getString(noHPIndex);
                    String tahunMasuk = cursor.getString(thnMasukIndex);
                    String tahunLulus = cursor.getString(thnLulusIndex);
                    String pekerjaan = cursor.getString(pekerjaanIndex);
                    String jabatan = cursor.getString(jabatanIndex);
                    user = new user(userId, nama, npm, tempatLahir, tanggalLahir, alamat, tahunMasuk, tahunLulus, pekerjaan, telepon, jabatan);
                } else {
                    Log.e("userRepository", "One or more columns are missing in the cursor");
                }
            } else {
                Log.e("userRepository", "Cursor is null or empty");
            }
        } catch (Exception e) {
            Log.e("userRepository", "Error while fetching user by ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return user;
    }

    public int updateUser(long id, String s, String nama, String npm, String tempatLahir, String tglLahir, String alamat, String noHP, String thnMasuk, String thnLulus, String pekerjaan, String jabatan) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_NPM, npm);
        values.put(DatabaseHelper.COLUMN_TEMPATLAHIR, tempatLahir);
        values.put(DatabaseHelper.COLUMN_TGL_LAHIR, tglLahir);
        values.put(DatabaseHelper.COLUMN_ALAMAT, alamat);
        values.put(DatabaseHelper.COLUMN_NOHP, noHP);
        values.put(DatabaseHelper.COLUMN_THNMASUK, thnMasuk);
        values.put(DatabaseHelper.COLUMN_THNLULUS, thnLulus);
        values.put(DatabaseHelper.COLUMN_PEKERJAAN, pekerjaan);
        values.put(DatabaseHelper.COLUMN_JABATAN, jabatan);

        return database.update(DatabaseHelper.TABLE_USER, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUser(long id) {
        database.delete(DatabaseHelper.TABLE_USER,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}