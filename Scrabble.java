public class Scrabble {
    private Joueur[] joueurs;
    private int numJoueur; // joueur courant (entre 0 et joueurs.length-1)
    private Plateau plateau;
    private MEE sac;
    ////////////////////////////////////// A. B. C. D. E. F. G. H. I.  J.  K. L. M. N. O. P. Q. R. S. T. U. V.  W.  X.  Y.  Z
     public static int[] nbPointsJeton = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 10, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10 };

    public Scrabble(String[] tabNomJ ) {
        this.joueurs=new Joueur[tabNomJ.length];
            for (int i=0;i<tabNomJ.length;i++){
            this.joueurs[i]= new Joueur (tabNomJ[i]);
            }
        this.numJoueur=Ut.randomMinMax(0, tabNomJ.length-1);
        this.plateau = new Plateau();
        int[] sacdebase = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
        this.sac=new MEE(sacdebase);
    }

    @Override
    public String toString() {
        String affiche=plateau.toString()+"\n"+"c'est au tour du joueur "+ joueurs[numJoueur].getNom();
        this.partie(sac, plateau, nbPointsJeton);
        return affiche;
    }

    public void partie (MEE sac,Plateau plateau,int[] nbPointsJet){
        for (int i=0;i<joueurs.length;i++){
        this.joueurs[i].prendJetons(sac, 7);
        }
        System.out.print("voici votre chevalet ");
        for (int i=0;i<joueurs[numJoueur].getChevalet().getTabFreq().length;i++){
            if (this.joueurs[numJoueur].getChevalet().getEltTabFreq(i)!=0){
            System.out.print(Ut.indexToMaj(i));
            }
        }
        System.out.println("\n");
        this.joueurs[numJoueur].joue(plateau, sac, nbPointsJet);

    }
    public String winner(){
        String win="and the winner issss ";
        if (this.joueurs[numJoueur].getChevalet().getNbTotEx()==0 && this.sac.getNbTotEx()==0){
            win+=this.joueurs[numJoueur].getNom();
            for (int i=0;i<joueurs.length;i++){
                if (i!=numJoueur){
                    this.joueurs[numJoueur].setScore(this.joueurs[numJoueur].getScore() + joueurs[i].getChevalet().sommeValeurs(joueurs[i].getChevalet().getTabFreq()));
                }
            }
        }
        return win ;
    }


    /*public static void main(String[] args) {
        ///////////// A. B. C. D. E. F. G. H. I. J. K. L. M. N. O. P. Q. R. S. T. U. V. W. X. Y. Z
        int[] tab = { 7, 6, 4, 4, 16, 8, 3, 1, 3, 4, 1, 2, 2, 1, 4, 3, 8, 4, 1, 3, 2, 5, 0, 0, 1, 1 };
        MEE meeP1 = new MEE(tab);
        meeP1.sommeValeurs(nbPointsJeton); 
        Plateau p = new Plateau();
        if (p.placementValide("ALIBIBA", 7, 5, 'h', meeP1)) {
            p.place("ALIBIBA", 7, 5, 'h', meeP1);
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("ALIBABA", 7, 5, 'h', nbPointsJeton));
        }
        if (p.placementValide("IFGJ", 7, 7, 'v', meeP1)) {
            System.out
                    .println("votre mot est valide ! points=" + p.nbPointsPlacement("IFGJ", 7, 7, 'v', nbPointsJeton));
            p.place("IFGJ", 7, 7, 'v', meeP1);
        }
        if (p.placementValide("AFFJ", 7, 11, 'v', meeP1)) {
            System.out
                    .println("votre mot est valide ! points=" + p.nbPointsPlacement("AFFJ", 7, 11, 'v', nbPointsJeton));
            p.place("AFFJ", 7, 11, 'v', meeP1);
        }
        if (p.placementValide("FLO", 9, 11, 'h', meeP1)) {
            System.out
                    .println("votre mot est valide ! points=" + p.nbPointsPlacement("FLO", 9, 11, 'h', nbPointsJeton));
            p.place("FLO", 9, 11, 'h', meeP1);
        }
        if (p.placementValide("FUCK", 8, 11, 'h', meeP1)) {
            System.out
                    .println("votre mot est valide ! points=" + p.nbPointsPlacement("FUCK", 8, 11, 'h', nbPointsJeton));
            p.place("FUCK", 8, 11, 'h', meeP1);
        }
        if (p.placementValide("AZERTYUF", 8, 0, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("AZERTYUF", 8, 0, 'h', nbPointsJeton));
            p.place("AZERTYUF", 8, 0, 'h', meeP1);
        }
        if (p.placementValide("IVBCDEFG", 7, 9, 'v', meeP1)) {
            System.out.println(
                    "IVBCDEFG est valide ! points=" + p.nbPointsPlacement("IVBCDEFG", 7, 9, 'v', nbPointsJeton));
            p.place("IVBCDEFG", 7, 9, 'v', meeP1);
        }
        if (p.placementValide("GOODIE", 14, 9, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("GOODIE", 14, 9, 'h', nbPointsJeton));
            p.place("GOODIE", 14, 9, 'h', meeP1);
        }
        if (p.placementValide("VBCDEFGB", 0, 10, 'v', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("VBCDEFGB", 0, 10, 'v', nbPointsJeton));
            p.place("VBCDEFGB", 0, 10, 'v', meeP1);
        }
        if (p.placementValide("VBCDE", 0, 10, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("VBCDE", 0, 10, 'h', nbPointsJeton));
            p.place("VBCDE", 0, 10, 'h', meeP1);
        }
        if (p.placementValide("AGABAFLO", 9, 6, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("AGABAFLO", 9, 6, 'h', nbPointsJeton));
            p.place("AGABAFLO", 9, 6, 'h', meeP1);
        }
        if (p.placementValide("EEEEEEE", 4, 4, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("EEEEEEE", 4, 4, 'h', nbPointsJeton));
            p.place("EEEEEEE", 4, 4, 'h', meeP1);
        }
        if (p.placementValide("EEGOODIE", 14, 7, 'h', meeP1)) {
            System.out.println(
                    "votre mot est valide ! points=" + p.nbPointsPlacement("EEGOODIE", 14, 7, 'h', nbPointsJeton));
            p.place("EEGOODIE", 14, 7, 'h', meeP1);
        }
        System.out.println(p.toString());

    }*/
}