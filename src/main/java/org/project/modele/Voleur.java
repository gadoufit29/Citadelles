package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;
import java.util.Random;

public class Voleur extends Personnage{

    public Voleur(){
        super("Voleur", 2, Caracteristiques.VOLEUR, 1);
    }
    @Override
    public void utiliserPouvoir() {
        System.out.println("Voulez-vous voler un joueur ? (o / oui ;  n / non)");
        Boolean voler = Interaction.lireOuiOuNon();

        if(voler){
            ArrayList<Personnage> listePersonnages = super.getPlateau().getListePersonnages();
            do{
                System.out.println("Liste des personnages : ");
                int i = 1;
                for (Personnage personnage : listePersonnages) {
                    System.out.println(i + ". " + personnage.getNom() + " (Rang : " + personnage.getRang() + ")");
                    i++;
                }
                System.out.println("\nQuel est le numéro de personnage que vous voulez voler ? (0 pour ne pas voler de personnage)");

                int choix = Interaction.lireUnEntier(1 , listePersonnages.size() + 1);

                if(choix == 0){
                    return;
                }

                if(listePersonnages.get(choix - 1).getRang() != 1 && listePersonnages.get(choix - 1).getRang() != 2){
                    listePersonnages.get(choix - 1).setEstVole();
                    return;
                }

                else if(listePersonnages.get(choix - 1).getRang() == 1){
                    System.out.println("Vous ne pouvez pas vous voler un joueur de rang 1 !");
                    System.out.println();
                }

                else if (listePersonnages.get(choix - 1).getNom().equals("Voleur")) {
                    System.out.println("Vous ne pouvez pas vous voler vous-même !");
                    System.out.println();
                }
            }while(true);
        }

        else{
            System.out.println("Vous n'avez pas volé de personnage.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        System.out.println("Voulez-vous voler un joueur ? (o / oui ;  n / non)");
        Random random = new Random();

        Boolean voler = random.nextBoolean();

        if(voler){
            System.out.println("oui");
            ArrayList<Personnage> listePersonnages = super.getPlateau().getListePersonnages();
            do{
                System.out.println("Liste des personnages : ");
                int i = 1;
                for (Personnage personnage : listePersonnages) {
                    System.out.println(i + ". " + personnage.getNom() + " (Rang : " + personnage.getRang() + ")");
                    i++;
                }
                System.out.println("\nQuel est le numéro de personnage que vous voulez voler ? (0 pour ne pas voler de personnage)");
                int choix = random.nextInt(listePersonnages.size() + 1);
                System.out.println(choix);

                if(choix == 0){
                    return;
                }

                else if(listePersonnages.get(choix - 1).getRang() != 1 && listePersonnages.get(choix - 1).getRang() != 2){
                    listePersonnages.get(choix - 1).setEstVole();
                    return;
                }

                else if(listePersonnages.get(choix - 1).getRang() == 1){
                    System.out.println("Vous ne pouvez pas vous voler un joueur de rang 1 !");
                    System.out.println();
                }

                else if (listePersonnages.get(choix - 1).getNom().equals("Voleur")) {
                    System.out.println("Vous ne pouvez pas vous voler vous-même !");
                    System.out.println();
                }
            }while(true);
        }

        else{
            System.out.println("non");
            System.out.println("Vous n'avez pas volé de personnage.");
        }
    }
}
