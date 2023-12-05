package org.project.modele;

import modele.Caracteristiques;
import org.project.controleur.Interaction;

import java.util.ArrayList;
import java.util.Scanner;

public class Assassin extends Personnage{

    public Assassin(){
        super("Assassin", 1, Caracteristiques.ASSASSIN, 1);
    }
    @Override
    public void utiliserPouvoir() {
        ArrayList<Personnage> listePersonnages = this.getPlateau().getListePersonnages();
        for (Personnage personnage : listePersonnages) {
            System.out.println("Personnage : " + personnage.getNom() + " Rang : " + personnage.getRang());
            System.out.println();
            System.out.println("Quel personnage voulez-vous assassiner ?");
            Interaction.lireUnEntier();
        }
    }
}
