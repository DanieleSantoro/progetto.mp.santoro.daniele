package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Collection;

public class Utente {

	private final String nome;
	private final Collection<Servizio> serviziPrenotati;

	public Utente(String nome, Collection<Servizio> serviziPrenotati) {
		this.nome = nome;
		this.serviziPrenotati = serviziPrenotati;
	}

	public void prenotaServizio(Servizio servizio) {
		serviziPrenotati.add(servizio);
		servizio.prenota(this);
	}

//	public Collection<Servizio> getServiziPrenotati() {
//		return serviziPrenotati;
//	}

	public int getNumeroServizi() {
		return serviziPrenotati.size();
	}

	public String getNome() {
		return nome;
	}

}