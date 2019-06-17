package Tutorial.essential.reflection;

public class Bean extends SuperBean{
	private int num;
	public int pNum;

	public int getNum() {
		return num;
	}

	private int getNumP() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
