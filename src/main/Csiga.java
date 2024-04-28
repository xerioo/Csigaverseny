package main;

import java.util.Random;

public class Csiga {
    public static final String RESET = "\u001B[0m";     //az alapszín és a csigák színei
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    
    public static Random rnd = new Random();    //egy általánosan használható véletlengenerátor
    private String szin;                     
    private int pozicio = 0;                    //a csigák tulajdonságai
    private int sebesseg = 0;

        public int getPozicio() {               //getterek és szetterek
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
        
    }