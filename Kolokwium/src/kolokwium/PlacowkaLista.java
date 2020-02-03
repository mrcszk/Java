package kolokwium;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlacowkaLista {
    List<Placowka> placowki = new ArrayList<Placowka>();
    public void read(String filename ) throws IOException {
        CSVReader reader = new CSVReader(filename,";",true);
        while (reader.next()) {
            //long regon = reader.getLong(0);
            String rspo = reader.get(1);
            String nazwa =  reader.get(2);
            String rodzaj = reader.get(3);
            String dzielnica = reader.get(4);
            String adres =  reader.get(5);
            String kod = reader.get(6);
            String telefony = reader.get(7);
            String email = reader.get(8);
            String dyrektor = reader.get(9);
            String organ = reader.get(10);

            Placowka placowka = new Placowka(rspo,nazwa,rodzaj,dzielnica,adres,
                    kod,telefony, email,dyrektor,organ);
            placowki.add(placowka);


        }
    }
    void list( PrintStream out ){
        for( Placowka placowka : placowki ) {
            out.println(placowka.toString());
        }
    }

    void bezPublicznychList( PrintStream out ){
        for( Placowka placowka : placowki ) {
            if(placowka.getRodzaj().contains("bez upr.publ."))
                out.println(placowka.toString());
        }
    }

    void dyrektorzyMList( PrintStream out ){
        for( Placowka placowka : placowki ) {
            String[] imie = placowka.getDyrektor().split(" ");
            if(!imie[0].endsWith("a") && !imie[0].isEmpty() )
                out.println(placowka.toString());
        }
    }

    void przedszkolaList( PrintStream out ){
        for( Placowka placowka : placowki ) {
            if(placowka.getNazwa().toLowerCase().contains("przedszkole"))
                out.println(placowka.toString());
        }
    }

    void listDyrektorzy( PrintStream out ){
        for( Placowka placowka : placowki ) {
            if(!placowka.getDyrektor().isEmpty())
                out.println(placowka.toStringDyrektor());
        }
    }

}
