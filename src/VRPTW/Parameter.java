package VRPTW;

public class Parameter {

	public static double INF = Double.MAX_VALUE;   //大值
	public static int CustomerNumber=100;//算例中除仓库以外的顾客节点个数
	public static int VehicleNumber = 25;
	public static int Capacity=250;//车辆最大容量
	public static int IterMax=1000;//搜索迭代次数
	
	public static int TabuTenure=20;//禁忌步长
	public static int[][] Tabu=new int[CustomerNumber + 10][VehicleNumber + 10];//禁忌表用于禁忌节点插入操作:[i][j]将节点i插入路径j中
	public static int[] TabuCreate=new int[CustomerNumber + 10];//禁忌表用于紧急拓展新路径或使用新车辆

	public static double Ans;//最优解距离
	public static double net_cost; //净成本
	public static double Alpha = 0, Beta = 20, Sita = 0.5;//Alpha，Beta为系数，计算目标函数值；Sita控制系数改变的速度
	public static double Gamma = 10 ;  //路径平衡系数
	public static double[][] Graph=new double[CustomerNumber + 10][CustomerNumber + 10];//记录图
	public static CustomerType[] customers=new CustomerType[CustomerNumber+10];//存储客户数据
	public static RouteType[] routes=new RouteType[CustomerNumber+10];//存储当前解路线数据
	public static RouteType[] Route_Ans=new RouteType[CustomerNumber+10];//存储最优解路线数据
	public static double[] Time = new double[VehicleNumber+1];         		//存储每条路径上的结束时间

	public static double[] Resoultions = new double[IterMax];   //记录每次迭代后的最优解

	public static double[] B = new double[IterMax] ;			//存储每次迭代后的路径平衡系数

	public static  int[] Faulty_vehicle = new int[VehicleNumber];							//会发生故障的路径
	public static  int Faulty_Nu =3;														//发生故障的路径数
}
