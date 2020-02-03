package kolokwium;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tablica {
    static double[]  array;
    private static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static class TablicaCalc extends Thread{
        private final int start;
        private final int end;
        double[] najw = {0,0,0};


        TablicaCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for (int i = start; i < end; i++) {
                if (array[i]>najw[0]){
                    najw[2] = najw[1];
                    najw[1]=najw[0];
                    najw[0] = array[i];
                    continue;
                }
                if (array[i]>najw[1]){
                    najw[2]=najw[1];
                    najw[1] = array[i];
                    continue;
                }
                if (array[i]>najw[2]){
                    najw[2] = array[i];
                }
            }
            try {
                results.put(najw[0]);
                results.put(najw[1]);
                results.put(najw[2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d Liczby: %f, %f, %f\n",start,end,najw[0], najw[1],najw[2]);
        }
    }

    void parallel(int cnt) throws InterruptedException {
        double[] najw = {0,0,0};
        TablicaCalc[] threads = new TablicaCalc[cnt];

        int size = array.length / cnt;
        for (int i = 0; i < cnt; i++) {
            threads[i] = new TablicaCalc(i * size, i * size + size);
        }
        double t1 = System.nanoTime()/1e6;
        for(TablicaCalc mc : threads){
            mc.start();
        }
        double t2 = System.nanoTime()/1e6;

        for (TablicaCalc mc : threads) {
            double x = results.take();
            if (x>najw[0]){
                najw[2] = najw[1];
                najw[1]=najw[0];
                najw[0] = x;
                continue;
            }
            if (x>najw[1]){
                najw[2]=najw[1];
                najw[1] = x;
                continue;
            }
            if (x>najw[2]){
                najw[2] = x;
            }

        }
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f Liczby: %f, %f, %f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                najw[0], najw[1],najw[2]);

        System.out.printf(Locale.US,"----->Czas dla wątków: %f",t3-t1);
    }

    void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= randomWithRange(-1000,1000);
        }

    }

    void sekwencyjnie(){
        double[] najw = {0,0,0};

        double t1 = System.nanoTime()/1e6;
        for(double x: array){
            if (x>najw[0]){
                najw[2] = najw[1];
                najw[1]=najw[0];
                najw[0] = x;
                continue;
            }
            if (x>najw[1]){
                najw[2]=najw[1];
                najw[1] = x;
                continue;
            }
            if (x>najw[2]){
                najw[2] = x;
            }
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,">size = %d >  t2-t1=%f, Liczby: %f, %f, %f  \n",
                array.length,
                t2-t1, najw[0], najw[1],najw[2]);
        System.out.printf(Locale.US,"----->Czas dla sekwencyjnego: %f \n",  t2-t1);
    }



    int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
