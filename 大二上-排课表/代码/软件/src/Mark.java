
import java.util.LinkedList;

public class Mark {
//用于生成一个老师对所有时间段的排序
	public static LinkedList<Period> giveQi(int[] Di, int[] Pi, int Ai, Period ai, Period bi, Period ci) throws Exception{
		int lengthD = Di.length;
		int lengthP = Pi.length;
		LinkedList<Period> tempList = new LinkedList<>();
		try {
			if(Ai == 0) {//D > P 即：日期大于课序
				for(int d = 0; d < lengthD; d++) {
					for(int p = 0; p < lengthP; p++) {
						//避免在这个时候把ai,bi,ci加入list
						if((Di[d] == ai.getDay() && Pi[p] == ai.getNumber()) ||
						(Di[d] == bi.getDay() && Pi[p] == bi.getNumber()) ||
						(Di[d] == ci.getDay() && Pi[p] == ci.getNumber())) {
							continue;
						}
						tempList.add(new Period(Di[d], Pi[p]));					
					}
				}
			}else if(Ai == 1) {//P > D 即：课序大于日期
				for(int p = 0; p < lengthP; p++) {
					for(int d = 0; d < lengthD; d++) {
						//避免在这个时候把ai,bi,ci加入list
						if((Di[d] == ai.getDay() && Pi[p] == ai.getNumber()) ||
						(Di[d] == bi.getDay() && Pi[p] == bi.getNumber()) ||
						(Di[d] == ci.getDay() && Pi[p] == ci.getNumber())) {
							continue;
						}
						tempList.add(new Period(Di[d], Pi[p]));					
					}
				}
			}
			//插入前三项
			tempList.addFirst(ci);
			tempList.addFirst(bi);
			tempList.addFirst(ai);
		}catch(Exception ex) {
			System.out.println("Error in Mark!");
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		//输出排好的Qi
		return tempList;
	}
}
