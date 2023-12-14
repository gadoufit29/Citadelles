package org.project.test;

import org.project.modele.Assassin;
import org.project.modele.Magicienne;
import org.project.modele.Roi;
import org.project.modele.Joueur;
import org.project.modele.Pioche;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Quartier;

import java.util.ArrayList;


public class TestMagicienne {
    public static void main(String[] args) {
        TestMagicienne test = new TestMagicienne();
        test.test1();
        test.test2();
        test.test3();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Magicienne magicienne = new Magicienne();
        plateau.ajouterPersonnage(magicienne);
        Test.test(plateau.getNbPersonnages() == 3, "nombre de personnages");
        Test.test(plateau.getPersonnage(2) == magicienne, "récupération du personnage de la magicienne");
        Test.test(plateau.getPersonnage(2).getRang() == 3, "rang de la magicienne");
        Test.test(plateau.getPersonnage(2).getNbPermisDeConstruire() == 1, "test du nombre de permis de construire de la magicienne");
    }

    public void test2() {
        System.out.println("TEST DU POUVOIR DE LA MAGICIENNE");
        PlateauDeJeu plateau = new PlateauDeJeu();

        // création de quatre personnages
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Magicienne magicienne = new Magicienne();
        plateau.ajouterPersonnage(magicienne);

        // création de trois joueurs
        Joueur joueur1 = new Joueur("Milou");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Belle");
        plateau.ajouterJoueur(joueur3);

        // on associe les personnages aux joueurs
        roi.setJoueur(joueur1);
        assassin.setJoueur(joueur2);
        magicienne.setJoueur(joueur3);

        // création d'une pioche :
        Pioche pioche = plateau.getPioche();
        pioche.ajouter(new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1));
        pioche.ajouter(new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2));
        pioche.ajouter(new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5));
        pioche.ajouter(new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1));
        pioche.ajouter(new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2));
        pioche.ajouter(new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4, "A la fin de la partie, ..."));
        pioche.ajouter(new Quartier("cathédrale", Quartier.TYPE_QUARTIERS[0], 5));
        pioche.ajouter(new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3));
        pioche.ajouter(new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3));
        pioche.ajouter(new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 15));
        pioche.ajouter(new Quartier("bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez..."));
        pioche.melanger();

        // on distribue les cartes aux joueurs :
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());

        // on affiche la main de chaque joueur :
        System.out.printf("Main du Roi (%s): ", roi.getJoueur().getNom());
        ArrayList<Quartier> mainRoi = roi.getJoueur().getMainJoueur();
        for (Quartier quartier : mainRoi)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de l'assassin (%s): ", assassin.getJoueur().getNom());
        ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMainJoueur();
        for (Quartier quartier : mainAssassin)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de la magicienne (%s): ", magicienne.getJoueur().getNom());
        ArrayList<Quartier> mainMagicienne = magicienne.getJoueur().getMainJoueur();
        for (Quartier quartier : mainMagicienne)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        // on récupère la taille de la pioche :
        int taillePiocheAvantPouvoir = pioche.nombreQuartiersDansPioche();

        // utiliser le pouvoir de la magicienne :
        magicienne.utiliserPouvoir();

        // on réaffiche la main de chaque joueur :
        System.out.printf("Main du Roi (%s): ", roi.getJoueur().getNom());
        mainRoi = roi.getJoueur().getMainJoueur();
        for (Quartier quartier : mainRoi)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de l'assassin (%s): ", assassin.getJoueur().getNom());
        mainAssassin = assassin.getJoueur().getMainJoueur();
        for (Quartier quartier : mainAssassin)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de la magicienne (%s): ", magicienne.getJoueur().getNom());
        mainMagicienne = magicienne.getJoueur().getMainJoueur();
        for (Quartier quartier : mainMagicienne)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        // on vérifie que la taille de la pioche n'a pas changé :
        Test.test(taillePiocheAvantPouvoir == pioche.nombreQuartiersDansPioche(), "taille inchangée de la pioche");
    }

    public void test3() {
        System.out.println("TEST DU POUVOIR DE LA MAGICIENNE AVEC L'AVATAR");
        PlateauDeJeu plateau = new PlateauDeJeu();

        // création de quatre personnages
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Magicienne magicienne = new Magicienne();
        plateau.ajouterPersonnage(magicienne);

        // création de trois joueurs
        Joueur joueur1 = new Joueur("Milou");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Belle");
        plateau.ajouterJoueur(joueur3);

        // on associe les personnages aux joueurs
        roi.setJoueur(joueur1);
        assassin.setJoueur(joueur2);
        magicienne.setJoueur(joueur3);

        // création d'une pioche :
        Pioche pioche = plateau.getPioche();
        pioche.ajouter(new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1));
        pioche.ajouter(new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2));
        pioche.ajouter(new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5));
        pioche.ajouter(new Quartier("taverne", Quartier.TYPE_QUARTIERS[3], 1));
        pioche.ajouter(new Quartier("échoppe", Quartier.TYPE_QUARTIERS[3], 2));
        pioche.ajouter(new Quartier("basilique", Quartier.TYPE_QUARTIERS[4], 4, "A la fin de la partie, ..."));
        pioche.ajouter(new Quartier("cathédrale", Quartier.TYPE_QUARTIERS[0], 5));
        pioche.ajouter(new Quartier("caserne", Quartier.TYPE_QUARTIERS[1], 3));
        pioche.ajouter(new Quartier("manoir", Quartier.TYPE_QUARTIERS[2], 3));
        pioche.ajouter(new Quartier("hôtel de ville", Quartier.TYPE_QUARTIERS[3], 15));
        pioche.ajouter(new Quartier("bibliothèque", Quartier.TYPE_QUARTIERS[4], 6, "Si vous choisissez..."));
        pioche.melanger();

        // on distribue les cartes aux joueurs :
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur1.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur2.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());
        joueur3.ajouterQuartierDansMain(pioche.piocher());

        // on affiche la main de chaque joueur :
        System.out.printf("Main du Roi (%s): ", roi.getJoueur().getNom());
        ArrayList<Quartier> mainRoi = roi.getJoueur().getMainJoueur();
        for (Quartier quartier : mainRoi)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de l'assassin (%s): ", assassin.getJoueur().getNom());
        ArrayList<Quartier> mainAssassin = assassin.getJoueur().getMainJoueur();
        for (Quartier quartier : mainAssassin)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de la magicienne (%s): ", magicienne.getJoueur().getNom());
        ArrayList<Quartier> mainMagicienne = magicienne.getJoueur().getMainJoueur();
        for (Quartier quartier : mainMagicienne)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        // on récupère la taille de la pioche :
        int taillePiocheAvantPouvoir = pioche.nombreQuartiersDansPioche();

        // utiliser le pouvoir de la magicienne :
        magicienne.utiliserPouvoirAvatar();

        // on réaffiche la main de chaque joueur :
        System.out.printf("Main du Roi (%s): ", roi.getJoueur().getNom());
        mainRoi = roi.getJoueur().getMainJoueur();
        for (Quartier quartier : mainRoi)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de l'assassin (%s): ", assassin.getJoueur().getNom());
        mainAssassin = assassin.getJoueur().getMainJoueur();
        for (Quartier quartier : mainAssassin)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        System.out.printf("Main de la magicienne (%s): ", magicienne.getJoueur().getNom());
        mainMagicienne = magicienne.getJoueur().getMainJoueur();
        for (Quartier quartier : mainMagicienne)
            System.out.print(quartier.getNom() + ", ");
        System.out.println();

        // on vérifie que la taille de la pioche n'a pas changé :
        Test.test(taillePiocheAvantPouvoir == pioche.nombreQuartiersDansPioche(), "taille inchangée de la pioche");
    }
}
