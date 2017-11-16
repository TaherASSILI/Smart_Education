package com.example.user.smart_education;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 11/11/2017.
 */

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String SQLCrateTableUtilisateur =
            "CREATE TABLE IF NOT EXISTS utilisateurs(id INTEGER PRIMARY KEY, role TEXT, nom TEXT, email TEXT, password TEXT, numInscription INTEGER)";

    private static final String SQLDeleteTableUtilisateur =
            "DROP TABLE IF EXISTS utilisateurs";

    public MaBaseSQLite(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCrateTableUtilisateur);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLDeleteTableUtilisateur);
        onCreate(db);
    }
}
