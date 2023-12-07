package org.project.application;

import org.project.controleur.Interaction;
import org.project.modele.Joueur;
import org.project.modele.Personnage;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Quartier;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu(){
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer(){
        System.out.println("Bienvenue dans Citadelle !");
        System.out.println();
        System.out.println("\t 1. Afficher les règles");
        System.out.println("\t 2. Jouer une partie");
        System.out.println("\t 3. Quitter");

        int choix = Interaction.lireUnEntier(1, 4);
        if(choix == 1){
            afficherLesRegles();
        }
        else if(choix == 2){
            jouerPartie();
        }
        else{
            System.out.println("Au revoir !");
            System.exit(0);
        }
    }

    private void afficherLesRegles(){
        System.out.println("Voici les règles du jeu : ");
        System.out.println();
        System.out.println("Le but du jeu est d'être le premier à construire 8 quartiers dans sa cité.");
        System.out.println("Pour cela, chaque joueur va incarner un personnage qui aura un pouvoir particulier.");
    }

    private void jouerPartie(){
        initialisation();
        choixPersonnages();
        /*do{
            choixPersonnages();
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
        }while(!partieFinie());

        calculDesPoints();*/
    }

    private void initialisation(){
        this.plateauDeJeu = Configuration.configurationDeBase();

        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");
        Joueur joueur3 = new Joueur("Joueur 3");
        Joueur joueur4 = new Joueur("Joueur 4");

        joueur1.ajouterPieces(2);
        joueur2.ajouterPieces(2);
        joueur3.ajouterPieces(2);
        joueur4.ajouterPieces(2);

        for(int i = 0; i < 4; i++){
            Quartier carte = plateauDeJeu.getPioche().piocher();
            joueur1.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = plateauDeJeu.getPioche().piocher();
            joueur2.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = plateauDeJeu.getPioche().piocher();
            joueur3.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = plateauDeJeu.getPioche().piocher();
            joueur4.ajouterQuartierDansMain(carte);
        }

        int choixCouronne = generateur.nextInt(4);

        switch (choixCouronne){
            case 0:
                joueur1.setPossedeCouronne(true);
                break;
            case 1:
                joueur2.setPossedeCouronne(true);
                break;
            case 2:
                joueur3.setPossedeCouronne(true);
                break;
            case 3:
                joueur4.setPossedeCouronne(true);
                break;
        }

        plateauDeJeu.ajouterJoueur(joueur1);
        plateauDeJeu.ajouterJoueur(joueur2);
        plateauDeJeu.ajouterJoueur(joueur3);
        plateauDeJeu.ajouterJoueur(joueur4);
    }

    private void choixPersonnages(){
        System.out.println("Choix des personnages : ");

        for (int i = 0; i < 3; i++) {
            if(i < 2){
                int indice = generateur.nextInt(plateauDeJeu.getNbPersonnages());
                System.out.println("Le personnage " + plateauDeJeu.getPersonnage(indice).getNom() + " est écarté face visible");
                plateauDeJeu.getListePersonnages().remove(indice);
                continue;
            }

            int indice = generateur.nextInt(plateauDeJeu.getNbPersonnages());
            System.out.println("Un personnage est écarté face cachée");
            plateauDeJeu.getListePersonnages().remove(indice);
        }

        int i = 0;
        ArrayList<Personnage> listePersonnages = this.plateauDeJeu.getListePersonnages();

        do {

            //Vérifier si le personnage actuelle a la couronne et a un personnage défini
            //Si il n'en a pas, commencer par lui puis faire la meme chose pour les autres
            //Si il en a un, passer au joueur suivant
            //...

            if(plateauDeJeu.getJoueur(i).getPersonnage() == null && plateauDeJeu.getJoueur(i).getPossedeCouronne()){
                System.out.println("Vous avez la couronne !");
                int j = 0;
                for(Personnage personnage : listePersonnages){
                    StringBuilder stringBuilder = new StringBuilder("\t " + j + (listePersonnages.indexOf(personnage) + 1) + ". " + personnage.getNom());
                    System.out.println(stringBuilder);
                    j++;
                }

                System.out.println("Quel personnage voulez-vous incarner ?");

                if(plateauDeJeu.getJoueur(i).getNom() == plateauDeJeu.getJoueur(0).getNom()){
                    int choixPersonnage = Interaction.lireUnEntier(1, listePersonnages.size() + 1);
                    plateauDeJeu.getPersonnage(choixPersonnage).setJoueur(plateauDeJeu.getJoueur(0));
                    System.out.println(plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + plateauDeJeu.getPersonnage(choixPersonnage).getNom());
                    plateauDeJeu.getListePersonnages().remove(choixPersonnage);
                    continue;
                }

                int choixPersonnage = generateur.nextInt(listePersonnages.size()) + 1;
                plateauDeJeu.getPersonnage(choixPersonnage).setJoueur(plateauDeJeu.getJoueur(i));
                System.out.println(plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + plateauDeJeu.getPersonnage(choixPersonnage).getNom());
                plateauDeJeu.getListePersonnages().remove(choixPersonnage);
                i++;
            }

            else if(plateauDeJeu.getJoueur(i).getPersonnage() == null && !plateauDeJeu.getJoueur(i).getPossedeCouronne()){

            }

        }while(i != plateauDeJeu.getListeJoueurs().size());
    }

    private void tourDeJeu(){

    }

    private void percevoirRessources(Personnage p){

    }

    private void percevoirRessourcesAvatar(Personnage p){

    }

    private void construire(Personnage p){

    }

    private void construireAvatar(Personnage p){

    }

    private void gestionCouronne(){

    }

    private void reinitialisationPersonnages(){

    }

    private boolean partieFinie(){
        return true;
    }

    private void calculDesPoints(){

    }
}
