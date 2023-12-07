package org.project.modele;

import java.util.ArrayList;

public class Architecte extends Personnage{

        public Architecte() {
            super("Architecte", 7, Caracteristiques.ARCHITECTE, 3);
        }

        @Override
        public void percevoirRessourcesSpecifiques() {
            for (int i = 0 ; i < 2 ; i++){
                this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
            }
        }
}
