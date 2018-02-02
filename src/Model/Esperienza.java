package Model;

import java.util.Date;

public class Esperienza {

	private int livello;
	private Date data;
	private int exp;
	
	public Esperienza(int livello, Date data, int exp) {

		this.livello = livello;
		this.data = data;
		this.exp = exp;
	}

	public int getLivello() {
		return livello;
	}
	
	public void setLivello(int livello) {
		this.livello = livello;
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
		return "Esperienza [livello=" + livello + ", data=" + data + ", exp=" + exp + "]";
	}
}
