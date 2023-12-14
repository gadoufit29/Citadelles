package org.project.controleur;

import org.project.application.Configuration;
import org.project.modele.Joueur;
import org.project.modele.Personnage;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Quartier;
import org.project.utils.Colors;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Jeu{
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    private ArrayList<Joueur> listeJoueursFinPartie = new ArrayList<>();
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
        System.out.println("Le but du jeu est d'être le premier à construire 7 quartiers dans sa cité.");
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

        joueur1.ajouterPieces(2);
        joueur2.ajouterPieces(2);
        joueur3.ajouterPieces(2);
        joueur4.ajouterPieces(2);


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

        boolean roiTrouve;
        do {
            roiTrouve = false;
            for (int index = 0; index < 3; index++) {
                int indice = generateur.nextInt(listePersonnages.size());
                Personnage personnage = listePersonnages.get(indice);

                if (index < 2) {
                    System.out.println("Le personnage " + personnage.getNom() + " est écarté face visible");
                    if (personnage.getNom().equals("Roi")) {
                        System.out.println("Le roi est présent dans les cartes faces visibles. Redémarrage de la partie");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Interruption détectée, sortie de la fonction.");
                            return;
                        }
                        return;
                    }
                }

                else {
                    System.out.println("Un personnage est écarté face cachée");
                }

                listePersonnages.remove(personnage);
            }
        } while (roiTrouve);

        System.out.println();

        int indexOfCrown = 0;
        for(Joueur joueur : this.plateauDeJeu.getListeJoueurs()){
            if(joueur.getPossedeCouronne()){
                indexOfCrown = this.plateauDeJeu.getListeJoueurs().indexOf(joueur);
            }
        }

        System.out.println(indexOfCrown);

        int nbJoueurs = this.plateauDeJeu.getNbJoueurs();
        for (int i = 0; i < nbJoueurs; i++) {
            int indexJoueur = (indexOfCrown + i) % nbJoueurs;
            Joueur joueurActuel = this.plateauDeJeu.getJoueur(indexJoueur);

            System.out.println("\n****************************************");
            System.out.println("Tour de " + joueurActuel.getNom());
            System.out.println("****************************************\n");

            if (joueurActuel.getPersonnage() == null && joueurActuel.getPossedeCouronne()) {
                System.out.println(joueurActuel.getNom() + " a la couronne !\n");
            }

            // Affichage des personnages disponibles
            for (int j = 0; j < listePersonnages.size(); j++) {
                Personnage personnage = listePersonnages.get(j);
                System.out.println("\t" + (j + 1) + ". " + personnage.getNom() + " (Rang " + personnage.getRang() + ")");
            }

            System.out.println("\nQuel personnage voulez-vous incarner ?");

            int choixPersonnage;
            if (joueurActuel.getNom().equals(this.plateauDeJeu.getJoueur(0).getNom())) {
                choixPersonnage = Interaction.lireUnEntier(1, listePersonnages.size() + 1);
            } else {
                choixPersonnage = generateur.nextInt(listePersonnages.size()) + 1;
                System.out.println(choixPersonnage);
            }

            Personnage personnageChoisi = listePersonnages.get(choixPersonnage - 1);
            personnageChoisi.setJoueur(joueurActuel);
            System.out.println(joueurActuel.getNom() + " a choisi d'incarner " + personnageChoisi.getNom());
            listePersonnages.remove(choixPersonnage - 1);
        }
    }

    private void tourDeJeu() {
        ArrayList<Personnage> listePersonnage = new ArrayList<>(this.plateauDeJeu.getListePersonnages());

        for (Personnage personnage : listePersonnage) {
            Joueur joueurDuTour = personnage.getJoueur();

            if (joueurDuTour != null) {
                System.out.println("\n****************************************");
                System.out.println("Tour de " + joueurDuTour.getNom());
                System.out.println("****************************************\n");

                System.out.println("C'est au tour de " + joueurDuTour.getNom() + " d'incarner le personnage " + joueurDuTour.getPersonnage().getNom() + "\n");

                if (personnage.getEstAssassine()) {
                    System.out.println("Vous avez été assassiné !\n");
                    continue;
                }

                if (personnage.getEstVole()) {
                    System.out.println("Vous avez été volé !\n");
                    joueurDuTour.retirerPieces(joueurDuTour.getTresor());
                    for (Joueur joueurPlateau : this.plateauDeJeu.getListeJoueurs()) {
                        if (joueurPlateau.getPersonnage().getNom().equals("Voleur")) {
                            joueurPlateau.ajouterPieces(joueurDuTour.getTresor());
                        }
                    }
                }

                // Percevoir ressources et utiliser pouvoir
                if (joueurDuTour.getNom().equals(this.plateauDeJeu.getJoueur(0).getNom())) {
                    percevoirRessources(joueurDuTour.getPersonnage());
                    joueurDuTour.getPersonnage().percevoirRessourcesSpecifiques();
                    joueurDuTour.getPersonnage().utiliserPouvoir();
                    construire(joueurDuTour.getPersonnage());
                }

                else {
                    percevoirRessourcesAvatar(joueurDuTour.getPersonnage());
                    joueurDuTour.getPersonnage().utiliserPouvoirAvatar();
                    joueurDuTour.getPersonnage().percevoirRessourcesSpecifiques();
                    construireAvatar(joueurDuTour.getPersonnage());
                }

                if(joueurDuTour.getCite().size() >= 7){
                    listeJoueursFinPartie.add(joueurDuTour);
                }

                System.out.println();
            }
        }
    }

    private void percevoirRessources(Personnage p) {
        System.out.println("Perception des ressources");
        System.out.println("\t 1. Percevoir 2 pièces" +
                "\n \t 2. Piocher 2 cartes");
        int choix = Interaction.lireUnEntier(1, 3);

        if (choix == 1) {
            p.ajouterPieces();
        } else if (choix == 2) {
            Quartier carte1 = this.plateauDeJeu.getPioche().piocher();
            Quartier carte2 = this.plateauDeJeu.getPioche().piocher();

            System.out.println("Voici les deux cartes que vous avez pioché : ");
            System.out.println("\t 1. " + carte1.getNom() + " (coût de construction : " + carte1.getCoutConstruction() + ")");
            System.out.println("\t 2. " + carte2.getNom() + " (coût de construction : " + carte2.getCoutConstruction() + ")");
            System.out.println();

            boolean bibliothequeDansCite = p.getJoueur().getCite().stream().anyMatch(quartier -> quartier.getNom().equals("bibliothequeDansCite"));

            if (!bibliothequeDansCite) {
                System.out.println("Quelle carte voulez-vous garder ?");
                int choixCarte = Interaction.lireUnEntier(1, 3);
                if (choixCarte == 1) {
                    p.ajouterQuartierDansMain(carte1);
                } else {
                    p.ajouterQuartierDansMain(carte2);
                }
            }

            else {
                System.out.println("Vous avez une bibliothèque dans votre cité. Vous gardez les deux cartes");
                p.getJoueur().ajouterQuartierDansMain(carte1);
                p.getJoueur().ajouterQuartierDansMain(carte2);
            }
        }
    }


    private void percevoirRessourcesAvatar(Personnage p){
        System.out.println("Perception des ressources");
        System.out.println("\t 1. Percevoir 2 pièces" +
                "\n \t 2. Piocher 2 cartes");
        int choix = generateur.nextInt(2) + 1;

        if (choix == 1) {
            p.ajouterPieces();
        } else if (choix == 2) {
            Quartier carte1 = this.plateauDeJeu.getPioche().piocher();
            Quartier carte2 = this.plateauDeJeu.getPioche().piocher();

            System.out.println("Voici les deux cartes que vous avez pioché : ");
            System.out.println("\t 1. " + carte1.getNom() + " (coût de construction : " + carte1.getCoutConstruction() + ")");
            System.out.println("\t 2. " + carte2.getNom() + " (coût de construction : " + carte2.getCoutConstruction() + ")");
            System.out.println();

            boolean bibliothequeDansCite = p.getJoueur().getCite().stream().anyMatch(quartier -> quartier.getNom().equals("bibliothequeDansCite"));

            if (!bibliothequeDansCite) {
                System.out.println("Quelle carte voulez-vous garder ?");
                int choixCarte = generateur.nextInt(2) + 1;
                if (choixCarte == 1) {
                    p.ajouterQuartierDansMain(carte1);
                } else {
                    p.ajouterQuartierDansMain(carte2);
                }
            }

            else {
                System.out.println("Vous avez une bibliothèque dans votre cité. Vous gardez les deux cartes");
                p.getJoueur().ajouterQuartierDansMain(carte1);
                p.getJoueur().ajouterQuartierDansMain(carte2);
            }
        }
    }

    private void construire(Personnage p) {
        if(!p.getJoueur().getMainJoueur().isEmpty()){
            System.out.println("Souhaitez-vous construire un quartier ? (o/ oui ; n / non)");
            if (!Interaction.lireOuiOuNon()) {
                System.out.println("Vous ne construisez pas de quartier");
                return;
            }

            // Vérification de la présence de certains quartiers
            Boolean carriere = false, manufacture = false;
            for (Quartier quartier : p.getJoueur().getCite()) {
                if (quartier.getNom().equals("Carrière")) carriere = true;
                if (quartier.getNom().equals("Manufacture")) manufacture = true;
            }

            // Boucle pour la construction des quartiers
            System.out.println("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier)");
            int nombreConstruction = 0;
            while (nombreConstruction != p.getJoueur().getPersonnage().getNbPermisDeConstruire()) {
                System.out.println("Votre main :");
                // Affichage des cartes de la main du joueur
                int i = 1;
                for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                    int coutConstruction = quartier.getCoutConstruction() - (manufacture && quartier.getType().equals(Quartier.TYPE_QUARTIERS[4]) ? 1 : 0);
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + coutConstruction + ")");
                    i++;
                }

                i = 1;

                System.out.println("Votre cité : ");
                // Affichage des cartes de la cité du joueur
                for (Quartier quartier : p.getJoueur().getCite()) {
                    System.out.println("\t " + i + ". " + quartier.getNom());
                    i++;
                }
                System.out.println(Colors.ANSI_YELLOW + "Vous avez : " + p.getJoueur().getTresor() + " pièce(s) d'or" + Colors.ANSI_RESET);

                System.out.print("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier) : ");
                int choix = Interaction.lireUnEntier(0, p.getJoueur().nbQuartiersDansMain() + 1);
                if (choix == 0) return;

                Quartier quartierChoisi = p.getJoueur().getMainJoueur().get(choix - 1);
                int coutConstruction = quartierChoisi.getCoutConstruction() - (manufacture && quartierChoisi.getType().equals(Quartier.TYPE_QUARTIERS[4]) ? 1 : 0);

                // Traitement de la construction d'un quartier
                if (quartierChoisi.getNom().equals("Tripot")) {
                    int nbPieces = 0;
                    if(p.getJoueur().getTresor() > 0){
                        System.out.println("Souhaitez-vous utiliser des pièces pour la construction ? (o / oui ; n / non)");
                        if (Interaction.lireOuiOuNon()) {
                            System.out.println("Combien de pièces voulez-vous utiliser ?");
                            nbPieces = Interaction.lireUnEntier(1, p.getJoueur().getTresor() + 1);

                            if (nbPieces > p.getJoueur().getTresor()) {
                                System.out.println("Vous ne pouvez pas payer plus de pièces que vous n'en avez");
                                continue;
                            }

                            else if(nbPieces > coutConstruction) {
                                System.out.println("Vous ne pouvez pas payer plus de pièces que le coût de construction du Tripot");
                                continue;
                            }

                            else if (nbPieces == coutConstruction) {
                                p.getJoueur().retirerPieces(nbPieces);
                                p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                                p.getJoueur().getMainJoueur().remove(quartierChoisi);
                                nombreConstruction++;
                                System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                                continue;
                            }
                        }

                        else {
                            System.out.println("Vous ne payez pas de pièces pour la construction du Tripot");
                        }
                    }

                    if(nbPieces + (p.getJoueur().getMainJoueur().size() -  1) >= coutConstruction){
                        ArrayList<Quartier> quartiersADefaite = new ArrayList<>();
                        if (nbPieces == 0 && (p.getJoueur().getMainJoueur().size() - 1) == coutConstruction) {
                            for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                                if (!quartier.getNom().equals("Tripot")) {
                                    this.plateauDeJeu.getPioche().ajouter(quartier);
                                    quartiersADefaite.add(quartier); // Ajoutez le quartier à la liste temporaire
                                }
                            }

                            for (Quartier quartier : quartiersADefaite) {
                                p.getJoueur().getMainJoueur().remove(quartier);
                            }

                            p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                            p.getJoueur().getMainJoueur().remove(quartierChoisi);
                            nombreConstruction++;
                            System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                            continue;
                        }

                        else{
                            int l = 0;
                            do {
                                int k = 1;
                                for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                                    System.out.println("\t " + k + ". " + quartier.getNom());
                                    k++;
                                }
                                System.out.println("Quartier n°" + (l+1) + "  a defausser : ");

                                int choixQuartierDefausser = Interaction.lireUnEntier(1, p.getJoueur().getMainJoueur().size() + 1);

                                if(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1).getNom().equals("Tripot")){
                                    System.out.println("Vous ne pouvez pas défausser le Tripot");
                                    continue;
                                }

                                this.plateauDeJeu.getPioche().ajouter(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1));
                                System.out.println("Vous avez défaussé le quartier : " + p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1).getNom());
                                System.out.println("Il vous reste " + (coutConstruction - nbPieces - (l+1)) + " quartier(s) à défausser");
                                p.getJoueur().getMainJoueur().remove(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1));
                                l++;

                            }while(nbPieces + l != coutConstruction);

                            p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                            p.getJoueur().getMainJoueur().remove(quartierChoisi);
                            System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                        }

                        nombreConstruction++;
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire le Tripot (Pas assez de pièces et/ou de quartiers à défausser)");
                    }
                }

                else if (carriere) {
                    // Logique de construction pour les autres quartiers
                    if (p.getJoueur().getTresor() >= coutConstruction) {
                        p.getJoueur().retirerPieces(coutConstruction);
                        p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                        p.getJoueur().getMainJoueur().remove(quartierChoisi);
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire ce quartier (Pas assez de pièces)");
                        continue;
                    }

                    System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                    nombreConstruction++;

                } else {
                    // Logique de construction pour les autres quartiers
                    if (p.getJoueur().getTresor() >= coutConstruction) {
                        p.getJoueur().retirerPieces(coutConstruction);
                        p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                        p.getJoueur().getMainJoueur().remove(quartierChoisi);
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire ce quartier (Pas assez de pièces)");
                        continue;
                    }

                    System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                    nombreConstruction++;
                }
            }
        }
        else{
            System.out.println("Vous n'avez pas de quartier dans votre main");
        }
    }

    private void construireAvatar(Personnage p){
        if(!p.getJoueur().getMainJoueur().isEmpty()){
            System.out.println("Souhaitez-vous construire un quartier ? (o/ oui ; n / non)");
            if (!generateur.nextBoolean()) {
                System.out.println("Vous ne construisez pas de quartier");
                return;
            }

            // Vérification de la présence de certains quartiers
            Boolean carriere = false, manufacture = false;
            for (Quartier quartier : p.getJoueur().getCite()) {
                if (quartier.getNom().equals("Carrière")) carriere = true;
                if (quartier.getNom().equals("Manufacture")) manufacture = true;
            }

            // Boucle pour la construction des quartiers
            System.out.println("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier)");
            int nombreConstruction = 0;
            while (nombreConstruction != p.getJoueur().getPersonnage().getNbPermisDeConstruire()) {
                System.out.println("Votre main :");
                // Affichage des cartes de la main du joueur
                int i = 1;
                for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                    int coutConstruction = quartier.getCoutConstruction() - (manufacture && quartier.getType().equals(Quartier.TYPE_QUARTIERS[4]) ? 1 : 0);
                    System.out.println("\t " + i + ". " + quartier.getNom() + " (coût de construction : " + coutConstruction + ")");
                    i++;
                }

                i = 1;

                System.out.println("Votre cité : ");
                // Affichage des cartes de la cité du joueur
                for (Quartier quartier : p.getJoueur().getCite()) {
                    System.out.println("\t " + i + ". " + quartier.getNom());
                    i++;
                }
                System.out.println(Colors.ANSI_YELLOW + "Vous avez : " + p.getJoueur().getTresor() + " pièce(s) d'or" + Colors.ANSI_RESET);

                System.out.print("Quel quartier voulez-vous construire ? (0 pour ne pas construire de quartier) : ");
                int choix = generateur.nextInt(p.getJoueur().nbQuartiersDansMain() + 1);
                if (choix == 0) return;

                Quartier quartierChoisi = p.getJoueur().getMainJoueur().get(choix - 1);
                int coutConstruction = quartierChoisi.getCoutConstruction() - (manufacture && quartierChoisi.getType().equals(Quartier.TYPE_QUARTIERS[4]) ? 1 : 0);

                // Traitement de la construction d'un quartier
                if (quartierChoisi.getNom().equals("Tripot")) {
                    int nbPieces = 0;
                    if(p.getJoueur().getTresor() > 0){
                        System.out.println("Souhaitez-vous utiliser des pièces pour la construction ? (o / oui ; n / non)");
                        if (generateur.nextBoolean()) {
                            System.out.println("Combien de pièces voulez-vous utiliser ?");
                            nbPieces = generateur.nextInt(1, p.getJoueur().getTresor() + 1);

                            if (nbPieces > p.getJoueur().getTresor()) {
                                System.out.println("Vous ne pouvez pas payer plus de pièces que vous n'en avez");
                                continue;
                            }

                            else if(nbPieces > coutConstruction) {
                                System.out.println("Vous ne pouvez pas payer plus de pièces que le coût de construction du Tripot");
                                continue;
                            }

                            else if (nbPieces == coutConstruction) {
                                p.getJoueur().retirerPieces(nbPieces);
                                p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                                p.getJoueur().getMainJoueur().remove(quartierChoisi);
                                nombreConstruction++;
                                System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                                continue;
                            }
                        }

                        else {
                            System.out.println("Vous ne payez pas de pièces pour la construction du Tripot");
                        }
                    }

                    if(nbPieces + (p.getJoueur().getMainJoueur().size() -  1) >= coutConstruction){
                        ArrayList<Quartier> quartiersADefaite = new ArrayList<>();
                        if (nbPieces == 0 && (p.getJoueur().getMainJoueur().size() - 1) == coutConstruction) {
                            for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                                if (!quartier.getNom().equals("Tripot")) {
                                    this.plateauDeJeu.getPioche().ajouter(quartier);
                                    quartiersADefaite.add(quartier); // Ajoutez le quartier à la liste temporaire
                                }
                            }

                            for (Quartier quartier : quartiersADefaite) {
                                p.getJoueur().getMainJoueur().remove(quartier);
                            }

                            p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                            p.getJoueur().getMainJoueur().remove(quartierChoisi);
                            nombreConstruction++;
                            System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                            continue;
                        }

                        else{
                            int l = 0;
                            do {
                                int k = 1;
                                for (Quartier quartier : p.getJoueur().getMainJoueur()) {
                                    System.out.println("\t " + k + ". " + quartier.getNom());
                                    k++;
                                }
                                System.out.println("Quartier n°" + (l+1) + "  a defausser : ");

                                int choixQuartierDefausser = generateur.nextInt(1, p.getJoueur().getMainJoueur().size() + 1);

                                if(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1).getNom().equals("Tripot")){
                                    System.out.println("Vous ne pouvez pas défausser le Tripot");
                                    continue;
                                }

                                this.plateauDeJeu.getPioche().ajouter(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1));
                                System.out.println("Vous avez défaussé le quartier : " + p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1).getNom());
                                System.out.println("Il vous reste " + (coutConstruction - nbPieces - (l+1)) + " quartier(s) à défausser");
                                p.getJoueur().getMainJoueur().remove(p.getJoueur().getMainJoueur().get(choixQuartierDefausser - 1));
                                l++;

                            }while(nbPieces + l != coutConstruction);

                            p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                            p.getJoueur().getMainJoueur().remove(quartierChoisi);
                            System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                        }

                        nombreConstruction++;
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire le Tripot (Pas assez de pièces et/ou de quartiers à défausser)");
                    }
                }

                else if (carriere) {
                    // Logique de construction pour les autres quartiers
                    if (p.getJoueur().getTresor() >= coutConstruction) {
                        p.getJoueur().retirerPieces(coutConstruction);
                        p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                        p.getJoueur().getMainJoueur().remove(quartierChoisi);
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire ce quartier (Pas assez de pièces)");
                        continue;
                    }

                    System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                    nombreConstruction++;

                } else {
                    // Logique de construction pour les autres quartiers
                    if (p.getJoueur().getTresor() >= coutConstruction) {
                        p.getJoueur().retirerPieces(coutConstruction);
                        p.getJoueur().ajouterQuartierDansCite(quartierChoisi);
                        p.getJoueur().getMainJoueur().remove(quartierChoisi);
                    }

                    else {
                        System.out.println("Vous ne pouvez pas construire ce quartier (Pas assez de pièces)");
                        continue;
                    }

                    System.out.println("Vous avez construit le quartier : \"" + quartierChoisi.getNom() + "\" !");
                    nombreConstruction++;
                }
            }
        }
        else{
            System.out.println("Vous n'avez pas de quartier dans votre main");
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
            if(joueur.nbQuartiersDansCite() >= 7){
                return true;
            }
        }
        return false;
    }

    private void calculDesPoints() {
        System.out.println("****************************************");
        System.out.println("************** FIN DE PARTIE **************");
        System.out.println("****************************************");
        System.out.println();

        Map<Joueur, Integer> scoreJoueurs = new HashMap<>();

        for (Joueur joueur : this.plateauDeJeu.getListeJoueurs()) {
            int nbPoints = 0;
            for (Quartier quartier : joueur.getCite()) {
                nbPoints += quartier.getCoutConstruction();
            }

            System.out.println("Le joueur " + joueur.getNom() + " gagne " + nbPoints + " point(s) pour le cout total de ses quartiers construits dans sa cité.");

            for (Quartier quartier : joueur.getCite()) {
                if(quartier.getNom().equals("Cour des Miracles")){
                    System.out.println("Le joueur " + joueur.getNom() + " a construit la Cour des Miracles !");
                    System.out.println("Choisis de quel type tu veux que ta merveille soit (Attention, tu ne pourras plus changer) : ");
                    System.out.println("\t 1. Religieux");
                    System.out.println("\t 2. Militaire");
                    System.out.println("\t 3. Noble");
                    System.out.println("\t 4. Commerçant");
                    System.out.println("\t 5. Merveille");

                    int choix = 0;

                    if(joueur == this.plateauDeJeu.getJoueur(0))
                        choix = Interaction.lireUnEntier(1, 6);

                    else
                        choix = generateur.nextInt(5) + 1;


                    switch (choix){
                        case 1:
                            System.out.println("\"La Cour des Miracles\" a pour ce tour le type suivant : Religieux.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[0]);
                            break;
                        case 2:
                            System.out.println("\"La Cour des Miracles\" a pour ce tour le type suivant : Militaire.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[1]);
                            break;
                        case 3:
                            System.out.println("\"La Cour des Miracles\" a pour ce tour le type suivant : Noble.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[2]);
                            break;
                        case 4:
                            System.out.println("\"La Cour des Miracles\" a pour ce tour le type suivant : Commerçant.");
                            quartier.setType(Quartier.TYPE_QUARTIERS[3]);
                            break;
                        default:
                            System.out.println("\"La Cour des Miracles\" reste inchangée (Type : Merveille).");
                            quartier.setType(Quartier.TYPE_QUARTIERS[4]);
                            break;
                    }

                    nbPoints += 1;
                }
            }

            for (Quartier quartier : joueur.getCite()) {
                if (quartier.getType().equals(Quartier.TYPE_QUARTIERS[0]) && quartier.getType().equals(Quartier.TYPE_QUARTIERS[1]) && quartier.getType().equals(Quartier.TYPE_QUARTIERS[2]) && quartier.getType().equals(Quartier.TYPE_QUARTIERS[3]) && quartier.getType().equals(Quartier.TYPE_QUARTIERS[4])){
                    System.out.println("Le joueur " + joueur.getNom() + " a construit les 5 types de quartiers ! Il gagne 3 points.");
                    nbPoints += 3;
                }
            }

            for (int i = 0  ; i < listeJoueursFinPartie.size() ; i++){
                if(joueur == listeJoueursFinPartie.get(0)){
                    System.out.println("Le joueur " + joueur.getNom() + " a gagné la partie en premier ! Il gagne 4 points.");
                    nbPoints += 4;
                }
                else{
                    System.out.println("Le joueur " + joueur.getNom() + " n'a pas été le premier à compléter sa cité ! Il gagne 2 points.");
                    nbPoints += 2;
                }
            }

            for (Quartier quartier : joueur.getCite()) {
                if(quartier.getNom().equals("Dracoport"))
                    nbPoints += 2;

                if(quartier.getNom().equals("Fontaine aux Souhaits")){
                    for(Quartier quartierMerveille : joueur.getCite()){
                        if(quartierMerveille.getType().equals(Quartier.TYPE_QUARTIERS[4])){
                            nbPoints += 1;
                        }
                    }
                }

                if(quartier.getNom().equals("Salle des Cartes")){
                    nbPoints += joueur.getMainJoueur().size();
                }

            }

            if(joueur.getPossedeCouronne()){
                nbPoints += 5;
            }

            nbPoints += joueur.getTresor();

            System.out.println("Le joueur " + joueur.getNom() + " fini avec " + nbPoints + " point(s) !");

            scoreJoueurs.put(joueur, nbPoints);
        }

        // Convertir la Map en une liste d'entrées pour le tri
        ArrayList<Map.Entry<Joueur, Integer>> listeScoreJoueurs = new ArrayList<>(scoreJoueurs.entrySet());

        // Trier la liste en fonction des scores
        listeScoreJoueurs.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Afficher les joueurs et leurs scores dans l'ordre décroissant
        System.out.println();
        System.out.println("****************************************");
        System.out.println("************** CLASSEMENT **************");
        System.out.println("****************************************");
        System.out.println();
        for (Map.Entry<Joueur, Integer> entry : listeScoreJoueurs) {
            Joueur joueur = entry.getKey();
            Integer score = entry.getValue();
            System.out.println("Joueur: " + joueur.getNom() + ", Score: " + score);
            System.out.println();
        }
    }
}
