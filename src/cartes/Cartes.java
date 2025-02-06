package cartes;
import personnages.Joueur;

public abstract class Cartes {
    protected String nom;
    protected String description;
    protected int numCarte;

    public Cartes(String nom, String description, int numCarte) {
        this.nom = nom;
        this.description = description;
        this.numCarte = numCarte;
    }

    public String getNomCartes() {
        return nom;
    }

    public int getNumeroCartes() {
        return numCarte;
    }

    public abstract void effet(Joueur joueur);
}
