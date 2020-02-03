package kolokwium;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        PlacowkaLista placowki = new PlacowkaLista();
        placowki.read("placowki.csv");
        placowki.bezPublicznychList(System.out);
        placowki.dyrektorzyMList(System.out);
        placowki.przedszkolaList(System.out);
        placowki.listDyrektorzy(System.out);



        // Zadanie 2
        Tablica tab = new Tablica();
        tab.initArray(100000000);
        tab.sekwencyjnie();
        tab.parallel(4);

    }
}
