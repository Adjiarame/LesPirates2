package cartes;

import personnages.Joueur;

public abstract class Cartes {
    protected String nom;
    protected TypeCarte type;

    public Cartes(String nom, TypeCarte type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public TypeCarte getType() {
        return type;
    }
    public abstract String getEffet();

    @Override
    public String toString() {
        return nom + " (" + type + ")";
    }

    public abstract void appliquerEffet(Joueur joueurActuel, Joueur adversaire);
    public boolean estSpeciale() {
        return false;
    }

	public void appliquerEffet1(Joueur joueurActuel, Joueur adversaire) {
		// TODO Auto-generated method stub
		
	}
}
