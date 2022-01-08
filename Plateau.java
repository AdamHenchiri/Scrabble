public class Plateau {

    private Case[][] g = new Case[15][15];

    public Plateau() {
        int plateau[][] = {
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 }
        };
        int x = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                x = plateau[i][j];
                Case c = new Case(x);
                this.g[i][j] = c;
            }
        }
    }

    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    @Override
    public String toString() {
        String res = "  .1.2.3.4.5.6.7.8.7.6.5.4.3.2.1." + "\n" + "  _______________________________" + "\n";
        for (int i = 0; i < 15; i++) {
            res += Ut.indexToMaj(i) + " |";
            for (int j = 0; j < 15; j++) {
                if (this.g[i][j].estRecouvert()) {
                    res += this.g[i][j].getLetter() + "|";
                } else if (this.g[i][j].getCouleur() != 1) {
                    res += this.g[i][j].getCouleur() + "|";
                } else {
                    res += " |";
                }
            }
            res += "\n" + "  _______________________________" + "\n";

        }
        String res2 = res.substring(0, res.length() - 1);
        return res2;
    }

    public boolean contientUneCaseR(String mot, int numLig, int numCol, char sens) {
        Boolean test = false;
        if (sens == 'h') {
            int i = numCol;
            while (i < mot.length() + numCol && !test) {
                if (this.g[numLig][i].estRecouvert()) {
                    test = true;
                }
                i++;
            }
        } else {
            int i = numLig;
            while (i < mot.length() + numLig && !test) {
                if (this.g[i][numCol].estRecouvert()) {
                    test = true;
                }
                i++;
            }
        }
        if (test == false) {
            System.out.println("ERREUR::le placement de ce mot n'est pas rattacher a un autre..");
        }
        return test;
    }

    public boolean contientUneCaseV(String mot, int numLig, int numCol, char sens) {
        Boolean test = false;
        if (sens == 'h') {
            int i = numCol;
            while (i < mot.length() + numCol && !test) {
                i++;
                if (!(this.g[numLig][i].estRecouvert())) {
                    test = true;
                }
            }
        } else {
            int i = numLig;
            while (i < mot.length() + numLig && !test) {
                i++;
                if (!(this.g[i][numCol].estRecouvert())) {
                    test = true;
                }
            }
        }
        if (test == false) {
            System.out.println("ERREUR::ce mot est place dans un autre");
        }
        return test;
    }

    public boolean mêmelettre(String mot, int numLig, int numCol, char sens) {
        Boolean test = true;
        if (sens == 'h') {
            for (int i = numCol; i < mot.length() + numCol; i++) {
                if ((this.g[numLig][i].estRecouvert()) && (this.g[numLig][i].getLetter() != mot.charAt(i - numCol))) {
                    test = false;
                }
            }
        } else {
            for (int i = numLig; i < mot.length() + numLig; i++) {
                if ((this.g[i][numCol].estRecouvert()) && (this.g[i][numCol].getLetter() != mot.charAt(i - numLig))) {
                    test = false;
                }
            }
        }
        if (test == false) {
            System.out
                    .println("ERREUR::les lettres de ce mot qui sont deja place sur le plateau ne correspondent pas ");
        }
        return test;
    }

    // renvoie true ssi toutes les lettres a placer existe dans le chevalet du
    // joueur
    public boolean lettreexiste(String mot, MEE e, int numLig, int numCol, char sens) {
        boolean test = false;
        int i = 0;
        MEE e1 = new MEE(e);
        while (i < mot.length() && test == false) {
            if ((sens == 'h') && this.g[numLig][i + numCol].estRecouvert() == false) {
                // System.out.println(Ut.majToIndex(mot.charAt(i)));
                test = !(e1.getEltTabFreq(Ut.majToIndex(mot.charAt(i))) > 0);
                e1.retire(Ut.majToIndex(mot.charAt(i)));
            } else if ((sens == 'v') && (this.g[i + numLig][numCol].estRecouvert()) == false) {
                // System.out.println((mot.charAt(i)));
                test = !(e1.getEltTabFreq(Ut.majToIndex(mot.charAt(i))) > 0);
                e1.retire(Ut.majToIndex(mot.charAt(i)));
            }
            i++;
        }
        if (test == true) {
            System.out.println("ERREUR::les lettres de ce mot n'existe pas sur votre chevalet");
        }
        return !test;
    }

    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
        char repCapelo='\0';
        boolean pasvide = this.g[7][7].estRecouvert();
        //System.out.println(pasvide);
        boolean test = false;
        boolean test1 = false;
        boolean test2 = false;
        do {
        System.out.println("CapeloDico valide ce mot O/N ?");
        repCapelo=Ut.saisirCaractere();
        }while (!(repCapelo=='O'||repCapelo=='N'));
       if (repCapelo=='O'){
        // si le plateau est vide
        if (pasvide == false) {
            // System.out.println("vide");
            if (sens == 'h' && numLig == 7 && numCol <= 7 && numCol >= 0 && numCol + mot.length() > 7
                    && mot.length() > 2 && lettreexiste(mot, e, numLig, numCol, sens)) {
                test = true;
            } else if (sens == 'v' && numCol == 7 && numLig <= 7 && numLig >= 0 && numLig + mot.length() > 7
                    && mot.length() > 2 && lettreexiste(mot, e, numLig, numCol, sens)) {
                test = true;
            }
        }
        // sinon si le plateau n'est pas vide
        else {
            if (sens == 'h') {
                // System.out.println("pas vide et h");
                if (contientUneCaseR(mot, numLig, numCol, sens) && contientUneCaseV(mot, numLig, numCol, sens)
                        && numCol + mot.length() <= 15 && mêmelettre(mot, numLig, numCol, sens)
                        && lettreexiste(mot, e, numLig, numCol, sens)) {
                    test1 = true;
                    // System.out.println("1");
                }
                if (numCol == 0) {
                    // System.out.println("2.1.1");
                    if (this.g[numLig][numCol + mot.length()].estRecouvert() == false) {
                        test2 = true;
                        // System.out.println("2.1");
                    }
                } else if (numCol + mot.length() == 15) {
                    // System.out.println("2.2.2");
                    if (this.g[numLig][numCol - 1].estRecouvert() == false)

                        test2 = true;
                    // System.out.println("2.2");
                } else {
                    // System.out.println("2.3.3");
                    if ((this.g[numLig][numCol - 1].estRecouvert() == false)
                            && (this.g[numLig][numCol + mot.length()].estRecouvert() == false)) {
                        test2 = true;
                        // System.out.println("2.3");
                    }
                }
            }
            // sinon si le sens du mot est vertical
            else {
                // System.out.println("pas vide et v");
                if (contientUneCaseR(mot, numLig, numCol, sens) && contientUneCaseV(mot, numLig, numCol, sens)
                        && numLig + mot.length() <= 15 && mêmelettre(mot, numLig, numCol, sens)
                        && lettreexiste(mot, e, numLig, numCol, sens)) {
                    test1 = true;
                    // System.out.println("1");
                }
                if (numLig == 0) {
                    // System.out.println("2.1.1");
                    if (this.g[numLig + mot.length()][numCol].estRecouvert() == false) {
                        test2 = true;
                        // System.out.println("2.1");
                    }
                } else if (numLig + mot.length() == 15) {
                    // System.out.println("2.2.2");
                    if (this.g[numLig + 1][numCol].estRecouvert() == false) {
                        test2 = true;
                        // System.out.println("2.2");
                    }
                } else {
                    // System.out.println("2.3.3");
                    if ((this.g[numLig + mot.length()][numCol].estRecouvert() == false)
                            && (this.g[numLig - 1][numCol].estRecouvert() == false)) {
                        test2 = true;
                        // System.out.println("2.3");
                    }
                }

            }

        }
       }else if (repCapelo=='N'){
           System.out.println("ERREUR::le mot n'est pas valide par CapeloDico!");
       }
        // System.out.println (test1);
        // System.out.println(test2);
        // System.out.println(test);
        return ((test) || (test1 && test2));
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
    public int nbPointsPlacement(String mot, int numLig, int numCol, char sens, int[] nbPointsJet) {
        int res = 0;
        int bonusx2 = 1;
        int bonusx3 = 1;
        for (int i = 0; i < mot.length(); i++) {
            if (sens == 'h') {
                if (g[numLig][numCol + i].getCouleur() < 4) {
                    // System.out.println(nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] + " x
                    // "+g[numLig][numCol+i].getCouleur());
                    res += nbPointsJet[Ut.majToIndex(mot.charAt(i))] * g[numLig][numCol + i].getCouleur();
                } else {
                    res += nbPointsJet[Ut.majToIndex(mot.charAt(i))];
                    if (g[numLig][numCol + i].getCouleur() == 4) {
                        bonusx2 = bonusx2 * 2;
                    } else {
                        bonusx3 = bonusx3 * 3;
                    }
                }
            } else {
                // System.out.println(nbPointsJet[ Ut.majToIndex( mot.charAt(i) ) ] + " x
                // "+g[numLig+i][numCol].getCouleur());
                if (g[numLig + i][numCol].getCouleur() < 4) {
                    res += nbPointsJet[Ut.majToIndex(mot.charAt(i))] * g[numLig + i][numCol].getCouleur();
                } else {
                    res += nbPointsJet[Ut.majToIndex(mot.charAt(i))];
                    if (g[numLig + i][numCol].getCouleur() == 4) {
                        bonusx2 = bonusx2 * 2;
                    } else {
                        bonusx3 = bonusx3 * 3;
                    }
                }
            }
        }
        return res * bonusx2 * bonusx3;
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
    public int place(String mot, int numLig, int numCol, char sens, MEE e) {
        int nbLetPlace = 0;
        if (sens == 'h') {
            for (int i = numCol; i < mot.length() + numCol; i++) {
                if (g[numLig][i].getLetter() == '\0') {
                    g[numLig][i].setLetter(mot.charAt(i - numCol));
                    e.retire(Ut.majToIndex(mot.charAt(i - numCol)));
                    nbLetPlace++;
                }
            }
        } else {
            for (int i = numLig; i < mot.length() + numLig; i++) {
                if (g[i][numCol].getLetter() == '\0') {
                    g[i][numCol].setLetter(mot.charAt(i - numLig));
                    e.retire(Ut.majToIndex(mot.charAt(i - numLig)));
                    nbLetPlace++;
                }
            }
        }
        return nbLetPlace;
    }
}
