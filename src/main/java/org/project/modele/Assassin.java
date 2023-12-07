package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;
import java.util.Random;

public class Assassin extends Personnage{

    public Assassin(){
        super("Assassin", 1, Caracteristiques.ASSASSIN, 1);
    }
    @Override
    public void utiliserPouvoir() {
        System.out.println("Voulez-vous asssassiner un joueur ? (o / oui ;  n / non)");
        Boolean assassiner = Interaction.lireOuiOuNon();

        if(assassiner){
            ArrayList<Personnage> listePersonnages = this.getPlateau().getListePersonnages();
            do{
                System.out.println("Liste des personnages : ");
                int i = 1;
                for (Personnage personnage : listePersonnages) {
                    System.out.println(i + ". " + personnage.getNom() + " (Rang : " + personnage.getRang() + ")");
                    i++;
                }
                System.out.println("\nQuel est le numéro de personnage que vous voulez assassiner ? (0 pour ne pas assassiner de personnage)");

                int choix = Interaction.lireUnEntier(1, listePersonnages.size() + 1);

                if(choix == 0){
                    System.out.println("Vous n'avez pas assassiné de personnage.");
                    return;
                }

                if(listePersonnages.get(choix - 1).getRang() != 1){
                    listePersonnages.get(choix - 1).setEstAssassine();
                    return;
                }

                if(listePersonnages.get(choix - 1).getNom().equals("Assassin")){
                    System.out.println("Vous ne pouvez pas vous assassiner vous-même !");
                    System.out.println();
                }

            }while(true);
        }
        else{
            System.out.println("Vous n'avez pas assassiné de personnage.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        Random random = new Random();

        System.out.println("Voulez-vous asssassiner un joueur ?");

        Boolean utiliserPouvoir = random.nextBoolean();

        if(utiliserPouvoir){
            System.out.println("oui");
            ArrayList<Personnage> listePersonnages = this.getPlateau().getListePersonnages();
            do{
                System.out.println("Liste des personnages : ");
                int i = 1;
                for (Personnage personnage : listePersonnages) {
                    System.out.println(i + ". " + personnage.getNom() + " (Rang : " + personnage.getRang() + ")");
                    i++;
                }

                int choix = random.nextInt(listePersonnages.size() + 1);

                System.out.println("\nQuel est le numéro de personnage que vous voulez assassiner ? (0 pour ne pas assassiner de personnage)");
                System.out.println(choix);

                if(choix == 0){
                    System.out.println("Vous n'avez pas assassiné de personnage.");
                    return;
                }

                if(listePersonnages.get(choix - 1).getRang() != 1){
                    listePersonnages.get(choix - 1).setEstAssassine();
                    return;
                }

                if(listePersonnages.get(choix - 1).getNom().equals("Assassin")){
                    System.out.println("Vous ne pouvez pas vous assassiner vous-même !");
                    System.out.println();
                }

            }while(true);
        }

        else{
            System.out.println("non");
            System.out.println("Vous n'avez pas assassiné de personnage.");
        }
    }
}