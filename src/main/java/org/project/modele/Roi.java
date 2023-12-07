package org.project.modele;
public class Roi extends Personnage {

    // 2. Implémentez le constructeur vide
    public Roi() {
        // Appel du constructeur de la classe mère avec les paramètres spécifiés
        super("Roi", 4, Caracteristiques.ROI, 1);
    }

    // 3. Surchargez la méthode percevoirRessourcesSpecifiques()
    @Override
    public void percevoirRessourcesSpecifiques() {
        if(super.getJoueur() == null || super.getEstAssassine()) {
            return;
        }
        else{
            int nbPieces = 0;
            for(int i = 0; i < this.getJoueur().getCite().size(); i++) {
                if(this.getJoueur().getCite().get(i).getType().equals("NOBLE")) {
                    this.getJoueur().ajouterPieces(1);
                    nbPieces++;
                }
            }
            System.out.println("Le joueur " + this.getJoueur().getNom() + " a gagné " + nbPieces);
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {}
}
