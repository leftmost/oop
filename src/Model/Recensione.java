package Model;

public class Recensione {

	private String utente_username;
	private int gioco_id;
	private boolean approvazione;
	private int voto;
	private String recensione;
	
	public Recensione(String utente_username, int gioco_id, int voto, String recensione) {
		
		this.utente_username = utente_username;
		this.gioco_id = gioco_id;
		this.recensione = recensione;
		this.voto = voto;
	}

	public String getUtente_username() {
		return utente_username;
	}

	public void setUtente_username(String utente_username) {
		this.utente_username = utente_username;
	}

	public int getGioco_id() {
		return gioco_id;
	}

	public void setGioco_id(int gioco_id) {
		this.gioco_id = gioco_id;
	}

	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	public boolean isApprovazione() {
		return approvazione;
	}

	public void setApprovazione(boolean approvazione) {
		this.approvazione = approvazione;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "Recensione [Username=" + utente_username + ", gioco_id=" + gioco_id + ", recensione="
				+ recensione + "]";
	}
	
	
	
	
	
	
	
}
