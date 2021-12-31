public class Scrabble {
    private static int[] nbPointsJeton = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 10, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10,
            10, 10 };

    public static void main(String[] args) {

        int[] tab = {0,0,0,0,0,4,2,0,3,4,0,1,2,1,1,3,8,1,1,1,1,4,0,0,0,0};
        MEE meeP1 = new MEE(tab);
        meeP1.sommeValeurs(nbPointsJeton);
        Plateau p=new Plateau();
         p.place("INES",7,12,'v', meeP1);
        System.out.println(p.toString());
        
    }
}