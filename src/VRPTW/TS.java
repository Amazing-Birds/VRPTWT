package VRPTW;
 
import java.util.Random;

import static VRPTW.EvaluateRoute.*;
import static java.lang.Math.*;
import static VRPTW.Parameter.*;

public class TS {
	
	public static void TabuSearch() {
		//��������
	    //��ȡ�������ӣ�����һ��·����ѡ��һ����뵽��һ��·����
		//����Ҳ���Կ����л�һ�����ӽ�������
	    //�ڸò������γɵ�������ѡȡʹĿ�꺯����С���Ľ�
		
	    double Temp1;
	    double Temp2;

	    //��ʼ�����ɱ�
	    for ( int i = 2; i <= CustomerNumber + 1; ++i ) {   //ΪʲôҪ��2��ʼ�� 0 �� 1�� ��Ϊ�ͻ����2��ʼ
	        for ( int j = 1; j <= VehicleNumber; ++j )
	            Tabu[i][j] = 0;								//{[],[],[]}
	        TabuCreate[i] = 0;
	    }

		//��ʼ�����ϱ�
		for (int i = 0; i < Faulty_vehicle.length; i++) {
			Faulty_vehicle[i] = CustomerNumber ;
		}
		//����������ϵ�·�����Լ�����·�ϵ�λ��
//		Random r1 = new Random(2);
//		for (int i = 0; i < Faulty_Nu; i++) {
//			Faulty_vehicle[r1.nextInt(VehicleNumber)] = r1.nextInt(10);
//		}
	    int Iteration = 0;      //������������������ͼ����ʾ������������仯���ܳɱ��ı仯 ����Ȼ����Զ����ӽ��б仯���Ƚ����Ӽ�����Ž�������ٶȵĿ���
	    while ( Iteration < IterMax ) {
	        int BestC = 0;
	        int BestR = 0;
	        int BestP = 0;
	        int P=0;
	        double BestV = INF;
	        for ( int i = 2; i <= CustomerNumber + 1; ++i ) {//��ÿһ���ͻ��ڵ�
	            for ( int j = 1; j < routes[customers[i].R].V.size(); ++j ) {  //��������·���е�ÿһ���ڵ�
	                if ( routes[customers[i].R].V.get(j).Number == i ) {//�ҵ��ڵ�i����·����������λ��j
	                    P = j;//���λ��
	                    break;
	                }
	            }
	          	if (P == 0) System.out.println("û���ҵ�λ��");
	            removenode(customers[i].R,P,i);//���ͻ�i��ԭ·���ĵ�P��λ�����Ƴ�
	            
	            //�ҵ�һ��·������ɾȥ�Ľڵ�
	            for ( int j = 1; j <= VehicleNumber; ++j ) 
	            	 for ( int l = 1; l < routes[j].V.size(); ++l )//�ֱ�ö��ÿһ���ڵ�����λ��
	                        if ( customers[i].R != j ) {
	                        	
	                        	addnode(j,l,i);//���ͻ�i����·��j�ĵ�l��λ��
	                        	
	                            Temp1 = routes[customers[i].R].SubT;  //��¼ԭ������·����ʱ�䴰Υ���ܺ�
	                            Temp2 = routes[j].SubT;               //��¼�����·��ʱ�䴰Υ���ܺ�
	                            
	                            //����i�ڵ��Ƴ���·����
	                    	    routes[customers[i].R].SubT = 0;				//��ʼ��SubTΪ0
	                    	    UpdateSubT(routes[customers[i].R]);
	                    	    //����i�ڵ������·��j��
	                    	    routes[j].SubT = 0;
	                    	    UpdateSubT(routes[j]);
	                            double TempV = Calculation ( routes, i, j ,Iteration);//����Ŀ�꺯��ֵ
	                            
	                            if((TempV < Ans)|| //����׼���������ȫ�����Ž�
	                            		(TempV < BestV &&   //����Ϊ�ֲ����Ž⣬��δ������
	                            		   ( routes[j].V.size() > 2 && Tabu[i][j] <= Iteration ) || ( routes[j].V.size() == 2 && TabuCreate[i] <= Iteration ))){
									BestV = TempV; //best object ���ųɱ�
									BestC = i;     //best customer�ͻ�
									BestR = j;     //best route   ����·��
									BestP = l;     //best position����λ��
								}
	                            	//���ɲ��������ǰ��Ϊ������ɱ����ɲ������ӣ�����Ϊ������ɱ�����ʹ���µĳ���
	            	            	//·���нڵ�������2���ж��Ƿ���ɲ������ӣ�·����ֻ����㡢�յ㣬�ж��Ƿ����ʹ���³�����
	                            if ( TempV < BestV ) { //��¼�ֲ��������

	                            }

	                            //�ڵ���·����ԭ
	                            routes[customers[i].R].SubT = Temp1;
	                            routes[j].SubT = Temp2;
	                            removenode(j,l,i);
	                        }
	            //�ڵ�ԭ·����ԭ
	            addnode(customers[i].R,P,i);
	        }

	        //���³������ɱ�
	        if ( routes[BestR].V.size() == 2 )//�������û�б�����
	            TabuCreate[BestC] = Iteration + 2 * TabuTenure + (int)(random() * 10);   //���¸ÿͻ���Ľ��ɲ���
	        //���½��ɱ�
	        Tabu[BestC][customers[BestC].R] = Iteration + TabuTenure + (int)(random() * 10);
	        //���ȫ�����ŵĽڵ��������ڵ�ǰ·������
	        for ( int i = 1; i < routes[customers[BestC].R].V.size(); ++i )
	            if ( routes[customers[BestC].R].V.get(i).Number == BestC ) {
	                P = i;
	                break;
	            }

	        //��������ѭ������ѡ�Ľ���������µ�����·���滮
	        //���θ��¸ı����·���ģ����أ����볤�ȣ�����ʱ�䴰����
	        
	        //����ԭ·��
	        removenode(customers[BestC].R,P,BestC);
	        //������·��
	        addnode(BestR,BestP,BestC);
	        //���³���ʱ��
	        routes[BestR].SubT = 0;
	        UpdateSubT(routes[BestR]);
	        routes[customers[BestC].R].SubT = 0;
	        UpdateSubT(routes[customers[BestC].R]);

	        //���±������Ľڵ�����·�����
	        customers[BestC].R = BestR;
	        
	        //�����ǰ��Ϸ��ҽ�������´洢���
	        if ( ( Check ( routes ) == true ) && ( Ans > BestV ) ) {
	        	 for ( int i = 1; i <= VehicleNumber; ++i ) {
	        	        Route_Ans[i].Load = routes[i].Load;
	        	        Route_Ans[i].V.clear();
	        	        for ( int j = 0; j < routes[i].V.size(); ++j )
	        	            Route_Ans[i].V.add ( routes[i].V.get(j) );
	        	    }
	            Ans = BestV;
	        }
	        Resoultions[Iteration] = Ans;
	        Iteration++;
	    }
	}


