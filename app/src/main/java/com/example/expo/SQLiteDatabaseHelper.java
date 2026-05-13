package com.example.expo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GSB.db";


    public static final String TABLE_PRO = "professionnel";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOM";
    public static final String COL_3 = "PRENOM";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "VILLE";
    public static final String COL_6 = "CODEPOSTAL";

    // TABLE RDV
    public static final String TABLE_RDV = "rendez_vous";

    public static final String RDV_1 = "ID";
    public static final String RDV_2 = "DATE_RDV";
    public static final String RDV_3 = "HEURE_RDV";
    public static final String RDV_4 = "ID_PRO";

    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE_PRO + " (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NOM TEXT, " +
                        "PRENOM TEXT, " +
                        "TYPE TEXT, " +
                        "VILLE TEXT, " +
                        "CODEPOSTAL TEXT)"
        );

        db.execSQL(
                "CREATE TABLE " + TABLE_RDV + " (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "DATE_RDV TEXT, " +
                        "HEURE_RDV TEXT, " +
                        "ID_PRO INTEGER)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRO);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RDV);

        onCreate(db);
    }


    public void insertData(String nom,
                           String prenom,
                           String type,
                           String ville,
                           String codepostal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nom);

        contentValues.put(COL_3, prenom);

        contentValues.put(COL_4, type);

        contentValues.put(COL_5, ville);

        contentValues.put(COL_6, codepostal);

        db.insert(TABLE_PRO, null, contentValues);

        db.close();
    }


    public Cursor getAllData() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_PRO, null);
    }


    public Cursor searchData(String recherche) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_PRO +
                        " WHERE VILLE LIKE ? OR CODEPOSTAL LIKE ?",
                new String[]{
                        "%" + recherche + "%",
                        "%" + recherche + "%"
                }
        );
    }


    public void insertRDV(String date,
                          String heure,
                          int idPro) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(RDV_2, date);

        contentValues.put(RDV_3, heure);

        contentValues.put(RDV_4, idPro);

        db.insert(TABLE_RDV, null, contentValues);

        db.close();
    }


    public Cursor getPlanning(String date) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_RDV +
                        " WHERE DATE_RDV = ?",
                new String[]{date}
        );
    }
}