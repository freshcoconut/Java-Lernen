
public class Period {
//��ʾ�Ͽ�ʱ���
	private int day;//�γ�����������������
	private int number;//�γ��ڵڼ��ڿΡ�������
	
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
