package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;

public class Magicienne extends Personnage{

    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE, 1);
    }

    @Override
    public void utiliserPouvoir() {
        if(this.getJoueur().getMainJoueur().size() == 0){
            System.out.println("Vous n'avez pas de cartes dans votre main, votre pouvoir ne peut pas être utilisé !");
        }
        else{
            afficherCartesDansMain();
            System.out.println("Voulez-vous échanger votre main avec celle d'un autre joueur ? (o / oui ;  n / non)");

            Boolean echanger = Interaction.lireOuiOuNon();

            if (echanger) {
                echangerMainAvecJoueur();
            }

            else if(!echanger){
                prendreCarteDansPioche();
            }
        }
    }

    public void echangerMainAvecJoueur(){
        ArrayList<Joueur> listeJoueurs = this.getPlateau().getListeJoueurs();
        do {
            System.out.println("Liste des joueurs : ");
            int i = 1;
            for (Joueur joueur : listeJoueurs) {
                System.out.println(i + ". " + joueur.getNom() + " (Nombre de cartes : " + joueur.getMainJoueur().size() + ")");
                i++;
            }
            System.out.println("\nQuel est le numéro du joueur avec lequel vous voulez échanger votre main ?");

            int choix = Interaction.lireUnEntier(1, this.getPlateau().getNbJoueurs() + 1);

            //...
            if (listeJoueurs.get(choix - 1).getNom().equals("Magicienne")){
                System.out.println("Vous ne pouvez pas échanger votre main avec vous-même !");
                System.out.println();
            }

            else if(listeJoueurs.get(choix - 1).getMainJoueur().size() == 0){
                System.out.println("Le joueur choisi n'a pas de cartes dans sa main, veuillez en choisir un autre !");
            }

            else{
                Joueur magicienne = this.getJoueur();
                Joueur joueurChoisi = listeJoueurs.get(choix - 1);
                ArrayList<Quartier> temp = magicienne.getMainJoueur();
                magicienne.replaceMain(joueurChoisi.getMainJoueur());
                joueurChoisi.replaceMain(temp);

                afficherCartesDansMain();

                return;
            }
        } while (true);
    }

    public void prendreCarteDansPioche(){
        afficherCartesDansMain();
        System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
        int nbCartes = Interaction.lireUnEntier(0, this.getJoueur().getMainJoueur().size() + 1);
        if(nbCartes == 0){
            return;
        }
        else if (nbCartes == this.getJoueur().getMainJoueur().size()){
            for(int i = 0; i < nbCartes; i++){
                this.getPlateau().getPioche().ajouter(this.getJoueur().getMainJoueur().get(i));
                this.getJoueur().retirerQuartierDansMain(0);
                this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
            }
        }
        else{
            int i = 0;
            do{
                System.out.println("Numéro de la carte n°" + (i+1) + " que vous voulez retirer (0 pour arrêter)");
                int choix = Interaction.lireUnEntier(0, this.getJoueur().getMainJoueur().size() + 1);
                if(choix == 0){
                    return;
                }
                else{
                    this.getPlateau().getPioche().ajouter(this.getJoueur().getMainJoueur().get(choix - 1));
                    this.getJoueur().retirerQuartierDansMain(choix - 1);
                    i++;
                }

            }while(i != nbCartes);

            for(int j = 0; j < nbCartes; j++){
                this.getJoueur().ajouterQuartierDansMain(this.getPlateau().getPioche().piocher());
            }
        }
        afficherCartesDansMain();
    }

    public void afficherCartesDansMain(){
        System.out.println("Liste des cartes dans votre main : ");
        int i = 1;
        for (Quartier quartier : this.getJoueur().getMainJoueur()) {
            System.out.println(i + ". " + quartier.getNom() + " (Type : " + quartier.getType() + ")");
            i++;
        }
        System.out.println();
    }
}
