package org.project.modele;

public class Quartier {
    private String nom, type, caracteristiques;
    private int coutConstruction;
    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX","MILITAIRE","NOBLE","COMMERCANT", "MERVEILLE"};

    public Quartier(){
        setNom("");
        setType("");
        setCoutConstruction(0);
        setCaracteristiques("");
    }

    public Quartier(String nom, String type, int coutConstruction){
        setNom(nom);
        setType(type);
        setCoutConstruction(coutConstruction);
        setCaracteristiques("");
    }

    public Quartier(String nom, String type, int coutConstruction, String caracteristiques){
        setNom(nom);
        setType(type);
        setCoutConstruction(coutConstruction);
        setCaracteristiques(caracteristiques);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        for(int i = 0; i < TYPE_QUARTIERS.length; i++){
            if(type.equals(TYPE_QUARTIERS[i])){
                this.type = type;
                return;
            }
        }
        this.type = "";
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public int getCoutConstruction() {
        return coutConstruction;
    }

    public void setCoutConstruction(int coutConstruction) {
        if(coutConstruction >= 1 && coutConstruction <= 6)
            this.coutConstruction = coutConstruction;
        else
            this.coutConstruction = 0;
    }
}
