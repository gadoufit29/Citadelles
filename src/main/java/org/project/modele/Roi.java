package org.project.modele;

public class Roi extends Personnage {
    private Joueur joueur;
    private String nom;
    private int rang;
    private String caracteristique;
    private int nbPermisDeConstruire;
    private boolean estAssasine;
    private boolean estVole;
    private PlateauDeJeu plateau;

    public Personnage(String nom, int rang, String caracteristiques, int nbPermisDeConstruire){
        this.nom = nom;
        this.rang = rang;
        this.caracteristique = caracteristiques;
        this.nbPermisDeConstruire = nbPermisDeConstruire
    }

    // 2. Implémentez le constructeur vide
    public Roi() {
        // Appel du constructeur de la classe mère avec les paramètres spécifiés
        super("Roi", 4, modele.Caracteristiques.ROI, 1);
    }

    // 3. Surchargez la méthode percevoirRessourcesSpecifiques()
    @Override
    public void percevoirRessourcesSpecifiques() {
        // Comptabiliser le nombre de quartiers nobles
        public class QartierNoble {

        }
        getMainJoueur()
        //int nbQuartiersNobles = joueur.getQuartiersNobles();

        // Ajouter ce nombre au trésor du joueur
        joueur.ajouterPieces(nbQuartiersNobles);

        // Afficher un message indiquant le nombre de pièces ajoutées
        System.out.println("Le Roi a perçu " + nbQuartiersNobles + " pièce(s) d'or.");
    }
}
