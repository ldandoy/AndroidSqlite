package com.example.loicdandoy.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by loicdandoy on 13/02/2018.
 */

public class LivresBDD extends SQLiteOpenHelper {
    // Les contantes
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "demo.db";

    private static final String TABLE_LIVRES = "table_livres";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ISBN = "ISBN";
    private static final int NUM_COL_ISBN = 1;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 2;

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRES + " ("
        + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ISBN + " TEXT NOT NULL, "
        + COL_TITRE + " TEXT NOT NULL);";

    private static final String DROP_BDD = "DROP TABLE " + TABLE_LIVRES + ";";

    private SQLiteDatabase bdd;

    public LivresBDD(Context context){
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        Log.i("MyApp", CREATE_BDD);
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.DROP_BDD);
        onCreate(db);
    }

    public void open(){
        //on ouvre la BDD en écriture
        this.bdd = this.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        this.bdd.close();
    }

    public long insertLivre(Livre livre){

        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        values.put(COL_ISBN, livre.getIsbn());
        values.put(COL_TITRE, livre.getTitre());

        //on insère l'objet dans la BDD via le ContentValues
        return this.bdd.insert(TABLE_LIVRES, null, values);
    }

    public Livre getLivreWithTitre(String titre){
        /**
         * Table (FROM)
         * Champs (SELECT)
         * condition (WHERE)
         **/
        Cursor c = bdd.query(
                TABLE_LIVRES,
                new String[] {COL_ID, COL_ISBN, COL_TITRE},
                COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null
        );

        return cursorToLivre(c);
    }

    private Livre cursorToLivre(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Livre livre = new Livre();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        livre.setId(c.getInt(NUM_COL_ID));
        livre.setIsbn(c.getString(NUM_COL_ISBN));
        livre.setTitre(c.getString(NUM_COL_TITRE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return livre;
    }
}
