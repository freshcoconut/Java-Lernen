
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ComputeS {
	public static void computeS(int nD, int nP, LinkedList<Teacher> ts, HashSet<Period> set, HashMap<Period, Teacher> map) throws Exception{
		//������ʦ��ɵ�linkedlist���ѱ������ʱ��εļ���set�������������ӳ��map
		try {
			LinkedList<Teacher> tempT = new LinkedList<>();//ts�ĸ���
			HashSet<Teacher> teacherR = new HashSet<>(); //ʣ�µ���ʦ
			HashSet<Teacher> doneT = new HashSet<>();//������ʱ��ε���ʦ
			for(int i = 0; i < ts.size(); i++) {//��ts�����ݸ��Ƶ�tempT��teacherR��
				tempT.add(ts.get(i));
				teacherR.add(ts.get(i));
			}
			Collections.shuffle(tempT);//����˳��
			for(int i = 0; i < ( nD * nP ) && !teacherR.isEmpty(); i++) {//��i�ּ��ʣ����ʦ�ĵ�i + 1־Ը
				//System.out.println("\n��ʼ����" + (i + 1) + "־Ըʱ����ʦ�ǵ�״̬��");
				//OperationFile.printTeacherList(ts);
				LinkedList<Teacher> tempi = new LinkedList<>();//��i��ʣ����ʦ���ɵ�LinkedList
				tempi.addAll(teacherR);//������ʣ�µ���ʦװ��tempi
				HashMap<Period, LinkedList<Teacher>> round = new HashMap<>();//��һ��
				for(int j = 0; j < tempi.size(); j++) {//�ڵ�i־Ը����ʦ�Ǹ�����ʱ���
					//remain�е�jλ��ʦ�ĵ�i־Ը
					Period jhope = tempi.get(j).getList().get(i);
					boolean had = false;//round���Ƿ���key jhope 
					Period pp = new Period(-1, -1);//Ŀǰ��ʱ���
					for(Period tp: round.keySet()) {
						if((tp.getDay() == jhope.getDay()) && (tp.getNumber() == jhope.getNumber())) {
							had = true;	
							pp = tp;
						}	
					}
					if(had) {	
					round.get(pp).add(tempi.get(j));//�ѵ�jλ��ʦѹ�����ʱ��ε��б��в��뾺��
					}else {
						LinkedList<Teacher> newLinklist = new LinkedList<>();
						newLinklist.add(tempi.get(j));
						round.put(jhope, newLinklist);
					}
				}//for j ��β
				//�����õ�����һ���г�ͻ����ʦ�Ͷ�Ӧ��ʱ���
				HashSet<Period> keySet = new HashSet<>();//����һ��hashmap�е�keyѹ�뼯��
				keySet.addAll(round.keySet());
				for(Period e: keySet) {//��keySet�еĸ���Period e
					LinkedList<Teacher> tempS = round.get(e);
					tempS.removeAll(doneT);
					LinkedList<Teacher> eTeacher = new LinkedList<>();
					eTeacher.addAll(tempS);		
					aRoundS(eTeacher, e, set, map);//�����i�ֹ���Period e������
					//����Ƿ�����ʦ�Ѿ������ʱ
					for(Teacher teacher: eTeacher) {
						if(teacher.getRemained() == 0) {
							doneT.add(teacher);
							teacherR.remove(teacher);
						}
					}				
				}
			}// for i ��β
		}catch(Exception ex) {
			System.out.println("Error in ComputeS.computeS!");
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}
	public static void aRoundS(LinkedList<Teacher> t, Period p, HashSet<Period> set, HashMap<Period, Teacher> map) throws Exception {
		//�ѱ������ʱ��εļ���set
		//���������˵���ʦ�ļ���t��ͨ�������������Ǹ���ʦ�Ƕ���Ҫ��ʱ���p
		try {
			//���p�Ƿ��ѱ�����
			boolean contain = false;
			for(Period e: set) {
				if((e.getDay() == p.getDay()) && (e.getNumber() == p.getNumber())) {
					contain = true;
				}
			}
			if(contain) {//����Ǹ�Period�ѱ����䣬ÿ����ʦ����ѡ������һ
				for(int i = 0; i < t.size(); i++) {
					t.get(i).setTimes(t.get(i).getTimes() + 1);
				}
				//System.out.print("��ʱ����ѱ����䣺");
				//p.print();
			}else {//����Ǹ�Periodδ�����䣬�������һ��
				//�õ���ʱ��ʦ������
				int size = t.size();
				//�����ѡ�������õ�ӵ�������ѡ��������ʦ���б�linkedlist tList
				LinkedList<Teacher> tList = new LinkedList<>();
				Teacher tBiggest = t.getFirst();
				tList.add(tBiggest);
				int biggestTimes = t.getFirst().getTimes();//������ѡ����
				for(int i = 1; i < size; i++) {//��һ���Ѿ�ȡ�����ˣ��ʴӵڶ��ʼ
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
				//�����б�tList����ʱ��η������ʦ
				int listSize = tList.size();
				if(listSize == 1) {//�������ѡ��������ʦֻ��һ��
					//�����ʤ�ߵ�����
					Teacher winner = tList.getFirst();
					winner.getResult().add(p);
					winner.setRemained(winner.getRemained() - 1);
					map.put(p, winner);
					set.add(p);//��ʾʱ���p�ѱ�����
					//������ѡ�ߵ����ݣ���ʤ�ߺͰ��߶����д���
					winner.setTimes(winner.getTimes() - 1);//�ȼ�һ�ͻ����һ���ļ�һ�����
					//ÿ���˵���ѡ������һ����ʤ���ߵ���ѡ�����൱��û�б仯
					for(int i = 0; i < size; i++) {
						t.get(i).setTimes(t.get(i).getTimes() + 1);
					}
					//System.out.println("��ʱ�������ѡ������ֻ��һ����ʦ��" + winner.getName());
					//System.out.print("��λ��ʦ�õ�");
					//p.print();
				}else {//�������ѡ��������ʦ��ֻһ��
					//�õ�������mi����λ��
					Calendar cld = Calendar.getInstance();
					int mi = cld.get(Calendar.MILLISECOND);
					int whoWin = mi % listSize;//��ʱ��whoWinλ��ʦ����һ�ֻ�ʤ
					//������ʤ�ߣ������ʤ�ߵ�����
					Teacher winner = tList.get(whoWin);
					winner.getResult().add(p);
					winner.setRemained(winner.getRemained() - 1);
					map.put(p, winner);
					set.add(p);//��ʾʱ���p�ѱ�����
					//������ѡ�ߵ����ݣ���ʤ�ߺͰ��߶����д���
					winner.setTimes(winner.getTimes() - 1);//�ȼ�һ�ͻ����һ���ļ�һ�����
					//ÿ���˵���ѡ������һ����ʤ���ߵ���ѡ�����൱��û�б仯
					for(int i = 0; i < size; i++) {
						t.get(i).setTimes(t.get(i).getTimes() + 1);
					}
					//System.out.print("��ʱ�������ʱ��ε��б��еĵ�" + whoWin + "λ��ʦ:" + winner.getName() + "��ʤ��" );
					//System.out.print("��λ��ʦ�õ�");
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
