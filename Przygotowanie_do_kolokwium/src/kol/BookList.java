package kol;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class BookList {
    List<Book> books = new ArrayList<Book>();
    Map<Integer, Integer> years = new HashMap<Integer, Integer>();
    Map<String, Integer> categories = new HashMap<String, Integer>();

    public void read(String filename ) throws IOException {
        CSVReader reader = new CSVReader(filename,";",true);
        while (reader.next()) {
            int ibukId = reader.getInt(0);
            String title = reader.get(1);
            String author =  reader.get(2);
            String isbn = reader.get(3);
            String publisher = reader.get(4);
            int year =  reader.getInt(5);
            String category = reader.get(6);
            String subCategory = reader.get(7);
            String link = reader.get(8);

            Book book = new Book(ibukId, title,author,isbn,publisher,year,category,subCategory,link);
            books.add(book);

            Integer yearNum = years.get(year);
            if(yearNum == null) yearNum = 1;
            else yearNum++;
            years.put(year, yearNum);

            Integer categoryNum = categories.get(category);
            if(categoryNum == null) categoryNum = 1;
            else categoryNum++;
            categories.put(category, categoryNum);
        }
    }
    void list( PrintStream out ){
        for( Book book : books ) {
            out.println(book.toString());
        }
    }

    public void listYear(PrintStream out){
        out.println("Rok Liczba");
        for (Integer year : years.keySet()) {
            out.println(year + " " + years.get(year));
        }
    }

    public void listCategory(PrintStream out){
        out.println("Kategoria Liczba");
        for (String category : categories.keySet()) {
            out.println(category + " " + categories.get(category));
        }
    }

    public void listPublisher(String pub, PrintStream out){
        for (Book book :books) {
            if(book.getPublisher().contains(pub)){
                book.print(out);
           }
        }
    }
}

