package kolokwium;

public class Placowka {
    //long regon;
    String rspo;
    String nazwa;
    String rodzaj;
    String dzielnica;
    String adres;
    String kod;
    String telefony;
    String email;
    String dyrektor;
    String organ;

    public Placowka(String rspo,String nazwa,String rodzaj,String dzielnica,String adres,
            String kod,String telefony,String email,String dyrektor,String organ){
        //this.regon = regon;
        this.rspo = rspo;
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.dzielnica = dzielnica;
        this.adres = adres;
        this.kod = kod;
        this.telefony = telefony;
        this.email = email;
        this.dyrektor = dyrektor;
        this.organ = organ;
    }

    @Override
    public String toString() {
        return "Placowka{" +
                "rspo='" + rspo + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", rodzaj='" + rodzaj + '\'' +
                ", dzielnica='" + dzielnica + '\'' +
                ", adres='" + adres + '\'' +
                ", kod='" + kod + '\'' +
                ", telefony='" + telefony + '\'' +
                ", email='" + email + '\'' +
                ", dyrektor='" + dyrektor + '\'' +
                ", organ='" + organ + '\'' +
                '}';
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public String getDyrektor() {
        return dyrektor;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String toStringDyrektor() {
        return  "dyrektor='" + dyrektor + '\'' +
                ", telefony='" + telefony + '\'' +
                ", email='" + email + '\'';
    }
}
