package org.project.controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un entier : ");
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            }
        } while (continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle
    //     [borneMin, borneMax[

    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                if (i < borneMin && i >= borneMax){
                    System.out.println("Veuillez entrer un entier dans l'intervalle : "+ borneMin + " " + borneMax);
                }
                else {
                    continu = false;
                }
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un entier : ");
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            }
        } while (continu);
        return i;
    }
/*
    // lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
    public static boolean lireOuiOuNon() {
        // ...
    }

    // renvoie une chaîne de caractère lue au clavier :
    public static String lireUneChaine() {
        // ...
    }

     */
}