package CSV;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        AdminUnitList unitsList = new AdminUnitList();
        unitsList.read("admin-units.csv");

        AdminUnitList unitsSelectedByName = new AdminUnitList();
        unitsSelectedByName = unitsList.selectByName(".*skie.*", true);
        unitsSelectedByName.list(System.out);
    }
}
