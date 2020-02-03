package kol;

public class Pracownik {
    int id;
    String tytul;
    String imie;
    String nazwisko;
    String pawilon;
    String pokoj;
    String telefon;
    String mail;
    Boolean kobieta;

    public Pracownik(int id, String tytul, String imie, String nazwisko, String pawilon, String pokoj, String telefon, String mail) {
        this.id=id;
        this.tytul=tytul;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.pawilon=pawilon;
        this.pokoj=pokoj;
        this.telefon=telefon;
        this.mail=mail;

        if (imie.endsWith("a") || imie.endsWith("a ")){
            kobieta=true;
        }
        else{
            kobieta=false;
        }
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pawilon='" + pawilon + '\'' +
                ", pokoj='" + pokoj + '\'' +
                ", telefon='" + telefon + '\'' +
                ", mail='" + mail + '\'' +
                ", kobieta=" + kobieta +
                '}';
    }
}