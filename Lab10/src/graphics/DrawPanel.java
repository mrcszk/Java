package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {
    private List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(225, 95, 0));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    private DrawPanel addShape(XmasShape newShape){
        this.shapes.add(newShape);
        return this;
    }

    DrawPanel Xmas(){
        Random gen = new Random();
        int i =8;
        double j = 0.3;
        DrawPanel XmasTree = new DrawPanel();
        while(i-->0){
            XmasTree.addShape(new Branch(500-80*(1+j*i), 100+20*i,1+j*i,
                    new GradientPaint(80, 0, new Color(120, 255, 0),
                            115, 110, new Color(0, 100, 0))));
        }
        while(i++<14) {
            XmasTree.addShape(new Bubble(285+30*i, 515 + 10*(int)(Math.pow(-1,i)) , 0.15,
                    new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                    new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            XmasTree.addShape(new Bubble(310+27*i, 465 + 10*(int)(Math.pow(-1,i)) , 0.15,
                    new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                    new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            if(i<13)
                XmasTree.addShape(new Bubble(330+28*i, 415 + 10*(int)(Math.pow(-1,i)) , 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            if(i<10) {
                XmasTree.addShape(new Bubble(350 + 30 * i, 365 + 10 * (int) (Math.pow(-1, i)), 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
                XmasTree.addShape(new Bubble(370 + 26 * i, 315 + 10 * (int) (Math.pow(-1, i)), 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            }
            if(i<9)
                XmasTree.addShape(new Bubble(395+24*i, 265 + 10*(int)(Math.pow(-1,i)) , 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            if(i<7)
                XmasTree.addShape(new Bubble(415+26*i, 215 + 10*(int)(Math.pow(-1,i)) , 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
            if(i<5)
                XmasTree.addShape(new Bubble(445+24*i, 160 + 10*(int)(Math.pow(-1,i)) , 0.15,
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255)),
                        new Color(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255))));
        }

        XmasTree.addShape(new Star(500,100,7,5, Color.yellow, Color.yellow))
                .addShape(new Trunk(500-50,550,1,new Color(80, 25, 0)))
                .addShape(new Gift(260,535,150,110,1,
                        new GradientPaint(80, 40, new Color(0, 0, 255),
                                100, 110, new Color(0, 0, 0))))
                .addShape(new Gift(360,575,130,80,1,
                        new GradientPaint(80, 40, new Color(255, 0, 0),
                                100, 110, new Color(0, 0, 0),true)))
                .addShape(new Gift(610,540,140,100,1,
                        new GradientPaint(80, 40, new Color(100, 250, 0),
                                100, 110, new Color(0, 100, 0),true)))
                .addShape(new Gift(560,580,120,80,1,
                        new GradientPaint(80, 40, new Color(255, 200, 0),
                                100, 110, new Color(0, 0, 0))))
                .addShape(new Gift(600,625,150,50,1,
                        new GradientPaint(80, 40, new Color(200, 0, 100),
                                95, 45, new Color(150, 0, 150),true)))
                .addShape(new Gift(300,625,120,60,1,
                        new GradientPaint(80, 40, new Color(100,0 , 100),
                                100, 110, new Color(0, 0, 0),true)))
                .addShape(new Gift(470,600,120,80,1,
                        new GradientPaint(80, 40, new Color(200,100 , 100),
                                100, 110, new Color(200, 0, 100),true)));
        return XmasTree;
    }
}