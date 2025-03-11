package cartes;

import personnages.Joueur;

public class CartePopularite extends Cartes {
    private int pointsPopularite;

    public CartePopularite(String nom, int pointsPopularite) {
        super(nom, TypeCarte.POPULARITE);
        this.pointsPopularite = pointsPopularite;
    }

    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        joueurActuel.ajouterPopularite(pointsPopularite);
    }
}


