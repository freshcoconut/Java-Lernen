
import java.util.LinkedList;

public class Mark {
//��������һ����ʦ������ʱ��ε�����
	public static LinkedList<Period> giveQi(int[] Di, int[] Pi, int Ai, Period ai, Period bi, Period ci) throws Exception{
		int lengthD = Di.length;
		int lengthP = Pi.length;
		LinkedList<Period> tempList = new LinkedList<>();
		try {
			if(Ai == 0) {//D > P �������ڴ��ڿ���
				for(int d = 0; d < lengthD; d++) {
					for(int p = 0; p < lengthP; p++) {
						//���������ʱ���ai,bi,ci����list
						if((Di[d] == ai.getDay() && Pi[p] == ai.getNumber()) ||
						(Di[d] == bi.getDay() && Pi[p] == bi.getNumber()) ||
						(Di[d] == ci.getDay() && Pi[p] == ci.getNumber())) {
							continue;
						}
						tempList.add(new Period(Di[d], Pi[p]));					
					}
				}
			}else if(Ai == 1) {//P > D ���������������
				for(int p = 0; p < lengthP; p++) {
					for(int d = 0; d < lengthD; d++) {
						//���������ʱ���ai,bi,ci����list
						if((Di[d] == ai.getDay() && Pi[p] == ai.getNumber()) ||
						(Di[d] == bi.getDay() && Pi[p] == bi.getNumber()) ||
						(Di[d] == ci.getDay() && Pi[p] == ci.getNumber())) {
							continue;
						}
						tempList.add(new Period(Di[d], Pi[p]));					
					}
				}
			}
			//����ǰ����
			tempList.addFirst(ci);
			tempList.addFirst(bi);
			tempList.addFirst(ai);
		}catch(Exception ex) {
			System.out.println("Error in Mark!");
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		//����źõ�Qi
		return tempList;
	}
}
