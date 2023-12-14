package org.project.modele;

import java.util.ArrayList;
import java.util.Random;

import org.project.controleur.Interaction;
import org.project.utils.Colors;

public class Condottiere extends Personnage{

    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE, 1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        super.percevoirRessourcesSpecifiques();
        ArrayList<Quartier> quartierDansCite = super.getJoueur().getCite();

        for(int i = 0 ; i < quartierDansCite.size() ; i++){
            if(quartierDansCite.get(i).getType().equals("MILITAIRE")){
                super.getJoueur().ajouterPieces(1);
            }
        }
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println("Voulez vous utiliser votre pouvoir de destruction ? (o / oui ; n / non)");
        Boolean utiliserPouvoir = Interaction.lireOuiOuNon();

        if(utiliserPouvoir){
            System.out.println("Voici la liste des joueurs et le contenu de leur cite : ");
            ArrayList<Joueur> listeJoueurs = super.getPlateau().getListeJoueurs();

            for(Joueur joueur : listeJoueurs){
                StringBuilder stringBuilder = new StringBuilder("\t " + (listeJoueurs.indexOf(joueur) + 1) + ". " + joueur.getNom() + " : ");
                int i = 1;
                for(Quartier quartier : joueur.getCite()){
                    stringBuilder.append("\n \t \t Quartier n°"+ i + " : " + joueur.getCite().get(joueur.getCite().indexOf(quartier)).getNom() + " (cout de destruction : " + (joueur.getCite().get(joueur.getCite().indexOf(quartier)).getCoutConstruction() - 1) + " pièces)");
                    i++;
                }

                System.out.println(stringBuilder);
            }

            System.out.println("Vous avez " + super.getJoueur().getTresor() + " pièces");

            do{
                System.out.println("Quel est le numéro du joueur chez qui vous voulez détruire un quartier ? (0 pour ne pas détruire de quartier)");
                int choixJoueur = Interaction.lireUnEntier(0, listeJoueurs.size() + 1);
                System.out.println(choixJoueur);

                if(choixJoueur == 0){
                    System.out.println("Aucun quartier détruit.");
                    return;
                }

                if(super.getPlateau().getJoueur(choixJoueur - 1).getCite().isEmpty()){
                    System.out.println("Vous ne pouvez pas détruire de quartier chez un joueur qui n'a pas de quartier !");
                }

                else if (super.getPlateau().getJoueur(choixJoueur - 1).getCite().size() >= 7) {
                    System.out.println("Vous ne pouvez pas détruire de quartier chez un joueur qui a une cité complète (7 quartiers ou plus).");
                }

                else{
                    do {
                        System.out.println("Quel est le numéro du quartier que vous voulez détruire ? (0 pour ne pas détruire de quartier)");
                        int choixQuartier = Interaction.lireUnEntier(0, listeJoueurs.get(choixJoueur - 1).getCite().size() + 1);
                        System.out.println(choixQuartier);

                        if(choixQuartier == 0){
                            System.out.println("Aucun quartier détruit.");
                            return;
                        }

                        if(listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getNom() == "Donjon"){
                            System.out.println("Vous ne pouvez pas détruire le quartier Donjon ! (Protection contre les personnages de rang 8 et plus)");
                        }

                        else{
                            if(super.getJoueur().getTresor() >= (listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getCoutConstruction() - 1)){
                                super.getJoueur().retirerPieces((listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getCoutConstruction() - 1));
                                System.out.println(Colors.ANSI_RED + "Le quartier " + listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getNom() + " a été détruit !" + Colors.ANSI_RESET);
                                super.getPlateau().getPioche().ajouter(listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1));
                                listeJoueurs.get(choixJoueur - 1).getCite().remove(choixQuartier - 1);
                                return;
                            }

                            System.out.println("Vous n'avez pas assez de pièces pour détruire ce quartier !");
                        }

                    }while(true);
                }
                return;

            }while(true);
        }
        else{
            System.out.println("Aucun quarter détruit.");
        }
    }

    @Override
    public void utiliserPouvoirAvatar() {
        System.out.println("Voulez vous utiliser votre pouvoir de destruction ?");
        Random random = new Random();

        Boolean utiliserPouvoir = random.nextBoolean();

        if(utiliserPouvoir){
            System.out.println("oui");
            System.out.println("Voici la liste des joueurs et le contenu de leur cite : ");
            ArrayList<Joueur> listeJoueurs = super.getPlateau().getListeJoueurs();

            for(Joueur joueur : listeJoueurs){
                StringBuilder stringBuilder = new StringBuilder("\t " + (listeJoueurs.indexOf(joueur) + 1) + ". " + joueur.getNom() + " : ");
                int i = 1;
                for(Quartier quartier : joueur.getCite()){
                    stringBuilder.append("\n \t \t Quartier n°"+ i + " : " + joueur.getCite().get(joueur.getCite().indexOf(quartier)).getNom() + " (cout de destruction : " + (joueur.getCite().get(joueur.getCite().indexOf(quartier)).getCoutConstruction() - 1) + " pièces)");
                    i++;
                }

                System.out.println(stringBuilder);
            }

            System.out.println("Vous avez " + super.getJoueur().getTresor() + " pièces");

            do{
                System.out.println("Quel est le numéro du joueur chez qui vous voulez détruire un quartier ? (0 pour ne pas détruire de quartier)");
                int choixJoueur = random.nextInt(listeJoueurs.size() + 1);
                System.out.println(choixJoueur);

                if(choixJoueur == 0){
                    System.out.println("Aucun quartier détruit.");
                    return;
                }

                if(super.getPlateau().getJoueur(choixJoueur - 1).getCite().isEmpty()){
                    System.out.println("Vous ne pouvez pas détruire de quartier chez un joueur qui n'a pas de quartier !");
                }

                else if (super.getPlateau().getJoueur(choixJoueur - 1).getCite().size() == 7) {
                    System.out.println("Vous ne pouvez pas détruire de quartier chez un joueur qui a une cité complète (7 quartiers ou plus).");
                }

                else{
                    if(choixJoueur != 0){
                        do {
                            System.out.println("Quel est le numéro du quartier que vous voulez détruire ? (0 pour ne pas détruire de quartier)");
                            int choixQuartier = random.nextInt(listeJoueurs.get(choixJoueur - 1).getCite().size() + 1);
                            System.out.println(choixQuartier);

                            if(choixQuartier == 0){
                                System.out.println("Aucun quartier détruit.");
                                return;
                            }

                            if(super.getJoueur().getTresor() >= (listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getCoutConstruction() - 1)){
                                super.getJoueur().retirerPieces((listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getCoutConstruction() - 1));
                                System.out.println(Colors.ANSI_RED + "Le quartier " + listeJoueurs.get(choixJoueur - 1).getCite().get(choixQuartier - 1).getNom() + " a été détruit !" + Colors.ANSI_RESET);
                                listeJoueurs.get(choixJoueur - 1).getCite().remove(choixQuartier - 1);
                                return;
                            }

                            System.out.println("Vous n'avez pas assez de pièces pour détruire ce quartier !");

                        }while(true);
                    }
                    return;
                }
            }while(true);
        }
        else{
            System.out.println("non");
            System.out.println("Aucun quarter détruit.");
        }
    }
}
