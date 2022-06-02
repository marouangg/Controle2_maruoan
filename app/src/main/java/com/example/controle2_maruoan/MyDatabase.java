package com.example.controle2_maruoan;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyDatabase extends SQLiteOpenHelper {


    public  static String DATABASE_NAME="Entreprise.db";
    public  static String TABLE_NAME="Entreprise";



    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,raison_social TEXT,adress TEXT,capital double)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);
    }

    public  static ArrayList<Entreprise> getAllEntreprise(SQLiteDatabase db)
    {
        ArrayList<Entreprise> lp = new ArrayList<>();
        Cursor c = db.rawQuery("select * from "+TABLE_NAME,null);

        while (c.moveToNext())
        {
            Entreprise p = new Entreprise();
            p.setId(c.getInt(0));
            p.setRaison_social(c.getString(1));
            p.setAdress(c.getString(2));
            p.setCapital(c.getDouble(3));


            lp.add(p);

        }

        return lp;
    }

    public static long AddEntreprise(SQLiteDatabase db,Entreprise p)
    {
        ContentValues c = new ContentValues();

        c.put("raison_social",p.getRaison_social());
        c.put("adress",p.getAdress());
        c.put("capital",p.getCapital());


        long l = db.insert(TABLE_NAME,null,c);
        return l;
    }


    public static long DeleteEntreprise(SQLiteDatabase db,int id)
    {

        long l = db.delete(TABLE_NAME,"ID="+id,null);
        return l;
    }

    public static long UpdateEntreprise(SQLiteDatabase sqLiteDatabase, Entreprise p){
        ContentValues ct = new ContentValues();

        ct.put("ID",p.getId());
        ct.put("raison_social",p.getRaison_social());
        ct.put("adress",p.getAdress());
        ct.put("capital",p.getCapital());

        return sqLiteDatabase.update(TABLE_NAME,ct,"id = ?",new String[] {String.valueOf(p.getId())});
    }

    public static Entreprise getOneEntreprise(SQLiteDatabase sqLiteDatabase, int id){
        Entreprise p = null;

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + id,null);

        if(cur.moveToNext()){
            p = new Entreprise();
            p.setId(cur.getInt(0));
            p.setRaison_social(cur.getString(1));
            p.setAdress(cur.getString(2));
            p.setCapital(cur.getDouble(3));

        }

        return p;
    }
}
