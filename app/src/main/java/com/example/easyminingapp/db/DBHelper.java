package com.example.easyminingapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NOM = "EasyMining";
    public static final String DB_TAULA_RIG = "rigs";
    public static final String DB_TAULA_GRAFICA = "grafiques";
    public static final String DB_TAULA_RELACIO = "rig_grafica";
    public static final int VERSIO = 2;

    // DECLARACIÓ CONSTANTS CLASSE RIG
    public static final String CLAU_ID_RIG = "_id";
    public static final String CLAU_NOM_RIG = "nom_rig";
    public static final String CLAU_DESCRIPCIO_RIG = "descripcio";

    //DECLARACIÓ CONSTANTS CLASSE GRAFICA
    public static final String CLAU_ID_GRAFICA = "_id";
    public static final String CLAU_NOM_GRAFICA = "nom_grafica";
    public static final String CLAU_HASHRATE_GRAFICA = "hashrate";
    public static final String CLAU_IMATGE_GRAFICA = "imatge";

    //DECLARACIÓ CONSTANTS CLASSE RELACIO
    public static final String CLAU_ID_RELACIO = "_id";
    public static final String CLAU_ID_RIG_RELACIO = "id_rig";
    public static final String CLAU_ID_GRAFICA_RELACIO = "id_grafica";
    public static final String CLAU_HASHRATE_RELACIO = "hashrate_real";

    //DECLARACIÓ QUERYS BASES DE DADES
    private final String CREATE_TABLE_RIG = "create table " + DB_TAULA_RIG + "("
            + CLAU_ID_RIG + " integer primary key, "
            + CLAU_NOM_RIG + " text not null, "
            + CLAU_DESCRIPCIO_RIG + " text not null "
            + ")";

    private final String CREATE_TABLE_GRAFICA = "create table " + DB_TAULA_GRAFICA + "("
            + CLAU_ID_GRAFICA + " integer primary key, "
            + CLAU_NOM_GRAFICA + " text not null, "
            + CLAU_HASHRATE_GRAFICA + " float not null,"
            + CLAU_IMATGE_GRAFICA + " BLOB)";

    private final String CREATE_TABLE_RELACIO = "create table " + DB_TAULA_RELACIO + "("
            + CLAU_ID_RELACIO + " integer primary key, "
            + CLAU_ID_RIG_RELACIO + " integer not null, "
            + CLAU_ID_GRAFICA_RELACIO + " integer not null, "
            + CLAU_HASHRATE_RELACIO + " float not null, "
            + "FOREIGN KEY ("+ CLAU_ID_RIG_RELACIO +") REFERENCES " + DB_TAULA_RIG + "(" + CLAU_ID_RIG + "), "
            + "FOREIGN KEY ("+ CLAU_ID_GRAFICA_RELACIO +") REFERENCES " + DB_TAULA_GRAFICA + "(" + CLAU_ID_GRAFICA + ")"
            + ")";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NOM, null, VERSIO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RIG);
        db.execSQL(CREATE_TABLE_GRAFICA);
        db.execSQL(CREATE_TABLE_RELACIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Detecta si hi ha una canvi a DATABASE_VERSION i recrea la base de dades
        Log.w(DBHelper.class.getName(), "Modificant desde la versió " + oldVersion + " a "+ newVersion );
        db.execSQL("DROP TABLE IF EXISTS " + DB_TAULA_RIG);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TAULA_GRAFICA);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TAULA_RELACIO);
        onCreate(db);
    }
}
