package progetto.mp.santoro.daniele;

import java.util.Collection;

public class ServizioSingolo implements Servizio {

	private final String nome;
	private final double prezzo;
	private final int durata;
	private final Collection<Prenotazione> prenotazioni;
	private final PrenotazioneFactory factory;

	public ServizioSingolo(String nome, double prezzo, int durata, Collection<Prenotazione> prenotazioni,
			PrenotazioneFactory factory) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.durata = durata;
		this.prenotazioni = prenotazioni;
		this.factory = factory;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public int getDurata() {
		return durata;
	}

	public Collection<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	@Override
	public void prenota(Utente utente) {
		Prenotazione prenotazione = factory.crea(utente, this);
		prenotazioni.add(prenotazione);
	}

	@Override
	public String mostraDettagli() {
		return nome + " | Prezzo: " + prezzo + " euro | Durata: " + durata + " min";
	}

	@Override
	public void accetta(ServizioVisitor visitor) {
		visitor.visitaServizio(this);
	}

}