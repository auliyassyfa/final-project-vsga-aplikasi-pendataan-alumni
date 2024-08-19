package com.example.dts.pnj.auliyaputriassyfa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names for User
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NPM = "npm";
    public static final String COLUMN_TEMPATLAHIR = "tempat_lahir";
    public static final String COLUMN_TGL_LAHIR = "tanggal_lahir";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_NOHP = "noHP";
    public static final String COLUMN_THNMASUK = "tahun_masuk";
    public static final String COLUMN_THNLULUS = "tahun_lulus";
    public static final String COLUMN_PEKERJAAN = "pekerjaan";
    public static final String COLUMN_JABATAN = "jabatan";

    // Table name and column names for News
    public static final String TABLE_NEWS = "news";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_PATH_IMAGE = "path_image";

    // SQL statement to create the User table
    private static final String TABLE_CREATE_USER =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);",
                    TABLE_USER,
                    COLUMN_ID,
                    COLUMN_NAMA,
                    COLUMN_NPM,
                    COLUMN_TEMPATLAHIR,
                    COLUMN_TGL_LAHIR,
                    COLUMN_ALAMAT,
                    COLUMN_NOHP,
                    COLUMN_THNMASUK,
                    COLUMN_THNLULUS,
                    COLUMN_PEKERJAAN,
                    COLUMN_JABATAN);

    // SQL statement to create the News table
    private static final String TABLE_CREATE_NEWS =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT);",
                    TABLE_NEWS,
                    COLUMN_ID,
                    COLUMN_TITLE,
                    COLUMN_CONTENT,
                    COLUMN_PATH_IMAGE);

    // Constructor
    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Logging saat tabel dibuat
        Log.d("databaseHelper", "Creating tables...");

        // Execute the SQL statement to create the tables
        db.execSQL(TABLE_CREATE_USER);
        db.execSQL(TABLE_CREATE_NEWS);

        // Logging setelah tabel dibuat
        Log.d("databaseHelper", "Tables created successfully");

        insertDummyData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "Upgrading database from version " + oldVersion + " to " + newVersion);

        // Drop the old tables and create new ones
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);

        Log.d("DatabaseHelper", "Old tables dropped, creating new tables...");
        onCreate(db);
    }

    private void insertDummyData(SQLiteDatabase db) {
        List<String> titles = Arrays.asList(
                "Bisakah Threads Gantikan Twitter? Pakar Akui 'Vibes' Membosankan",
                "Google Luncurkan Gemini Live, AI yang Bisa Diajak Ngobrol",
                "Peneliti BRIN Temukan Anggrek Spesies Baru Endemik Indonesia",
                "Terungkap, Alasan Hacker Brain Cipher Serang Pusat Data Indonesia",
                "Robert Downey Jr Gagal Jadi Cameo di Deadpool 3 Gara-gara Doctor Doom",
                "KPU Batasi Iklan Kampanye Peserta Pemilu di Media Massa",
                "Elon Musk Blak-blakan Soal Kapan Manusia Bisa ke Mars",
                "Taman Safari Siap Bangun Tempat Wisata di IKN",
                "Software Rawan Bug, Siapa Tanggung Jawab?",
                "Pulau-pulau 'Atlantis' yang Tenggelam di Laut Ditemukan di Spanyol"
        );

        List<String> contents = Arrays.asList(
                "Platform media sosial microblogging dari Meta, Threads, digadang-gadang hadir untuk menggusur peran X (sebelumnya Twitter). Namun, sejumlah pengguna berbagi pengalaman yang membosankan soal media sosial-nya Mark Zuckerberg ini.\n",
                    "Saat Threads pertama kali diluncurkan tahun lalu, banyak pengguna X yang eksodus ke aplikasi ini. Namun ternyata, Threads yang menjadi alternatif itu memiliki banyak kekurangan.\n",
                "Google resmi meluncurkan Gemini Live, asisten AI yang bisa diajak mengobrol dalam acara Made by Google pada Kamis (14/8).\n" +
                        "Gemini Live sebelumnya telah diperkenalkan pada gelaran I/O 2024 pada Mei lalu. Kini, fitur ini resmi diluncurkan untuk menjadi teman mengobrol pengguna.\n" +
                        "Sayangnya, tidak semua orang bisa menggunakan asisten AI ini. Google meluncurkan Gemini Live pada Gemini Advanced, versi berbayar AI dari Google.\n",
                "Peneliti Pusat Riset Biosistematika dan Evolusi, Badan Riset dan Inovasi Nasional (BRIN) menemukan anggrek spesies baru, yang merupakan spesies endemik asal Pulau Sulawesi, Indonesia.\n" +
                        "Temuan spesies anggrek yang dikenal dengan para pehobi dengan nama lokal anggrek kuku macan tersebut telah dipublikasikan pada jurnal Edinburgh Journal of Botany pada Mei 2024 sebagai anggrek spesies baru endemik Sulawesi dengan nama Aerides obyrneana.\n" +
                        "Spesies baru ini memiliki sosok bunga atraktif dengan kombinasi warna yang langka di genusnya, yaitu sepal dan petalnya berwarna putih keunguan dengan bibir bunga berwarna kuning cerah kehijauan,\" kata Peneliti BRIN Destario Metusala melalui keterangan di Jakarta, Minggu.\n",
                "Hacker Brain Cipher merinci setidaknya tiga alasan atau motif utama mereka menyerang PDNS 2 Surabaya.\n"+
                        "Pertama, serangan ransomware ini sebagai Pentest (Penetration Testing) alias uji keamanan semata, tidak ada motif politik. Penetretion testing ini merupakan istilah yang merujuk kepada proses menguji keamanan sistem jaringan komputer dengan melakukan simulasi serangan siber. Tujuannya adalah untuk mencari kelemahan-kelemahan dalam sistem dan mencegah kemungkinan peretasan.\n"+
                        "Alasan kedua, hacker Brain Cipher ingin pemerintah Indonesia sadar bahwa Indonesia perlu meningkatkan keamanan siber mereka, terutama merekrut SDM keamanan siber yang kompeten.\n"+
                        "Ketiga, hacker Brain Cipher ingin pemerintah Indonesia juga sadar bahwa data center (pusat data) merupakan industri berteknologi tinggi yang membutuhkan investasi besar.",
                "Para penulis Deadpool & Wolverine buka-bukaan terkait kameo yang gagal muncul di film terbaru Marvel Cinematic Universe (MCU) itu, yaitu Robert Downey Jr. sebagai Tony Stark.\n" +
                        "Paul Wernick dan Rhett Reese menjelaskan Tony Stark awalnya direncanakan muncul dalam film Deadpool 3 bersama Happy Hogan yang diperankan oleh Jon Favreau.\n" +
                        "Pada akhirnya adegan kameo Robert Downey Jr. dicoret dari naskah setelah tim penulis Deadpool & Wolverine mengetahui keterlibatan sang aktor kembali sebagai Doctor Doom.\n" +
                        "Keterlibatan Downey Jr. kembali ke MCU membuatnya harus menolak tawaran dari Ryan Reynolds untuk muncul di Deadpool & Wolverine.\n",
                 "Komisi Pemilihan Umum (KPU) RI membatasi iklan kampanye di media massa yang dapat dilakukan oleh peserta Pemilu 2024 pada masa kampanye.\n"+
                         "Batasan-batasan tersebut diatur di dalam Keputusan KPU Nomor 1621 Tahun 2023 tentang Pedoman Teknis Pelaksanaan Kampanye Pemilu.\n"+
                         "Pada media massa cetak, KPU membatasi iklan kampanye paling banyak 2 halaman di masing-masing media. Peserta pemilu dapat beriklan paling banyak di 3 media massa cetak selama paling lama 21 hari.",
                "Miliarder sekaligus pemilik perusahaan kedirgantaraan Space X, Elon Musk, buka-bukaan soal kapan manusia bisa menginjakkan kakinya di Planet Mars. Simak penjelasannya.\n" +
                        "Dalam buku baru \"SpaceX: Elon Musk and the Final Frontier\" (Motorbooks), jurnalis sains Brad Bergan membuat gambaran menarik tentang visi Musk dan bagaimana ia membangun sebuah bisnis yang telah menjadi penting bagi lembaga-lembaga seperti NASA, dan usaha-usaha lain yang berambisi mengeksplorasi ruang angkasa.\n" +
                        "\n" +
                        "Dalam tulisan ini, ia mengeksplorasi biaya besar yang dibutuhkan untuk perjalanan luar angkasa, dan mengapa, terlepas dari potensi kekayaan yang dipertaruhkan, mungkin lebih baik tetap tinggal di Bumi untuk sementara waktu.",
                "Taman Safari Indonesia (TSI) berencana membangun tempat wisata edukasi di Ibu Kota Nusantara (IKN), Kalimantan Timur. IKN dianggap lokasi yang cocok untuk membangun taman marga satwa.\n" +
                        "Direktur Taman Safari Indonesia Jansen Manansang menyampaikan bahwa pihaknya telah melakukan pemetaan untuk menentukan calon lokasi tempat rekreasi Taman Safari di IKN.\n",
                "Demi mencegah bug atau cacat, perangkat lunak mesti melalui proses software testing sebelum diluncurkan. Siapa yang berwenang?\n" +
                        "Software testing atau pengujian perangkat lunak adalah proses mengevaluasi dan memverifikasi apakah produk perangkat lunak atau aplikasi bisa melakukan apa yang seharusnya dilakukan.\n" +
                        "\n" +
                        "Pengujian aplikasi saat ini paling efektif jika dilakukan secara berkesinambungan, dimulai saat perancangan, berlanjut saat perangkat lunak dibangun, dan bahkan saat di-deploy atau digunakan.\n",
                "Sebuah studi terbaru menduga awal mula legenda Atlantis terkait dengan pulau-pulau dan gunung api yang tenggelam ke samudra jutaan tahun lalu.\n" +
                        "Hal itu disimpulkan setelah para peneliti di Spanyol menemukan pulau-pulau yang hilang lantaran tenggelam ke dalam lautan jutaan tahun yang lalu, dengan beberapa di antaranya masih memiliki pantai yang utuh.\n" +
                        "\n" +
                        "\"Ini bisa jadi merupakan asal mula legenda Atlantis,\" kata Luis Somoza, kepala proyek untuk mempelajari aktivitas vulkanik di lepas pantai Kepulauan Canary, dikutip dari LiveScience.\n"
        );

        List<String> imageResourceIds = Arrays.asList(
                "drawable/imageThreadsApp",
                "drawable/geminiAI",
                "drawable/imageAnggrek",
                "drawable/beritahacker",
                "drawable/imageDeadpool3",
                "drawable/beritamediamasa",
                "drawable/imageElonMusk",
                "drawable/imageLaptopVirus",
                "drawable/atlantisjpg",
                "drawable/vegasgp"
        );

        for (int i = 0; i < titles.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, titles.get(i));
            values.put(COLUMN_CONTENT, contents.get(i));
            values.put(COLUMN_PATH_IMAGE, imageResourceIds.get(i));

            // Log values for debugging
            Log.d("databaseHelper", "Inserting data: " + values);

            long rowId = db.insert(TABLE_NEWS, null, values);
            if (rowId == -1L) {
                Log.e("databaseHelper", "Failed to insert row for title: " + titles.get(i));
            } else {
                Log.d("databaseHelper", "Inserted row with ID: " + rowId);
            }
        }
    }
}