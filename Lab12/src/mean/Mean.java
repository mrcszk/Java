package mean;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    private static double[] array;
    private static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;


        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            double sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            mean = sum / (end - start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    private static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    private static void parallelMean(int cnt) throws InterruptedException {
        double mean = 0;
        // utwórz tablicę wątków
        MeanCalc[] threads=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        int size = array.length / cnt;
        for (int i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(i * size, i * size + size);
        }
        double t1 = System.nanoTime()/1e6;
        for(MeanCalc mc : threads){
            mc.start();
        }
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''
//        for(MeanCalc mc:threads) {
//            mc.join();
//        }
        // oblicz średnią ze średnich
        for (MeanCalc mc : threads) {
            try {
                mean += results.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mean = mean / cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    public static void main(String[] args) throws InterruptedException {
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMean(cnt);
        }
    }

}
