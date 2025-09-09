package progetto.mp.santoro.daniele.centroBenessere;

import java.util.Objects;

public class Prenotazione {
	private final Utente utente;
	private final Servizio servizio;

	private Prenotazione(Utente utente, Servizio servizio) {
		this.utente = utente;
		this.servizio = servizio;
	}

	public static Prenotazione crea(Utente utente, Servizio servizio) {
		return new Prenotazione(utente, servizio);
	}
	
	public Utente getUtente() {
		return utente;
	}

	public Servizio getServizio() {
		return servizio;
	}

	@Override
	public String toString() {
		return "Prenotazione{utente=" + utente.getNome() + ", servizio=" + servizio.getNome() + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Prenotazione that = (Prenotazione) o;
		return Objects.equals(utente, that.utente) && Objects.equals(servizio, that.servizio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(utente, servizio);
	}
}