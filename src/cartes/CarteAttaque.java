package cartes;

import personnages.Joueur;

public class CarteAttaque extends Cartes {
    private int degats;

    public CarteAttaque(String nom, int degats) {
        super(nom, TypeCarte.ATTAQUE);
        this.degats = degats;
    }
    public int getDegats() {
        return degats;
    }


    @Override
    public String getEffet() {
        return "(-" + degats + " PV)";
    }

    @Override
    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        adversaire.reduireVie(degats);
    }
}
    

