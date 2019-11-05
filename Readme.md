# Java
    public double[] getColumn(int column){
        if(column<1||column>this.getCols()) throw new IllegalArgumentException("ZŁA KOLUMNA!");
        double [] col = new double[rows];
        int x=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(j==column-1){
                    col[x]=get(i,j);
                    x++;
                }
            }
    }
        return col;
    }
    
        public Matrix sumRows() {
        Matrix resultMatrix = new Matrix(1, this.rows);
        for (int i = 0; i < this.cols; i++) {
            double sum = 0;
            for (int j = 0; j < this.rows; j++) {
                sum += this.data[j * this.cols + i];
            }
            resultMatrix.data[i] = sum;
        }
        return resultMatrix;
    }
}
