package cartes;

import personnages.Joueur;

public class CarteSpeciale extends Cartes {
    private String effet;

    public CarteSpeciale(String nom, String effet) {
        super(nom, TypeCarte.SPECIALE);
        this.setEffet(effet);
    }

    @Override
    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        if (nom.equals("Parade Éclair")) {
            System.out.println(" " + joueurActuel.getNom() + " annule une attaque !");
        } else if (nom.equals("Tempête en Mer")) {
            System.out.println(" Tempête en mer ! Mélange des mains des joueurs !");
            Cartes[] temp = joueurActuel.getMain();
            joueurActuel.setMain(adversaire.getMain());
            adversaire.setMain(temp);
        }
    }

	public String getEffet() {
		return effet;
	}

	public void setEffet(String effet) {
		this.effet = effet;
	}
}


