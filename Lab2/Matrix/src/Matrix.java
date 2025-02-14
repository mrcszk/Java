import java.util.Random;

public class Matrix {
    double[]data;
    int rows;
    int cols;
    public static void main(String[] args ){
        //Matrix m = new Matrix(5,5);
//        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9,8},{10}});
//        Matrix m3 = new Matrix(new double[][]{{5,1,5,5},{0,1,0,0},{0,0,5,0},{5,0,0,1}});
//        System.out.print(m3.determinant());
//        m2=m2.transponded1();
//        System.out.print(m2.toString());
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix row = m.sumRows();
        System.out.print(row.toString());
    }
    Matrix(int rows, int cols){
        if(rows<1 || cols<1)
            throw new IllegalArgumentException("Wymiary mają być większe niż 1");
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d){
        int rows = d.length;
        int cols=0,tmp;

        for (int j=0;j<rows;j++){
            tmp = d[j].length;
            if(tmp>cols) cols = tmp;
        }
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
        for(int j=0;j<rows; j++ ){
            System.arraycopy(d[j],0,data,j*cols,d[j].length);
        }
    }
    public double[][] asArray(){
        int m = 0;
        double[][] A = new double[this.rows][this.cols];
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = this.data[m++];
            }
        }
        return A;
    }
    public double get(int r,int c){
        return data[r*cols + c];
    }
    public void set (int r,int c, double value){
        data[r*cols + c] = value;
    }
    public void setAdd (int r,int c, double value){
        data[r*cols + c] += value;
    }
    public double getRows(){
        return rows;
    }
    public double getCols(){
        return cols;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        int m=0;
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++){
                buf.append(data[m++]);
                if(j!=cols-1){
                    buf.append(",");
                }
            }
            buf.append("]");
            if(i!=rows-1){
                buf.append(",");
            }
        }
        buf.append("]");
        return buf.toString();
    }
    public void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",
                    rows,cols,newRows,newCols));
        rows = newRows;
        cols = newCols;
    }

    public int[] shape(){
        int[] s= new int[2];
        s[0] = rows;
        s[1] = cols;
        return s;
    }

    public Matrix add(Matrix m) {
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new IllegalArgumentException("ZŁE WYMIARY!");
        }
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum.set(i, j, this.get(i, j) + m.get(i, j));
            }
        }
        return sum;
    }
    public Matrix sub(Matrix m) {
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new IllegalArgumentException("ZŁE WYMIARY!");
        }
        Matrix sub = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub.set(i, j, this.get(i, j) - m.get(i, j));
            }
        }
        return sub;
    }
    public Matrix mul(Matrix m) {
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new IllegalArgumentException("ZŁE WYMIARY!");
        }
        Matrix mul = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mul.set(i, j, this.get(i, j) * m.get(i, j));
            }
        }
        return mul;
    }
    public Matrix div(Matrix m) {
        if (m.rows != this.rows || m.cols != this.cols) {
            throw new IllegalArgumentException("ZŁE WYMIARY!");
        }
        Matrix div = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(m.get(i, j)==0) throw new RuntimeException("NIE DZIEL PRZEZ 0!");
                div.set(i, j, this.get(i, j) / m.get(i, j));
            }
        }
        return div;
    }
    Matrix add(double w){
        Matrix sum = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum.set(i, j, this.get(i, j) + w);
            }
        }
        return sum;
    }
    Matrix sub(double w){
        Matrix sub = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sub.set(i, j, this.get(i, j) - w);
            }
        }
        return sub;
    }
    Matrix mul(double w){
        Matrix mul = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mul.set(i, j, this.get(i, j) * w);
            }
        }
        return mul;
    }
    Matrix div(double w){
        if(w==0){
            throw new RuntimeException("NIE DZIEL PRZEZ 0!");
        }
        Matrix div = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                div.set(i, j, this.get(i, j) / w);
            }
        }
        return div;
    }
    public Matrix dot(Matrix m){
        if(this.rows == m.cols && this.cols == m.rows) {
            Matrix mul = new Matrix(this.rows, m.cols);
            for (int w=0 ; w < this.rows; w++) {
                for (int k=0 ; k < m.cols; k++) {
                    for (int i = 0; i < this.cols; i++) {
                        mul.setAdd(w, k, this.get(w,i) * m.get(i, k));
                    }
                }

            }
            return mul;
        }
        throw new IllegalArgumentException("ZŁE WYMIARY!");
    }

    public double frobenius() {
        double frob = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frob += get(i, j) * get(i, j);
            }
        }
        return frob;
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.set(i, j, r.nextDouble());
            }
        }
        return m;
    }
    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for (int i = 0; i < n; i++) {
            m.set(i, i,1);
        }
        return m;
    }
    public double determinant(){
        double determinant=0;
        if(rows != cols ) throw new RuntimeException("Złe wymiary");
        if (rows ==1) return get(0,0);
        else{
            Matrix m = new Matrix(rows-1,cols-1);
            for (int i = 0; i < rows; i++) {
                int index = 0;
                for (int j = 0; j < cols; j++) {
                    if (j != i){
                        for (int k = 0; k < cols - 1; k++) {
                            m.set(k,index,get(k + 1,j));
                        }
                        index++;
                    }
                }
                determinant += Math.pow(-1, i) * data[i] * m.determinant();
            }
            return determinant;
        }
    }
    public Matrix transponded1(){
        Matrix m = new Matrix(cols,rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                m.set(i, j, get(j, i));
            }
        }
        return m;
    }
//    public void transponded2(){
//        reshape(cols,rows);
//        double tmp;
//        for (int i = 0; i < cols; i++) {
//            for (int j = i+1; j < rows; j++) {
//                    tmp = get(i, j);
//                    set(i, j, get(j,i));
//                    set(j,i, tmp);
//            }
//        }
//    }

    //Kartkówka 12.11
    public Matrix sumRows() {
        Matrix m = new Matrix(1, this.rows);
        for (int i = 0; i < this.cols; i++) {
            double sum = 0;
            for (int j = 0; j < this.rows; j++) {
                sum += this.data[j * this.cols + i];
            }
            m.data[i] = sum;
        }
        return m;
    }
}
