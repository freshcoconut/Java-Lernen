
public class Period {
//表示上课时间段
	private int day;//课程所在天数——日期
	private int number;//课程在第几节课——课序
	
	public Period() {
		this.day = -1;
		this.number = -1;
	}
	public Period(int d, int n) {
		this.day = d;
		this.number = n;
	}
	public int getDay() {
		return this.day;
	}
	public void setDay(int t) {
		this.day = t;
	}
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int t) {
		this.number = t;		
	}
	public void print() {
		System.out.println("(" + day + ", " + number + ")");
	}
}
