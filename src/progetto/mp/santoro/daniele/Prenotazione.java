package progetto.mp.santoro.daniele;

import java.util.Objects;

public class Prenotazione {
	private final Utente utente;
	private final Servizio servizio;

	public Prenotazione(Utente utente, Servizio servizio) {
		this.utente = utente;
		this.servizio = servizio;
	}

	public Utente getUtente() {
		return utente;
	}

	public Servizio getServizio() {
		return servizio;
	}

	@Override
	public String toString() {
		return "Prenotazione{utente=" + utente.getNome()
        + ", servizio=" + servizio.mostraDettagli()
        + "}";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenotazione)) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(utente, that.utente) &&
               Objects.equals(servizio, that.servizio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utente, servizio);
    }
}
