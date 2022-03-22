package VRPTW;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static VRPTW.Parameter.*;


//用java生成各种图形，比如柱状图、曲线图、条形图、饼图
public class JavaCreateMap {

    // 生成曲线图
    private void createQXT(String title, String dataName1, String xtitle, String ytitle, String filePath,double[] DataSource) {

        try {

            XYSeries xyseries = new XYSeries(dataName1);         //数据1名称
            XYSeriesCollection xyseriescollection = new XYSeriesCollection();//数据集
            double count1 = 1;
            for (int i = 0; i < IterMax; i++) {//迭代次数
                Double data1 = DataSource[i];
                xyseries.add(count1, data1);            //添加数据的值
                count1++;

            }
            xyseriescollection.addSeries(xyseries);

            JFreeChart chart = createChart(xyseriescollection, title, xtitle, ytitle); // 标题、x轴标题、y轴标题
            chart.setBackgroundPaint(Color.white); // 设置背景色
            chart.setBorderVisible(false); // 设置不边框
            XYPlot plot = (XYPlot) chart.getPlot();

            //saveChartAsJPEG：表示保存为jpeg格式的图片
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 800, 500);//宽800,高500

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JFreeChart createChart(XYDataset xydataset, String title, String xtitle, String ytitle) {
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, xtitle, ytitle, xydataset,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
        xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{6F, 6F}, 0.0F));
        xylineandshaperenderer.setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F, null, 0.0F));
        xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());

        xylineandshaperenderer.setItemLabelFont(new Font("黑体", Font.TRUETYPE_FONT, 24));// 设置字体
        xyplot.setRenderer(xylineandshaperenderer);

        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return jfreechart;
    }

    public static void draw(){

        JavaCreateMap jcm = new JavaCreateMap();

        jcm.createQXT("优化收敛曲线", "总成本", "迭代次数", "成本","F:/pic/Convergence curve 2.jpeg",Resoultions);
        jcm.createQXT("车辆平衡指数", "惩罚成本", "迭代次数", "成本", "F:/pic/Vehicle equilibrium index 2.jpeg",B);
        System.out.println("	------生成图片完成" + "F:/pic/"+ "------");
    }
}
