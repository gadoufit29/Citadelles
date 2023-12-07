package org.project.modele;

import java.util.ArrayList;

public class PlateauDeJeu {
    private ArrayList<Joueur> listeJoueurs;
    private ArrayList<Personnage> listePersonnages;
    private Pioche pioche;
    private int nbPersonnages;
    private int nbJoueurs;

    public PlateauDeJeu() {
        this.listePersonnages = new ArrayList<>();
        this.listeJoueurs = new ArrayList<>();

        // Initialiser la pioche en créant une instance
        this.pioche = new Pioche();

        // Initialiser les compteurs à zéro
        this.nbPersonnages = 0;
        this.nbJoueurs = 0;
    }

    public PlateauDeJeu(Pioche pioche){
        this.listePersonnages = new ArrayList<>();
        this.listeJoueurs = new ArrayList<>();

        // Initialiser la pioche en créant une instance
        this.pioche = pioche;

        // Initialiser les compteurs à zéro
        this.nbPersonnages = 0;
        this.nbJoueurs = 0;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public int getNbPersonnages() {
        return nbPersonnages;
    }

    public Pioche getPioche() {
        return pioche;
    }

    public Joueur getJoueur(int i) {
        if (i >= 0 && i < listeJoueurs.size()) {
            return listeJoueurs.get(i);
        } else {
            return null;
        }
    }

    public Personnage getPersonnage(int i) {
        if (i >= 0 && i < listePersonnages.size()) {
            return listePersonnages.get(i);
        } else {
            return null;
        }
    }

    public void ajouterPersonnage(Personnage nouveauPersonnage) {
        if (nouveauPersonnage != null && listePersonnages.size() < 9) {
            listePersonnages.add(nouveauPersonnage);
            nouveauPersonnage.setPlateau(this);
            nbPersonnages++;
        }
    }

    public void ajouterJoueur(Joueur nouveauJoueur) {
        if (nouveauJoueur != null && listeJoueurs.size() < 9) {
            listeJoueurs.add(nouveauJoueur);
            nbJoueurs++;
        }
    }

    public ArrayList<Personnage> getListePersonnages() {
        return listePersonnages;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

}

