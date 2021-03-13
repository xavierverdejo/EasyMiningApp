package com.example.easyminingapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.easyminingapp.objects.Grafica;
import com.example.easyminingapp.objects.Rig;
import com.example.easyminingapp.objects.Rig_Grafica;

import java.util.ArrayList;

public class DBInterface {
    private final Context context;
    private DBHelper ajuda;
    private SQLiteDatabase bd;

    private String[] allColumnsRig = {DBHelper.CLAU_ID_RIG, DBHelper.CLAU_NOM_RIG, DBHelper.CLAU_DESCRIPCIO_RIG};

    private String[] allColumnsGrafica = {DBHelper.CLAU_ID_GRAFICA, DBHelper.CLAU_NOM_GRAFICA, DBHelper.CLAU_HASHRATE_GRAFICA, DBHelper.CLAU_IMATGE_GRAFICA};

    private String[] allColumnsRelacio = {DBHelper.CLAU_ID_RELACIO, DBHelper.CLAU_ID_RIG_RELACIO, DBHelper.CLAU_ID_GRAFICA_RELACIO, DBHelper.CLAU_HASHRATE_RELACIO};
    
    public DBInterface(Context con){
        this.context = con;
        this.ajuda = new DBHelper(context);
        Log.d("DBInterface", "New DBInterface created");
    }

    //Obre la Base de dades
    public void obre() throws SQLException {
        bd = ajuda.getWritableDatabase();
        Log.d("DBInterface", "Open database");
    }

    //Tanca la Base de dades
    public void tanca() {
        ajuda.close();
        Log.d("DBInterface", "Close database");
    }

    public Rig createRig(Rig rig) {
        // insert d'un nou rig
        ContentValues values = new ContentValues();
        values.put(DBHelper.CLAU_NOM_RIG, rig.getNom());
        values.put(DBHelper.CLAU_DESCRIPCIO_RIG, rig.getDescripcio());
        long insertId = bd.insert(DBHelper.DB_TAULA_RIG, null, values);
        rig.setRig_id(insertId);
        Log.d("DBInterface", "New rig created");
        return rig;
    }

    public Grafica createGrafica(Grafica grafica) {
        // insert d'un nou rig
        ContentValues values = new ContentValues();
        values.put(DBHelper.CLAU_NOM_GRAFICA, grafica.getNom());
        values.put(DBHelper.CLAU_HASHRATE_GRAFICA, grafica.getHashrate());
        values.put(DBHelper.CLAU_IMATGE_GRAFICA, grafica.getImagte());

        long insertId = bd.insert(DBHelper.DB_TAULA_GRAFICA, null, values);
        grafica.setId_grafica(insertId);
        Log.d("DBInterface", "Grafica creada");
        return grafica;
    }

