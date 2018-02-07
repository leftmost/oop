package Model;

public class Gioco {
	
	private int id;
	private String titolo;
	private int valutazione;
	
	public Gioco() {}
	
	
	public Gioco(int id, String titolo) {
		
		this.id = id;
		this.titolo = titolo;
		
	}

	public Gioco(int id, String titolo, int valutazione) {
	
		this.id = id;
		this.titolo = titolo;
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

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	@Override
	public String toString() {
		return "Gioco [id=" + id + ", titolo=" + titolo + ", valutazione=" + valutazione + "]";
	}
	
	
	
	

}
