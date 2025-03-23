package personnages;

import cartes.Cartes;

public class Joueur {
    private String nom;
    private int pointsVie;
    private int popularite;
    private Cartes[] main;
    private int nbCartesMain;
    private int derniersDegatsSubis;

    public Joueur(String nom) {
        this.nom = nom;
        this.pointsVie = 5;
        this.popularite = 0;
        this.main = new Cartes[6];
        this.nbCartesMain = 0;
        this.derniersDegatsSubis = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPointsVie() {
        return pointsVie;
    }

    public int getPopularite() {
        return popularite;
    }

    public Cartes[] getMain() {
        return main;
    }

    public void setMain(Cartes[] nouvelleMain) {
        this.main = nouvelleMain;
    }

    public void ajouterCarte(Cartes carte) {
        if (nbCartesMain < main.length) {
            main[nbCartesMain++] = carte;
        } else {
            System.out.println(nom + " ne peut pas avoir plus de 6 cartes en main !");
        }
    }

    public Cartes jouerCarte(int index) {
        if (index >= 0 && index < nbCartesMain) {
            Cartes carteJouee = main[index];
            for (int i = index; i < nbCartesMain - 1; i++) {
                main[i] = main[i + 1];
            }
            main[nbCartesMain - 1] = null;
            nbCartesMain--;
            return carteJouee;
        }
        return null;
    }

    public void reduireVie(int valeur) {
        derniersDegatsSubis = valeur;
        pointsVie -= valeur;
        if (pointsVie < 0) {
            pointsVie = 0;
        }
    }

    public void annulerDerniersDegats() {
        pointsVie += derniersDegatsSubis;
        derniersDegatsSubis = 0;
    }

    public boolean aSubiDesDegats() {
        return derniersDegatsSubis > 0;
    }
    public void resetParadeEclair() {
        derniersDegatsSubis = 0;  
    }


    public void ajouterPopularite(int valeur) {
        popularite += valeur;
    }

    public boolean estElimine() {
        return pointsVie == 0;
    }

    public boolean estCapitaine() {
        return popularite >= 5;
    }

    @Override
    public String toString() {
        return nom + " - Vie: " + pointsVie + ", Popularité: " + popularite;
    }
    
    public int getNombreDeCartes() {
        int count = 0;
        for (Cartes carte : main) {
            if (carte != null) count++;
        }
        return count;
    }
}