package org.project.modele;

import java.util.ArrayList;

public class Architecte extends Personnage{

    public Architecte() {
        super("Architecte", 7, Caracteristiques.ARCHITECTE, 3);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        for (int i = 0 ; i < 2 ; i++){
            super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
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
