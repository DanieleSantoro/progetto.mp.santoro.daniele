package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Prenotazione;
import progetto.mp.santoro.daniele.centroBenessere.Servizio;
import progetto.mp.santoro.daniele.centroBenessere.Utente;

public class PrenotazioneFactory {
    public Prenotazione crea(Utente utente, Servizio servizio) {
        return new Prenotazione(utente, servizio);
    }
}