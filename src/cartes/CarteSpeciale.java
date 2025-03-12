package cartes;

import personnages.Joueur;

public class CarteSpeciale extends Cartes {
    private String effet;

    public CarteSpeciale(String nom, String effet) {
        super(nom, TypeCarte.SPECIALE);
        this.effet = effet;
    }
    @Override
    public String getEffet() {
        if (nom.equals("Parade Éclair")) {
            return "(Annule une attaque)";
        } else if (nom.equals("Tempête en Mer")) {
            return "(Mélange les mains des joueurs)";
        }
        return "(Effet spécial : " + effet + ")";
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

	
}


