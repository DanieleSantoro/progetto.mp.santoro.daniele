package progetto.mp.santoro.daniele;

import java.util.Collection;

public class Utente {
	
	private String nome;
	private Collection<Servizio> serviziPrenotati;
	private CalcoloPrezzoStrategy strategiaPrezzo;
	
	public Utente(String nome, Collection<Servizio> serviziPrenotati, CalcoloPrezzoStrategy strategiaPrezzo) {
		this.nome = nome;
		this.serviziPrenotati = serviziPrenotati;
		this.strategiaPrezzo = strategiaPrezzo;
	}
	
	public void prenotaServizio(Servizio servizio) {
		servizio.prenota(this);
		serviziPrenotati.add(servizio);
	}
	
	public double calcolaPrezzoTotale() {
		double prezzoBase = serviziPrenotati.stream().mapToDouble(Servizio::getPrezzo).sum();
		return strategiaPrezzo.calcolaPrezzo(prezzoBase, this);
	}
	
	public int getNumeroServizi() {
		return serviziPrenotati.size();
	}
	
	public Collection<Servizio> getServiziPrenotati() {
		return serviziPrenotati;
	}
	
	public String getNome() {
		return nome;
	}
	
}