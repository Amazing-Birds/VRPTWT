package VRPTW;

import java.util.Random;

import static VRPTW.Parameter.*;

public class EvaluateRoute {
	
	public static boolean Check ( RouteType R[] ) {//�����R�Ƿ���������Լ��
	    double Q = 0;
	    double T = 0;
		Boolean flag = true;
	    //����Ƿ���������Լ��
	    for ( int i = 1; i <= VehicleNumber; ++i ) {
			if (R[i].V.size() > Faulty_vehicle[i-1] + 2) 											//�������ϣ�
				flag = false;
			if (R[i].V.size() > 2 && R[i].Load > Capacity)//���пͻ��ҳ�������Լ����·������¼��������
				Q = Q + R[i].Load - Capacity;
		}


	    //����Ƿ�����ʱ�䴰Լ��
	    for ( int i = 1; i <= VehicleNumber; ++i )
	        T += R[i].SubT;

	    //�ֱ����Լ�����������Ϳ���ϵ��Sita����Alpha��Betaֵ
	    //��·�������������ͷ�ϵ����С��
	    //��·��Υ���������ͷ�ϵ���Ӵ�
//	    if ( Q == 0 && Alpha >= 0.001 )
//	        Alpha /= ( 1 + Sita );
//	    else if ( Q != 0 && Alpha <= 2000 )
//	        Alpha *= ( 1 + Sita );
//		//�ͷ�ϵ���Ƿ���������أ�
//	    if ( T == 0 && Beta >= 0.001 )
//	        Beta /= ( 1 + Sita );
//	    else if ( T != 0 && Beta <= 2000 )
//	        Beta *= ( 1 + Sita );

//	    if ( T == 0 && Q == 0 )
//	        return true;
//	    else
//	        return false;
		if ( Q == 0 && flag)
			return true;
		else
			return false;
	}


	public static void UpdateSubT(RouteType r) {//����·��r��ʱ�䴰��Υ����
		double ArriveTime =0;
        for ( int j = 1; j < r.V.size(); ++j ) {//��ÿһ���ڵ�ֱ���㳬��ʱ�䴰�Ĳ���
            ArriveTime = ArriveTime 
            		+ r.V.get(j-1).Service //����ʱ��
            		+ Graph[r.V.get(j-1).Number][r.V.get(j).Number];//·;����ʱ��
            if ( ArriveTime > r.V.get(j).End )//��������¼
                r.SubT = r.SubT + ArriveTime - r.V.get(j).End;
            else if ( ArriveTime < r.V.get(j).Begin )//δ�ﵽ���ȴ�
                ArriveTime = r.V.get(j).Begin;
        }
//		if (r.V.size()==2){
//			Time[r.V.get(1).R] = 0;    			//�����е�����
//		}else if (r.V.size() > 2){
//			Time[r.V.get(1).R] = ArriveTime;
//		}
	}
	
	
	//����·���滮R��Ŀ�꺯��ֵ��ͨ����Ŀ�꺯���жϽ��Ƿ����

	public static double Calculation ( RouteType R[], int Cus, int NewR ,int Iteration) {
	    //Ŀ�꺯����Ҫ������������ɣ�D·���ܳ��ȣ��Ż�Ŀ�꣩��Q��������Լ��������T����ʱ�䴰Լ������
	    //Ŀ�꺯���ṹΪ f(R) = D + Alpha * Q + Beta * T, ��һ��Ϊ������С��Ŀ�꣬������Ϊ�ͷ�����
		//����Alpha��BetaΪ�������ֱ���ݵ�ǰ���Ƿ���������Լ�����б仯������ÿ�ֵ����õ��Ľ���Check�����и���
	    
	    double Q = 0;
	    double T = 0;
	    double D = 0;

	    //���㵥��·����������Լ��������
	    for ( int i = 1; i <= VehicleNumber; ++i )
	        if ( R[i].V.size() > 2 && R[i].Load > Capacity )
	            Q = Q + R[i].Load - Capacity;

	    //�����ܳ���ʱ��
	    for ( int i = 1; i <= VehicleNumber; ++i )
	        T += R[i].SubT;

	    //����·���ܳ���
	    for ( int i = 1; i <= VehicleNumber; ++i )
	        D += R[i].Dis;

		//����·����ľ���ָ��
		double min = 1e5;
		double max = 0;
		double sum =0;
		int V_n = 0;
//		for (int i = 0; i < Time.length; i++) {
//			if (Time[i] != 0 ){
//				sum += Time[i];
//				V_n++;
//				if (Time[i] < min)
//					min = Time[i];
//				if (Time[i] > max)
//					max = Time[i];
//			}
//		}

		for (int i = 1; i <= VehicleNumber; i++) {
			if (R[i].Dis != 0) {
				sum += R[i].Dis;
				V_n++;
				if (R[i].Dis < min)
					min = R[i].Dis;
				if (R[i].Dis > max)
					max = R[i].Dis;
			}
		}
		 B[Iteration] = (max-min)/(sum/V_n);
	    return (D + Alpha * Q + Beta * T +B[Iteration] );//����Ŀ�꺯��ֵ
	}
}