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
    public static final String COLUMN_NOHP = "  noHP";
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
                "TECH WEEKLY: How Mercedes finally unlocked the secrets of the ground effect regulations",
                "EXCLUSIVE: ’You have to take risk everywhere’ – Fred Vasseur on how he’s going to take Ferrari back to the top",
                "Red Bull make decision on Perez's future and RB driver line-up",
                "Charles Leclerc lifts lid on Ferrari updates during Lewis Hamilton negotiations",
                "The dominant RB19 has been spotted on a truck in Prague",
                "Big $100m Adrian Newey deal now ‘finalised’ with ‘major input’ granted – report",
                "Wolff had to change approach: 'Team in danger of complete collapse'",
                "Shock driver market twist as F1 2024 star conducts secret rally testing",
                "Another F1 2026 shake-up on the way as FIA confirm latest rule change",
                "Las Vegas Grand Prix issues huge F1 race weekend announcement",
                "Fresh hope for Mick Schumacher as ‘low-risk’ F1 return option emerges – report"
        );

        List<String> contents = Arrays.asList(
                "The Mercedes W15 that began this season did not look so outwardly different to the car which won three of the last four races before the summer break",
                "Twenty months into the biggest job in Formula 1 and Ferrari boss Fred Vasseur is methodically chipping away at reshaping the world’s most famous racing team, as he bids to turn them back into world beaters and champions of the sport.\n" +
                        "\n" +
                        "It hasn’t been easy, obviously. Every decision he makes, every move on track, every result at the chequered flag is analysed in minute detail. At Ferrari, if you succeed, it’s expected. If you lose, you’re criticised more than most.",
                "Red Bull are sticking with Sergio Perez and will field unchanged line-ups in their works team and sister squad RB when the Formula 1 season resumes",
                "Committing his future to Ferrari a week before the bombshell Lewis Hamilton announcement, Charles Leclerc was kept abreast with the developments as Hamilton negotiated his swap to Ferrari.\n"
                        + "Hamilton broke the internet back in February when the Briton announced that he had exercised a release clause in his Mercedes contract and would leave the team at the end of the 2024 championship.",
                "At Red Bull Racing, they will look back on the RB19's performance in a special way. Only one race was won by another team in 2023. Indeed, the Singapore Grand Prix was won by Carlos Sainz. For the rest, it was always Verstappen or his teammate, Sergio Perez, who stood on the top step of the podium.\n" +
                        "\n" +
                        "RB19 spotted in Prague\n" +
                        "The RB19 could be seen again this week. Not on the track, but on the back of a special Red Bull truck. This made for a special sight in the capital of the Czechia.",
                "Adrian Newey’s sensational switch to Aston Martin has been “finalised” with the F1 design guru set to earn $100million over three years.\n" +
                        "\n" +
                        "That is the claim of former Red Bull F1 driver Robert Doornbos, who says Newey will be granted “major input in technical decisions” at Aston Martin after Ferrari balked at the 65-year-old’s demands.",
                "When Toto Wolff first joined Mercedes' Formula One Team in 2013, the German team were on the rise, with first winning Grands Prix that season, and then in 2014, they were already on top of F1's world, mastering the regulation change. However, once another regulation change happened in 2022, Mercedes were left behind, something that was hard for the Austrian.",
                "An unnamed F1 2024 driver has been testing rally cars in the utmost secrecy in a potential bombshell for the driver market.\n" +
                        "\n" +
                        "That is the astonishing claim of F1 analyst Peter Windsor, who has revealed that the mystery star has impressed in rally tests – and confirmed that the driver in question is not reigning World Champion Max Verstappen.",
                "The FIA has confirmed a further parts rule change will be introduced from 2026, with bespoke suppliers set to return.\n" +
                        "\n" +
                        "A standardised part in recent years, F1 teams from 2026 will be given the freedom to sign their own wheel rim suppliers in a reversion of a rule introduced in 2022.",
                "Formula 1 have issued a hugely exciting announcement for the fans ahead of the 2024 Las Vegas Grand Prix.\n" +
                        "\n" +
                        "F1 is set to return to the iconic US city in November for round 22 of the 2024 season following last year's inaugural event around the street track.",
                "Mick Schumacher’s name is increasingly cropping up in conversations over Audi’s F1 2025 driver line-up, it has been claimed.\n" +
                        "\n" +
                        "Audi, who will take over the existing Sauber team in time for F1’s new regulations in 2026, are still searching for a team-mate for Nico Hulkenberg for next season."
        );

        List<String> imageResourceIds = Arrays.asList(
                "drawable/w15",
                "drawable/ferrari",
                "drawable/redbull",
                "drawable/charlewis",
                "drawable/rb19",
                "drawable/newey",
                "drawable/wolf",
                "drawable/rally",
                "drawable/fiarules",
                "drawable/vegasgp",
                "drawable/schumi"

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