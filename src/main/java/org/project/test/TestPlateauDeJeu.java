package org.project.test;

import org.project.modele.Joueur;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Roi;

public class TestPlateauDeJeu {

    public static void main(String[] args) {
        TestPlateauDeJeu testPlateau = new TestPlateauDeJeu();
        testPlateau.test1();
        testPlateau.test2();
        testPlateau.test3();
        testPlateau.test4();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Test.test(plateauDeJeu.getNbJoueurs() == 0, "test du nombre de joueurs");
        Test.test(plateauDeJeu.getNbPersonnages() == 0, "test du nombre de personnages");
        Test.test(plateauDeJeu.getPioche() != null && plateauDeJeu.getPioche().nombreQuartiersDansPioche() == 0, "test de l'existence de la pioche");
    }

    public void test2() {
        System.out.println("TEST DE L'AJOUT D'UN JOUEUR");
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateauDeJeu.ajouterJoueur(joueur);
        Test.test(plateauDeJeu.getNbJoueurs() == 1, "nombre de joueurs");
        Test.test(plateauDeJeu.getJoueur(0) == joueur, "récupération de ce joueur depuis le tableau");
    }

    public void test3() {
        System.out.println("TEST DE L'AJOUT D'UN PERSONNAGE");
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Roi roi = new Roi();
        plateauDeJeu.ajouterPersonnage(roi);
        Test.test(plateauDeJeu.getNbPersonnages() == 1, "nombre de joueurs");
        Test.test(plateauDeJeu.getPersonnage(0) == roi, "récupération du personnage depuis le tableau");
    }

    public void test4() {
        System.out.println("TEST DE L'ASSOCIATION DU PLATEAU AU PERSONNAGE");
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Roi roi = new Roi();
        plateauDeJeu.ajouterPersonnage(roi);
        Test.test(roi.getPlateau() == plateauDeJeu, "association du plateau au personnage");
    }
}
