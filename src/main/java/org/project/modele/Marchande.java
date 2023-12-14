package org.project.modele;

import java.util.ArrayList;

public class Marchande extends Personnage{
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        super.percevoirRessourcesSpecifiques();
        if(super.getJoueur() != null){
            int nbQuartiersPourBonus = 0;
            for(int i = 0 ; i < super.getJoueur().getCite().size() ; i++){
                if(super.getJoueur().getCite().get(i).getType().equals("COMMERCANT")){
                    super.getJoueur().ajouterPieces(1);
                    nbQuartiersPourBonus++;
                }
            }

            if(nbQuartiersPourBonus > 0)
                System.out.println("Vous avez gagné " + nbQuartiersPourBonus + " pièces grâce à votre personnage Marchande car vous avez " + nbQuartiersPourBonus + " quartiers de type COMMERCANT dans votre cité.");
            else
                System.out.println("Vous n'avez pas de quartiers de type COMMERCANT dans votre cité.");

            System.out.println();
        }
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Vous avez gagné 1 pièce pour votre rang de marchande.");
        super.getJoueur().ajouterPieces(1);
    }

    @Override
    public void utiliserPouvoirAvatar() {
        super.utiliserPouvoir();
    }
}
