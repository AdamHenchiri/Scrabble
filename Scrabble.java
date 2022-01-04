public class Scrabble {                  //A.B.C.D.E.F.G.H.I.J .K .L .M.N.O.P.Q.R.S.T.U.V.W.X .Y .Z
    private static int[] nbPointsJeton = { 1,3,3,2,1,4,2,4,1,10,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10 };

    public static void main(String[] args) {
                   //A.B.C.D.E.F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z
        int[] tab = {2,1,0,0,1,4,1,1,2,4,0,1,2,1,1,3,8,1,1,1,1,4,0,0,0,0};
        MEE meeP1 = new MEE(tab);
        meeP1.sommeValeurs(nbPointsJeton);
        Plateau p=new Plateau();
        if(p.placementValide("ALIBIBA",7,5,'h',meeP1)){
         p.place("ALIBIBA",7,5,'h',meeP1);
        }
        if(p.placementValide("IFGJ",7,7,'v',meeP1)){
        p.place("IFGJ",7,7,'v',meeP1);
        }
        if(p.placementValide("AFFJ",7,11,'v',meeP1)){
            p.place("AFFJ",7,11,'v',meeP1);
        }
        System.out.println(p.toString());
        System.out.println(p.nbPointsPlacement("ALIBABA",7,5,'h',nbPointsJeton));
        System.out.println(p.nbPointsPlacement("IFGJ",7,7,'v',nbPointsJeton));
        System.out.println(p.nbPointsPlacement("AFFJ",7,11,'v',nbPointsJeton));
        
    }
}