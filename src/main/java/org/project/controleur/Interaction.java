package org.project.controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        do {
            try {
                i = sc.nextInt();
                sc.nextLine();
                return i;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un entier : ");
                sc.nextLine();
            }
        } while (true);
    }

    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        do {
            try {
                i = sc.nextInt();
                if (i >= borneMin && i < borneMax)
                    return i;
                else
                    System.out.print("Veuillez rentrer un entier entre " + borneMin + " et " + (borneMax - 1) + " : ");
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un entier : ");
                sc.nextLine();
            }
        } while (true);
    }

    public static boolean lireOuiOuNon() {
        String s = "";
        boolean reponse;
        do {
            try{
                s = sc.nextLine();
                if (s.equals("oui") || s.equals("o")){
                    reponse = true;
                    return reponse;
                }
                else if (s.equals("non") || s.equals("n")){
                    reponse = false;
                    return reponse;
                }
                else{
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e){
                System.out.print("Veuillez rentrer \"oui\", \"o\", \"non\" ou \"n\" : ");
            }
        } while (true);
    }

    public static String lireUneChaine() {
        String s = "";
        do {
            try{
                s = sc.nextLine();
                if (s.length() != 0){
                    return s;
                }
                else{
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e){
                System.out.print("Veuillez rentrer un mot: ");
            }
        } while (true);
    }
}