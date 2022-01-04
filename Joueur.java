public class Joueur{
    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom){
        this.nom=unNom;
    }

    public Joueur(String nom, MEE chevalet, int score) {
        this.nom = nom;
        this.chevalet = chevalet;
        this.score = score;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public MEE getChevalet() {
        return this.chevalet;
    }

    public void setChevalet(MEE chevalet) {
        this.chevalet = chevalet;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", chevalet='" + getChevalet() + "'" +
            ", score='" + getScore() + "'" +
            "}";
    }

}