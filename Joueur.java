public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;


    public Joueur(String unNom) {
        
        this.nom = unNom;
        this.chevalet = new MEE(7);
        this.score = 0;

    }

    public MEE getChevalet() {

        return this.chevalet;
    }
    
    public int getScore() {

        return this.score;
    }

    @Override
    public String toString() {

        return "{" +
        " nom='" + getNom() + "'" +
        ", chevalet='" + getChevalet() + "'" +
        ", score='" + getScore() + "'" +
        "}";
    }

    public void ajouteScore(int nb) {

        this.score = this.score + nb;
    }

    /* pré-requis : nbPointsJet indique le nombre de points rapportés par chaque jeton/lettre */

    public int nbPointsChevalet(int[] nbPointsJet) {

        int nbPointsChevalet=0;

        if (!(this.chevalet.estVide())) {

            nbPointsChevalet = this.chevalet.sommeValeurs(nbPointsJet);

        } 
        return nbPointsChevalet ;
    }

    /* pré-requis : les éléments de s sont inférieurs à 26 */ 

    public void prendJetons(MEE s, int nbJetons) {
        int nbExampTransfer=0;
        while (nbJetons!=0){
        s.transfereAleat(this.chevalet,nbJetons);
        nbExampTransfer=s.transfereAleat(this.chevalet,nbJetons);
        nbJetons=nbJetons-nbExampTransfer;
        }
    }

}