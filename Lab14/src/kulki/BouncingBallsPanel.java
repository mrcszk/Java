package kulki;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {
    static class Ball {
        int x;
        int y;
        double vx;
        double vy;
        Color color;

        public void move() {
            if (x + vx < 0 || x + vx > 670) {vx *= -1;}
            if (y + vy < 0 || y + vy > 610) {vy *= -1;}
            x += vx;
            y += vy;
        }

        public Ball(int x, int y, double vx, double vy, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.setColor(b.color);
            g2.fillOval(b.x, b.y, 20, 20);
        }
    }

    void collision(Ball b) {
        for(Ball b2: balls){
            if (b2 != b) {
                double dx = Math.abs((b2.x - b.x) + (b2.vx - b.vx)*0.5);
                double dy = Math.abs((b2.y - b.y)+(b2.vy - b.vy)*0.5);
                if (Math.sqrt((dx * dx) + (dy * dy))<= 20) {
                    double theta1 = Math.atan2(b.vy, b.vx);
                    double theta2 = Math.atan2(b2.vy, b2.vx);
                    double phi = Math.atan2(b2.y - b.y, b2.x - b.x);
                    double v1 = Math.sqrt(b.vx * b.vx + b.vy * b.vy);
                    double v2 = Math.sqrt(b2.vx * b2.vx + b2.vy * b2.vy);

                    double vx1 = v2 * Math.cos(theta2 - phi) * Math.cos(phi) + v1 * Math.sin(theta1 - phi) * Math.cos(phi + Math.PI / 2);
                    double vy1 = v2 * Math.cos(theta2 - phi) * Math.sin(phi) + v1 * Math.sin(theta1 - phi) * Math.sin(phi + Math.PI / 2);
                    double vx2 = v1 * Math.cos(theta1 - phi) * Math.cos(phi) + v2 * Math.sin(theta2 - phi) * Math.cos(phi + Math.PI / 2);
                    double vy2 = v1 * Math.cos(theta1 - phi) * Math.sin(phi) + v2 * Math.sin(theta2 - phi) * Math.sin(phi + Math.PI / 2);
                    b.vx = vx1;
                    b.vy = vy1;
                    b2.vx = vx2;
                    b2.vy = vy2;
                }
            }
        }
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread {
        boolean suspend = true;

        public void pause() {
            suspend = true;
        }

        public synchronized void wake() {
            suspend = false;
            notify();
        }

        public void run() {
            while (true) {
                for (Ball ball : balls) {
                    ball.move();
                    collision(ball);
                }
                repaint();

                synchronized (this) {
                    try {
                        if (suspend) {
                            System.out.println("suspending");
                            wait();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private AnimationThread workingThread = new AnimationThread();
    BouncingBallsPanel() {
        workingThread.start();
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        System.out.println("Add some balls by clicking plus.");
        for(int i=0;i<10;i++)
        onPlus();

    }

    void onStart() {
        System.out.println("Start or resume animation thread");
        workingThread.wake();
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        workingThread.pause();
    }

    void onPlus() {
        Random r = new Random();
        Color ballColor = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        this.balls.add(new Ball(50 + r.nextInt(700 - 105), 50 + r.nextInt(700 - 105), r.nextInt(10) + 5, r.nextInt(10) + 5, ballColor));
        System.out.println("Add a ball");
    }

    void onMinus() {
        if (!this.balls.isEmpty()){
            this.balls.remove(0);
            System.out.println("Remove a ball");
        }
    }
}