package main;

import static java.lang.Integer.parseInt;
import main.Csiga;

public class Csigaverseny {

    public static Csiga[] csiga = new Csiga[3];         //tal�n nem a legjobb v�laszt�s a h�rom csig�t glob�lis statikus v�ltoz�v� tenni,
    public static int turboEsely = 20;                  //de kezd� vagyok a Javaval �s meggy�lt a bajom a statikus main-b�l b�rmilyen nem-statikus
                                                        //met�dust ind�tani �s/vagy v�ltoz�t haszn�lni, ez lett az �thidal� megold�s
    public static void main(String[] args) {

        indulas();                                      //a 2.0 verzi�ban csak ennyi lesz a main-ben :)
        int tipp = fogadas();
        for (int aktualisKor = 1; aktualisKor < 6; aktualisKor++) { //innen a ciklus v�g�ig lehetne egy "verseny" met�dusba rakni
            for (int i = 0; i < 3; i++) {
                csiga[i].setPozicio(csiga[i].getPozicio()+csiga[i].getSebesseg()); //a saj�t sebess�g�vel el�rel�p a csiga
                csiga[i].setSebesseg(Csiga.rnd.nextInt(4));                         //azt�n �j sebess�get kap
            }
            if (Csiga.rnd.nextInt(100) <= turboEsely) {                             ////ha v�letlen�l kap gyors�t�t, dupl�z�dik a sebess�ge
                int kivalasztott = Csiga.rnd.nextInt(3);                            //annak, aki a Kiv�lasztott
                csiga[kivalasztott].setSebesseg(csiga[kivalasztott].getSebesseg()*2);
            }
            versenytRajzol(aktualisKor);
            if (aktualisKor<5) {                        //�kezetes bet�k helyett cs�cs�ra �ll�tott n�gyzetben lev� k�rd�jelek jelennek meg a kimeneten,
                System.out.println("Nagyon izgalmas a verseny, a k�vetkez� k�rt az Enterrel ind�thatod.");
                try{System.in.read();}                  //ez�rt �kezetek n�lk�l �rtam az �zeneteket. A r�gi Netbeans-szel nem volt ilyen gondom
                catch(Exception e){}                    //megtal�ltam a megold�st, �t�rtam az �zeneteket (a projekt k�dlapja utf-8-r�l win-1250-re)
            }
        }
        int gyoztes = kiNyert();                    //a csig�k 0-1-2 index-szel futnak, a tipp 1-2-3 lehet. Ut�bbihoz igaz�tottam a kiNyert �rt�k�t is
        System.out.println("Fantasztikus hajr�val v�g�l gy�z�tt a " + csiga[gyoztes--].getSzin() + " csiga.");
        if (tipp==gyoztes) {
            System.out.println("Gratul�lok, eltal�ltad!");
        } else System.out.println("Nem j�tt be a tipped.");

    }
     public static void indulas() {
        String[] szinek = {"piros", "zold", "kek"};
        for (int i = 0; i < 3; i++) {
            Csiga eticsiga = new Csiga();           //egyes�vel l�tre kell hozni mindh�rom csig�t, k�l�nben null pointerek maradnak mindenhol
            eticsiga.setSzin(szinek[i]);
            eticsiga.setSebesseg(Csiga.rnd.nextInt(4));
            eticsiga.setPozicio(0);
            csiga[i] = eticsiga;
        }
    }

    private static int fogadas() {
        versenytRajzol(0);
        System.out.println("Itt �llunk a rajtvonaln�l, ki lesz a gy�ztes? ( 1 / 2 / 3 )");
        String tippelt = System.console().readLine();
        return parseInt(tippelt);                   //az esetlegesen be�rt h�lyes�gek vizsg�lat�t�l most eltekintettem
    }
    
    public static void versenytRajzol(int kor){
        System.out.println(Csiga.RESET);            //a rajtvonal �s az eg�sz fels� sor alapsz�nnel �r�dik
        System.out.printf("Start |      %d. k�r\n", kor);
        for (int i = 0; i < 3; i++) {
            csigatRajzol(csiga[i]);
        }
    }
    
    public static void csigatRajzol(Csiga jocsiga){
        String vonalSzin = "";
        switch (jocsiga.getSzin()) {
            case "piros": vonalSzin=Csiga.RED; break;   //a csig�k pedig a saj�t sz�n�kkel jelennek meg
            case "zold": vonalSzin=Csiga.GREEN; break;
            case "kek": vonalSzin=Csiga.BLUE; break;
        }
        System.out.print(vonalSzin+"______");           //el�sz�r elmentek a startvonalig,
        for (int i = 0; i < jocsiga.getPozicio(); i++) { //azt�n olyan messze jutnak, amit a poz�ci�juk mutat
            System.out.print("_");
        }
        System.out.println("@");
    }

    private static int kiNyert() {                      //a holtverseny lehet�s�g�t most nem vizsg�lom
        int nyeresreAll = 0;
        for (int i = 1; i < 3; i++) {
            if (csiga[i].getPozicio()>csiga[nyeresreAll].getPozicio()) {
                nyeresreAll = i;
            }
        }
        return nyeresreAll++;       //az index �s a tipp k�z�tti elt�r�s miatt +1
    }
    
}
