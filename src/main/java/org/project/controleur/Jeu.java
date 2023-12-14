package org.project.controleur;

import org.project.application.Configuration;
import org.project.modele.Joueur;
import org.project.modele.Personnage;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Quartier;
import org.project.utils.Colors;
import java.util.ArrayList;
import java.util.Random;

public class Jeu{
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu(){
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer(){
        do {
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
                return;
            }
            else{
                System.out.println("Au revoir !");
                return;
            }
        }while(true);
    }

    private void afficherLesRegles(){
        System.out.println("Voici les règles du jeu : ");
        System.out.println();
        System.out.println("Le but du jeu est d'être le premier à construire 8 quartiers dans sa cité.");
        System.out.println("Pour cela, chaque joueur va incarner un personnage qui aura un pouvoir particulier.");
    }

    private void jouerPartie(){
        initialisation();
        do{
            choixPersonnages();
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
            partieFinie();
        }while(!partieFinie());

        calculDesPoints();
    }

    private void initialisation(){
        this.plateauDeJeu = Configuration.configurationDeBase();

        Joueur joueur1 = new Joueur("Joueur 1");
        Joueur joueur2 = new Joueur("Joueur 2");
        Joueur joueur3 = new Joueur("Joueur 3");
        Joueur joueur4 = new Joueur("Joueur 4");

        joueur1.ajouterPieces(10);
        joueur2.ajouterPieces(2);
        joueur3.ajouterPieces(2);
        joueur4.ajouterPieces(2);

        Quartier quartier0 = new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1);
        joueur1.ajouterQuartierDansCite(quartier0);

        Quartier quartier2 = new Quartier("Tripot", Quartier.TYPE_QUARTIERS[4], 6);
        joueur1.ajouterQuartierDansMain(quartier2);

        Quartier quartier1 = new Quartier("Manufacture", Quartier.TYPE_QUARTIERS[4], 1);
        joueur1.ajouterQuartierDansCite(quartier1);

        //Quartier quartier4 = new Quartier("Forge", Quartier.TYPE_QUARTIERS[0], 1);
        //joueur1.ajouterQuartierDansCite(quartier4);


        for(int i = 0; i < 4; i++){
            Quartier carte = this.plateauDeJeu.getPioche().piocher();
            joueur1.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = this.plateauDeJeu.getPioche().piocher();
            joueur2.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = this.plateauDeJeu.getPioche().piocher();
            joueur3.ajouterQuartierDansMain(carte);
        }

        for(int i = 0; i < 4; i++){
            Quartier carte = this.plateauDeJeu.getPioche().piocher();
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

        this.plateauDeJeu.ajouterJoueur(joueur1);
        this.plateauDeJeu.ajouterJoueur(joueur2);
        this.plateauDeJeu.ajouterJoueur(joueur3);
        this.plateauDeJeu.ajouterJoueur(joueur4);
    }

    private void choixPersonnages(){
        System.out.println("Choix des personnages : ");
        ArrayList<Personnage> listePersonnages = new ArrayList<>(this.plateauDeJeu.getListePersonnages());

        for (int index = 0; index < 3; index++) {
            int indice = generateur.nextInt(listePersonnages.size());

            if (index < 2) {
                System.out.println("Le personnage " + listePersonnages.get(indice).getNom() + " est écarté face visible");
                if(listePersonnages.get(indice).getNom().equals("Roi")){
                    System.out.println("Le roi est présent dans les cartes faces visibles. Redémarrage de la partie");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    choixPersonnages();
                }
            } else {
                System.out.println("Un personnage est écarté face cachée");
            }
            listePersonnages.remove(indice);
        }
        System.out.println();

        int indexOfCrown = 0;
        for(Joueur joueur : this.plateauDeJeu.getListeJoueurs()){
            if(joueur.getPossedeCouronne()){
                indexOfCrown = this.plateauDeJeu.getListeJoueurs().indexOf(joueur);
            }
        }

        for(int i = indexOfCrown; i < this.plateauDeJeu.getNbJoueurs(); i++){

            if(this.plateauDeJeu.getJoueur(i).getPersonnage() == null && this.plateauDeJeu.getJoueur(i).getPossedeCouronne()){

                System.out.println();
                System.out.println("****************************************");
                System.out.println("Tour de " + this.plateauDeJeu.getJoueur(i).getNom());
                System.out.println("****************************************");
                System.out.println();

                System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a la couronne !");
                System.out.println();
                int j = 1;
                for(Personnage personnage : listePersonnages){
                    StringBuilder stringBuilder = new StringBuilder("\t " + j + ". " + personnage.getNom() + " (Rang " + personnage.getRang() + ")");
                    System.out.println(stringBuilder);
                    j++;
                }

                System.out.println();
                System.out.println("Quel personnage voulez-vous incarner ?");

                if(plateauDeJeu.getJoueur(i).getNom() == this.plateauDeJeu.getJoueur(0).getNom()){
                    int choixPersonnage = Interaction.lireUnEntier(1, listePersonnages.size() + 1);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(0));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                }

                else{
                    int choixPersonnage = generateur.nextInt(listePersonnages.size()) + 1;
                    System.out.println(choixPersonnage);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(i));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                }
            }

