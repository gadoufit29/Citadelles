package org.project.modele;
public class Roi extends Personnage {

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        for(int i = 0; i < super.getJoueur().getCite().size(); i++) {
            if(super.getJoueur().getCite().get(i).getType().equals("NOBLE")) {
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
