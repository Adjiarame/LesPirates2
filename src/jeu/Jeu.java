package jeu;

import personnages.Joueur;
import cartes.*;

import java.util.Random;

public class Jeu {
    private Affichage affichage;
    private Joueur joueur1;
    private Joueur joueur2;
    private Cartes[] paquet;
    private int indexPaquet;
    private Random random;
    private boolean paradeEclairJouee = false;

    public Jeu() {
        this.affichage = new Affichage();
        this.random = new Random();
        this.joueur1 = new Joueur("Jack le Borgne");
        this.joueur2 = new Joueur("Bill Jambe-de-Bois");
        this.paquet = creerPaquet();
        this.indexPaquet = 0;
        distribuerCartes();
    }

    private Cartes[] creerPaquet() {
        Cartes[] paquet = new Cartes[30];

        for (int i = 0; i < 4; i++) {
            paquet[i] = new CartePopularite("Capitaine de Légende", 3);
            paquet[i + 4] = new CartePopularite("Protecteur des Mers", 2);
            paquet[i + 8] = new CartePopularite("Duel Gagné", 1);
            paquet[i + 12] = new CarteAttaque("Coup Bas", 3);
            paquet[i + 16] = new CarteAttaque("Sabordage", 2);
            paquet[i + 20] = new CarteAttaque("Balles Perdues", 1);
        }

        paquet[24] = new CarteSpeciale("Parade Éclair", "Annule une attaque");
        paquet[25] = new CarteSpeciale("Parade Éclair", "Annule une attaque");
        paquet[26] = new CarteSpeciale("Tempête en Mer", "Mélange les mains");
        paquet[27] = new CarteSpeciale("Tempête en Mer", "Mélange les mains");
        paquet[28] = new CartePopularite("Héros des Océans", 1);
        paquet[29] = new CarteAttaque("Tir en Traître", 2);

        for (int i = paquet.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Cartes temp = paquet[i];
            paquet[i] = paquet[j];
            paquet[j] = temp;
        }

        return paquet;
    }

    private void distribuerCartes() {
        for (int i = 0; i < 4; i++) {
            joueur1.ajouterCarte(paquet[indexPaquet++]);
            joueur2.ajouterCarte(paquet[indexPaquet++]);
        }
    }

    public Cartes piocherCarte() {
        if (indexPaquet < paquet.length) {
            return paquet[indexPaquet++];
        } else {
            affichage.afficherPaquetVide();
            return null;
        }
    }

    public void jouerPartie() {
        affichage.introductionJeu();
        boolean victoire = false;
        Joueur joueurActuel = joueur1;
        Joueur adversaire = joueur2;

        while (!victoire) {
            affichage.afficherDebutTour(joueurActuel.getNom());

            // pioche une carte
            Cartes nouvelleCarte = piocherCarte();
            if (nouvelleCarte != null) {
                joueurActuel.ajouterCarte(nouvelleCarte);
                affichage.afficherPiocherCartes(joueurActuel.getNom(), nouvelleCarte);
            }

            // afficher cartes disponibles et choisir une carte
            affichage.afficherCartes(joueurActuel.getMain());
            int choix = affichage.choisirCarte(joueurActuel.getMain().length);
            Cartes carteJouee = joueurActuel.jouerCarte(choix);

            // vérifie si la carte est spéciale avant d'appliquer son effet
            if (carteJouee.estSpeciale()) {
                affichage.afficherAction(joueurActuel.getNom(), carteJouee.getNom());
                carteJouee.appliquerEffet(joueurActuel, adversaire);

                if (carteJouee.getNom().equals("Parade Éclair")) {
                    joueurActuel.annulerDerniersDegats();
                    paradeEclairJouee = false; // pour désactive immédiatement après son utilisation 
                } else if (carteJouee.getNom().equals("Tempête en Mer")) {
                    melangerMains(joueurActuel, adversaire);
                }
            } else {
                if (paradeEclairJouee) {
                    paradeEclairJouee = false;
                } else {
                    carteJouee.appliquerEffet(joueurActuel, adversaire);
                }
            }

            // vérifie la victoire
            if (joueurActuel.estCapitaine() || adversaire.estElimine()) {
                affichage.afficherVictoire(joueurActuel.getNom());
                victoire = true;
            }

            affichage.afficherEtatJoueur(joueur1);
            affichage.afficherEtatJoueur(joueur2);

            // Changé de tour
            Joueur temp = joueurActuel;
            joueurActuel = adversaire;
            adversaire = temp;

            affichage.afficherFinTour();
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

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouerPartie();
    }
}