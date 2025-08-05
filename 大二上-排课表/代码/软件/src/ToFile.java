
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class ToFile {
	//用于从txt文件中读取信息
	public static TDP getTS(String location) throws Exception {
		//老师组成的LinkedList
		LinkedList<Teacher> ts = new LinkedList<>();
		int nD = 0;
		int nP = 0;
		try {
			File file = new File(location);
			Scanner input = new Scanner(file, "UTF-8");//找到文件，准备读取信息
			System.out.println("找到文件");
			//前三行用于注释，不读取
			for(int i = 0; i < 3; i++) {
				input.nextLine();
			}
			//从第四行开始读
			input.next();//跳过“有几个老师：”
			int nTeachers = input.nextInt();//读取老师数量
			input.next();//跳过“老师上几节课：”
			int nClasses = input.nextInt();//读取一位老师要上几节课
			input.next();//跳过“一周要上几天课：”
			nD = input.nextInt();//读取一周要上课的天数
			input.next();//跳过“一天有几节课：”
			nP = input.nextInt();//读取每天的课数
			input.nextLine();//换到第五行
			input.nextLine();//跳过第五行，第五行作为注释
			//开始录入老师数据
			int indexT = 0;//indexOfTeacher
			while(input.next().equals("[") && indexT < nTeachers) {
				//int index = 0;//用于确定指针位置
				String namei = input.next();//读老师姓名
				int[] Di = new int[nD];
				int[] Pi = new int[nP];
				input.next();//跳过姓名与Di间的逗号
				for(int i = 0; i < nD; i++) {//读Di
					Di[i] = input.nextInt();
				}
				input.next();//跳过Di与Pi间的逗号
				for(int i = 0; i < nP; i++) {//读Pi
					Pi[i] = input.nextInt();				
				}
				input.next();//跳过Pi与Ai间的逗号
				int Ai = input.nextInt();//读Ai
				input.next();//跳过Ai和ai间的逗号

				int dai = input.nextInt();//读ai
				int pai = input.nextInt();
				Period ai = new Period(dai, pai);
				input.next();//跳过ai和bi间的逗号
				int dbi = input.nextInt();//读bi
				int pbi = input.nextInt();
				Period bi = new Period(dbi, pbi);
				input.next();//跳过bi和ci间的逗号
				int dci = input.nextInt();
				int pci = input.nextInt();
				Period ci = new Period(dci, pci);
				ts.add(new Teacher(namei, nClasses, Di, Pi, Ai, ai, bi, ci));
				String next = input.next();//读取这一行结尾的字符串
				if(next.equals("]")) {
					indexT++;
					input.nextLine();//换到下一行，读取下一个老师的信息
				}else if(next.equals("]666")) {
					input.close();
					break;
				}else {//不是这种情况
					input.close();
					throw new Exception("ToFile的while循环出错！");
				}		
			}
		}catch(Exception ex) {
			System.out.println("Error in ToFile.getTS!");
			System.out.println(ex.getMessage());
			System.exit(0);
		}
		
		return new TDP(ts, nD, nP);
	}
}
