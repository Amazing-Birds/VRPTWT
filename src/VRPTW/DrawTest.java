package VRPTW;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import static VRPTW.Parameter.*;

public class DrawTest {


}

class DrawImg extends JFrame{
    DrawImg(){
        setLocationByPlatform(true);
        setTitle("最优路径图：");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new DrawComponent());
        pack();
        setVisible(true);
    }
}

class DrawComponent extends JComponent{
    private static final int DEFAULT_W=600;
    private static final int DEFAULT_H=600;
    DrawComponent(){
        setPreferredSize(new Dimension(DEFAULT_W,DEFAULT_H));
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;

        double centerX=DEFAULT_W/2;
        double centerY=DEFAULT_H/2;

        double zoom =5;
        double level = centerX - zoom * 40;
        double vertical =centerY - zoom * 50;

        for (int i = 1; i < VehicleNumber; i++) {
            if (Route_Ans[i].V.size() > 2){
                for (int j = 0; j < Route_Ans[i].V.size()-1; j++) {
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  //消除锯齿
                    g2d.setColor(Color.RED);
                    Ellipse2D ellipse = new Ellipse2D.Double(Route_Ans[i].V.get(j).X*zoom+level-2,
                            Route_Ans[i].V.get(j).Y*zoom+vertical-1,5,5);  //这里减去的值是为了修正坐标点在图中的位置
                    g2d.draw(ellipse);
                    g2d.setColor(Color.blue);
                    Line2D line= new Line2D.Double(Route_Ans[i].V.get(j).X*zoom+level,
                            Route_Ans[i].V.get(j).Y*zoom+vertical,Route_Ans[i].V.get(j+1).X*zoom+level,Route_Ans[i].V.get(j+1).Y*zoom+vertical);
                    g2d.draw(line);
                }
            }
        }
        g2d.dispose();
    }
}



