package org.project.modele;

import org.project.controleur.Interaction;

import java.util.Random;

public abstract class Personnage {

    // Attributs
    private String nom, caracteristiques;
    private int rang, nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine, estVole;
    private PlateauDeJeu plateau;

    public Personnage(String nom, int rang, String caracteristiques, int nbPermisDeConstruire) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.nbPermisDeConstruire = nbPermisDeConstruire;
        this.joueur = null;
        this.estAssassine = false;
        this.estVole = false;
    }

    //Getters et setters

    public String getNom() {
        return nom;
    }

    public int getRang() {
        return rang;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public int getNbPermisDeConstruire() {
        return nbPermisDeConstruire;
    }

    public boolean getEstAssassine() {
        return estAssassine;
    }

    public boolean getEstVole() {
        return estVole;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur j) {
        this.joueur = j;
        this.joueur.monPersonnage = this;
    }

    public void setEstVole() {
        this.estVole = true;
    }

    public void setEstAssassine() {
        this.estAssassine = true;
    }

    public PlateauDeJeu getPlateau() {
        return plateau;
    }

    public void setPlateau(PlateauDeJeu plateau) {
        this.plateau = plateau;
    }

    public void ajouterPieces() {
        if (getJoueur() != null && !getEstAssassine()){
            getJoueur().ajouterPieces(2);
            System.out.println("Ajout de deux pièces au trésor de " + getJoueur().getNom());
        }

        else if(getJoueur() == null){
            System.out.println("Aucune pièce ajoutée. Le personnage n'est pas associé à un joueur");
        }

        else if(getEstAssassine()){
            System.out.println("Aucune pièce ajoutée. Le personnage est assassiné");
        }

        else{
            System.out.println("Aucune pièce ajoutée. Erreur inconnue");
        }
    }

    public void ajouterQuartierDansMain(Quartier nouveau) {
        if (getJoueur() != null && !getEstAssassine()) {
            getJoueur().ajouterQuartierDansMain(nouveau);
        }
        else if(getJoueur() == null){
            System.out.println("Aucun quartier ajouté. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine() == true){
            System.out.println("Aucun quartier ajouté. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun quartier ajouté. Erreur inconnue");
        }
    }

    public void construire(Quartier nouveau) {
        if (getJoueur() != null && !getEstAssassine()) {
            getJoueur().ajouterQuartierDansCite(nouveau);
            System.out.println("Construction du quartier " + nouveau.getNom() + " dans la cité du joueur " + getJoueur().getNom());
        }
        else if(getJoueur() == null){
            System.out.println("Aucun quartier construit. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine()){
            System.out.println("Aucun quartier construit. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun quartier construit. Erreur inconnue");
        }
    }

    public void percevoirRessourcesSpecifiques() {
        if (getJoueur() != null && !getEstAssassine()) {
            Random generateur = new Random();
            int nbCartesMerveille = 0;
            for(Quartier quartier : this.joueur.getCite()){
                if(quartier.getNom().equals("Forge")){
                    System.out.println();
                    System.out.println("***** Merveille : Forge *****");
                    System.out.println();
                    System.out.println("Voulez-vous payer 2 pièces d'or pour piocher 3 cartes ? (o / oui ; n / non)");
                    if(this.joueur == this.plateau.getJoueur(0)){
                        Boolean reponse = Interaction.lireOuiOuNon();
                        if(reponse){
                            this.joueur.retirerPieces(2);
                            for(int i = 0; i < 3; i++){
                                this.joueur.ajouterQuartierDansMain(this.plateau.getPioche().piocher());
                            }

                        }
                    }

                    else {
                        Boolean reponse = generateur.nextBoolean();
                        if(reponse){
                            System.out.println("oui");
                            this.joueur.retirerPieces(2);
                            for(int i = 0; i < 3; i++){
                                this.joueur.ajouterQuartierDansMain(this.plateau.getPioche().piocher());
                            }
                        }
                    }

                    nbCartesMerveille++;
                }

                if(quartier.getNom().equals("Laboratoire")){
                    System.out.println();
                    System.out.println("***** Merveille : Laboratoire *****");
                    System.out.println();
                    System.out.println("Voulez-vous vous défaussez d'une carte pour recevoir 2 pièces d'or ? (o / oui ; n / non)");
                    if(this.joueur == this.plateau.getJoueur(0)){
                        Boolean reponse = Interaction.lireOuiOuNon();
                        if(reponse){
                            System.out.println("Quel quartier voulez-vous défausser ? (0 pour annuler)");
                            int j = 1;
                            for(Quartier listeQuartier : this.joueur.getMainJoueur()){
                                StringBuilder stringBuilder = new StringBuilder("\t " + j + ". " + listeQuartier.getNom() + " (Cout de construction " + listeQuartier.getCoutConstruction() + ")");
                                System.out.println(stringBuilder);
                                j++;
                            }

                            int choix = Interaction.lireUnEntier(0, this.joueur.nbQuartiersDansMain() + 1);
                            if(choix == 0){
                                break;
                            }
                            else{
                                this.plateau.getPioche().ajouter(this.joueur.getMainJoueur().get(choix - 1));
                                this.joueur.retirerQuartierDansMain(choix - 1);
                                this.joueur.ajouterPieces(2);
                            }
                        }
                    }
                    else{
                        Boolean reponse = generateur.nextBoolean();
                        if(reponse){
                            System.out.println("Quel quartier voulez-vous défausser ?");
                            int j = 1;
                            for(Quartier listeQuartier : this.joueur.getMainJoueur()){
                                StringBuilder stringBuilder = new StringBuilder("\t " + j + ". " + listeQuartier.getNom() + " (Cout de construction " + listeQuartier.getCoutConstruction() + ")");
                                System.out.println(stringBuilder);
                                j++;
                            }

                            int choix = generateur.nextInt(this.joueur.nbQuartiersDansMain() + 1);
                            if(choix == 0){
                                break;
                            }
                            else{
                                this.plateau.getPioche().ajouter(this.joueur.getMainJoueur().get(choix - 1));
                                this.joueur.retirerQuartierDansMain(choix - 1);
                                this.joueur.ajouterPieces(2);
                            }
                        }
                    }

                    nbCartesMerveille++;
                }

                if(quartier.getNom().equals("École de Magie")){
                    System.out.println();
                    System.out.println("***** Merveille : École de Magie *****");
                    System.out.println();
                    System.out.println("Quel type de quartier voulez-vous que votre merveille \"École de Magie\" prenne durant ce tour ?");
                    System.out.println("\t 1. Religieux");
                    System.out.println("\t 2. Militaire");
                    System.out.println("\t 3. Noble");
                    System.out.println("\t 4. Commerçant");
                    System.out.println("\t 5. Merveille");

                    int choix = 0;

                    if(this.joueur == this.plateau.getJoueur(0))
                        choix = Interaction.lireUnEntier(1, 6);

                    else
                        choix = generateur.nextInt(5) + 1;


                    switch (choix){
                        case 1:
                            System.out.println("\"L'École de magie\" a pour ce tour le type suivant : Religieux.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[0]);
                            break;
                        case 2:
                            System.out.println("\"L'École de magie\" a pour ce tour le type suivant : Militaire.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[1]);
                            break;
                        case 3:
                            System.out.println("\"L'École de magie\" a pour ce tour le type suivant : Noble.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[2]);
                            break;
                        case 4:
                            System.out.println("\"L'École de magie\" a pour ce tour le type suivant : Commerçant.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[3]);
                            break;
                        default:
                            System.out.println("\"L'École de magie\" a pour ce tour le type suivant : Merveille.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[4]);
                            break;
                    }
                }

                if(quartier.getType() == "MERVEILLE")
                    nbCartesMerveille++;
            }

            if(nbCartesMerveille == 0){
                System.out.println("Aucune merveille qui permet d'avoir une ressource spécifique");
                System.out.println();
            }

            if(this.joueur.getPersonnage().getNom().equals("Assassin") || this.joueur.getPersonnage().getNom().equals("Voleur") || this.joueur.getPersonnage().getNom().equals("Magicienne")){
                System.out.println("Pas de ressources spéicifiques liés à votre personnage");
                System.out.println();
            }
        }
        else if(getJoueur() == null){
            System.out.println("Aucune ressource spécifique. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine()){
            System.out.println("Aucune ressource spécifique. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucune ressource spécifique. Erreur inconnue");
        }
    }

    public void utiliserPouvoir() {
        if (getJoueur() != null && !getEstAssassine()) {
            System.out.println("Aucun pouvoir");
        }
        else if(getJoueur() == null){
            System.out.println("Aucun pouvoir. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine()){
            System.out.println("Aucun pouvoir. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun pouvoir. Erreur inconnue");
        }
    }

    public abstract void utiliserPouvoirAvatar();

    public void reinitialiser() {
        this.estAssassine = false;
        this.estVole = false;
        if(this.joueur != null)
            this.joueur.monPersonnage = null;
        this.joueur = null;
    }
}








