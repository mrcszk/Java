package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();

    public void read(String filename) throws IOException {
        Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();
        Map<AdminUnit, Long> unit2id = new HashMap<>();

        CSVReader reader = new CSVReader(filename, ",", true);
        while (reader.next()) {
            String name = reader.get("name");
            long parent = reader.getLong("parent");
            int adminLevel = reader.getInt("admin_level");
            double population = reader.getDouble("population", -1);
            double area = reader.getDouble("area", -1);
            double density = reader.getDouble("density", -1);

            AdminUnit unit = new AdminUnit(name, adminLevel, population, area, density, null, new BoundingBox(), null);
            unit.getBbox().addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
            unit.getBbox().addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
            unit.getBbox().addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
            unit.getBbox().addPoint(reader.getDouble("x4"), reader.getDouble("y4"));

            units.add(unit);
            unit2id.put(unit, reader.getLong("id"));

            if (!parentid2child.containsKey(parent)) {
                parentid2child.put(parent, new ArrayList<>());
            }
            parentid2child.get(parent).add(unit);
        }

        for (AdminUnit unit : units) {
            if (parentid2child.containsKey(unit2id.get(unit))) {
                unit.setChildren(parentid2child.get(unit2id.get(unit)));
            }
            if (unit.getChildren() != null) {
                for (AdminUnit child : unit.children) {
                    child.setParent(unit);
                }
            }
            unit.fixMissingValues();
        }
    }

    void list(PrintStream out) {
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }
    }

    void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < Math.min(offset + limit, units.size()); i++) {
            out.println(units.get(i).toString());
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit unit : units) {
            if (regex == true) {
                if (unit.getName().matches(pattern)) {
                    ret.units.add(unit);
                }
            } else {
                if (unit.getName().contains(pattern)) {
                    ret.units.add(unit);
                }
            }
        }

        return ret;
    }

    public AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList neighbourList = new AdminUnitList();
        for(AdminUnit potentialNeighbour : units){
            if(potentialNeighbour != unit && potentialNeighbour.adminLevel == unit.adminLevel) {
                if(unit.adminLevel == 8 && unit.bbox.distanceTo(potentialNeighbour.bbox) < maxdistance){
                    neighbourList.units.add(potentialNeighbour);
                }else if(unit.adminLevel != 8 && unit.bbox.intersects(potentialNeighbour.bbox)){
                    neighbourList.units.add(potentialNeighbour);
                }
            }
        }
        return neighbourList;
    }

    
}