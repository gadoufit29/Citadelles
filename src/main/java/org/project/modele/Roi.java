package org.project.modele;
public class Roi extends Personnage {

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        if(super.getJoueur() != null){
            int nbQuartiersPourBonus = 0;
            for(int i = 0; i < super.getJoueur().getCite().size(); i++) {
                if(super.getJoueur().getCite().get(i).getType().equals("NOBLE")) {
                    super.getJoueur().ajouterPieces(1);
                    nbQuartiersPourBonus++;
                }
            }
            if(nbQuartiersPourBonus > 0)
                System.out.println("Vous avez gagné " + nbQuartiersPourBonus + " pièces grâce à votre personnage Roi car vous avez " + nbQuartiersPourBonus + " quartiers de type NOBLE dans votre cité.");
            else
                System.out.println("Vous n'avez pas de quartiers de type NOBLE dans votre cité.");

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