            else if(this.plateauDeJeu.getJoueur(i).getPersonnage() == null && !this.plateauDeJeu.getJoueur(i).getPossedeCouronne()){

                System.out.println();
                System.out.println("****************************************");
                System.out.println("Tour de " + this.plateauDeJeu.getJoueur(i).getNom());
                System.out.println("****************************************");
                System.out.println();

                int j = 1;
                for(Personnage personnage : listePersonnages){
                    StringBuilder stringBuilder = new StringBuilder("\t " + j + ". " + personnage.getNom() + " (Rang " + personnage.getRang() + ")");
                    System.out.println(stringBuilder);
                    j++;
                }

                System.out.println();
                System.out.println("Quel personnage voulez-vous incarner ?");

                if(plateauDeJeu.getJoueur(i).getNom() == this.plateauDeJeu.getJoueur(0).getNom()){
                    int choixPersonnage = Interaction.lireUnEntier(1, listePersonnages.size() + 1);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(0));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                }

                else{
                    int choixPersonnage = generateur.nextInt(listePersonnages.size()) + 1;
                    System.out.println(choixPersonnage);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(i));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                }
            }
        }

        for(int i = 0 ; i < indexOfCrown; i++) {
            if (this.plateauDeJeu.getJoueur(i).getPersonnage() == null && !this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {

                System.out.println();
                System.out.println("****************************************");
                System.out.println("Tour de " + this.plateauDeJeu.getJoueur(i).getNom());
                System.out.println("****************************************");
                System.out.println();

                int j = 1;
                for (Personnage personnage : listePersonnages) {
                    StringBuilder stringBuilder = new StringBuilder("\t " + j + ". " + personnage.getNom() + " (Rang " + personnage.getRang() + ")");
                    System.out.println(stringBuilder);
                    j++;
                }

                System.out.println();
                System.out.println("Quel personnage voulez-vous incarner ?");

                if (plateauDeJeu.getJoueur(i).getNom() == this.plateauDeJeu.getJoueur(0).getNom()) {
                    int choixPersonnage = Interaction.lireUnEntier(1, listePersonnages.size() + 1);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(0));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                } else {
                    int choixPersonnage = generateur.nextInt(listePersonnages.size()) + 1;
                    System.out.println(choixPersonnage);
                    listePersonnages.get(choixPersonnage - 1).setJoueur(this.plateauDeJeu.getJoueur(i));
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " a choisi d'incarner " + listePersonnages.get(choixPersonnage - 1).getNom());
                    listePersonnages.remove(choixPersonnage - 1);
                }
            }
            else if (this.plateauDeJeu.getJoueur(i).getPersonnage() != null) {
                return;
            }
        }
    }

    private void tourDeJeu(){
        ArrayList<Personnage> listePersonnage = new ArrayList<>(this.plateauDeJeu.getListePersonnages());

        for(int i = 0; i < listePersonnage.size(); i++){

            if(listePersonnage.get(i).getJoueur() != null){

                Joueur joueurDuTour = listePersonnage.get(i).getJoueur();

                System.out.println();
                System.out.println("****************************************");
                System.out.println("Tour de " + joueurDuTour.getNom());
                System.out.println("****************************************");
                System.out.println();

                System.out.println("C'est au tour de " + joueurDuTour.getNom() + " d'incarner le personnage " + joueurDuTour.getPersonnage().getNom());
                System.out.println();

                if(joueurDuTour.getPersonnage().getEstAssassine()){
                    System.out.println("Vous avez eté assassiné !");
                    System.out.println();
                    continue;
                }
                if(joueurDuTour.getPersonnage().getEstVole()){
                    System.out.println("Vous avez été volé !");
                    System.out.println();
                    joueurDuTour.retirerPieces(joueurDuTour.getTresor());
                    for(int k = 0; k < this.plateauDeJeu.getNbJoueurs(); k++){
                        if(this.plateauDeJeu.getJoueur(k).getPersonnage().getNom().equals("Voleur")){
                            this.plateauDeJeu.getJoueur(k).ajouterPieces(joueurDuTour.getTresor());
                        }
                    }
                }

                if(joueurDuTour.getNom() == this.plateauDeJeu.getJoueur(0).getNom()){
                    percevoirRessources(joueurDuTour.getPersonnage());
                    System.out.println();
                }

                else{
                    percevoirRessourcesAvatar(joueurDuTour.getPersonnage());
                    System.out.println();
                }

                joueurDuTour.getPersonnage().percevoirRessourcesSpecifiques();

                if(joueurDuTour.getNom() == this.plateauDeJeu.getJoueur(0).getNom()){
                    joueurDuTour.getPersonnage().utiliserPouvoir();
                    System.out.println();
                }

                else{
                    joueurDuTour.getPersonnage().utiliserPouvoirAvatar();
                    System.out.println();
                }

                if(joueurDuTour.getNom() == this.plateauDeJeu.getJoueur(0).getNom()){
                    for(int j = 0 ; j < joueurDuTour.getPersonnage().getNbPermisDeConstruire() ; j++){
                        construire(joueurDuTour.getPersonnage());
                        System.out.println();
                    }
                }
                else{
                    construireAvatar(joueurDuTour.getPersonnage());
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println();
    }
    private void percevoirRessources(Personnage p){
        System.out.println("Perception des ressources");
        System.out.println("\t 1. Percevoir 2 pièces" +
                            "\n \t 2. Piocher 2 cartes");
        int choix = Interaction.lireUnEntier(1, 3);
        switch (choix){
            case 1:
                p.ajouterPieces();
                break;
            case 2:
                Quartier carte1 = this.plateauDeJeu.getPioche().piocher();
                Quartier carte2 = this.plateauDeJeu.getPioche().piocher();

                System.out.println("Voici les deux cartes que vous avez pioché : ");
                System.out.println("\t 1. " + carte1.getNom() + " (coût de construction : " + carte1.getCoutConstruction() + ")");
                System.out.println("\t 2. " + carte2.getNom() + " (coût de construction : " + carte2.getCoutConstruction() + ")");
                System.out.println();

                if(p.getJoueur().getCite().isEmpty()){
                    System.out.println("Quelle carte voulez-vous garder ?");
                    int choixCarte = Interaction.lireUnEntier(1, 3);
                    switch (choixCarte){
                        case 1:
                            p.ajouterQuartierDansMain(carte1);
                            break;
                        case 2:
                            p.ajouterQuartierDansMain(carte2);
                            break;
                    }
                    break;
                }

                else{
                    for(Quartier quartier : p.getJoueur().getCite()){
                        if(quartier.getNom().equals("Bibliothèque")){
                            System.out.println("Vous avez une bibliothèque dans votre cité. Vous gardez les deux cartes");
                            p.getJoueur().ajouterQuartierDansMain(carte1);
                            p.getJoueur().ajouterQuartierDansMain(carte2);
                            return;
                        }
                    }

                    System.out.println("Quelle carte voulez-vous garder ?");
                    int choixCarte = Interaction.lireUnEntier(1, 3);
                    switch (choixCarte){
                        case 1:
                            p.ajouterQuartierDansMain(carte1);
                            break;
                        case 2:
                            p.ajouterQuartierDansMain(carte2);
                            break;
                    }

                    break;
                }
        }
    }

    private void percevoirRessourcesAvatar(Personnage p){
        System.out.println("Perception des ressources");
        System.out.println("\t 1. Percevoir 2 pièces" +
                            "\n \t 2. Piocher 2 cartes");
        int choix = generateur.nextInt(2) + 1;
        System.out.println(choix);
        switch (choix){
            case 1:
                p.ajouterPieces();
                System.out.println();
                break;
            case 2:
                Quartier carte1 = this.plateauDeJeu.getPioche().piocher();
                Quartier carte2 = this.plateauDeJeu.getPioche().piocher();

                System.out.println("Voici les deux cartes que vous avez pioché : ");
                System.out.println("\t 1. " + carte1.getNom() + " (coût de construction : " + carte1.getCoutConstruction() + ")");
                System.out.println("\t 2. " + carte2.getNom() + " (coût de construction : " + carte2.getCoutConstruction() + ")");
                System.out.println();

                if(p.getJoueur().getCite().isEmpty()){
                    System.out.println("Quelle carte voulez-vous garder ?");
                    int choixCarte = generateur.nextInt(2) + 1;
                    System.out.println(choixCarte);
                    switch (choixCarte){
                        case 1:
                            p.ajouterQuartierDansMain(carte1);
                            break;
                        case 2:
                            p.ajouterQuartierDansMain(carte2);
                            break;
                    }
                    break;
                }

                else{
                    for(Quartier quartier : p.getJoueur().getCite()){
                        if(quartier.getNom().equals("Bibliothèque")){
                            System.out.println("Vous avez une bibliothèque dans votre cité. Vous gardez les deux cartes");
                            p.getJoueur().ajouterQuartierDansMain(carte1);
                            p.getJoueur().ajouterQuartierDansMain(carte2);
                            return;
                        }
                    }

                    System.out.println("Quelle carte voulez-vous garder ?");
                    int choixCarte = generateur.nextInt(2) + 1;
                    System.out.println(choixCarte);
                    switch (choixCarte){
                        case 1:
                            p.ajouterQuartierDansMain(carte1);
                            break;
                        case 2:
                            p.ajouterQuartierDansMain(carte2);
                            break;
                    }

                    break;
                }
        }
    }

    private void construire(Personnage p){
        System.out.println("Souhaitez-vous construire un quartier ? (o/ oui ; n / non)");
        Boolean construire = Interaction.lireOuiOuNon();
        if(construire){
            Boolean carriere = false;
            Boolean manufacture = false;
            Boolean tripot = false;
            for(Quartier quartierCite : p.getJoueur().getCite()){
                if(quartierCite.getNom().equals("Carrière"))
                    carriere = true;

                if(quartierCite.getNom().equals("Manufacture"))
                    manufacture = true;
            }

            for(Quartier quartier : p.getJoueur().getMainJoueur()){
                if(quartier.getNom().equals("Tripot"))
                    tripot = true;
            }

            System.out.println("Voici les cartes de votre main : ");
            int i = 1;
            for(Quartier quartier : p.getJoueur().getMainJoueur()){
                System.out.println();
                if(manufacture && quartier.getType().equals(Quartier.TYPE_QUARTIERS[4]))
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + Colors.ANSI_GREEN + (quartier.getCoutConstruction() - 1) + Colors.ANSI_RESET + ")");
                else
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + quartier.getCoutConstruction() + ")");
                i++;
            }
            System.out.println("Voici votre trésorerie : " + p.getJoueur().getTresor());

            System.out.println();

            if(carriere)
                System.out.println("Vous avez une carte \"Carrière\" dans votre cité. Vous pouvez construire 2 quartiers identitques");
            if(manufacture)
                System.out.println("Vous avez une carte \"Manufacture\" dans votre cité. Vous payez une pièce d'or en moins pour construire une merveille");
            if(tripot)
                System.out.println("Vous avez une carte \"Tripot\" dans votre main. Vous pouvez payer tout ou partie du coût de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 pièce d’or.");

            do{
                System.out.println("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier)");
                int choix = Interaction.lireUnEntier(0, p.getJoueur().nbQuartiersDansMain() + 1);
                if(choix == 0)
                    return;

                if(p.getJoueur().getMainJoueur().get(choix - 1).getNom().equals("Tripot")){
                    int coutConstruction = p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction();
                    if(manufacture){
                        System.out.println("Vous payez le cout de construction du Tripot, 1 pièce d'or en moins grâce à votre merveille : Manufacture");
                        if(coutConstruction != 0)
                            coutConstruction -= 1;
                    }

                    if(p.getJoueur().getMainJoueur().size() > 1){
                        System.out.println("Voulez vous utiliser des cartes de votre main pour payer le Tripot ? (o / oui ; n / non)");
                        if((p.getJoueur().getMainJoueur().size() + p.getJoueur().getTresor()) >= coutConstruction){
                            Boolean utiliserCartes = Interaction.lireOuiOuNon();
                            if(utiliserCartes){
                                System.out.println("Voici les cartes de votre main : ");
                                int j = 1;
                                for(Quartier quartier : p.getJoueur().getMainJoueur()){
                                    System.out.println("\t " + j + ". " + quartier.getNom() + " (coût de construction : " + quartier.getCoutConstruction() + ")");
                                    j++;
                                }

                                System.out.println("Combien de cartes voulez-vous utiliser ?");
                                int nombreCartes = Interaction.lireUnEntier(1, p.getJoueur().nbQuartiersDansMain() + 1);

                                if(nombreCartes == p.getJoueur().getMainJoueur().size() - 1){
                                    for(Quartier quartier : p.getJoueur().getMainJoueur()){
                                        if(quartier.getNom() != "Tripot"){
                                            this.plateauDeJeu.getPioche().ajouter(quartier);
                                            p.getJoueur().getMainJoueur().remove(quartier);
                                            if(coutConstruction != 0)
                                                coutConstruction -= 1;
                                        }
                                    }
                                }

                                else{
                                    for (int k = 0; k < nombreCartes; k++) {
                                        System.out.println("Voici les cartes de votre main : ");
                                        int l = 1;
                                        for(Quartier quartier : p.getJoueur().getMainJoueur()){
                                            System.out.println("\t " + l + ". " + quartier.getNom() + " (coût de construction : " + quartier.getCoutConstruction() + ")");
                                            l++;
                                        }
                                        System.out.println("Carte n°" + (k+1) + " que voulez-vous utiliser : ");
                                        int choixCarte = Interaction.lireUnEntier(1, p.getJoueur().nbQuartiersDansMain() + 1);

                                        if(p.getJoueur().getMainJoueur().get(choixCarte - 1).getNom().equals("Tripot")){
                                            System.out.println("Vous ne pouvez pas utiliser le Tripot pour payer le Tripot");
                                            k--;
                                        }

                                        else{
                                            this.plateauDeJeu.getPioche().ajouter(p.getJoueur().getMainJoueur().get(choixCarte - 1));
                                            p.getJoueur().getMainJoueur().remove(choixCarte - 1);
                                            if(coutConstruction != 0)
                                                coutConstruction -= 1;
                                        }
                                    }
                                }
                            }
                        }

                        else{
                            System.out.println("Vous n'avez pas assez de ressources pour payer le Tripot");
                        }

                        if(coutConstruction >= 0){
                            System.out.println("Vous payez " + coutConstruction + " pièces d'or pour construire le Tripot");
                            p.getJoueur().retirerPieces(coutConstruction);
                            p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                            p.getJoueur().getMainJoueur().remove(choix - 1);
                        }
                    }

                    if(p.getJoueur().getPersonnage().getNbPermisDeConstruire() == 1)
                        return;
                }

                else{
                    if(p.getJoueur().getCite().isEmpty()){
                        if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                            p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                            p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                            p.getJoueur().getMainJoueur().remove(choix - 1);
                        }

                        else
                            System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                    }

                    else{
                        for(Quartier quartier : p.getJoueur().getCite()){
                            if(quartier.getNom().equals("Carrière")){
                                if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                                    p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                                    p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                                    p.getJoueur().getMainJoueur().remove(choix - 1);
                                }

                                else{
                                    System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                                }
                            }

                            if(quartier.getNom().equals("Manufacture")){
                                if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction() - 1){
                                    p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                                    p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                                    p.getJoueur().getMainJoueur().remove(choix - 1);
                                    return;
                                }

                                else{
                                    System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                                    break;
                                }
                            }
                        }

                        for(Quartier quartier : p.getJoueur().getCite()){
                            if(quartier.getNom().equals(p.getJoueur().getMainJoueur().get(choix - 1).getNom())){
                                System.out.println("Vous avez déjà ce quartier dans votre cité");
                                break;
                            }

                            if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                                p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                                p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                                p.getJoueur().getMainJoueur().remove(choix - 1);
                                return;
                            }

                            else
                                System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                        }
                    }
                }
            }while (true);
        }
        else
            System.out.println("Vous ne construisez pas de quartier");
    }
    private void construireAvatar(Personnage p){
        System.out.println("Souhaitez-vous construire un quartier ? (o/ oui ; n / non)");
        Boolean construire = generateur.nextBoolean();
        if(construire){
            Boolean carriere = false;
            Boolean manufacture = false;
            Boolean tripot = false;
            for(Quartier quartierCite : p.getJoueur().getCite()){
                if(quartierCite.getNom().equals("Carrière"))
                    carriere = true;

                if(quartierCite.getNom().equals("Manufacture"))
                    manufacture = true;

                if(quartierCite.getNom().equals("Tripot")){
                    tripot = true;
                }
            }
            System.out.println("oui");
            System.out.println("Voici les cartes de votre main : ");
            int i = 1;
            for(Quartier quartier : p.getJoueur().getMainJoueur()){
                System.out.println();
                if(manufacture && quartier.getType().equals(Quartier.TYPE_QUARTIERS[4]))
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + Colors.ANSI_GREEN + (quartier.getCoutConstruction() - 1) + Colors.ANSI_RESET + ")");
                else
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + quartier.getCoutConstruction() + ")");
                i++;
            }
            System.out.println("Voici votre trésorerie : " + p.getJoueur().getTresor());
            System.out.println();

            if(carriere)
                System.out.println("Vous avez une carrière dans votre cité. Vous pouvez construire 2 quartiers identitques");
            if(manufacture)
                System.out.println("Vous avez une manufacture dans votre cité. Vous payez une pièce d'or en moins pour construire une merveille");
            if(tripot)
                System.out.println("Vous avez un tripot dans votre cité. Vous pouvez payer tout ou partie du coût de construction du Tripot en cartes de votre main, au prix de 1 carte pour 1 pièce d’or");

            do{
                System.out.println("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier)");
                int choix = generateur.nextInt(p.getJoueur().nbQuartiersDansMain() + 1);

                if(choix == 0)
                    return;

                if(p.getJoueur().getCite().isEmpty()){
                    if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                        p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                        p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                        p.getJoueur().getMainJoueur().remove(choix - 1);
                        return;
                    }

                    else
                        System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                }

                else{
                    for(Quartier quartier : p.getJoueur().getCite()){
                        if(quartier.getNom().equals("Carrière")){
                            System.out.println("Vous avez une carrière dans votre cité. Vous pouvez construire 2 quartiers identitques");

                            if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                                p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                                p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                                p.getJoueur().getMainJoueur().remove(choix - 1);
                                return;
                            }

                            else{
                                System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                                break;
                            }
                        }
                    }

                    for(Quartier quartier : p.getJoueur().getCite()){
                        if(quartier.getNom().equals(p.getJoueur().getMainJoueur().get(choix - 1).getNom())){
                            System.out.println("Vous avez déjà ce quartier dans votre cité");
                            break;
                        }

                        if(p.getJoueur().getTresor() >= p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction()){
                            p.getJoueur().retirerPieces(p.getJoueur().getMainJoueur().get(choix - 1).getCoutConstruction());
                            p.getJoueur().ajouterQuartierDansCite(p.getJoueur().getMainJoueur().get(choix - 1));
                            p.getJoueur().getMainJoueur().remove(choix - 1);
                            return;
                        }

                        else
                            System.out.println("Vous n'avez pas assez de pièces pour construire ce quartier");
                    }
                }
            }while (true);
        }
        else{
            System.out.println("non");
            System.out.println("Vous ne construisez pas de quartier");
        }
    }

    private void gestionCouronne() {
        for (Personnage personnage : this.plateauDeJeu.getListePersonnages()) {
            if (personnage.getJoueur() != null && personnage.getNom().equals("Roi")) {
                for (Joueur joueur : this.plateauDeJeu.getListeJoueurs()) {
                    joueur.setPossedeCouronne(false);
                }
                personnage.getJoueur().setPossedeCouronne(true);
            }
        }
    }

    private void reinitialisationPersonnages(){
        for(Personnage personnage : this.plateauDeJeu.getListePersonnages()){
            personnage.reinitialiser();
        }
    }

    private boolean partieFinie(){
        for(Joueur joueur : this.plateauDeJeu.getListeJoueurs()){
            if(joueur.nbQuartiersDansCite() >= 8){
                System.out.println("La partie est terminée !");
                System.out.println("Le joueur " + joueur.getNom() + " a gagné !");
                return true;
            }
        }
        return false;
    }

    private void calculDesPoints(){
    }
}
