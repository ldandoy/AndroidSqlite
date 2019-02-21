package com.example.loicdandoy.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by loicdandoy on 13/02/2018.
 */

public class MaBaseSQLite extends SQLiteOpenHelper {
    private String sql_create;
    private String sql_drop;

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String table, String sql) {
        super(context, name, factory, version);
        this.sql_create = sql;
        this.sql_drop = "DROP TABLE " + table + ";";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        Log.i("MyApp", this.sql_create);
        db.execSQL(this.sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL(this.sql_drop);
        onCreate(db);
    }
}
