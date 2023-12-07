package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;

public class Assassin extends Personnage{

    public Assassin(){
        super("Assassin", 1, Caracteristiques.ASSASSIN, 1);
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
            System.out.println("\nQuel est le numéro de personnage que vous voulez assassiner ?");

            int choix = Interaction.lireUnEntier(1, listePersonnages.size() + 1);

            if(listePersonnages.get(choix - 1).getRang() != 1){
                listePersonnages.get(choix - 1).setEstAssassine();
                return;
            }

            else if(listePersonnages.get(choix - 1).getNom().equals("Assassin")){
                System.out.println("Vous ne pouvez pas vous assassiner vous-même !");
                System.out.println();
            }

        }while(true);
    }
}