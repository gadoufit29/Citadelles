package org.project.test;

import org.project.modele.Assassin;
import org.project.modele.PlateauDeJeu;
import org.project.modele.Roi;

public class TestAssassin {
    public static void main(String[] args) {
        TestAssassin test = new TestAssassin();
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
        Test.test(plateau.getNbPersonnages() == 2, "nombre de joueurs");
        Test.test(plateau.getPersonnage(1) == assassin, "récupération du personnage de l'assassin");
        Test.test(plateau.getPersonnage(1).getRang() == 1, "récupération du rang");
        Test.test(plateau.getPersonnage(1).getNbPermisDeConstruire() == 1, "test du nombre de permis de construire de l'assassin");
    }

    public void test2() {
        System.out.println("TEST DE L'ASSASSINAT DU ROI");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);

        // on utilise le pouvoir de l'assassin
        // NB : seul le roi peut être assassiné dans cette situation
        assassin.utiliserPouvoir();
        if(roi.getEstAssassine()) {
            Test.test(roi.getEstAssassine(), "le roi est assassiné");
        }
        else {
            Test.test(!roi.getEstAssassine(), "le roi n'est pas assassiné");
        }
    }

    public void test3() {
        System.out.println("TEST DE L'ASSASSINAT DU ROI AVEC L'AVATAR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);

        // on utilise le pouvoir de l'assassin
        // NB : seul le roi peut être assassiné dans cette situation
        assassin.utiliserPouvoirAvatar();
        if(roi.getEstAssassine()) {
            Test.test(roi.getEstAssassine(), "le roi est assassiné");
        }
        else {
            Test.test(!roi.getEstAssassine(), "le roi n'est pas assassiné");
        }
    }

}