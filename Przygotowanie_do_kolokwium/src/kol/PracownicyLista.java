package kol;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PracownicyLista {
    List<Pracownik> pracownicy = new ArrayList<>();
    int wszyscy=0,sumaKobiet=0;


    public void read(String filename ) throws IOException {
        CSVReader reader = new CSVReader(filename,";",true);
        while (reader.next()) {
            int id = reader.getInt(0);
            String tytul = reader.get(1);
            String imie =  reader.get(2);
            String nazwisko = reader.get(3);
            String pawilon = reader.get(4);
            String pokoj =  reader.get(5);
            String telefon = reader.get(6);
            String mail = reader.get(7);


            Pracownik pracownik = new Pracownik(id,tytul,imie,nazwisko,pawilon,pokoj,telefon,mail);
            pracownicy.add(pracownik);
            wszyscy+=1;

            if (imie.endsWith("a") || imie.endsWith("a ")){
                sumaKobiet+=1;
            }
        }
    }
    void zC4 (){
        PracownicyLista wynik = new PracownicyLista();
        for (Pracownik pr:this.pracownicy){
            if (pr.kobieta && pr.pawilon.matches("C4")){
                System.out.println(pr.toString());            }
        }
    }
    void list(PrintStream out) {
        for (Pracownik pracownik :pracownicy ){
            out.println(pracownik.toString());
        }
    }

    void wiecejTel (){
        for (Pracownik pr:this.pracownicy){
            if (pr.telefon.contains(",")){
                System.out.println(pr.toString());
            }
        }
    }

}