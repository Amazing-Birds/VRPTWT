package VRPTW;

import java.awt.*;

import static VRPTW.InitAndPrint.*;
import static VRPTW.TS.*;
import static VRPTW.JavaCreateMap.*;
import static VRPTW.DrawTest.*;


public class Main {
	public static void main(String arg[]) {

		long begintime = System.nanoTime();

		ReadIn();
		Construction();
		TabuSearch();
		Output();
		CheckAns();

		long endtime = System.nanoTime();
		double usedTime = (endtime - begintime) / (1e9);
		System.out.println();
		System.out.println("Total run time ：" + usedTime + "s");
		draw();										//绘制收敛图表
		//new Visualization().setVisible(true);   //Graphics类实现绘图，但是会有锯齿产生
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				DrawImg f=new DrawImg();
			}
		});

	}
}