package cartes;

import personnages.Joueur;

public class CarteAttaque extends Cartes {
    private int degats;

    public CarteAttaque(String nom, int degats) {
        super(nom, TypeCarte.ATTAQUE);
        this.degats = degats;
    }

    @Override
    public void appliquerEffet1(Joueur joueurActuel, Joueur adversaire) {
        adversaire.reduireVie(degats);
    }

	@Override
	public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {

		
	}
}