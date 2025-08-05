
import java.util.LinkedList;

public class TDP {
	private LinkedList<Teacher> ts;
	private int nd;
	private int np;
	
	public TDP() {
		this.ts = new LinkedList<>();
		this.nd = 0;
		this.np = 0;
	}
	public TDP(LinkedList<Teacher> t, int d, int p) {
		this.ts = t;
		this.nd = d;
		this.np = p;
	}
	
	public LinkedList<Teacher> getTS() {
		return this.ts;
	}
	public void setTS(LinkedList<Teacher> temp) {
		this.ts = temp;
	}
	public int getnD() {
		return this.nd;
	}
	public void setnD(int temp) {
		this.nd = temp;
	}
	public int getnP() {
		return this.np;
	}
	public void setnPTS(int temp) {
		this.np = temp;
	}
	
}
