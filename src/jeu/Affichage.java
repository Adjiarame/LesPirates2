package jeu;

import personnages.Joueur;
import cartes.Cartes;


import java.util.Scanner;

public class Affichage {
    private Scanner scanner;

    public Affichage() {
        this.scanner = new Scanner(System.in);
    }

    public void introductionJeu() {
        System.out.println("\n Bienvenue à bord du Sanguinaire ! ");
        System.out.println("Deux pirates, Jack le Borgne et Bill Jambe-de-Bois, s'affrontent pour devenir capitaine !");
        System.out.println("Pour gagner, il faut soit atteindre 5 points de popularité, soit réduire son adversaire à 0 PV !");
        System.out.println(" Préparez-vous à une bataille sans merci !\n");
    }

    public void afficherDebutTour(String nomJoueur) {
        System.out.println("\n C'est au tour de " + nomJoueur + " de jouer !");
    }

    public void afficherPiocherCarte(String nomJoueur, Cartes carte) {
        System.out.println(" " + nomJoueur + " pioche : " + carte.getNom());
    }

    public void afficherCartes(Cartes[] main) {
        System.out.println("\n Cartes en main :");
        for (int i = 0; i < main.length; i++) {
            if (main[i] != null) {
                System.out.println((i + 1) + ". " + main[i].getNom());
            }
        }
    }

    public int choisirCarte(int nbCartes) {
        System.out.print(" Entrez le numéro de la carte que vous voulez jouer : ");
        int numCarte = scanner.nextInt();
        while (numCarte < 1 || numCarte > nbCartes) {
            System.out.print(" Choix invalide ! Réessayez : ");
            numCarte = scanner.nextInt();
        }
        return numCarte - 1;
    }

    public void afficherAction(String nomJoueur, String action) {
        System.out.println(" " + nomJoueur + " joue : " + action);
    }

    public void afficherEtatJoueur(Joueur joueur) {
        System.out.println("\n État du joueur " + joueur.getNom() + " : " + joueur.getPointsVie() + " PV et " + joueur.getPopularite() + " PP.");
    }

    public void afficherVictoire(String nomJoueur) {
        System.out.println("\n " + nomJoueur + " a gagné la partie et devient le capitaine du Sanguinaire ! ");
    }

    public void afficherFinTour() {
        System.out.println("\n Fin du tour. Passage au joueur suivant...\n");
    }

    public void afficherPaquetVide() {
        System.out.println(" Le paquet est vide ! Plus de cartes à piocher.");
    }
}

