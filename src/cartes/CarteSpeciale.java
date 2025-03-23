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
            return "(Annule une attaque précédente)";
        } else if (nom.equals("Tempête en Mer")) {
            return "(Mélange les mains des joueurs)";
        }
        return "(Effet spécial : " + effet + ")";
    }

    @Override
    public boolean estSpeciale() {
        return true;
    }

    @Override
    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        if (nom.equals("Parade Éclair")) {
            if (joueurActuel.aSubiDesDegats()) {  
                System.out.println(joueurActuel.getNom() + " utilise Parade Éclair et annule les dégâts subis !");
                joueurActuel.annulerDerniersDegats();
            } else {
                System.out.println("Aucune attaque récente à annuler !");
            }
        } else if (nom.equals("Tempête en Mer")) {
            System.out.println("Tempête en Mer ! Les joueurs échangent leurs cartes...");
            melangerMains(joueurActuel, adversaire);
        }
    }

    private void melangerMains(Joueur joueurActuel, Joueur adversaire) {
        Cartes[] main1 = joueurActuel.getMain();
        Cartes[] main2 = adversaire.getMain();

        // Fusionner les mains les 4 cartes des joeurs
        Cartes[] cartesMelangees = new Cartes[8];
        System.arraycopy(main1, 0, cartesMelangees, 0, 4);
        System.arraycopy(main2, 0, cartesMelangees, 4, 4);

        // Mélanger les cartes
        for (int i = cartesMelangees.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Cartes temp = cartesMelangees[i];
            cartesMelangees[i] = cartesMelangees[j];
            cartesMelangees[j] = temp;
        }

        // Redistribuer 4 cartes aux joueurs
        Cartes[] nouvelleMain1 = new Cartes[5];  // On ajuste ici pour avoir 5 cartes
        Cartes[] nouvelleMain2 = new Cartes[5];
        System.arraycopy(cartesMelangees, 0, nouvelleMain1, 0, 4);
        System.arraycopy(cartesMelangees, 4, nouvelleMain2, 0, 4);

        joueurActuel.setMain(nouvelleMain1);
        adversaire.setMain(nouvelleMain2);

        System.out.println("Les cartes ont été redistribuées après la Tempête en Mer!");

        // Assure 5 cartes après le mélange
        if (joueurActuel.getNombreDeCartes() < 5) {
            Cartes cartePiochée1 = piocherCarte();
            if (cartePiochée1 != null) {
                joueurActuel.ajouterCarte(cartePiochée1);
                System.out.println(joueurActuel.getNom() + " pioche une carte supplémentaire !");
            }
        }
        if (adversaire.getNombreDeCartes() < 5) {
            Cartes cartePiochée2 = piocherCarte();
            if (cartePiochée2 != null) {
                adversaire.ajouterCarte(cartePiochée2);
                System.out.println(adversaire.getNom() + " pioche une carte supplémentaire !");
            }
        }
    }

	private Cartes piocherCarte() {
		// TODO Auto-generated method stub
		return null;
	}
}
