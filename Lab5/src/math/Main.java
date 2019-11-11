package math;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        //buildAndPrint();
        //buildAndEvaluate();
        //defineCircle();
        diffPoly();
        //diffCircle();
    }
    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());

    }
    static void buildAndEvaluate(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x,3))
                .add(-2,new Power(x,2))
                .add(-1,x)
                .add(2);
        int i = 1;
        System.out.println("\nPierwiastki: f(x) = " + exp.toString());
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            if ( exp.evaluate() < 0.00001 && exp.evaluate() > -0.00001 ) {
                System.out.printf(Locale.US,"%d. %f\n",i++,v);
            }
            //System.out.printf(Locale.US,"f(%f)=%f\n",v,exp.evaluate());
        }
    }
    static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());
        int i=1;
        while(i<=100) {
            double xv = 100 * (Math.random() - .5);
            double yv = 100 * (Math.random() - .5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if(fv<0) System.out.println(String.format("%d. Punkt (%f,%f) leży %s koła %s",i++, xv, yv, "wewnątrz", circle.toString()));
        }
    }
    static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }
    static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());

    }
}
