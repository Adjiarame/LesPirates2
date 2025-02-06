package jeu;

import java.util.Scanner;

import personnages.Joueur;

public class Jeu {
    public static void main(String[] args) {
        Joueur joueur1 = new Joueur("Jack le Borgne");
        Joueur joueur2 = new Joueur("Bill Jambe-de-Bois");

        //List<cartes> pioche = new ArrayList<>();
       // pioche.add(new CartePopularite("Révolte Organisée", "Gagne 1 popularité", 1, 1));
      //  pioche.add(new CarteAttaque("Coup de Sabre", "Enlève 2 points de vie", 2, 2));
        // aprés ajouter d'autes cartes
        
        // faire les dialoqgue entre les joueur sytémeoutprintln et terminer de faires les effects de la carte polpularité et comprendre comment se déroule le jeu 
        // avec le diagramme de séquence , et enum et affichage dans jeu, enum egalement mettre les nom de cartes 

        Scanner scanner = new Scanner(System.in);

        while (joueur1.getVie() > 0 && joueur2.getVie() > 0 && joueur1.getPopularite() < 5 && joueur2.getPopularite() < 5) {
            // déroulement.
        }

        scanner.close();
    }
}
