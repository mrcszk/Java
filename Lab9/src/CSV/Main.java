package CSV;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AdminUnitList unitsList = new AdminUnitList();
        unitsList.read("admin-units.csv");

        unitsList.filter(a->a.name.startsWith("Ż")).sortByArea().list(System.out);

        System.out.println("\n100 rekordów zaczynających się na 'Sz' lub powierzchnia większa niż 1000, posortowane");
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unitsList)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);
    }
}