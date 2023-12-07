package org.project.modele;

import java.util.ArrayList;

public class Pioche {
    private ArrayList<Quartier> listeQuartiers;

    public Pioche() {
        listeQuartiers = new ArrayList<Quartier>();
    }

    public Quartier piocher(){
        if(listeQuartiers.size() == 0){
            return null;
        }

        Quartier carte = listeQuartiers.get(0);
        listeQuartiers.remove(0);
        return carte;
    }

    public void ajouter(Quartier nouveau){
        listeQuartiers.add(nouveau);
    }

    public int nombreQuartiersDansPioche(){
        return listeQuartiers.size();
    }

    public void melanger(){
        ArrayList<Quartier> nouvelleListe = new ArrayList<>();
        while(listeQuartiers.size() > 0){
            int index = (int)(Math.random() * listeQuartiers.size());
            nouvelleListe.add(listeQuartiers.remove(index));
        }
        listeQuartiers = nouvelleListe;
    }
}
