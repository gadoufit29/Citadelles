package org.project.modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor;
    private ArrayList<Quartier> cite, mainJoueur;
    private boolean possedeCouronne;
    protected Personnage monPersonnage;

    public Joueur(String nomJoueur){
        this.nom = nomJoueur;
        this.tresor = 0;
        this.possedeCouronne = false;
        this.mainJoueur = new ArrayList<Quartier>();
        this.cite = new ArrayList<Quartier>();
        monPersonnage = null;
    }

    public String getNom() {
        return nom;
    }

    public int getTresor() {
        return tresor;
    }

    public Personnage getPersonnage(){
        return monPersonnage;
    }

    public void ajouterPieces(int pieces){
        if(pieces > 0){
            this.tresor += pieces;
        }
    }

    public void retirerPieces(int pieces){
        if(pieces > 0 && this.tresor >= pieces){
            this.tresor -= pieces;
        }
    }

    public ArrayList<Quartier> getCite() {
        return cite;
    }

    public int nbQuartiersDansCite(){
        return this.cite.size();
    }

    public void ajouterQuartierDansCite(Quartier quartier){
        this.cite.add(quartier);
    }

    public boolean quartierPresentDansCite(String nomQuartier){
        for(Quartier quartier : this.cite){
            if(quartier.getNom().equals(nomQuartier)){
                return true;
            }
        }
        return false;
    }

    public void retirerQuartierDansCite(String nomQuartier){
        for(Quartier quartier : this.cite){
            if(quartier.getNom().equals(nomQuartier)){
                this.cite.remove(quartier);
            }
        }
    }

    public ArrayList<Quartier> getMainJoueur() {
        return mainJoueur;
    }

    public int nbQuartiersDansMain(){
        return this.mainJoueur.size();
    }

    public void ajouterQuartierDansMain(Quartier quartier){
        this.mainJoueur.add(quartier);
    }

    public Quartier retirerQuartierDansMain(){
        Random generateur = new Random();
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        if(this.nbQuartiersDansMain() == 0){
            return null;
        }
        else{
            return this.mainJoueur.remove(numeroHasard);
        }
    }

    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) {
        this.possedeCouronne = b;
    }

    public void reinitialiser() {
        this.tresor = 0;
        this.mainJoueur.clear();
        this.cite.clear();
    }
}
