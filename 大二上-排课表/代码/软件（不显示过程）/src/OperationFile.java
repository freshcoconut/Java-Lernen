
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//Ҫ��computeS, printMap, printTS


public class OperationFile {
	//������
	//���ı��ļ��ж�ȡ��Ϣ<----
	//����ͼ�λ�����
	public static void main(String[] args) throws Exception {
		try {
			//��argsָ����ļ��ж�ȡ��Ϣ
			TDP tdp = ToFile.getTS(args[0]);
			LinkedList<Teacher> ts = tdp.getTS();
			int nD = tdp.getnD();
			int nP = tdp.getnP();
			HashMap<Period, Teacher> map = new HashMap<>();//map����װ�ѷ���õ�ʱ���
			HashSet<Period> set = new HashSet<>();//set��װ�ѷ����ʱ���
			//��������
			ComputeS.computeS(nD, nP, ts, set, map);
			//������������α�
			printMap(nD, nP, map);
			//�������������ʦ�ֵ���ʱ���
			printResult(ts);
		}catch(Exception ex){
			System.out.println("Error!");
			System.exit(0);
		}
	}
	public static void printMap(int nD, int nP, HashMap<Period, Teacher> map) throws Exception {
		//������������α�
		//��ӡʱ��Ŵ�1��ʼ����
		//�˴��ٶ�d0,d1�����ֱ��Ӧ��һ���ܶ�����
		//System.out.println("�˴��ٶ�d0,d1�����ֱ��Ӧ��һ���ܶ�����");
		try {
			//��ӡ�ָ���
			for(int i = 0; i < (5 + nD * 12); i++) {
				System.out.print("-");
			}
			System.out.println();
			//System.out.printf("%-5s%-6s%-6s%-6s%-6s%-6s%-6s%-6s\n", "����", "����һ", "���ڶ�", "������", "������", "������", "������", "������");
			System.out.printf("%-7s", "Class");
			for(int i = 0; i < nD; i++) {
				System.out.printf("%-12s", "d" + i);//��ӡʱ��Ŵ�0��ʼ����
			}
			System.out.println();
			//��ӡ�ָ���
			for(int i = 0; i < (5 + nD * 12); i++) {
				System.out.print("-");
			}
			System.out.println();
			for(int p = 0; p < nP; p++) {//���Ŵ�ӡ��һ��һ�еش�ӡ����Ӧ����
				System.out.printf("%-7d", p);//��ӡʱ��Ŵ�0��ʼ����
				for(int d = 0; d < nD; d++) {
					String temp = "";
					for(Period tp: map.keySet()) {
						if((d == tp.getDay()) && (p == tp.getNumber())) {
							temp = map.get(tp).getName();//�����ʦ������
						}	
					}
					System.out.printf("%-12s", temp);
				}
				System.out.println();	
				//��ӡ�ָ���
				for(int i = 0; i < (5 + nD * 12); i++) {
					System.out.print("-");
				}
				System.out.println();
			}//for p ��β
		}catch(Exception ex) {
			System.out.println("Error in OperationFile�е�printMap����!");
			System.out.println(ex.getMessage());
			System.exit(1);
		}
	}
	
	public static void printList(LinkedList<Teacher> t) throws Exception {//��ӡ��ʦ��ʱ��ε�����
		try {
			for(int i = 0; i < t.size(); i++) {//��iλ��ʦ
				Teacher iTeacher = t.get(i);
				LinkedList<Period> plist = iTeacher.getList();
				System.out.printf("%-5s", iTeacher.getName());
				for(int j = 0; j < plist.size(); j++) {//��j�������䵽��ʱ���
					Period jperiod = plist.get(j);
					int d = jperiod.getDay();
					int p = jperiod.getNumber();
					System.out.printf("%-9s", "(" + d + ", " + p + ")");
				}
				System.out.println();//����	
			}
		}catch(Exception ex) {
			System.out.println("Error in Operation.printList!");
			System.out.println(ex.getMessage());
			System.exit(2);
		}
	}

	public static void printResult(LinkedList<Teacher> t) throws Exception {//��ӡ��ʦ�����䵽��ʱ���
		try {
			for(int i = 0; i < t.size(); i++) {//��iλ��ʦ
				Teacher iteacher = t.get(i);
				LinkedList<Period> iresult = iteacher.getResult();
				System.out.printf("%-12s", iteacher.getName());
				for(int j = 0; j < iresult.size(); j++) {//��j�������䵽��ʱ���
					Period jperiod = iresult.get(j);
					int d = jperiod.getDay();
					int p = jperiod.getNumber();
					System.out.printf("%-8s", "(" + d + ", " + p + ")");
				}
				System.out.println();//����		
			}
		}catch(Exception ex) {
			System.out.println("Error in Operation.printResult!");
			System.out.println(ex.getMessage());
			System.exit(3);
		}
	}
	public static void printTeacherList(LinkedList<Teacher> tL) {
		for(int i = 0; i < tL.size(); i++) {
			Teacher iT = tL.get(i);
			System.out.print(iT.getName() + " " + "��ѡ������" + iT.getTimes() 
			+ " " + "δ�����ʱ��ε�������" + iT.getRemained() 
			+ "\n �ѷ����ʱ��Σ�");
			for(int j = 0; j < iT.getResult().size(); j++) {//��j�������䵽��ʱ���
				Period jperiod = iT.getResult().get(j);
				int d = jperiod.getDay();
				int p = jperiod.getNumber();
				System.out.printf("%-8s", "(" + d + ", " + p + ")");
			}
			System.out.println();//����			
		}
	}
	
}
