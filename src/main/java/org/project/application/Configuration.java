package org.project.application;

import org.project.modele.*;

public class Configuration {
    public static Pioche nouvellePioche() {

        Pioche nouvellePioche = new Pioche();

        // Création des quartiers

        /* Religieux */

        Quartier quartier1 = new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier2 = new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier3 = new Quartier("Temple", Quartier.TYPE_QUARTIERS[0], 1);

        Quartier quartier4 = new Quartier("Église", Quartier.TYPE_QUARTIERS[0], 2);
        Quartier quartier5 = new Quartier("Église", Quartier.TYPE_QUARTIERS[0], 2);
        Quartier quartier6 = new Quartier("Église", Quartier.TYPE_QUARTIERS[0], 2);
        
        Quartier quartier7 = new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3);
        Quartier quartier8 = new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3);
        Quartier quartier9 = new Quartier("Monastère", Quartier.TYPE_QUARTIERS[0], 3);

        Quartier quartier10 = new Quartier("Cathédrale", Quartier.TYPE_QUARTIERS[0], 5);
        Quartier quartier11 = new Quartier("Cathédrale", Quartier.TYPE_QUARTIERS[0], 5);

        /* Militaire */
        
        Quartier quartier12 = new Quartier("Tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
        Quartier quartier13 = new Quartier("Tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
        Quartier quartier14 = new Quartier("Tour de guet", Quartier.TYPE_QUARTIERS[1], 1);
        
        Quartier quartier15 = new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2);
        Quartier quartier16 = new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2);
        Quartier quartier17 = new Quartier("Prison", Quartier.TYPE_QUARTIERS[1], 2);
        
        Quartier quartier18 = new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3);
        Quartier quartier19 = new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3);
        Quartier quartier20 = new Quartier("Caserne", Quartier.TYPE_QUARTIERS[1], 3);
        
        Quartier quartier21 = new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5);
        Quartier quartier22 = new Quartier("Forteresse", Quartier.TYPE_QUARTIERS[1], 5);

        /* Noble */
        
        Quartier quartier23 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);
        Quartier quartier24 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);
        Quartier quartier25 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);
        Quartier quartier26 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);
        Quartier quartier27 = new Quartier("Manoir", Quartier.TYPE_QUARTIERS[2], 3);

        Quartier quartier28 = new Quartier("Château", Quartier.TYPE_QUARTIERS[2], 4);
        Quartier quartier29 = new Quartier("Château", Quartier.TYPE_QUARTIERS[2], 4);
        Quartier quartier30 = new Quartier("Château", Quartier.TYPE_QUARTIERS[2], 4);
        Quartier quartier31 = new Quartier("Château", Quartier.TYPE_QUARTIERS[2], 4);

        Quartier quartier32 = new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5);
        Quartier quartier33 = new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5);
        Quartier quartier34 = new Quartier("Palais", Quartier.TYPE_QUARTIERS[2], 5);

        /* Commerçant */

        Quartier quartier35 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);
        Quartier quartier36 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);
        Quartier quartier37 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);
        Quartier quartier38 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);
        Quartier quartier39 = new Quartier("Taverne", Quartier.TYPE_QUARTIERS[3], 1);

        Quartier quartier40 = new Quartier("Échoppe", Quartier.TYPE_QUARTIERS[3], 2);
        Quartier quartier41 = new Quartier("Échoppe", Quartier.TYPE_QUARTIERS[3], 2);
        Quartier quartier42 = new Quartier("Échoppe", Quartier.TYPE_QUARTIERS[3], 2);

        Quartier quartier43 = new Quartier("Marché", Quartier.TYPE_QUARTIERS[3], 2);
        Quartier quartier44 = new Quartier("Marché", Quartier.TYPE_QUARTIERS[3], 2);
        Quartier quartier45 = new Quartier("Marché", Quartier.TYPE_QUARTIERS[3], 2);
        Quartier quartier46 = new Quartier("Marché", Quartier.TYPE_QUARTIERS[3], 2);

        Quartier quartier47 = new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3);
        Quartier quartier48 = new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3);
        Quartier quartier49 = new Quartier("Comptoir", Quartier.TYPE_QUARTIERS[3], 3);

        Quartier quartier50 = new Quartier("Port", Quartier.TYPE_QUARTIERS[3], 4);
        Quartier quartier51 = new Quartier("Port", Quartier.TYPE_QUARTIERS[3], 4);
        Quartier quartier52 = new Quartier("Port", Quartier.TYPE_QUARTIERS[3], 4);

        Quartier quartier53 = new Quartier("Hôtel de ville", Quartier.TYPE_QUARTIERS[3], 5);
        Quartier quartier54 = new Quartier("Hôtel de ville", Quartier.TYPE_QUARTIERS[3], 5);

        // Ajout des quartiers à la pioche

        nouvellePioche.ajouter(quartier1);
        nouvellePioche.ajouter(quartier2);
        nouvellePioche.ajouter(quartier3);
        nouvellePioche.ajouter(quartier4);
        nouvellePioche.ajouter(quartier5);
        nouvellePioche.ajouter(quartier6);
        nouvellePioche.ajouter(quartier7);
        nouvellePioche.ajouter(quartier8);
        nouvellePioche.ajouter(quartier9);
        nouvellePioche.ajouter(quartier10);
        nouvellePioche.ajouter(quartier11);
        nouvellePioche.ajouter(quartier12);
        nouvellePioche.ajouter(quartier13);
        nouvellePioche.ajouter(quartier14);
        nouvellePioche.ajouter(quartier15);
        nouvellePioche.ajouter(quartier16);
        nouvellePioche.ajouter(quartier17);
        nouvellePioche.ajouter(quartier18);
        nouvellePioche.ajouter(quartier19);
        nouvellePioche.ajouter(quartier20);
        nouvellePioche.ajouter(quartier21);
        nouvellePioche.ajouter(quartier22);
        nouvellePioche.ajouter(quartier23);
        nouvellePioche.ajouter(quartier24);
        nouvellePioche.ajouter(quartier25);
        nouvellePioche.ajouter(quartier26);
        nouvellePioche.ajouter(quartier27);
        nouvellePioche.ajouter(quartier28);
        nouvellePioche.ajouter(quartier29);
        nouvellePioche.ajouter(quartier30);
        nouvellePioche.ajouter(quartier31);
        nouvellePioche.ajouter(quartier32);
        nouvellePioche.ajouter(quartier33);
        nouvellePioche.ajouter(quartier34);
        nouvellePioche.ajouter(quartier35);
        nouvellePioche.ajouter(quartier36);
        nouvellePioche.ajouter(quartier37);
        nouvellePioche.ajouter(quartier38);
        nouvellePioche.ajouter(quartier39);
        nouvellePioche.ajouter(quartier40);
        nouvellePioche.ajouter(quartier41);
        nouvellePioche.ajouter(quartier42);
        nouvellePioche.ajouter(quartier43);
        nouvellePioche.ajouter(quartier44);
        nouvellePioche.ajouter(quartier45);
        nouvellePioche.ajouter(quartier46);
        nouvellePioche.ajouter(quartier47);
        nouvellePioche.ajouter(quartier48);
        nouvellePioche.ajouter(quartier49);
        nouvellePioche.ajouter(quartier50);
        nouvellePioche.ajouter(quartier51);
        nouvellePioche.ajouter(quartier52);
        nouvellePioche.ajouter(quartier53);
        nouvellePioche.ajouter(quartier54);

        return nouvellePioche;
    }

    public static PlateauDeJeu configurationDeBase() {
        // Création de la pioche
        Pioche pioche = nouvellePioche();

        // Création des merveilles

        Quartier merveille1 = new Quartier("Bibliothèque", Quartier.TYPE_QUARTIERS[4], 6);
        Quartier merveille2 = new Quartier("Carrière", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille3 = new Quartier("Cour des Miracles", Quartier.TYPE_QUARTIERS[4], 2);
        Quartier merveille4 = new Quartier("Donjon", Quartier.TYPE_QUARTIERS[4], 3);
        Quartier merveille5 = new Quartier("Dracoport", Quartier.TYPE_QUARTIERS[4], 6);
        Quartier merveille6 = new Quartier("École de Magie", Quartier.TYPE_QUARTIERS[4], 6);
        Quartier merveille7 = new Quartier("Fontaine au Souhaits", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille8 = new Quartier("Forge", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille9 = new Quartier("Laboratoire", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille10 = new Quartier("Manufacture", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille11 = new Quartier("Salle des Cartes", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille12 = new Quartier("Statue Équestre", Quartier.TYPE_QUARTIERS[4], 3);
        Quartier merveille13 = new Quartier("Trésor Impérial", Quartier.TYPE_QUARTIERS[4], 5);
        Quartier merveille14 = new Quartier("Tripot", Quartier.TYPE_QUARTIERS[4], 6);


        // Ajout des merveilles à la pioche
        pioche.ajouter(merveille1);
        pioche.ajouter(merveille2);
        pioche.ajouter(merveille3);
        pioche.ajouter(merveille4);
        pioche.ajouter(merveille5);
        pioche.ajouter(merveille6);
        pioche.ajouter(merveille7);
        pioche.ajouter(merveille8);
        pioche.ajouter(merveille9);
        pioche.ajouter(merveille10);
        pioche.ajouter(merveille11);
        pioche.ajouter(merveille12);
        pioche.ajouter(merveille13);
        pioche.ajouter(merveille14);

        pioche.melanger();

        // Création des personnages
        Personnage assassin = new Assassin();
        Personnage voleur = new Voleur();
        Personnage magicienne = new Magicienne();
        Personnage roi = new Roi();
        Personnage eveque = new Eveque();
        Personnage marchande = new Marchande();
        Personnage architecte = new Architecte();
        Personnage condottiere = new Condottiere();

        // Création du plateau de jeu
        PlateauDeJeu plateau = new PlateauDeJeu(pioche);

        // Ajout des personnages au plateau
        plateau.ajouterPersonnage(assassin);
        plateau.ajouterPersonnage(voleur);
        plateau.ajouterPersonnage(magicienne);
        plateau.ajouterPersonnage(roi);
        plateau.ajouterPersonnage(eveque);
        plateau.ajouterPersonnage(marchande);
        plateau.ajouterPersonnage(architecte);
        plateau.ajouterPersonnage(condottiere);

        return plateau;
    }
}
