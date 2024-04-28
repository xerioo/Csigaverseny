package main;

import static java.lang.Integer.parseInt;
import main.Csiga;

public class Csigaverseny {

    public static Csiga[] csiga = new Csiga[3];
    public static int turboEsely = 20;

    public static void main(String[] args) {

        indulas();
        int tipp = fogadas();
        for (int aktualisKor = 1; aktualisKor < 6; aktualisKor++) {
            for (int i = 0; i < 3; i++) {
                csiga[i].setSebesseg(Csiga.rnd.nextInt(4));
                csiga[i].setPozicio(csiga[i].getPozicio()+csiga[i].getSebesseg());
            }
            if (Csiga.rnd.nextInt(100) <= turboEsely) {
                int kivalasztott = Csiga.rnd.nextInt(3);
                csiga[kivalasztott].setSebesseg(csiga[kivalasztott].getSebesseg()*2);
            }
            versenytRajzol(aktualisKor);
            if (aktualisKor<5) { 
                System.out.println("Nagyon izgalmas a verseny, a kovetkezo kort az Enterrel indithatod.");
                try{System.in.read();}
                catch(Exception e){}
            
            }
        }
        int gyoztes = kiNyert();
        System.out.println("Fantasztikus hajraval vegul gyozott a " + csiga[gyoztes-1].getSzin() + " csiga.");
        if (tipp==gyoztes) {
            System.out.println("Gratulalok, eltalaltad!");
        } else System.out.println("Nem jott be a tipped.");

    }
     public static void indulas() {
        String[] szinek = {"piros", "zold", "kek"};

        for (int i = 0; i < 3; i++) {
            Csiga eticsiga = new Csiga();
            eticsiga.setSzin(szinek[i]);
            eticsiga.setSebesseg(Csiga.rnd.nextInt(4));
            eticsiga.setPozicio(0);
            csiga[i] = eticsiga;
        }
    }

    private static int fogadas() {
        versenytRajzol(0);
        System.out.println("Itt allunk a rajtvonalnal, ki lesz a gyoztes? ( 1 / 2 / 3 )");
        String tippelt = System.console().readLine();
        return parseInt(tippelt);
    }
    
    public static void versenytRajzol(int kor){
        System.out.println(Csiga.RESET);
        System.out.printf("Start |      %d. kor\n", kor);
        for (int i = 0; i < 3; i++) {
            csigatRajzol(csiga[i]);
        }
    }
    
    public static void csigatRajzol(Csiga jocsiga){
        String vonalSzin = "";
        switch (jocsiga.getSzin()) {
            case "piros": vonalSzin=Csiga.RED; break;
            case "zold": vonalSzin=Csiga.GREEN; break;
            case "kek": vonalSzin=Csiga.BLUE; break;
        }
        System.out.print(vonalSzin+"______");
        for (int i = 0; i < jocsiga.getPozicio(); i++) {
            System.out.print("_");
        }
        System.out.println("@");
    }

    private static int kiNyert() {
        int nyeresreAll = 0;
        for (int i = 1; i < 3; i++) {
            if (csiga[i].getPozicio()>csiga[nyeresreAll].getPozicio()) {
                nyeresreAll = i;
            }
        }
        return nyeresreAll+1;
    }
    
}
