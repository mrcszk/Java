package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {
    private LocalTime time = LocalTime.now();


    class ClockThread extends Thread{

        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        ClockThread ct = new ClockThread();
        g2d.translate(getWidth()/2,getHeight()/2);

        AffineTransform saveFace = g2d.getTransform();
        g2d.setStroke(new BasicStroke(1, CAP_ROUND,JOIN_MITER));
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-135,-135,270,270);
        g2d.setColor(new Color(130,70,50));
        g2d.fillOval(-130,-130,260,260);
        g2d.setColor(Color.BLACK);
        g2d.setTransform(saveFace);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i );
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),
                    (int)trg.getX() - 4*g2d.getFontMetrics().stringWidth(Integer.toString(i))/7,
                    (int)trg.getY() + g2d.getFontMetrics().getHeight()/3);

            AffineTransform saveATL = g2d.getTransform();
            g2d.rotate((i * 2*Math.PI )/12 );
            g2d.setStroke(new BasicStroke(5, CAP_ROUND, JOIN_MITER));
            g2d.drawLine(0, -108, 0, -110);
            g2d.setTransform(saveATL);
        }
        for(int i=1;i<61;i++){
            if(i%5 != 0) {
                AffineTransform saveAT = g2d.getTransform();
                g2d.rotate((i * 2 * Math.PI) / 60);
                g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_MITER));
                g2d.drawLine(0, -108, 0, -115);
                g2d.setTransform(saveAT);
            }
        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getHour()%12*2*Math.PI/12 + time.getMinute()*Math.PI/360);
        g2d.setStroke(new BasicStroke(5, CAP_ROUND,JOIN_MITER));
        g2d.drawLine(0,0,0,-60);
        g2d.setTransform(saveAT);

        AffineTransform saveATM = g2d.getTransform();
        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(3, CAP_ROUND,JOIN_MITER));
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveATM);

        AffineTransform saveATS = g2d.getTransform();
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(1, CAP_ROUND,JOIN_MITER));
        g2d.setColor(Color.RED);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveATS);
    }

    private ClockWithGui() {
        new ClockThread().start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane( new ClockWithGui());
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}