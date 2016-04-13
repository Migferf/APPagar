package com.example.nunse.appagar.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nunse.appagar.conf.DBConf;

/**
 * Created by nunse on 13/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = DBConf.get("DATABASE_NAME");
    private static final int version = 1;

    private static final String CREATE_CONTACTOS = DBConf.get("CREATE_CONTACTOS");
    private static final String CREATE_DEUDAS = DBConf.get("CREATE_DEUDAS");

    private static final String DROP_CONTACTOS = DBConf.get("DROP_CONTACTOS");
    private static final String DROP_DEUDAS = DBConf.get("DROP_DEUDAS");

    public DBHelper(Context context) {


        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTACTOS);
        db.execSQL(CREATE_DEUDAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_CONTACTOS);
        db.execSQL(DROP_DEUDAS);
        onCreate(db);
    }
}
