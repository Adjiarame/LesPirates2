package personnages;

import cartes.Cartes;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String nom;
    private int vie;
    private int popularite;
    private List<Cartes> main;

    public Joueur(String nom) {
        this.nom = nom;
        this.vie = 5;
        this.popularite = 0;
        this.main = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public int getPopularite() {
        return popularite;
    }

    public void ajouterCarte(Cartes cartes) {
        main.add(cartes);
    }

    public void montrerCartes() {
        for (int i = 0; i < main.size(); i++) {
            System.out.println((i + 1) + ". " + main.get(i).getNomCartes());
        }
    }

    public Cartes choisirCarte(int index) {
        return main.remove(index);
    }

    public void ajouterPopularite(int points) {
        this.popularite += points;
    }

    public void retirerVie(int points) {
        this.vie -= points;
    }
}
