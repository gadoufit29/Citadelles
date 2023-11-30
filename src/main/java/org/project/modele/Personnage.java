package org.project.modele;

public class Personnage {
    private String nom;
    private int rang;
    private String caracteristiques;
    private int nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine;
    private boolean estVole;

    public String getNom() {
        return nom;
    }

    public int getRang() {
        return rang;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public int getNbPermisDeConstruire() {
        return nbPermisDeConstruire;
    }


    public Joueur getJoueur() {
        return joueur;
    }

    public boolean isEstAssassine() {
        return estAssassine;
    }

    public boolean isEstVole() {
        return estVole;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setEstVole(boolean estVole) {
        this.estVole = estVole;
    }

    public void setEstAssassine(boolean estAssassine) {
        this.estAssassine = estAssassine;
    }

    public Personnage(String nom, int rang, String caracteristiques, int nbPermisDeConstruire) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.nbPermisDeConstruire = nbPermisDeConstruire;
        this.joueur = null;
        this.estAssassine = false;
        this.estVole = false;
    }
        public void ajouterPieces() {
            if (joueur != null && !estAssassine) {
                joueur.ajouterPieces(2);
                System.out.println("Ajout de deux pièces au trésor du joueur " + joueur.getNom());
            } else {
                System.out.println("Aucune pièce ajoutée. Le personnage n'est pas associé à un joueur ou est assassiné.");
            }
        }

    public void ajouterQuartierDansMain(Quartier nouveau) {
        if (joueur != null && !estAssassine) {
            if (joueur.getPossedeCouronne()) {
                joueur.ajouterQuartierDansMain(nouveau);
                System.out.println("Ajout du quartier " + nouveau.getNom() + " à la main du joueur " + joueur.getNom());
            } else {
                System.out.println("Le joueur " + joueur.getNom() + " ne peut pas ajouter de quartier à sa main. Il ne possède pas de couronne.");
            }
        } else {
            System.out.println("Le personnage n'est pas associé à un joueur ou est assassiné. Aucun quartier ajouté à la main.");
        }
    }

        public void construire(Quartier nouveau) {

            if (joueur != null && !estAssassine) {

                if (joueur.getPossedeCouronne()) {

                    joueur.ajouterQuartierDansCite(nouveau);
                    System.out.println("Construction du quartier " + nouveau.getNom() + " dans la cité du joueur " + joueur.getNom());
                } else {
                    System.out.println("Le joueur " + joueur.getNom() + " ne peut pas construire de quartier dans sa cité. Il ne possède pas de couronne.");
                }
            } else {
                System.out.println("Le personnage n'est pas associé à un joueur ou est assassiné. Aucun quartier construit dans la cité.");
            }
        }
        public void percevoirRessourcesSpecifiques() {

            if (joueur != null && !estAssassine) {

                if (joueur.getPossedeCouronne()) {

                    System.out.println("Aucune ressource spécifique");
                } else {
                    System.out.println("Le joueur " + joueur.getNom() + " ne peut pas percevoir de ressources spécifiques. Il ne possède pas de couronne.");
                }
            } else {
                System.out.println("Le personnage n'est pas associé à un joueur ou est assassiné. Aucune ressource spécifique perçue.");
            }
        }

        // Méthode pour utiliser le pouvoir du personnage
        public void utiliserPouvoir() {
            // Vérifier si le personnage est associé à un joueur et n'est pas assassiné
            if (joueur != null && !estAssassine) {
                // Traiter le cas où le personnage utilise son pouvoir
                // (Ajoutez votre logique spécifique ici si nécessaire)
                System.out.println("Aucun pouvoir");
            } else {
                System.out.println("Le personnage n'est pas associé à un joueur ou est assassiné. Aucun pouvoir utilisé.");
            }
        }

        // Méthode pour réinitialiser les attributs joueur, estAssassine et estVole
        public void reinitialiser() {
            this.joueur = null;
            this.estAssassine = false;
            this.estVole = false;
        }
    }








