package cartes;

public enum TypeCarte {
    POPULARITE("Carte de Popularité"),
    ATTAQUE("Carte d'Attaque"),
    SPECIALE("Carte Spéciale");

    private String description;

    private TypeCarte(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
