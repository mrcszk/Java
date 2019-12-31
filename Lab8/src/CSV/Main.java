package CSV;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList unitsList = new AdminUnitList();
        unitsList.read("admin-units.csv");

        System.out.println("5 rekordów zaczynając od 5");
        unitsList.list(System.out, 5, 5);

        System.out.println("\nRekordy zawierające 'skie':");
        for (AdminUnit adminUnit : unitsList.selectByName(".*skie.*", true).units) {
            System.out.println(adminUnit.toString());
            System.out.println(adminUnit.getBbox().getWKT());
        }
        double t1 = System.nanoTime()/1e6;
        System.out.println("\nSąsiedzi gm.'Słomniki':");
        unitsList.getNeighbors(unitsList.selectByName("Słomniki",true).units.get(0),
                10).list(System.out);

        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }
}