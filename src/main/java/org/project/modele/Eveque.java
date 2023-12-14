package org.project.modele;

import java.util.ArrayList;

public class Eveque extends Personnage{

    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        if(super.getJoueur() != null){
            int nbQuartiersPourBonus = 0;
            for(int i = 0 ; i < super.getJoueur().getCite().size() ; i++){
                if(super.getJoueur().getCite().get(i).getType().equals("RELIGIEUX")){
                    super.getJoueur().ajouterPieces(1);
                    nbQuartiersPourBonus++;
                }
            }

            if(nbQuartiersPourBonus > 0)
                System.out.println("Vous avez gagné " + nbQuartiersPourBonus + " pièces grâce à votre personnage Eveque car vous avez " + nbQuartiersPourBonus + " quartiers de type RELIGIEUX dans votre cité.");
            else
                System.out.println("Vous n'avez pas de quartiers de type RELIGIEUX dans votre cité.");

            System.out.println();
        }
    }

    @Override
    public void utiliserPouvoir() {
        super.utiliserPouvoir();
    }

    @Override
    public void utiliserPouvoirAvatar() {
        super.utiliserPouvoir();
    }
}
