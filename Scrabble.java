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
            //int[] sacdebase = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
                            //{ 1, 3, 3, 2, 1, 4, 2, 4, 1, 10, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10 }
            int[] sacdebase = { 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 2, 0, 1, 2, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0 };
            this.sac=new MEE(sacdebase);
        this.numJoueur=Ut.randomMinMax(0, tabNomJ.length-1); 
        this.partie(sac, plateau, nbPointsJeton);
        
    }

    @Override
    public String toString() {
        String affiche=plateau.toString()+"\n"+"c'est au tour du joueur "+ joueurs[numJoueur].getNom();
        //this.partie(sac, plateau, nbPointsJeton);
       
        return affiche;
    }

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
            if (this.joueurs[numJoueur].getChevalet().getNbTotEx()<7){
                this.joueurs[numJoueur].prendJetons(sac, 7-this.joueurs[numJoueur].getChevalet().getNbTotEx());
            }
            //pour terminer une partie 1
            if (repJ==-1){
                x++;
            }else{
                x=0;
            }
            terminer1= x==joueurs.length ;
            
            if (terminer1 == true) {
                
                for (int i = 0 ; i < this.joueurs.length ; i++) {
                    this.joueurs[numJoueur].ajouteScore(this.joueurs[numJoueur].nbPointsChevalet( this.joueurs[numJoueur].getChevalet().getTabFreq())*-1);
                }
            }
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
            String win="and the winner issss ";
            int numV=numJoueur;
            if (terminer1){
            int max=joueurs[0].getScore();
            String nomV=joueurs[0].getNom();
            for (int i=1;i<this.joueurs.length;i++){
                if (max<joueurs[i].getScore()){
                    max=joueurs[i].getScore();
                    nomV=joueurs[i].getNom();
                    numV=i;
                }
            }

            win+="Avec le score de "+max+" est "+nomV; 
            }else if (terminer2){
                if (this.numJoueur==0){
                    this.numJoueur=joueurs.length-1;
                }else {
                    this.numJoueur--;
                }
                    for (int i=0;i<joueurs.length;i++){
                        if (i != numJoueur){
                            this.joueurs[numJoueur].setScore(this.joueurs[numJoueur].getScore() + joueurs[i].getChevalet().sommeValeurs(joueurs[i].getChevalet().getTabFreq()));
                        }
                    }
                win+="Avec le score de "+joueurs[numJoueur].getScore()+" est "+joueurs[numJoueur].getNom();
                numV=numJoueur;
            }  
            int max=joueurs[0].getScore();
            for (int i=1;i<this.joueurs.length;i++){
                if (max<joueurs[i].getScore()){
                    max=joueurs[i].getScore();
                }
            }
            for (int i=0;i<this.joueurs.length;i++){
                System.out.println(numV);
                System.out.println(joueurs[numV].getScore());
                System.out.println(joueurs[i].getScore());

                if (i!=numV && joueurs[i].getScore()==joueurs[numV].getScore()){
                    win+=", "+joueurs[i].getNom();
                }
            }

            System.out.println(win);  
    }
    
}