
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//要改computeS, printMap, printTS


public class OperationFile {
	//主程序
	//从文本文件中读取信息<----
	//不搞图形化界面
	public static void main(String[] args) throws Exception {
		try {
			//从args指向的文件中读取信息
			TDP tdp = ToFile.getTS(args[0]);
			LinkedList<Teacher> ts = tdp.getTS();
			int nD = tdp.getnD();
			int nP = tdp.getnP();
			HashMap<Period, Teacher> map = new HashMap<>();//map用来装已分配好的时间段
			HashSet<Period> set = new HashSet<>();//set中装已分配的时间段
			//处理争端
			ComputeS.computeS(nD, nP, ts, set, map);
			//用命令行输出课表
			printMap(nD, nP, map);
			//用命令行输出老师分到的时间段
			printResult(ts);
		}catch(Exception ex){
			System.out.println("Error!");
			System.exit(0);
		}
	}
	public static void printMap(int nD, int nP, HashMap<Period, Teacher> map) throws Exception {
		//用命令行输出课表
		//打印时序号从1开始计数
		//此处假定d0,d1……分别对应周一、周二……
		//System.out.println("此处假定d0,d1……分别对应周一、周二……");
		try {
			//打印分割线
			for(int i = 0; i < (5 + nD * 12); i++) {
				System.out.print("-");
			}
			System.out.println();
			//System.out.printf("%-5s%-6s%-6s%-6s%-6s%-6s%-6s%-6s\n", "课序", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日");
			System.out.printf("%-7s", "Class");
			for(int i = 0; i < nD; i++) {
				System.out.printf("%-12s", "d" + i);//打印时序号从0开始计数
			}
			System.out.println();
			//打印分割线
			for(int i = 0; i < (5 + nD * 12); i++) {
				System.out.print("-");
			}
			System.out.println();
			for(int p = 0; p < nP; p++) {//横着打印，一行一行地打印，对应课序
				System.out.printf("%-7d", p);//打印时序号从0开始计数
				for(int d = 0; d < nD; d++) {
					String temp = "";
					for(Period tp: map.keySet()) {
						if((d == tp.getDay()) && (p == tp.getNumber())) {
							temp = map.get(tp).getName();//获得老师的名字
						}	
					}
					System.out.printf("%-12s", temp);
				}
				System.out.println();	
				//打印分割线
				for(int i = 0; i < (5 + nD * 12); i++) {
					System.out.print("-");
				}
				System.out.println();
			}//for p 结尾
		}catch(Exception ex) {
			System.out.println("Error in OperationFile中的printMap方法!");
			System.out.println(ex.getMessage());
			System.exit(1);
		}
	}
	
	public static void printList(LinkedList<Teacher> t) throws Exception {//打印老师对时间段的排序
		try {
			for(int i = 0; i < t.size(); i++) {//第i位老师
				Teacher iTeacher = t.get(i);
				LinkedList<Period> plist = iTeacher.getList();
				System.out.printf("%-5s", iTeacher.getName());
				for(int j = 0; j < plist.size(); j++) {//第j个被分配到的时间段
					Period jperiod = plist.get(j);
					int d = jperiod.getDay();
					int p = jperiod.getNumber();
					System.out.printf("%-9s", "(" + d + ", " + p + ")");
				}
				System.out.println();//换行	
			}
		}catch(Exception ex) {
			System.out.println("Error in Operation.printList!");
			System.out.println(ex.getMessage());
			System.exit(2);
		}
	}

	public static void printResult(LinkedList<Teacher> t) throws Exception {//打印老师被分配到的时间段
		try {
			for(int i = 0; i < t.size(); i++) {//第i位老师
				Teacher iteacher = t.get(i);
				LinkedList<Period> iresult = iteacher.getResult();
				System.out.printf("%-12s", iteacher.getName());
				for(int j = 0; j < iresult.size(); j++) {//第j个被分配到的时间段
					Period jperiod = iresult.get(j);
					int d = jperiod.getDay();
					int p = jperiod.getNumber();
					System.out.printf("%-8s", "(" + d + ", " + p + ")");
				}
				System.out.println();//换行		
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
			System.out.print(iT.getName() + " " + "落选次数：" + iT.getTimes() 
			+ " " + "未分配的时间段的数量：" + iT.getRemained() 
			+ "\n 已分配的时间段：");
			for(int j = 0; j < iT.getResult().size(); j++) {//第j个被分配到的时间段
				Period jperiod = iT.getResult().get(j);
				int d = jperiod.getDay();
				int p = jperiod.getNumber();
				System.out.printf("%-8s", "(" + d + ", " + p + ")");
			}
			System.out.println();//换行			
		}
	}
	
}