	private static void addnode(int r,int pos,int Cus) {//�ڵ�����·��routes[r],�ڵ�customer[Cus],�ڵ����·����λ��pos
		//������·��r�м��Ͻڵ�Cus������
        routes[r].Load += customers[Cus].Demand;
        //������·��r�в���ڵ�Cus������ɵ�·������
        routes[r].Dis = routes[r].Dis 
        		- Graph[routes[r].V.get(pos-1).Number][routes[r].V.get(pos).Number]
                + Graph[routes[r].V.get(pos-1).Number][customers[Cus].Number]
                + Graph[routes[r].V.get(pos).Number][customers[Cus].Number];

		//ͬ������Ϊʲô������ʱ�䴰��ʱ�ɱ���

        //��·��r�в���ڵ�Cus
        routes[r].V.add (pos ,new CustomerType (customers[Cus]) );//����i���±�Ϊl��
	}
	
	
	private static void removenode(int r,int pos,int Cus) {//�ڵ�ȥ����·��routes[r],�ڵ�customer[cus],�ڵ�����·����λ��pos 
        //������·��r��ȥ���ڵ�Cus������
        routes[r].Load -= customers[Cus].Demand;
        //������·��r��ȥ���ڵ�Cus������ɵ�·���ľ���
        routes[r].Dis = routes[r].Dis 
         		- Graph[routes[r].V.get(pos-1).Number][routes[r].V.get(pos).Number]
	            - Graph[routes[r].V.get(pos).Number][routes[r].V.get(pos+1).Number]
	            + Graph[routes[r].V.get(pos-1).Number][routes[r].V.get(pos+1).Number];

		//����Ϊʲôû�и���ʱ�䴰�ĳɱ���  ��Ϊ��ǰ�Ѿ�ѡ���˲���ĳ���·��������ֱ�����·������Ϣ���������ڲ���·�����еľ���λ�û��������������ʱ���㳬ʱʱ�䴰�ɱ����������Խ��ͼ���ʱ��

        //��·��r��ȥ���ڵ�Cus
        routes[r].V.remove ( pos );
	}
}