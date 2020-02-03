package kol;

public class Name {
    int year;
    String name;
    int number;
    String gender;

    public Name(int year, String name, int number, String gender){
        this.year = year;
        this.name = name;
        this.number = number;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name{" +
                "year=" + year +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", gender='" + gender + '\'' +
                '}';
    }
}
