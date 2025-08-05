
import java.util.LinkedList;

public class Teacher {
	private String name;//老师的姓名
	private int times;//老师落选的次数
	private LinkedList<Period> list;//老师对各个时间段的排序
	private LinkedList<Period> result;//老师已分配到的时间段的集合
	private int remained;//未分配的课时数
	
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
		//n是姓名，r是老师未分配的课时数，Di是老师对日期的排序，Pi是老师对课序的排序
		//Ai是老师对D与P重视程度的比较，bi是老师的第二候选项，ci是老师的第三候选项
		this.name = n;
		this.times = 0;
		this.remained = r;
		try {
			this.list = Mark.giveQi(Di, Pi, Ai, ai, bi, ci);
		}catch(Exception ex) {
			System.out.println("Error in Teacher有参构造方法的this.list = Mark.giveQi");
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
	public LinkedList<Period> getList(){//老师对时间段的排序
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
		System.out.print("落选次数：" + this.times + "  ");
		System.out.print("未分配的课时数：" + this.remained + "  ");
		System.out.println("已分配的课时：");
		for(Period p: this.result) {
			p.print();
		}
	}
}
