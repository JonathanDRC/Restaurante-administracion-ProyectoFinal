package com.example.restaurante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREATE_TABLA_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versioNueva) {
        db.execSQL("DROP TABLE IF EXISTS PEDIDOS");
        onCreate(db);
    }
}
