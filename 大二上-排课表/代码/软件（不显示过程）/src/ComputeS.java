
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ComputeS {
	public static void computeS(int nD, int nP, LinkedList<Teacher> ts, HashSet<Period> set, HashMap<Period, Teacher> map) throws Exception{
		//给了老师组成的linkedlist，已被分配的时间段的集合set，输出分配结果的映射map
		try {
			LinkedList<Teacher> tempT = new LinkedList<>();//ts的副本
			HashSet<Teacher> teacherR = new HashSet<>(); //剩下的老师
			HashSet<Teacher> doneT = new HashSet<>();//分配完时间段的老师
			for(int i = 0; i < ts.size(); i++) {//把ts的数据复制到tempT和teacherR中
				tempT.add(ts.get(i));
				teacherR.add(ts.get(i));
			}
			Collections.shuffle(tempT);//打乱顺序
			for(int i = 0; i < ( nD * nP ) && !teacherR.isEmpty(); i++) {//第i轮检查剩余老师的第i + 1志愿
				//System.out.println("\n开始检查第" + (i + 1) + "志愿时，老师们的状态：");
				//OperationFile.printTeacherList(ts);
				LinkedList<Teacher> tempi = new LinkedList<>();//第i轮剩余老师构成的LinkedList
				tempi.addAll(teacherR);//把所有剩下的老师装进tempi
				HashMap<Period, LinkedList<Teacher>> round = new HashMap<>();//这一轮
				for(int j = 0; j < tempi.size(); j++) {//在第i志愿中老师们给出的时间段
					//remain中第j位老师的第i志愿
					Period jhope = tempi.get(j).getList().get(i);
					boolean had = false;//round中是否含有key jhope 
					Period pp = new Period(-1, -1);//目前的时间段
					for(Period tp: round.keySet()) {
						if((tp.getDay() == jhope.getDay()) && (tp.getNumber() == jhope.getNumber())) {
							had = true;	
							pp = tp;
						}	
					}
					if(had) {	
					round.get(pp).add(tempi.get(j));//把第j位老师压入这个时间段的列表中参与竞争
					}else {
						LinkedList<Teacher> newLinklist = new LinkedList<>();
						newLinklist.add(tempi.get(j));
						round.put(jhope, newLinklist);
					}
				}//for j 结尾
				//这样得到了这一轮有冲突的老师和对应的时间段
				HashSet<Period> keySet = new HashSet<>();//把这一轮hashmap中的key压入集合
				keySet.addAll(round.keySet());
				for(Period e: keySet) {//对keySet中的各个Period e
					LinkedList<Teacher> tempS = round.get(e);
					tempS.removeAll(doneT);
					LinkedList<Teacher> eTeacher = new LinkedList<>();
					eTeacher.addAll(tempS);		
					aRoundS(eTeacher, e, set, map);//处理第i轮关于Period e的争端
					//检查是否有老师已经分完课时
					for(Teacher teacher: eTeacher) {
						if(teacher.getRemained() == 0) {
							doneT.add(teacher);
							teacherR.remove(teacher);
						}
					}				
				}
			}// for i 结尾
		}catch(Exception ex) {
			System.out.println("Error in ComputeS.computeS!");
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}
	public static void aRoundS(LinkedList<Teacher> t, Period p, HashSet<Period> set, HashMap<Period, Teacher> map) throws Exception {
		//已被分配的时间段的集合set
		//输入有争端的老师的集合t，通过计算来分配那个老师们都想要的时间段p
		try {
			//检查p是否已被分配
			boolean contain = false;
			for(Period e: set) {
				if((e.getDay() == p.getDay()) && (e.getNumber() == p.getNumber())) {
					contain = true;
				}
			}
			if(contain) {//如果那个Period已被分配，每个老师的落选次数加一
				for(int i = 0; i < t.size(); i++) {
					t.get(i).setTimes(t.get(i).getTimes() + 1);
				}
				//System.out.print("该时间段已被分配：");
				//p.print();
			}else {//如果那个Period未被分配，则进行下一步
				//得到此时老师的数量
				int size = t.size();
				//检查落选次数，得到拥有最大落选次数的老师的列表linkedlist tList
				LinkedList<Teacher> tList = new LinkedList<>();
				Teacher tBiggest = t.getFirst();
				tList.add(tBiggest);
				int biggestTimes = t.getFirst().getTimes();//最大的落选次数
				for(int i = 1; i < size; i++) {//第一项已经取出来了，故从第二项开始
					Teacher temp = t.get(i);
					int tempTimes = temp.getTimes();
					if(tempTimes > biggestTimes) {
						tList.clear();
						tList.add(temp);
						tBiggest = temp;
						biggestTimes = tempTimes;
					}else if(tempTimes == biggestTimes) {
						tList.add(temp);
					}else {
						continue;
					}
				}
				//处理列表tList，把时间段分配给老师
				int listSize = tList.size();
				if(listSize == 1) {//有最大落选次数的老师只有一个
					//处理获胜者的数据
					Teacher winner = tList.getFirst();
					winner.getResult().add(p);
					winner.setRemained(winner.getRemained() - 1);
					map.put(p, winner);
					set.add(p);//表示时间段p已被分配
					//处理落选者的数据，对胜者和败者都进行处理
					winner.setTimes(winner.getTimes() - 1);//先减一就会和下一步的加一相抵消
					//每个人的落选次数加一，而胜利者的落选次数相当于没有变化
					for(int i = 0; i < size; i++) {
						t.get(i).setTimes(t.get(i).getTimes() + 1);
					}
					//System.out.println("此时有最大落选次数的只有一名老师：" + winner.getName());
					//System.out.print("这位老师得到");
					//p.print();
				}else {//有最大落选次数的老师不只一个
					//得到毫秒数mi，三位数
					Calendar cld = Calendar.getInstance();
					int mi = cld.get(Calendar.MILLISECOND);
					int whoWin = mi % listSize;//此时第whoWin位老师在这一轮获胜
					//弹出获胜者，处理获胜者的数据
					Teacher winner = tList.get(whoWin);
					winner.getResult().add(p);
					winner.setRemained(winner.getRemained() - 1);
					map.put(p, winner);
					set.add(p);//表示时间段p已被分配
					//处理落选者的数据，对胜者和败者都进行处理
					winner.setTimes(winner.getTimes() - 1);//先减一就会和下一步的加一相抵消
					//每个人的落选次数加一，而胜利者的落选次数相当于没有变化
					for(int i = 0; i < size; i++) {
						t.get(i).setTimes(t.get(i).getTimes() + 1);
					}
					//System.out.print("此时关于这个时间段的列表中的第" + whoWin + "位老师:" + winner.getName() + "获胜。" );
					//System.out.print("这位老师得到");
					//p.print();
				}
			}
		}catch(Exception ex) {
			System.out.println("Error in ComputeS.aRoundS!");
			System.out.println(ex.getMessage());
			System.exit(3);
		}
	}
}
