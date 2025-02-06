package cartes;

import personnages.Joueur;

public class CarteAttaque extends Cartes {
    private int ptVie;

    public CarteAttaque(String nom, String description, int numCarte, int ptVie) {
        super(nom, description, numCarte);
        this.ptVie = ptVie;
    }

    @Override
    public void effet(Joueur joueur) {
        joueur.retirerVie(ptVie);
    }
}
