package main;

import java.util.Random;

public class Csiga {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    
    private static Random rnd = new Random();
    private String szin;
    private int pozicio = 0;
    private int sebesseg = 0;

        public int getPozicio() {
            return pozicio;
        }

        public void setPozicio(int pozicio) {
            this.pozicio = pozicio;
        }

        public int getSebesseg() {
            return sebesseg;
        }

        public void setSebesseg(int sebesseg) {
            this.sebesseg = sebesseg;
        }

        public String getSzin() {
            return szin;
        }

        public void setSzin(String szin) {
            this.szin = szin;
        }

        public void csigatRajzol(Csiga csiga){
            String vonalSzin;
            switch (this.szin) {
                case "piros": vonalSzin=Csiga.RED; break;
                case "zold": vonalSzin=Csiga.GREEN; break;
                case "kek": vonalSzin=Csiga.BLUE; break;
            }
            System.out.print(vonalSzin+"______");
            for (int i = 0; i < this.getPozicio(); i++) {
                System.out.print("_");
            }
            System.out.print("@");
        }
        
        public void versenytRajzol(int kor){
            System.out.println(RESET);
            System.out.print(f"Start |      %d. kör\n", kor);
            
            
        }
        
        public void indulas() {
            String[] szinek = {"piros", "zold", "kek"};
            Csiga[] csiga = new Csiga[3];
            for (int i = 0; i < 3; i++) {
                csiga[i].setSzin(szinek[i]);
                csiga[i].setSebesseg(Csiga.rnd.nextInt(3));
            }
               
        }
    }

