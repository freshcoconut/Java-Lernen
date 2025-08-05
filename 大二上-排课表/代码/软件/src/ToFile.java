
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class ToFile {
	//���ڴ�txt�ļ��ж�ȡ��Ϣ
	public static TDP getTS(String location) throws Exception {
		//��ʦ��ɵ�LinkedList
		LinkedList<Teacher> ts = new LinkedList<>();
		int nD = 0;
		int nP = 0;
		try {
			File file = new File(location);
			Scanner input = new Scanner(file, "UTF-8");//�ҵ��ļ���׼����ȡ��Ϣ
			System.out.println("�ҵ��ļ�");
			//ǰ��������ע�ͣ�����ȡ
			for(int i = 0; i < 3; i++) {
				input.nextLine();
			}
			//�ӵ����п�ʼ��
			input.next();//�������м�����ʦ����
			int nTeachers = input.nextInt();//��ȡ��ʦ����
			input.next();//��������ʦ�ϼ��ڿΣ���
			int nClasses = input.nextInt();//��ȡһλ��ʦҪ�ϼ��ڿ�
			input.next();//������һ��Ҫ�ϼ���Σ���
			nD = input.nextInt();//��ȡһ��Ҫ�Ͽε�����
			input.next();//������һ���м��ڿΣ���
			nP = input.nextInt();//��ȡÿ��Ŀ���
			input.nextLine();//����������
			input.nextLine();//���������У���������Ϊע��
			//��ʼ¼����ʦ����
			int indexT = 0;//indexOfTeacher
			while(input.next().equals("[") && indexT < nTeachers) {
				//int index = 0;//����ȷ��ָ��λ��
				String namei = input.next();//����ʦ����
				int[] Di = new int[nD];
				int[] Pi = new int[nP];
				input.next();//����������Di��Ķ���
				for(int i = 0; i < nD; i++) {//��Di
					Di[i] = input.nextInt();
				}
				input.next();//����Di��Pi��Ķ���
				for(int i = 0; i < nP; i++) {//��Pi
					Pi[i] = input.nextInt();				
				}
				input.next();//����Pi��Ai��Ķ���
				int Ai = input.nextInt();//��Ai
				input.next();//����Ai��ai��Ķ���

				int dai = input.nextInt();//��ai
				int pai = input.nextInt();
				Period ai = new Period(dai, pai);
				input.next();//����ai��bi��Ķ���
				int dbi = input.nextInt();//��bi
				int pbi = input.nextInt();
				Period bi = new Period(dbi, pbi);
				input.next();//����bi��ci��Ķ���
				int dci = input.nextInt();
				int pci = input.nextInt();
				Period ci = new Period(dci, pci);
				ts.add(new Teacher(namei, nClasses, Di, Pi, Ai, ai, bi, ci));
				String next = input.next();//��ȡ��һ�н�β���ַ���
				if(next.equals("]")) {
					indexT++;
					input.nextLine();//������һ�У���ȡ��һ����ʦ����Ϣ
				}else if(next.equals("]666")) {
					input.close();
					break;
				}else {//�����������
					input.close();
					throw new Exception("ToFile��whileѭ������");
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
