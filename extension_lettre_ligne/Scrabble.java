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
            this.plateau = new Plateau();
            int[] sacdebase = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
            this.sac=new MEE(sacdebase);
        this.numJoueur=Ut.randomMinMax(0, tabNomJ.length-1); 
        this.partie(sac, plateau, nbPointsJeton);
        
    }

    @Override
    public String toString() {
        String affiche=plateau.toString()+"\n"+"c'est au tour du joueur "+ joueurs[numJoueur].getNom();
        return affiche;
    }
    /**
     * orchestre une partie de Scrabble. 
        . la distribution initiale des jetons aux joueurs,
        . des itérations sur les différents tours de jeu jusqu’à la fin de la partie,
        . le calcul des scores finaux,
        . l’affichage du ou des gagnants.
     * @param sac
     * @param plateau
     * @param nbPointsJet
     */
    public void partie (MEE sac,Plateau plateau,int[] nbPointsJet){
        boolean terminer=false;
        boolean terminer1=false;
        boolean terminer2=false;
        int repJ=2;
        int x=0;
        for (int i=0;i<joueurs.length;i++){
            this.joueurs[i].prendJetons(sac, 7);
        }
        while (!(terminer)) {
            System.out.println( this.toString());
            //affichage du chevalet du joueur 
            System.out.print("voici votre chevalet [");
            for (int i=0;i<joueurs[numJoueur].getChevalet().getTabFreq().length;i++){
                if (this.joueurs[numJoueur].getChevalet().getEltTabFreq(i)==1){
                    System.out.print(Ut.indexToMaj(i)+",");
                }else if (this.joueurs[numJoueur].getChevalet().getEltTabFreq(i)>1){
                    int xx=0;
                    while (this.joueurs[numJoueur].getChevalet().getEltTabFreq(i)!=xx){
                        System.out.print(Ut.indexToMaj(i)+",");
                        xx++;
                    }
                }
                
            }
            System.out.println("]"+"\n");
            repJ=this.joueurs[numJoueur].joue(plateau, sac, nbPointsJet);
            //la reprise de jetons apres avoir jouer un mot 
            int nbrReprisejet=7-this.joueurs[numJoueur].getChevalet().getNbTotEx();
            //en cas de scrabble le joueurs prend un bonus de 50 points
            if (nbrReprisejet==7){
                this.joueurs[numJoueur].ajouteScore(50)  ;
            }
            if (this.joueurs[numJoueur].getChevalet().getNbTotEx()<7){
                this.joueurs[numJoueur].prendJetons(sac, nbrReprisejet);
            }
            System.out.println("bravo votre mot a bien été placé , nbr de point "+this.joueurs[numJoueur].getScore());
            //pour terminer une partie 1
            if (repJ==-1){
                x++;
            }else{
                x=0;
            }
            terminer1= x==joueurs.length ;
            //pour terminer une partie 2
            terminer2= joueurs[numJoueur].getChevalet().getNbTotEx()==0 ;
            //
            terminer=terminer1||terminer2;  
            //pour changer de joueur
                if (this.numJoueur==joueurs.length-1){
                    this.numJoueur=0;
                }
                else {
                    this.numJoueur++;
                }
            }
            System.out.println("partie terminee!!");
            //affichage du ou des gagnants
            String win="and the winner issss ";
            int numV=0;
            // cas de terminaison (rare)
            if (terminer1){
             for (int i = 0 ; i < this.joueurs.length ; i++) {
                this.joueurs[i].ajouteScore(this.joueurs[i].nbPointsChevalet(nbPointsJet) * -1);
             }
            int max=joueurs[0].getScore();
            String nomV=joueurs[0].getNom();
            numV=0;
            for (int i=1;i<this.joueurs.length;i++){
                if (max<joueurs[i].getScore()){
                    max=joueurs[i].getScore();
                    nomV=joueurs[i].getNom();
                    numV=i;
                }
            }

            win+="Avec le score de "+max+" est "+nomV; 
            }// cas de terminaison classic
            else if (terminer2){
                if (this.numJoueur==0){
                    this.numJoueur=joueurs.length-1;
                }else {
                    this.numJoueur--;
                }
                    for (int i=0;i<joueurs.length;i++){
                        if (i != numJoueur){
                            this.joueurs[numJoueur].ajouteScore(joueurs[i].getChevalet().sommeValeurs(joueurs[i].getChevalet().getTabFreq()));
                        }
                    }
                win+="Avec le score de "+joueurs[numJoueur].getScore()+" "+joueurs[numJoueur].getNom();
                numV=numJoueur;
            }  
            for (int i=0;i<this.joueurs.length;i++){
                System.out.println(joueurs[i].getScore());
                System.out.println(numV);
                System.out.println(joueurs[numV].getScore());

                if (i!=numV && joueurs[i].getScore()==joueurs[numV].getScore()){
                    win+=", "+joueurs[i].getNom();
                }
            }

            System.out.println(win);  
    }
    
}