package VRPTW;

import java.util.ArrayList;


public class RouteType {
	public double Load;//单条路径承载量
    public double SubT;//单条路径违反各节点时间窗约束时长总和
    public double Dis;//单条路径总长度  ！！可以使用这个参数尽心路径平衡设置
    public ArrayList <CustomerType> V=new ArrayList<>();
    //单条路径上顾客节点序列。在route中，第0个、最后一个都为depot，第k个为第k位。
    //public ArrayList<Double> arrivaltime = new ArrayList<>(); //定义为到达各客户点的时间
    public double time;
}