    public void relacionaGraficaRig(long idRig, Grafica gpu) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CLAU_ID_RIG_RELACIO, idRig);
        values.put(DBHelper.CLAU_ID_GRAFICA_RELACIO, gpu.getId_grafica());
        values.put(DBHelper.CLAU_HASHRATE_RELACIO, gpu.getHashrate());

        long insertId = bd.insert(DBHelper.DB_TAULA_RELACIO, null, values);

        Log.d("relacionaGraficaRig", "Grafica "+gpu.getId_grafica() + " relacionada amb rig "+idRig);
    }

    public ArrayList<Grafica> getAllGrafiques() {
        ArrayList<Grafica> grafiques = new ArrayList<Grafica>();
        Cursor cursor = bd.query(DBHelper.DB_TAULA_GRAFICA, allColumnsGrafica, null, null, null, null, DBHelper.CLAU_NOM_GRAFICA + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Grafica grafica = new Grafica();
            grafica.setId_grafica(cursor.getLong(0));
            grafica.setNom(cursor.getString(1));
            grafica.setHashrate(cursor.getFloat(2));
            grafiques.add(grafica);
            Log.d("DBInterface", "Grafica added -> " + grafica.getNom());
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return grafiques;
    }

    public Grafica getGraficaById(long id) {
        Grafica grafica = new Grafica();
        Cursor cursor = bd.query(DBHelper.DB_TAULA_GRAFICA, allColumnsGrafica, DBHelper.CLAU_ID_GRAFICA+"="+id, null, null, null, DBHelper.CLAU_NOM_GRAFICA + " ASC");
        if(cursor.moveToFirst()){
            grafica = new Grafica();
            grafica.setId_grafica(cursor.getLong(0));
            grafica.setNom(cursor.getString(1));
            grafica.setHashrate(cursor.getFloat(2));
            grafica.setImagte(cursor.getBlob(3));
        }
        // Make sure to close the cursor
        cursor.close();
        return grafica;
    }


    public ArrayList<Rig> getAllRigs() {
        ArrayList<Rig> rigs = new ArrayList<Rig>();
        Cursor cursor = bd.query(DBHelper.DB_TAULA_RIG, allColumnsRig, null, null, null, null, DBHelper.CLAU_NOM_RIG + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rig rig = cursorToRig(cursor);
            rigs.add(rig);
            Log.d("DBInterface", "Rig added -> " + rig.getNom());
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return rigs;
    }

    public Rig getRigById(long id){
        Rig rig = new Rig();
        Cursor b = bd.query(DBHelper.DB_TAULA_RIG, allColumnsRig, DBHelper.CLAU_ID_RIG+"="+id, null,null,null,null,null);
        if(b.moveToFirst()){
            rig = new Rig();
            rig.setRig_id(b.getLong(0));
            rig.setNom(b.getString(1));
            rig.setDescripcio(b.getString(2));
            rig.setGrafiques(getGrafiquesByRigId(rig.getRig_id()));
            b.close();
        }
        return rig;
    }

    public Rig_Grafica getRelacioById(long id){
        Rig_Grafica rel = new Rig_Grafica();
        Cursor cursor = bd.query(DBHelper.DB_TAULA_RELACIO, allColumnsRelacio, DBHelper.CLAU_ID_RELACIO+"="+id, null,null,null,null,null);
        if(cursor.moveToFirst()){
            rel = new Rig_Grafica();
            rel.setId_relacio(cursor.getLong(0));
            rel.setId_rig(cursor.getLong(1));
            rel.setId_grafica(cursor.getLong(2));
            rel.setHashrate_real(cursor.getFloat(3));
            rel.setGrafica(getGraficaById(rel.getId_grafica()));
            cursor.close();
        }
        return rel;
    }

    public ArrayList<Rig_Grafica> getGrafiquesByRigId(long id)
    {
        ArrayList<Rig_Grafica> rels = new ArrayList<Rig_Grafica>();
        Cursor cursor = bd.query(DBHelper.DB_TAULA_RELACIO, allColumnsRelacio, DBHelper.CLAU_ID_RIG_RELACIO+"="+id, null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rig_Grafica rel =  new Rig_Grafica();
            rel.setId_relacio(cursor.getLong(0));
            rel.setId_rig(cursor.getLong(1));
            rel.setId_grafica(cursor.getLong(2));
            rel.setHashrate_real(cursor.getFloat(3));
            rel.setGrafica(getGraficaById(rel.getId_grafica()));
            Log.d("getGrafiquesByRigId", rel.getGrafica().getNom());
            rels.add(rel);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return rels;
    }

    private Rig cursorToRig(Cursor cursor) {
        Rig rig = new Rig();
        rig.setRig_id(cursor.getLong(0));
        rig.setNom(cursor.getString(1));
        rig.setDescripcio(cursor.getString(2));
        rig.setGrafiques(getGrafiquesByRigId(rig.getRig_id()));
        return rig;
    }

    public void updateHashrate(long idRel, float hashRate){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.CLAU_HASHRATE_RELACIO,hashRate);
        bd.update(DBHelper.DB_TAULA_RELACIO, cv,DBHelper.CLAU_ID_RELACIO + "=" + idRel, null);
    }

    public boolean deleteRig(long id)
    {
        bd.delete(DBHelper.DB_TAULA_RELACIO, DBHelper.CLAU_ID_RIG_RELACIO + "=" + id, null);
        return bd.delete(DBHelper.DB_TAULA_RIG, DBHelper.CLAU_ID_RIG + "=" + id, null) > 0;
    }

    public void deleteRels(long rigId)
    {
        bd.delete(DBHelper.DB_TAULA_RIG, DBHelper.CLAU_ID_RIG + "=" + rigId, null);
    }

    public void deleteGraficaByRig(long id) {
        bd.delete(DBHelper.DB_TAULA_RELACIO, DBHelper.CLAU_ID_RELACIO + "=" + id, null);
    }

    public void buidaRigs()
    {
        bd.execSQL("delete from "+DBHelper.DB_TAULA_RIG+";");
    }

    public void buidaGrafiques()
    {
        bd.execSQL("delete from "+DBHelper.DB_TAULA_GRAFICA+";");
    }
}



