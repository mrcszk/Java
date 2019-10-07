import java.util.Scanner;
public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj liczbę z przedziału 1-45: ");
        int n = scan.nextInt();
        if(n<1 || n>45){
            return;
        }
        int [] tab = new int[n];
        tab[0]=1;
        if(n>1) tab[1] = 1;
        for (int i=2;i<n;i++) {
            tab[i]=tab[i-1] + tab[i-2];
        }
        for(int i=0;i<n;i++){
            System.out.println(tab[i]);
        }

    }
}