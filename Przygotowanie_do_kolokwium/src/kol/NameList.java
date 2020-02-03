package kol;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameList {
    List<Name> names = new ArrayList<Name>();
    Map<String, Integer> males = new HashMap<>();
    Map<String, Integer> females = new HashMap<String, Integer>();
    Map<String, Integer> males2000 = new HashMap<>();
    Map<String, Integer> females2000 = new HashMap<String, Integer>();
    Map<String, Integer> males2016 = new HashMap<>();
    Map<String, Integer> females2016 = new HashMap<String, Integer>();


    int births =0;
    public void read(String filename ) throws IOException {
        CSVReader reader = new CSVReader(filename,";",true);
        while (reader.next()) {
            int year = reader.getInt(0);
            String forename = reader.get(1);
            int number =  reader.getInt(2);
            String gender = reader.get(3);

            Name name = new Name(year, forename , number, gender);
            names.add(name);

            births += number;

            if (gender.equals("M")) {
                Integer forenameNum = males.get(forename);
                if (forenameNum == null) forenameNum = number;
                else forenameNum+=number;
                males.put(forename, forenameNum);
                if (year == 2000) males2000.put(forename, number);
                else if (year == 2016) males2016.put(forename, number);
            }
            else{
                Integer forenameNum = females.get(forename);
                if (forenameNum == null) forenameNum = number;
                else forenameNum+=number;
                females.put(forename, forenameNum);
                if (year == 2000) females2000.put(forename, number);
                else if (year == 2016) females2016.put(forename, number);
            }


        }
    }
    void list( PrintStream out ){
        for( Name name : names ) {
            out.println(name.toString());
        }
    }

    void listBirths(PrintStream out ){
        out.println("Suma urodzeń wynosi:" + births);
    }

    void mostPopularMale(){
        Integer max = 0;
        String most = "";
        for (String male : males.keySet()) {
            if (males.get(male) > max) {
                max = males.get(male);
                most = male;
            }
        }

        System.out.println("Najczęściej występującym męskim imieniem był " + most + "," + max + " razy.");
    }

    void mostPopularFemale(){
        Integer max = 0;
        String most = "";
        for (String female : females.keySet()) {
            if (females.get(female) > max) {
                max = females.get(female);
                most = female;
            }
        }
        System.out.println("Najczęściej występującym żeńskim imieniem była " + most + "," + max + " razy.");
    }

    void mostChangedMale(){
        double mostRiseV = 0;
        double mostDropV = 1000;
        String mostRiseN = "";
        String mostDropN = "";
        for (String male : males.keySet()) {
            if(!males2000.containsKey(male) || !males2016.containsKey(male)) continue;

            double cardio = (males2016.get(male)-males2000.get(male))* 100/males2000.get(male) ;
            if ( cardio > mostRiseV) {
                mostRiseV = cardio;
                mostRiseN = male;
            }
            else if (cardio < mostDropV) {
                mostDropV = cardio;
                mostDropN = male;
            }
        }
        System.out.println("Największy skok imienia męskiego " + mostRiseV + "%," + mostRiseN);
        System.out.println("Największy spadek imienia męskiego " + mostDropV + "%," + mostDropN);
    }

    void mostChangedFemale(){
        double mostRiseV = 0;
        double mostDropV = 1000;
        String mostRiseN = "";
        String mostDropN = "";
        for (String male : females.keySet()) {
            if(!females2000.containsKey(male) || !females2016.containsKey(male)) continue;

            double cardio = (females2016.get(male)-females2000.get(male))* 100/females2000.get(male) ;
            if ( cardio > mostRiseV) {
                mostRiseV = cardio;
                mostRiseN = male;
            }
            else if (cardio < mostDropV) {
                mostDropV = cardio;
                mostDropN = male;
            }
        }
        System.out.println("Największy skok imienia żeńskiego " + mostRiseV + "%," + mostRiseN);
        System.out.println("Największy spadek imienia żeńskiego " + mostDropV + "%," + mostDropN);
    }
}
