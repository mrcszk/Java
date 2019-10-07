import java.util.Scanner;
import java.util.Locale;
public class Main {

    public static void main(String[] args) {
        System.out.print("...");
        System.out.println("...");
        System.out.printf("String %s int %d double %f","aa",34,3.9);
//        Scanner scan = new Scanner(System.in);
//        String s = scan.next();
//        int i = scan.nextInt();
//        double d = scan.nextDouble();
//        System.out.printf("Wczytano %s , %d, %f",s,i,d);
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf(Locale.US,"Wczytano %s , %d, %f",s,i,d);
    }
}
