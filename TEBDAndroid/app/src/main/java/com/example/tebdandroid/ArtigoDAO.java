package com.example.tebdandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class ArtigoDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "BancoArtigos";
    private static final int VERSAO = 1;

    public ArtigoDAO(Context context){
        super(context, DATABASE, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE Artigo (id INTEGER PRIMARY KEY,"+" artigo TEXT UNIQUE NOT NULL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int velha, int nova) {
        String ddl = "DROP TABLE IF EXISTS Artigo";
        db.execSQL(ddl);
        onCreate(db);
    }

    public void dropAll(){
        String ddl ="DROP TABLE IF EXISTS Artigo";
        getWritableDatabase().execSQL(ddl);
        onCreate( getWritableDatabase());
    }

    public void salvar(ArtigoValue artigoValue){
        ContentValues values = new ContentValues();
        values.put("artigo", artigoValue.getArtigo());

        getWritableDatabase().insert("Artigo",null,values);
    }

    public List getLista(){
        List<ArtigoValue> artigos = new LinkedList<ArtigoValue>();

        String query = "SELECT * FROM Artigo order by artigo";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        ArtigoValue artigo = null;
        if(cursor.moveToFirst()){
            do{
                artigo = new ArtigoValue();
                artigo.setId(Long.parseLong(cursor.getString(0)));
                artigo.setArtigo(cursor.getString(1));
                artigos.add(artigo);
            } while(cursor.moveToNext());
        }
        return artigos;
    }

    public void	deletar(ArtigoValue artigoValue) {
        String[] args = { artigoValue.getId().toString() };
        getWritableDatabase().delete("Artigo","id=?",	args);
    }

    public void	alterar(ArtigoValue artigo)	{
        ContentValues values = new ContentValues();
        values.put("artigo", artigo.getArtigo());
        getWritableDatabase().update("Artigo", values,"id=?",	new	String[]{artigo.getId().toString()});
    }

}
