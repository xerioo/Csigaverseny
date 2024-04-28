package main;

import main.Csiga;

public class Csigaverseny {

    public static Csiga[] csiga = new Csiga[3];
    public static int turboEsely = 20;

    public static void main(String[] args) {

        indulas();
        //fogadas();
        for (int aktualisKor = 1; aktualisKor < 6; aktualisKor++) {
            versenytRajzol(aktualisKor);
            for (int i = 0; i < 3; i++) {
                csiga[i].setPozicio(csiga[i].getPozicio()+csiga[i].getSebesseg());
                csiga[i].setSebesseg(Csiga.rnd.nextInt(4));
            }
            if (Csiga.rnd.nextInt(100) <= turboEsely) {
                int kivalasztott = Csiga.rnd.nextInt(3);
                csiga[kivalasztott].setSebesseg(csiga[kivalasztott].getSebesseg()*2);
            }
            try{System.in.read();}
            catch(Exception e){}
         }

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

    private static void fogadas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
}
