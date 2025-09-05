package progetto.mp.santoro.daniele;

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
        return "[Prenotazione{" +
                "utente=" + utente.getNome() +
                ", servizio=" + servizio.getNome();
    }
}
