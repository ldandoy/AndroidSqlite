package com.example.loicdandoy.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MyApp", "Test");

        // Création d'une instance de ma classe LivresBDD
        LivresBDD livreBdd = new LivresBDD(getBaseContext());

        // Création d'un livre
        Livre livre = new Livre("123456789", "Programmez pour Android");

        // On ouvre la base de données pour écrire dedans
        livreBdd.open();

        // On insère le livre que l'on vient de créer
        livreBdd.insertLivre(livre);

        // On ferme la base de données
        livreBdd.close();
    }
}
