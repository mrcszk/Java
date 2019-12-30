package graphics;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        DrawPanel XmasTree = new DrawPanel().Xmas();

        JFrame frame = new JFrame("Christmas Tree");
        frame.setContentPane(XmasTree);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
