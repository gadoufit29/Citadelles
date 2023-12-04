package org.project.modele;

public class Roi extends Personnage {
    public Roi(String nom, int rang, String caracteristiques, int nbPermisDeConstruire) {
        super(nom, rang, caracteristiques, nbPermisDeConstruire);
    }

    /*public void percevoirRessourcesSpecifiques() {
        if (plateau != null) {
            for (Personnage personnage : plateau.getListePersonnages()) {
                personnage.retirerCouronne();
            }
        }
    }*/

}

