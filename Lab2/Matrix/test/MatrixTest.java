import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void Matrix() {
        Matrix test = new Matrix(5,6);
        if(test.rows != 5 || test.cols != 6) fail("Błąd podstawienia.");
        try {
            Matrix test2 = new Matrix(0,5);
        } catch (IllegalArgumentException e) {
            System.out.println("Złapany wyjątek.");
        }

    }

    @org.junit.Test
    public void TestMatrix() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1,1},{1,1,1}});
        double [][] test = m1.asArray();
        if(test[1][1] != 1 || test[0][3] != 0 || test[1][3] != 0 || test[3][3] != 0) fail("Błąd");
    }

    @org.junit.Test
    public void asArray() {
        Matrix m1 = new Matrix(new double[][]{{1,8,1},{3,1,6},{1,18,1,6},{0,9,1}});
        double[][] data = m1.asArray();
        if(data[0][3] != 0 || data[1][0]!=3) fail("Błąd podstawienia.");
    }

    @org.junit.Test
    public void get() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1,1},{1,1,1}});
        assertTrue(m1.get(0,0) == 1);
    }

    @org.junit.Test
    public void set() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1,1},{1,1,1}});
        m1.set(0,0,5);
        if(m1.get(0,0) != 5) fail("Błąd");
    }

    @org.junit.Test
    public void setAdd() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1,1},{1,1,1}});
        m1.setAdd(0,0,5);
        if(m1.get(0,0) != 6) fail("Błąd");
    }

    @org.junit.Test
    public void getRows() {
        Matrix m1 = new Matrix(4,5);
        if(m1.getRows()!= 4) fail("Błąd");
    }

    @org.junit.Test
    public void getCols() {
        Matrix m1 = new Matrix(4,5);
        if(m1.getCols()!= 5) fail("Błąd");
    }

    @org.junit.Test
    public void getData() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1,1},{1,1,1}});
        if(m1.get(0,0) != 1) fail("Błąd");
    }

    @org.junit.Test
    public void testToString() {
        Matrix m1 = new Matrix(new double[][]{{1.0,2.3,4.56},{12.3, 45, 21.8}});
        String s0 = m1.toString();
        String s= "[[1.0,2.3,4.56],[12.3,45.0,21.8]]";
        System.out.println(s0);
        int count = 0;

        for (int i = 0; i < s0.length(); i++) {
            if (s0.charAt(i) == ',') {
                count++;
            }
        }
            if(count != 5) {fail("Błąd ilości przecinków.");}
            if(!s0.equals(s)) {fail("Błąd");}
//        s= s.replaceAll("(\\[|\\]|\\s)+","");
//        String[] t = s.split("(,)+");
//        for(String x:t){
//            System.out.println(String.format("\'%s\'",x ));
//        }
//
//        double[]d=new double[t.length];
//        for(int i=0;i<t.length;i++) {
//            d[i] = Double.parseDouble(t[i]);
//        }
//
//        double [][]arr=new double[1][];
//        arr[0]=d;
//
//        for(int i=0;i<arr.length;i++){
//            for(int j=0;j<arr[i].length;j++){
//                System.out.println(arr[i][j]);
//            }
//        }
    }

    @org.junit.Test
    public void reshape() {
        Matrix m1 = new Matrix(4,5);
        try {
           m1.reshape(3,3);
        } catch (RuntimeException e) {
            System.out.println("Złapany wyjątek.");
        }
        m1.reshape(5,4);
        if(m1.getRows() != 5) {fail("Błąd");}

    }

    @org.junit.Test
    public void shape() {
        Matrix m1 = new Matrix(4,5);
        int[] tab = m1.shape();
        if(tab[0]!=4 && tab[1]!=5){fail("Błąd");}
    }

    @org.junit.Test
    public void add() {
        Matrix m1 = new Matrix(new double[][]{{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1},{-1,-1,-1,-1}});
        m1 = m1.add(1);
        if(m1.frobenius() != 0) {fail("Błąd");}
    }

    @org.junit.Test
    public void sub() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        m1 = m1.sub(m2);
        if(m1.frobenius() != 0) fail("Błąd");

    }

    @org.junit.Test
    public void mul() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        m1=m1.mul(2);
        if(m1.frobenius() != 16*4) {fail("Błąd");}
    }

    @org.junit.Test
    public void div() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        m1=m1.div(1);
        if(m1.frobenius() != 16) {fail("Błąd");}
    }

    @org.junit.Test
    public void testAdd() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        m2 = m2.mul(-1);
        m1 = m1.add(m2);
        if(m1.frobenius() != 0) {fail("Błąd");}
    }

    @org.junit.Test
    public void testSub() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        m1=m1.sub(1);
        if(m1.frobenius() != 0) {fail("Błąd");}
    }

    @org.junit.Test
    public void testMul() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        m2 = m2.mul(-1);
        m1 = m1.add(m2);
        if(m1.frobenius() != 0) {fail("Błąd");}
    }

    @org.junit.Test
    public void testDiv() {
        Matrix m1 = new Matrix(new double[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        Matrix m2 = new Matrix(new double[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
        m1=m1.div(m2);
        if(m1.frobenius() != 16) {fail("Błąd");}
    }

    @org.junit.Test
    public void dot() {
        Matrix m1 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix m2 = new Matrix(new double[][]{{68,38,3,4},{35,46,15,20},{47,62,21,28},{9,18,27,36}});
        m1=m1.dot(m1);
        String s1 = m1.toString();
        String s2 = m2.toString();
        if(!s1.equals(s2)) {fail("Błąd");}
    }

    @org.junit.Test
    public void frobenius() {
        Matrix test = new Matrix(3,3);
        test = test.eye(3);
        if(test.frobenius()!=3) fail("Błąd");
    }

    @org.junit.Test
    public void random() {
        Matrix m = new Matrix(5,5);
        m = m.random(5,5);
        //if(m.get(2,2) ) fail("Błąd");

    }

    @org.junit.Test
    public void eye() {
        Matrix test = new Matrix(3,3);
        test = test.eye(3);
        if(test.frobenius()!=3) fail("Błąd");
    }
}