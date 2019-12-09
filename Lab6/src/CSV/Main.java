package CSV;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVReader reader = null;
        CSVReader reader2 = null;
        String text = "a,b,c\n123.4,567.8,91011.12";
        try {
            reader = new CSVReader("titanic-part.csv",",",true);
            reader2 = new CSVReader(new StringReader(text),",",true);
        } catch (IOException | NullPointerException e ) {
            e.printStackTrace();
        }

        while(reader.next()){
            int id = reader.getInt(0);
            String name = reader.get("Name");
            int age = reader.getInt("Age");
            String cabin = reader.get("Cabin");
            System.out.printf("%d %s %d %s\n",id, name, age, cabin);
        }
        while(reader2.next()) {
            String a = reader2.get(0);
            String b = reader2.get("b");
            System.out.printf("%s, %s", a, b);
        }
    }
}
