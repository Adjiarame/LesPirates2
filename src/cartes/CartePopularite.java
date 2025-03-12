package cartes;

import personnages.Joueur;

public class CartePopularite extends Cartes {
    private int pointsPopularite;

    public CartePopularite(String nom, int pointsPopularite) {
        super(nom, TypeCarte.POPULARITE);
        this.pointsPopularite = pointsPopularite;
    }

    public int getPointsPopularite() {
        return pointsPopularite;
    }

    @Override
    public String getEffet() {
        return "(+" + pointsPopularite + " PP)";
    }

    @Override
    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        joueurActuel.ajouterPopularite(pointsPopularite);
    }
}

