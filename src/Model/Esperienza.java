package Model;

import java.util.Date;

public class Esperienza {

	private Date data;
	private int exp;
	
	public Esperienza(Date data, int exp) {

		this.data = data;
		this.exp = exp;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public int getExp() {
		return exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	@Override
	public String toString() {
		return "Esperienza [Exp=" + ", data=" + data + ", exp=" + exp + "]";
	}
}
