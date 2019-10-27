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
