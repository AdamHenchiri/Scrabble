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
            res+="\n"+"°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°"+"\n";
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
                i++;
                if (this.g[numLig][i].estRecouvert()){
                    test=true;
                }
            }
        }
        else{
            int i=numLig;
            while(i<mot.length()+numLig && !test){
                i++;
                if (this.g[i][numCol].estRecouvert()){
                    test=true;
                }
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
        for (int i=0;i<mot.length();i++){
            test=e.
        }
        return test;
    }
    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
        boolean pasvide=this.g[7][7].estRecouvert();;
        boolean test=false;
        //si le plateau est vide
        if (pasvide==false){
                if (mot.length()<2){
                    test=false;
                }
                else if (sens=='h' && numLig==7 && numCol<=7 && numCol>=1 && numCol+mot.length()>=7 ){
                    test=true;
                }
                else if (sens=='v' && numCol==7 && numLig<=7 && numLig>=1 && numLig+mot.length()>=7){
                    test=true;
                }
        }
        //sinon si le plateau n'est pas vide 
        else {
            if (sens=='h'){
                if (contientUneCaseR (mot,numLig,numCol,sens)==false ){
                    test=false;
                }
                else if (contientUneCaseV (mot,numLig,numCol,sens)==false ){
                    test=false;
                }
                else if (numCol+mot.length()>14){
                    test=false;
                }
                else if (numCol!=0  && this.g[numLig][numCol+1].estRecouvert()){
                    test=false;
                }
                else if (numCol+mot.length()!=14  && this.g[numLig][numCol-1].estRecouvert()){
                    test=false;
                }
            }
            //sinon si le sens du mot est vertical
            else {
                if (contientUneCaseR (mot,numLig,numCol,sens)==false ){
                    test=false;
                }
                else if (contientUneCaseV (mot,numLig,numCol,sens)==false ){
                    test=false;
                }
                else if (numLig!=0  && this.g[numLig+1][numCol].estRecouvert()){
                    test=false;
                }
                else if (numLig+mot.length()!=14  && this.g[numLig+1][numCol].estRecouvert()){
                    test=false;
                }
                else if (numLig+mot.length()>14){
                    test=false;
                }
            }


        }
        return test;
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
        res += nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] * g[numLig+i][numCol+i].getCouleur() ;
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
                System.out.println(e.retire(Ut.majToIndex(mot.charAt(i-numCol)))); 
            }
        }else{
            for (int i=numLig;i<mot.length()+numLig;i++){
                g[i][numCol].setLetter(mot.charAt(i-numLig));
                System.out.println(e.retire(Ut.majToIndex(mot.charAt(i-numLig)))); 
            }
        }
        return mot.length();
    }
}
