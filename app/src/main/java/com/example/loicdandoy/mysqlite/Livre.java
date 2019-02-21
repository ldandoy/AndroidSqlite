package com.example.loicdandoy.mysqlite;

/**
 * Created by loicdandoy on 13/02/2018.
 */

public class Livre {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String isbn;
    private String titre;

    public Livre(){}

    public Livre(String isbn, String titre){
        this.isbn = isbn;
        this.titre = titre;

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString(){
        return "ID : "+id+"\nISBN : "+isbn+"\nTitre : "+titre;
    }

}
