package main;

import static java.lang.Integer.parseInt;
import main.Csiga;

public class Csigaverseny {

    public static Csiga[] csiga = new Csiga[3];         //talán nem a legjobb választás a három csigát globális statikus változóvá tenni,
    public static int turboEsely = 20;                  //de kezdõ vagyok a Javaval és meggyûlt a bajom a statikus main-bõl bármilyen nem-statikus
                                                        //metódust indítani és/vagy változót használni, ez lett az áthidaló megoldás
    public static void main(String[] args) {

        indulas();                                      //a 2.0 verzióban csak ennyi lesz a main-ben :)
        int tipp = fogadas();
        for (int aktualisKor = 1; aktualisKor < 6; aktualisKor++) { //innen a ciklus végéig lehetne egy "verseny" metódusba rakni
            for (int i = 0; i < 3; i++) {
                csiga[i].setPozicio(csiga[i].getPozicio()+csiga[i].getSebesseg()); //a saját sebességével elõrelép a csiga
                csiga[i].setSebesseg(Csiga.rnd.nextInt(4));                         //aztán új sebességet kap
            }
            if (Csiga.rnd.nextInt(100) <= turboEsely) {                             ////ha véletlenül kap gyorsítót, duplázódik a sebessége
                int kivalasztott = Csiga.rnd.nextInt(3);                            //annak, aki a Kiválasztott
                csiga[kivalasztott].setSebesseg(csiga[kivalasztott].getSebesseg()*2);
            }
            versenytRajzol(aktualisKor);
            if (aktualisKor<5) {                        //Ékezetes betûk helyett csúcsára állított négyzetben levõ kérdõjelek jelennek meg a kimeneten,
                System.out.println("Nagyon izgalmas a verseny, a következõ kört az Enterrel indíthatod.");
                try{System.in.read();}                  //ezért ékezetek nélkül írtam az üzeneteket. A régi Netbeans-szel nem volt ilyen gondom
                catch(Exception e){}                    //megtaláltam a megoldást, átírtam az üzeneteket (a projekt kódlapja utf-8-ról win-1250-re)
            }
        }
        int gyoztes = kiNyert();                    //a csigák 0-1-2 index-szel futnak, a tipp 1-2-3 lehet. Utóbbihoz igazítottam a kiNyert értékét is
        System.out.println("Fantasztikus hajrával végül gyõzött a " + csiga[gyoztes--].getSzin() + " csiga.");
        if (tipp==gyoztes) {
            System.out.println("Gratulálok, eltaláltad!");
        } else System.out.println("Nem jött be a tipped.");

    }
     public static void indulas() {
        String[] szinek = {"piros", "zold", "kek"};
        for (int i = 0; i < 3; i++) {
            Csiga eticsiga = new Csiga();           //egyesével létre kell hozni mindhárom csigát, különben null pointerek maradnak mindenhol
            eticsiga.setSzin(szinek[i]);
            eticsiga.setSebesseg(Csiga.rnd.nextInt(4));
            eticsiga.setPozicio(0);
            csiga[i] = eticsiga;
        }
    }

    private static int fogadas() {
        versenytRajzol(0);
        System.out.println("Itt állunk a rajtvonalnál, ki lesz a gyõztes? ( 1 / 2 / 3 )");
        String tippelt = System.console().readLine();
        return parseInt(tippelt);                   //az esetlegesen beírt hülyeségek vizsgálatától most eltekintettem
    }
    
    public static void versenytRajzol(int kor){
        System.out.println(Csiga.RESET);            //a rajtvonal és az egész felsõ sor alapszínnel íródik
        System.out.printf("Start |      %d. kör\n", kor);
        for (int i = 0; i < 3; i++) {
            csigatRajzol(csiga[i]);
        }
    }
    
    public static void csigatRajzol(Csiga jocsiga){
        String vonalSzin = "";
        switch (jocsiga.getSzin()) {
            case "piros": vonalSzin=Csiga.RED; break;   //a csigák pedig a saját színükkel jelennek meg
            case "zold": vonalSzin=Csiga.GREEN; break;
            case "kek": vonalSzin=Csiga.BLUE; break;
        }
        System.out.print(vonalSzin+"______");           //elõször elmentek a startvonalig,
        for (int i = 0; i < jocsiga.getPozicio(); i++) { //aztán olyan messze jutnak, amit a pozíciójuk mutat
            System.out.print("_");
        }
        System.out.println("@");
    }

    private static int kiNyert() {                      //a holtverseny lehetõségét most nem vizsgálom
        int nyeresreAll = 0;
        for (int i = 1; i < 3; i++) {
            if (csiga[i].getPozicio()>csiga[nyeresreAll].getPozicio()) {
                nyeresreAll = i;
            }
        }
        return nyeresreAll++;       //az index és a tipp közötti eltérés miatt +1
    }
    
}
