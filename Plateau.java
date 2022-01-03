import javax.management.ConstructorParameters;
public class Plateau {
    
    private Case [][] g=new Case [15][15];
    
    public Plateau() {
        int plateau [][] ={
                      {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5},
                      {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                      {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                      {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                      {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                      {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                      {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                      {5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5},
                      {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                      {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                      {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                      {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                      {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                      {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                      {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5}
                      };
                      int x=0;
                      for (int i=0;i<15;i++){
                        for (int j=0;j<15;j++){
                             x=plateau[i][j];
                             Case c =new Case (x);
                             this.g[i][j]=c;
                        }
                    }
    }
    
    public Plateau (Case[][] plateau) {
        this.g = plateau;
    }

    @Override
    public String toString() {
        String res="_______________________________"+"\n"+"|";
       for (int i=0;i<15;i++){
           for (int j=0;j<15;j++){
               if (this.g[i][j].estRecouvert()){
                   res+=this.g[i][j].getLetter()+"|";
                }
                else if (this.g[i][j].getCouleur()!=1){
                    res+=this.g[i][j].getCouleur()+"|";
                }
                else {
                    res+=" |";
                }
            }          
            res+="\n"+"_______________________________"+"\n";
            res+="|";
           
       }
       String res2 =res.substring(0,res.length()-1);
       return res2;
    }

    public boolean contientUneCaseR (String mot, int numLig, int numCol, char sens){
        Boolean test=false;
        if (sens=='h'){
            int i=numCol;
            while (i<mot.length()+numCol && !test){
                if (this.g[numLig][i].estRecouvert()){
                    test=true;
                }
                i++;
            }
        }
        else{
            int i=numLig;
            while(i<mot.length()+numLig && !test){
                if (this.g[i][numCol].estRecouvert()){
                    test=true;
                }
                i++;
            }
        }
        return test;
    }
    public boolean contientUneCaseV (String mot, int numLig, int numCol, char sens){
        Boolean test=false;
        if (sens=='h'){
            int i=numCol;
            while (i<mot.length()+numCol && !test){
                i++;
                if (!(this.g[numLig][i].estRecouvert())){
                    test=true;
                }
            }
        }
        else{
            int i=numLig;
            while(i<mot.length()+numLig && !test){
                i++;
                if (!(this.g[i][numCol].estRecouvert())){
                    test=true;
                }
            }
        }
        return test;
    }
    public boolean mêmelettre (String mot, int numLig, int numCol, char sens){
        Boolean test=false;
        if (sens=='h'){
            for (int i=numCol;i<mot.length()+numCol ;i++){
                if ((this.g[numLig][i].estRecouvert())&&(this.g[numLig][i].getLetter()==mot.charAt(i-numCol))){
                    test=true;
                }
            }
        }
        else{
            for(int i=numLig;i<mot.length()+numLig;i++){
                if ((this.g[i][numCol].estRecouvert())&&(this.g[i][numCol].getLetter()==mot.charAt(i-numLig))){
                    test=true;
                }
            }
        }
        return test;
    }
    public boolean lettreexiste (String mot, MEE e) {
        boolean test=false;
        int i=0;
        while (i<mot.length() && test==false){
            //System.out.println(Ut.majToIndex(mot.charAt(i)));
            test=!(e.getTabFreq(Ut.majToIndex(mot.charAt(i)))>0);
            i++;
        }
        return !test;
    }
    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
        boolean pasvide=this.g[7][7].estRecouvert();
        //System.out.println(pasvide);
        boolean test=false;
        boolean test1=false;
        boolean test2=false;
        //si le plateau est vide
        if (pasvide==false){
            //System.out.println("vide");
                if (sens=='h' && numLig==7 && numCol<=7 && numCol>=0 && numCol+mot.length()>7 && mot.length()>2){
                    test=true;
                }
                else if (sens=='v' && numCol==7 && numLig<=7 && numLig>=0 && numLig+mot.length()>7 && mot.length()>2){
                    test=true;
                }
        }
        //sinon si le plateau n'est pas vide 
        else {
           
            if (sens=='h'){
                //System.out.println("pas vide et h");
                if (contientUneCaseR (mot,numLig,numCol,sens) && contientUneCaseV (mot,numLig,numCol,sens) && numCol+mot.length()<15 && mêmelettre (mot, numLig,numCol,sens) && lettreexiste (mot,e)){
                    test1=true;
                //    System.out.println("1");
                }
                if  (numCol==0) {
                //    System.out.println("2.1.1");
                    if (this.g[numLig][numCol+mot.length()+1].estRecouvert()==false){
                    test2=true;
                //    System.out.println("2.1");
                    }
                }
                    else if (numCol+mot.length()==15 ){
                //        System.out.println("2.2.2");
                        if (this.g[numLig][numCol-1].estRecouvert()==false)
                        
                        test2=true;
                //        System.out.println("2.2");
                    }
                    else {
                //        System.out.println("2.3.3");
                        if( (this.g[numLig][numCol-1].estRecouvert()==false) && (this.g[numLig][numCol+mot.length()+1].estRecouvert()==false) ){
                        
                            test2=true;
                //        System.out.println("2.3");
                        }
                    }
            }
            //sinon si le sens du mot est vertical
            else {
                //System.out.println("pas vide et v");
                if (contientUneCaseR (mot,numLig,numCol,sens) && contientUneCaseV (mot,numLig,numCol,sens) && numLig+mot.length()<15 && mêmelettre (mot, numLig,numCol,sens) && lettreexiste (mot,e)){
                    test1=true;
                //    System.out.println("1");
                }
                if (numLig==0)
                //System.out.println("2.1.1");
                    if (this.g[numLig+1][numCol].estRecouvert()==false){
                        test2=true;
                //        System.out.println("2.1");
                    }
                    else if (numLig+mot.length()==15)
                //    System.out.println("2.2.2");
                        if (this.g[numLig+1][numCol].estRecouvert()==false){
                            test2=true;
                //            System.out.println("2.2");
                        }
                    else {
                //        System.out.println("2.3.3");
                        if( (this.g[numLig+mot.length()+1][numCol].estRecouvert()==false) && (this.g[numLig-1][numCol].estRecouvert()==false) ){
                            test2=true;
                //            System.out.println("2.3");
                            }
                    }
                
            }


        }
        return ( (test) || (test1 && test2) );
    }
    /**
     * 
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @param nbPointsJet
     * @return le nombre de points rapportés par un placement de mot
     */
    public int nbPointsPlacement(String mot, int numLig, int numCol,char sens, int[] nbPointsJet) {
        int res=0; 
        for (int i=0; i<mot.length() ; i++){
            if (sens=='h'){
            //System.out.println(nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] + " x "+ g[numLig][numCol+i].getCouleur());
            res += nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] * g[numLig][numCol+i].getCouleur() ;
            }
            else {
            //System.out.println(nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] + " x "+ g[numLig+i][numCol].getCouleur());
            res += nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] * g[numLig+i][numCol].getCouleur() ;
            }
        }
        return res;
    }
    /**
     * 
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     * @param e
     * @return le nombre de jetons retirés de e
     */
    public int place(String mot, int numLig, int numCol, char sens, MEE e){ 
        if (sens=='h'){
            for (int i=numCol;i<mot.length()+numCol;i++){
                g[numLig][i].setLetter(mot.charAt(i-numCol));
                //System.out.println(e.retire(Ut.majToIndex(mot.charAt(i-numCol)))); 
            }
        }else{
            for (int i=numLig;i<mot.length()+numLig;i++){
                g[i][numCol].setLetter(mot.charAt(i-numLig));
                //System.out.println(e.retire(Ut.majToIndex(mot.charAt(i-numLig)))); 
            }
        }
        return mot.length();
    }
}
