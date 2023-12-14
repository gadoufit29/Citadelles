package org.project.modele;

import org.project.controleur.Interaction;

import java.util.ArrayList;
import java.util.Random;

public class Magicienne extends Personnage{

    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE, 1);
    }

    @Override
    public void utiliserPouvoir() {
        if(super.getJoueur().getMainJoueur().isEmpty()){
            System.out.println("Vous n'avez pas de cartes dans votre main, votre pouvoir ne peut pas être utilisé !");
        }
        else{
            afficherCartesDansMain();
            System.out.println("Voulez-vous échanger votre main avec celle d'un autre joueur ? (o / oui ;  n / non)");

            Boolean echanger = Interaction.lireOuiOuNon();

            if (echanger) {
                ArrayList<Joueur> listeJoueurs = super.getPlateau().getListeJoueurs();
                do {
                    System.out.println("Liste des joueurs : ");
                    int i = 1;
                    for (Joueur joueur : listeJoueurs) {
                        System.out.println(i + ". " + joueur.getNom() + " (Nombre de cartes : " + joueur.getMainJoueur().size() + ")");
                        i++;
                    }
                    System.out.println("\nQuel est le numéro du joueur avec lequel vous voulez échanger votre main ? (0 pour ne pas échanger)");

                    int choix = Interaction.lireUnEntier(0, super.getPlateau().getNbJoueurs() + 1);

                    if(choix == 0){
                        return;
                    }

                    if (listeJoueurs.get(choix - 1).getNom().equals("Magicienne")){
                        System.out.println("Vous ne pouvez pas échanger votre main avec vous-même !");
                        System.out.println();
                        continue;
                    }

                    if(listeJoueurs.get(choix - 1).getMainJoueur().size() == 0){
                        System.out.println("Le joueur choisi n'a pas de cartes dans sa main, veuillez en choisir un autre !");
                        continue;
                    }

                    Joueur magicienne = super.getJoueur();
                    Joueur joueurChoisi = listeJoueurs.get(choix - 1);
                    ArrayList<Quartier> temp = magicienne.getMainJoueur();
                    magicienne.replaceMain(joueurChoisi.getMainJoueur());
                    joueurChoisi.replaceMain(temp);

                    afficherCartesDansMain();

                    return;

                } while (true);
            }

            else if(!echanger){
                afficherCartesDansMain();
                System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? (0 pour ne pas prendre de cartes)");
                int nbCartes = Interaction.lireUnEntier(0, super.getJoueur().getMainJoueur().size() + 1);

                if(nbCartes == 0){
                    return;
                }

                else if (nbCartes == super.getJoueur().getMainJoueur().size()){
                    for(int i = 0; i < nbCartes; i++){
                        super.getPlateau().getPioche().ajouter(super.getJoueur().getMainJoueur().get(i));
                        super.getJoueur().retirerQuartierDansMain(0);
                        super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
                    }
                }
                else{
                    int i = 0;
                    do{
                        System.out.println("Numéro de la carte n°" + (i+1) + " que vous voulez retirer");
                        int choix = Interaction.lireUnEntier(1, super.getJoueur().getMainJoueur().size() + 1);

                        super.getPlateau().getPioche().ajouter(super.getJoueur().getMainJoueur().get(choix - 1));
                        super.getJoueur().retirerQuartierDansMain(choix - 1);
                        afficherCartesDansMain();
                        i++;

                    }while(i != nbCartes);

                    for(int j = 0; j < nbCartes; j++){
                        super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
                    }
                }
                afficherCartesDansMain();
            }
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        if(super.getJoueur().getMainJoueur().size() == 0){
            System.out.println("Vous n'avez pas de cartes dans votre main, votre pouvoir ne peut pas être utilisé !");
        }
        else{
            afficherCartesDansMain();
            System.out.println("Voulez-vous échanger votre main avec celle d'un autre joueur ? (o / oui ;  n / non)");

            Random random = new Random();

            Boolean echanger = random.nextBoolean();

            if (echanger) {
                System.out.println("oui");
                ArrayList<Joueur> listeJoueurs = super.getPlateau().getListeJoueurs();
                do {
                    System.out.println("Liste des joueurs : ");
                    int i = 1;
                    for (Joueur joueur : listeJoueurs) {
                        System.out.println(i + ". " + joueur.getNom() + " (Nombre de cartes : " + joueur.getMainJoueur().size() + ")");
                        i++;
                    }
                    System.out.println("\nQuel est le numéro du joueur avec lequel vous voulez échanger votre main ? (0 pour ne pas échanger)");
                    int choix = random.nextInt(super.getPlateau().getNbJoueurs() + 1);
                    System.out.println(choix);

                    if(choix == 0){
                        return;
                    }

                    if (listeJoueurs.get(choix - 1).getNom().equals("Magicienne")){
                        System.out.println("Vous ne pouvez pas échanger votre main avec vous-même !");
                        System.out.println();
                        continue;
                    }

                    if(listeJoueurs.get(choix - 1).getMainJoueur().isEmpty()){
                        System.out.println("Le joueur choisi n'a pas de cartes dans sa main, veuillez en choisir un autre !");
                        continue;
                    }

                    Joueur magicienne = super.getJoueur();
                    Joueur joueurChoisi = listeJoueurs.get(choix - 1);
                    ArrayList<Quartier> temp = magicienne.getMainJoueur();
                    magicienne.replaceMain(joueurChoisi.getMainJoueur());
                    joueurChoisi.replaceMain(temp);

                    afficherCartesDansMain();

                    return;

                } while (true);
            }

            else if(!echanger){
                System.out.println("non");
                afficherCartesDansMain();

                System.out.println("Combien de cartes voulez-vous prendre dans la pioche ? (0 pour ne pas prendre de cartes)");
                int nbCartes = random.nextInt(super.getJoueur().getMainJoueur().size() + 1);
                System.out.println(nbCartes);

                if(nbCartes == 0){
                    return;
                }
                else if (nbCartes == super.getJoueur().getMainJoueur().size()){
                    for(int i = 0; i < nbCartes; i++){
                        super.getPlateau().getPioche().ajouter(super.getJoueur().getMainJoueur().get(i));
                        super.getJoueur().retirerQuartierDansMain(0);
                        super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
                    }
                }
                else{
                    int i = 0;
                    do{
                        System.out.println("Numéro de la carte n°" + (i+1) + " que vous voulez retirer");
                        int choix = random.nextInt(super.getJoueur().getMainJoueur().size()) + 1;
                        System.out.println(choix);

                        super.getPlateau().getPioche().ajouter(super.getJoueur().getMainJoueur().get(choix - 1));
                        super.getJoueur().retirerQuartierDansMain(choix - 1);
                        afficherCartesDansMain();
                        i++;

                    }while(i != nbCartes);

                    for(int j = 0; j < nbCartes; j++){
                        super.getJoueur().ajouterQuartierDansMain(super.getPlateau().getPioche().piocher());
                    }
                }
                afficherCartesDansMain();
            }
        }
    }

    public void afficherCartesDansMain(){
        System.out.println("Liste des cartes dans votre main : ");
        int i = 1;
        for (Quartier quartier : super.getJoueur().getMainJoueur()) {
            System.out.println(i + ". " + quartier.getNom() + " (Type : " + quartier.getType() + ")" + " (Cout : " + quartier.getCoutConstruction() + ")");
            i++;
        }
        System.out.println();
    }
}
