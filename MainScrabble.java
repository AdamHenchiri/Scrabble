public class  MainScrabble{
    public static void main(String[] args) {
        int nbjoueur=0;
        do{
        System.out.println("veuillez saisir le nombre de joueurs...");
        nbjoueur=Ut.saisirEntier();
        } while (!(nbjoueur>=1 && nbjoueur<15));
        String [] tabNomJ = new String [nbjoueur];
        for (int i=0;i<tabNomJ.length;i++){
            System.out.println("veillez saisir le nom du joueur num "+(i+1));
            tabNomJ[i]=Ut.saisirChaine();
        }
       new Scrabble (tabNomJ);
    }
}