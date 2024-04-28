
package main;

import main.Csiga;

public class Verseny {
    indulas();
    fogadas();
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
 public void indulas() {
    String[] szinek = {"piros", "zold", "kek"};

    for (int i = 0; i < 3; i++) {
        csiga[i].setSzin(szinek[i]);
        csiga[i].setSebesseg(Csiga.rnd.nextInt(4));
    }
}

private void fogadas() {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
}
