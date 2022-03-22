package VRPTW;

public class Parameter {

	public static double INF = Double.MAX_VALUE;   //��ֵ
	public static int CustomerNumber=100;//�����г��ֿ�����Ĺ˿ͽڵ����
	public static int VehicleNumber = 25;
	public static int Capacity=250;//�����������
	public static int IterMax=1000;//������������
	
	public static int TabuTenure=20;//���ɲ���
	public static int[][] Tabu=new int[CustomerNumber + 10][VehicleNumber + 10];//���ɱ����ڽ��ɽڵ�������:[i][j]���ڵ�i����·��j��
	public static int[] TabuCreate=new int[CustomerNumber + 10];//���ɱ����ڽ�����չ��·����ʹ���³���

	public static double Ans;//���Ž����
	public static double net_cost; //���ɱ�
	public static double Alpha = 0, Beta = 20, Sita = 0.5;//Alpha��BetaΪϵ��������Ŀ�꺯��ֵ��Sita����ϵ���ı���ٶ�
	public static double Gamma = 10 ;  //·��ƽ��ϵ��
	public static double[][] Graph=new double[CustomerNumber + 10][CustomerNumber + 10];//��¼ͼ
	public static CustomerType[] customers=new CustomerType[CustomerNumber+10];//�洢�ͻ�����
	public static RouteType[] routes=new RouteType[CustomerNumber+10];//�洢��ǰ��·������
	public static RouteType[] Route_Ans=new RouteType[CustomerNumber+10];//�洢���Ž�·������
	public static double[] Time = new double[VehicleNumber+1];         		//�洢ÿ��·���ϵĽ���ʱ��

	public static double[] Resoultions = new double[IterMax];   //��¼ÿ�ε���������Ž�

	public static double[] B = new double[IterMax] ;			//�洢ÿ�ε������·��ƽ��ϵ��

	public static  int[] Faulty_vehicle = new int[VehicleNumber];							//�ᷢ�����ϵ�·��
	public static  int Faulty_Nu =3;														//�������ϵ�·����
}
