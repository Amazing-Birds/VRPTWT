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


//��java���ɸ���ͼ�Σ�������״ͼ������ͼ������ͼ����ͼ
public class JavaCreateMap {

    // ��������ͼ
    private void createQXT(String title, String dataName1, String xtitle, String ytitle, String filePath,double[] DataSource) {

        try {

            XYSeries xyseries = new XYSeries(dataName1);         //����1����
            XYSeriesCollection xyseriescollection = new XYSeriesCollection();//���ݼ�
            double count1 = 1;
            for (int i = 0; i < IterMax; i++) {//��������
                Double data1 = DataSource[i];
                xyseries.add(count1, data1);            //������ݵ�ֵ
                count1++;

            }
            xyseriescollection.addSeries(xyseries);

            JFreeChart chart = createChart(xyseriescollection, title, xtitle, ytitle); // ���⡢x����⡢y�����
            chart.setBackgroundPaint(Color.white); // ���ñ���ɫ
            chart.setBorderVisible(false); // ���ò��߿�
            XYPlot plot = (XYPlot) chart.getPlot();

            //saveChartAsJPEG����ʾ����Ϊjpeg��ʽ��ͼƬ
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 800, 500);//��800,��500

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

        xylineandshaperenderer.setItemLabelFont(new Font("����", Font.TRUETYPE_FONT, 24));// ��������
        xyplot.setRenderer(xylineandshaperenderer);

        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return jfreechart;
    }

    public static void draw(){

        JavaCreateMap jcm = new JavaCreateMap();

        jcm.createQXT("�Ż���������", "�ܳɱ�", "��������", "�ɱ�","F:/pic/Convergence curve 2.jpeg",Resoultions);
        jcm.createQXT("����ƽ��ָ��", "�ͷ��ɱ�", "��������", "�ɱ�", "F:/pic/Vehicle equilibrium index 2.jpeg",B);
        System.out.println("	------����ͼƬ���" + "F:/pic/"+ "------");
    }
}
