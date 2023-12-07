package org.project.modele;

import java.util.ArrayList;

public class Marchande extends Personnage{
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques(){
        ArrayList<Quartier> quartierDansCite = this.getJoueur().getCite();

        for(int i = 0 ; i < quartierDansCite.size() ; i++){
            if(quartierDansCite.get(i).getType().equals("COMMERCANT")){
                this.getJoueur().ajouterPieces(1);
            }
        }
    }

    @Override
    public void utiliserPouvoir() {
        this.getJoueur().ajouterPieces(1);
    }
}
