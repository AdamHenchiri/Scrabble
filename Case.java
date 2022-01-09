public class Case {

    private int couleur;
    private char letter;

    /**
     * 
     * action : constructeur de Case
     * 
     * @param uneCouleur
     */
    public Case(int uneCouleur) {
        this.couleur = uneCouleur;
    }

    /**
     * résultat : la couleur de this, un nombre entre 1 et 5
     * 
     * @return
     */
    public int getCouleur() {
        return couleur;
    }

    /**
     * return la lettre
     */
    public char getLetter() {
        return letter;
    }

    public void setLetter(char let) {
        this.letter = let;
    }

    public Boolean estRecouvert() {
        return (this.letter != '\0');
    }

    @Override
    public String toString() {
        return "Case [couleur=" + couleur + ", letter=" + letter + "]";
    }

}