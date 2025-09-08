package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Collection;

import progetto.mp.santoro.daniele.utils.PrenotazioneFactory;
import progetto.mp.santoro.daniele.utils.ServizioVisitor;

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

	@Override
	public void prenota(Utente utente) {
		prenotazioni.add(factory.crea(utente, this));
	}

	@Override
	public String mostraDettagli(int livello, boolean mostraTotale) {
		String indent = "  ".repeat(livello);
		return indent + "- " + nome + " | Prezzo: " + prezzo + " euro | Durata: " + durata + " min";
	}

	@Override
	public void accetta(ServizioVisitor visitor) {
		visitor.visitaServizio(this);
	}

}