public class Scrabble {
    private static int[] nbPointsJeton = {1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    public static void main(String[] args){

        int[] tab = {1,5,7,9,4};
        MEE meeP1 = new MEE(tab);
        meeP1.afficheMEE();
        meeP1.sommeValeurs(nbPointsJeton);
    }
}