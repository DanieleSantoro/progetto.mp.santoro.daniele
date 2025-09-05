package progetto.mp.santoro.daniele;

import java.util.Collection;

public class PacchettoServizi implements Servizio {

	private final String nome;
	private final Collection<Servizio> servizi;

	public PacchettoServizi(String nome, Collection<Servizio> servizi) {
		this.nome = nome;
		this.servizi = servizi;
	}

	// visibilità dei metodi
	public void aggiungiServizio(Servizio servizio) {
		servizi.add(servizio);
	}

	public void rimuoviServizio(Servizio servizio) {
		servizi.remove(servizio);
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public double getPrezzo() {
		return servizi.stream().mapToDouble(Servizio::getPrezzo).sum();
	}

	@Override
	public int getDurata() {
		return servizi.stream().mapToInt(Servizio::getDurata).sum();
	}

	@Override
	public void prenota(Utente utente) {		
		servizi.forEach(s -> s.prenota(utente));
	}

	@Override
	public String mostraDettagli() {
	    String dettagliServizi = servizi.stream()
	        .map(s -> " - " + s.getNome() 
	                + " | Prezzo: " + s.getPrezzo() 
	                + " euro | Durata: " + s.getDurata() + " min")
	        .reduce("", (a, b) -> a + "\n" + b);

	    return "Pacchetto: " + nome
	           + dettagliServizi
	           + "\nTotale Prezzo: " + getPrezzo() 
	           + " euro, Durata totale: " + getDurata() + " min";
	}


}
