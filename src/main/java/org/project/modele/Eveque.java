package org.project.modele;

import java.util.ArrayList;

public class Eveque extends Personnage{

    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        ArrayList<Quartier> quartierDansCite = super.getJoueur().getCite();

        for(int i = 0 ; i < quartierDansCite.size() ; i++){
            if(quartierDansCite.get(i).getType().equals("RELIGIEUX")){
                super.getJoueur().ajouterPieces(1);
            }
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
