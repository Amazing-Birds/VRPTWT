package VRPTW;

import javax.swing.*;
import java.awt.*;

import static VRPTW.Parameter.*;

public class Visualization extends JFrame {
    public Visualization()
    {
        setSize(1000,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void paint(Graphics g)
    {
        int zoom = 6 ;
        int level = 200;
        int vertical = 150;
        for (int i = 1; i < VehicleNumber; i++) {
            if (Route_Ans[i].V.size() > 2){
                int[] px1 = new int[Route_Ans[i].V.size()];
                int[] py1 = new int[Route_Ans[i].V.size()];
                for (int j = 0; j < Route_Ans[i].V.size(); j++) {
                    g.setColor(Color.RED);
                    g.drawOval((int)Route_Ans[i].V.get(j).X*zoom+level,(int)Route_Ans[i].V.get(j).Y*zoom+vertical,3,3);
                    g.fillOval((int)Route_Ans[i].V.get(j).X*zoom+level,(int)Route_Ans[i].V.get(j).Y*zoom+vertical,3,3);
                    g.setColor(Color.blue);
                    px1[j] = (int)Route_Ans[i].V.get(j).X*zoom+level;
                    py1[j] = (int)Route_Ans[i].V.get(j).Y*zoom+vertical;
                }
                g.drawPolygon(px1,py1,Route_Ans[i].V.size());

            }
        }



    }

}
