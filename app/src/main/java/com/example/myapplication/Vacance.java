package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Vacance implements Serializable {
    String titre, description;
    String time;
    int idImage;
    public Vacance(String titre, String description, int idImage, String time ) {
        this.titre = titre;
        this.description = description;
        this.idImage=idImage;
        this.time=time;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }


// avec des getteurs et setteurs
}
