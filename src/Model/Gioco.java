package Model;

public class Gioco {
	
	private int id;
	private String titolo;
	private int exp;
	private int valutazione;
	
	public Gioco() {}
	
	public Gioco(String titolo, int exp) {
		
		this.titolo = titolo;
		this.exp = exp;
	}
	
	public Gioco(int id, String titolo, int exp) {
		
		this.id = id;
		this.titolo = titolo;
		this.exp = exp;
	}

	public Gioco(int id, String titolo, int exp, int valutazione) {
	
		this.id = id;
		this.titolo = titolo;
		this.exp = exp;
		this.valutazione = valutazione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	@Override
	public String toString() {
		return "Gioco [id=" + id + ", titolo=" + titolo + ", exp=" + exp + ", valutazione=" + valutazione + "]";
	}
	
	
	
	

}
