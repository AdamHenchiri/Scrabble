public class Case {
    private int couleur;
    private char letter;

    public Case(int uneCouleur) {
        this.couleur = uneCouleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char let) {
        this.letter = let;
    }

    public Boolean estRecouvert() {
        return (this.letter != ' ');
    }

    @Override
    public String toString() {
        return "Case [couleur=" + couleur + ", letter=" + letter + "]";
    }

}