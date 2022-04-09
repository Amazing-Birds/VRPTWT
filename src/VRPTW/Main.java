package VRPTW;

import java.awt.*;

import static VRPTW.InitAndPrint.*;
import static VRPTW.TS.*;
import static VRPTW.JavaCreateMap.*;
import static VRPTW.DrawTest.*;
import static VRPTW.Parameter.*;


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
		System.out.println("Total run time £∫" + usedTime + "s");
		draw();										//ªÊ÷∆ ’¡≤Õº±Ì
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				DrawImg f=new DrawImg();
			}
		});

		WriteArray wa = new WriteArray();
		wa.writeArrayToTxt(Resoultions, "result1.txt");
		wa.writeArrayToTxt(B,"result2.txt");
	}

}