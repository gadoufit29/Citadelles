package org.project.modele;

public class Personnage {

    // Attributs
    private String nom, caracteristiques;
    private int rang, nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine, estVole;


    private Plateau plateau;

    /*
        Constructeur
        On utilise les setters dès que l'on peut, ici on utilise uniquement le setter de joueur car c'est
        la seule méthode qui permet de réecrire un attribut en fonction d'un paramètre dans les parentheses
     */
    public Personnage(String nom, int rang, String caracteristiques, int nbPermisDeConstruire) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.nbPermisDeConstruire = nbPermisDeConstruire;
        setJoueur(null);
        this.estAssassine = false;
        this.estVole = false;
    }

    //Getters et setters

    public String getNom() {
        return nom;
    }

    public int getRang() {
        return rang;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public int getNbPermis() {
        return nbPermisDeConstruire;
    }


    public Joueur getJoueur() {
        return joueur;
    }

    public boolean getEstAssassine() {
        return estAssassine;
    }

    public boolean getEstVole() {
        return estVole;
    }

    public void setJoueur(Joueur j) {
        this.joueur = j;
    }

    public void setEstVole() {
        this.estVole = true;
    }

    public void setEstAssassine() {
        this.estAssassine = true;
    }

    public Plateau getPlateau() {return plateau;}

    public void setPlateau(Plateau plateau) {this.plateau = plateau;}

    // Méthode pour ajouter des pièces au trésor du joueur
    public void ajouterPieces() {
        // Si le personnage est associé à un joueur et n'est pas assassiné, on ajoute deux pièces au trésor du joueur
        if (getJoueur() != null && getEstAssassine() == false){
            getJoueur().ajouterPieces(2);
            System.out.println("Ajout de deux pièces au trésor du joueur " + getJoueur().getNom());
        }

        // Si le personnage n'est pas associé à un joueur, on affiche un message d'erreur
        else if(getJoueur() == null){
            System.out.println("Aucune pièce ajoutée. Le personnage n'est pas associé à un joueur");
        }

        // Si le personnage est assassiné, on affiche un message d'erreur
        else if(getEstAssassine() == true){
            System.out.println("Aucune pièce ajoutée. Le personnage est assassiné");
        }

        // Si le personnage n'est pas associé à un joueur et n'est pas assassiné, on affiche un message d'erreur
        else{
            System.out.println("Aucune pièce ajoutée. Erreur inconnue");
        }
    }

    // Méthode pour piocher un quartier
    public void ajouterQuartierDansMain(Quartier nouveau) {
        if (getJoueur() != null && getEstAssassine() == false) {
            getJoueur().ajouterQuartierDansMain(nouveau);
            System.out.println("Ajout du quartier " + nouveau.getNom() + " à la main du joueur " + getJoueur().getNom());
        }
        else if(getJoueur() == null){
            System.out.println("Aucun quartier ajouté. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine() == true){
            System.out.println("Aucun quartier ajouté. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun quartier ajouté. Erreur inconnue");
        }
    }

    // Méthode pour construire un quartier
    public void construire(Quartier nouveau) {
        if (getJoueur() != null && getEstAssassine() == false) {
            getJoueur().ajouterQuartierDansCite(nouveau);
            System.out.println("Construction du quartier " + nouveau.getNom() + " dans la cité du joueur " + getJoueur().getNom());
        }
        else if(getJoueur() == null){
            System.out.println("Aucun quartier construit. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine() == true){
            System.out.println("Aucun quartier construit. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun quartier construit. Erreur inconnue");
        }
    }

    // Méthode pour percevoir les ressources spécifiques du personnage
    public void percevoirRessourcesSpecifiques() {
        if (getJoueur() != null && getEstAssassine() == false) {
            System.out.println("Aucune ressource spécifique");
        }
        else if(getJoueur() == null){
            System.out.println("Aucune ressource spécifique. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine() == true){
            System.out.println("Aucune ressource spécifique. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucune ressource spécifique. Erreur inconnue");
        }
    }

    // Méthode pour utiliser le pouvoir du personnage
    public void utiliserPouvoir() {
        if (getJoueur() != null && getEstAssassine() == false) {
            System.out.println("Aucun pouvoir");
        }
        else if(getJoueur() == null){
            System.out.println("Aucun pouvoir. Le personnage n'est pas associé à un joueur");
        }
        else if(getEstAssassine() == true){
            System.out.println("Aucun pouvoir. Le personnage est assassiné");
        }
        else{
            System.out.println("Aucun pouvoir. Erreur inconnue");
        }
    }

    // Méthode pour réinitialiser les attributs joueur, estAssassine et estVole
    public void reinitialiser() {
        setJoueur(null);
        this.estAssassine = false;
        this.estVole = false;
    }
}








