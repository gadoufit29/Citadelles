package org.project.modele;

import java.util.ArrayList;

public class Plateau {
    private Personnage[] listePersonnages;

    private Joueur[] listeJoueurs;

    private Pioche pioche;
    private int nbPersonnages;



    private int nbJoueurs;

    public Plateau() {
        // Initialiser les tableaux avec une taille de 9
        this.listePersonnages = new Personnage[9];
        this.listeJoueurs = new Joueur[9];

        // Initialiser la pioche en créant une instance
        this.pioche = new Pioche();

        // Initialiser les compteurs à zéro
        this.nbPersonnages = 0;
        this.nbJoueurs = 0;
    }

    // Ajoutez ici d'autres méthodes ou fonctionnalités de la classe Plateau au besoin

    public int getNbPersonnages() {return nbPersonnages;}

    public Pioche getPioche() {return pioche;}

    public int getNbJoueurs() {return nbJoueurs;}

    public Joueur getJoueur(int i) {
        if (i >= 0 && i < listeJoueurs.length) {
            return listeJoueurs[i];
        } else {
            return null;
        }
    }

    public Personnage getPersonnage(int i) {
        if (i >= 0 && i < listePersonnages.length) {
            return listePersonnages[i];
        } else {
            return null;
        }
    }

    public void ajouterPersonnage(Personnage nouveauPersonnage) {
        if (nouveauPersonnage != null && nbPersonnages < listePersonnages.length) {
            listePersonnages[nbPersonnages] = nouveauPersonnage;
            nouveauPersonnage.setPlateau(this);
            nbPersonnages++;
        }
    }

    public void ajouterJoueur(Joueur nouveauJoueur) {
        if (nouveauJoueur != null && nbJoueurs < listeJoueurs.length) {
            listeJoueurs[nbJoueurs] = nouveauJoueur;
            nbJoueurs++;
        }
    }



}

