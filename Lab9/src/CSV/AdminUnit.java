package CSV;

import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;

    public AdminUnit(String name, int adminLevel, double population, double area, double density, AdminUnit parent, BoundingBox bbox, List<AdminUnit> children) {
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        this.parent = parent;
        this.bbox = bbox;
        this.children = children;
    }

    public String toString() {
        String parentName = "none";
        if ( parent != null ) {
            parentName = parent.getName();
        }

        return "AdminUnit{" +
                "name='" + name + '\'' +
                ", adminLevel=" + adminLevel +
                ", population=" + population +
                ", area=" + area +
                ", density=" + density +
                ", parent='" + parentName + '\'' +
                '}';
    }

    public double fixMissingValues() {
        if( this.getDensity() != -1 ) {
            return this.getDensity();
        } else {
            if( this.getParent() == null ) {
                return -1;
            } else {
                this.setDensity(this.getParent().fixMissingValues());
                this.setPopulation(this.getDensity() * this.getArea());
                return this.getDensity();
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    public void setBbox(BoundingBox bbox) {
        this.bbox = bbox;
    }

    public void setChildren(List<AdminUnit> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public BoundingBox getBbox() {
        return bbox;
    }

    public List<AdminUnit> getChildren() {
        return children;
    }
}