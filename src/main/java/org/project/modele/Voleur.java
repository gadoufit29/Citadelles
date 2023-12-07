package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;

public class Voleur extends Personnage{

    public Voleur(){
        super("Voleur", 2, Caracteristiques.VOLEUR, 1);
    }
    @Override
    public void utiliserPouvoir() {
        ArrayList<Personnage> listePersonnages = this.getPlateau().getListePersonnages();
        do{
            System.out.println("Liste des personnages : ");
            int i = 1;
            for (Personnage personnage : listePersonnages) {
                System.out.println(i + ". " + personnage.getNom() + " (Rang : " + personnage.getRang() + ")");
                i++;
            }
            System.out.println("\nQuel est le numéro de personnage que vous voulez voler ?");

            int choix = Interaction.lireUnEntier(1 , listePersonnages.size() + 1);

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
}
