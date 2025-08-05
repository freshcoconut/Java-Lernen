
import java.util.LinkedList;

public class Teacher {
	private String name;//��ʦ������
	private int times;//��ʦ��ѡ�Ĵ���
	private LinkedList<Period> list;//��ʦ�Ը���ʱ��ε�����
	private LinkedList<Period> result;//��ʦ�ѷ��䵽��ʱ��εļ���
	private int remained;//δ����Ŀ�ʱ��
	
	public Teacher() {
		this.name = "default";
		this.times = 0;
		this.remained = 0;
		this.list = new LinkedList<>();
		this.result = new LinkedList<>();
	}
	public Teacher(String n, int r, LinkedList<Period> Qi) {
		this.name = n;
		this.times = 0;
		this.remained = r;
		this.list = Qi;
		this.result = new LinkedList<>();
	}
	public Teacher(String n, int r, int[] Di, int[] Pi, int Ai, Period ai, Period bi, Period ci) throws Exception {
		//n��������r����ʦδ����Ŀ�ʱ����Di����ʦ�����ڵ�����Pi����ʦ�Կ��������
		//Ai����ʦ��D��P���ӳ̶ȵıȽϣ�bi����ʦ�ĵڶ���ѡ�ci����ʦ�ĵ�����ѡ��
		this.name = n;
		this.times = 0;
		this.remained = r;
		try {
			this.list = Mark.giveQi(Di, Pi, Ai, ai, bi, ci);
		}catch(Exception ex) {
			System.out.println("Error in Teacher�вι��췽����this.list = Mark.giveQi");
		}
		this.result = new LinkedList<>();
	}
	public String getName() {
		return this.name;
	}
	public void setName(String s) {
		this.name = s;
	}
	public int getTimes() {
		return this.times;
	}
	public void setTimes(int t) {
		this.times = t;
	}
	public LinkedList<Period> getList(){//��ʦ��ʱ��ε�����
		return this.list;		
	}
	public void setList(LinkedList<Period> t) {
		this.list = t;
	}
	public LinkedList<Period> getResult(){
		return this.result;
	}
	public void setResult(LinkedList<Period> t) {
		this.result = t;
	}
	public int getRemained() {
		return this.remained;
	}
	public void setRemained(int t) {
		this.remained = t;
	}
	public void print() {
		System.out.print(this.name + "  ");
		System.out.print("��ѡ������" + this.times + "  ");
		System.out.print("δ����Ŀ�ʱ����" + this.remained + "  ");
		System.out.println("�ѷ���Ŀ�ʱ��");
		for(Period p: this.result) {
			p.print();
		}
	}
}
