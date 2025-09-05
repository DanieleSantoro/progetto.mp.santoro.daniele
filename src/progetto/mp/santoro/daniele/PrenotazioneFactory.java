package progetto.mp.santoro.daniele;

public class PrenotazioneFactory {
    public Prenotazione crea(Utente utente, Servizio servizio) {
        return new Prenotazione(utente, servizio);
    }
}
