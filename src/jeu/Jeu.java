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

    private Cartes piocherCarte() {
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

            // Piocher 
            Cartes nouvelleCarte = piocherCarte();
            if (nouvelleCarte != null) {
                joueurActuel.ajouterCarte(nouvelleCarte);
                affichage.afficherPiocherCartes(joueurActuel.getNom(), nouvelleCarte);
            }

            // Afficher cartes disponibles
            affichage.afficherCartes(joueurActuel.getMain());
            int choix = affichage.choisirCarte(joueurActuel.getMain().length);

            // Jouer  carte
            Cartes carteJouee = joueurActuel.jouerCarte(choix);
            carteJouee.appliquerEffet(joueurActuel, adversaire);
            affichage.afficherAction(joueurActuel.getNom(), carteJouee.getNom());

            // Vérifie victoire
            if (joueurActuel.estCapitaine()) {
                affichage.afficherVictoire(joueurActuel.getNom());
                victoire = true;
            } else if (adversaire.estElimine()) {
                affichage.afficherVictoire(joueurActuel.getNom());
                victoire = true;
            }

            affichage.afficherEtatJoueur(joueur1);
            affichage.afficherEtatJoueur(joueur2);

         
            Joueur temp = joueurActuel;
            joueurActuel = adversaire;
            adversaire = temp;

            affichage.afficherFinTour();
        }
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouerPartie();
    }
}
