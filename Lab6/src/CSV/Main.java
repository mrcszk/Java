package CSV;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
        CSVReader reader = null;
        try {
            reader = new CSVReader("with-header.csv",";",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(reader.nextLine()){
            int id = reader.getInt(0);
            String name = reader.get(1);
            String surname = reader.get("nazwisko");
            System.out.printf("%d %s %s\n",id, name, surname);

        }
    }
}
