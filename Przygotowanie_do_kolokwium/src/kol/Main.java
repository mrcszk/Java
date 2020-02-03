package kol;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws IOException {
//  1

//	BookList books = new BookList();
//	books.read("ibuk_wykaz_pozycji.csv");

//	System.out.println("zad 1");
//	books.listYear(System.out);
//    System.out.println("zad 2");
//    books.listCategory(System.out);
//    System.out.println("zad 3");
//    books.listPublisher("PWN",System.out);

//  2
    Word word = new Word("w-pustyni.txt",
                "[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?|\\;|\\:|\\”|\\„|\\…]+", Charset.forName("cp1250"));
    //word.most();

// 3
    NameList names = new NameList();
    //names.read("imiona-2000-2016.csv");
    // 1
    //names.listBirths(System.out);

    //2
    //names.mostPopularMale();
    //names.mostPopularFemale();

    //3
    //names.mostChangedMale();
    //names.mostChangedFemale();
// 4
    //Word2 word = new Word2("w-pustyni.txt",
                //"[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?|\\;|\\:|\\”|\\„|\\…]+", Charset.forName("cp1250"));
    //word.most();

    PracownicyLista pracownicy = new PracownicyLista();
    pracownicy.read("pracownicy-geo-agh.csv");
    //pracownicy.list(System.out);
    //pracownicy.wiecejTel();
        pracownicy.zC4();
    }
}
