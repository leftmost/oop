/**
 * @(#) UTENTE.java
 */

package Model;

public class Utente
{
	private String username;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private String tipologia;
	private Timeline timeline;

	public Utente(String username, String email, String password, String nome, String cognome, String tipologia){
		this.username = username;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.tipologia = tipologia;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	  
	
	public int getEsperienza() {
		return timeline.lastExp().getExp();
	}
	
	public Timeline getTimeline() {
		return this.timeline;
	}
	
	  @Override
	public String toString() {
		return "Utente [username=" + username + ", email=" + email + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", tipologia=" + tipologia + ", timeline=" + timeline + "]";
	}

	@Override
	  public boolean equals(Object obj){
	    Utente utente;
	    if( obj instanceof Utente )
	        utente = (Utente) obj;
	    else
		return false;
		
	    if( this.username.equals(utente.getUsername()) || this.email.equals(utente.getEmail()) )
		return true;
	    return false; 
	  }

	
}
