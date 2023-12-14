package org.project.modele;

import java.util.ArrayList;

public class Marchande extends Personnage{
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        super.percevoirRessourcesSpecifiques();
        System.out.println("Vous avez gagné 1 pièce pour votre rang de marchande.");
        super.getJoueur().ajouterPieces(1);
        ArrayList<Quartier> quartierDansCite = super.getJoueur().getCite();

        int count = 0;
        for(int i = 0 ; i < quartierDansCite.size() ; i++){
            if(quartierDansCite.get(i).getType().equals("COMMERCANT")){
                count++;
            }
        }

        super.getJoueur().ajouterPieces(count);
        System.out.println("Vous avez gagné " + count + " pièce(s) car vous avez " + count + " quartier(s) de type COMMERCANT.");
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
