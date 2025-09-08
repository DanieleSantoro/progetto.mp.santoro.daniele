package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;
import progetto.mp.santoro.daniele.centroBenessere.Utente;

public class CalcolatricePrezzo {

    private final CalcoloPrezzoStrategy strategia;

    public CalcolatricePrezzo(CalcoloPrezzoStrategy strategia) {
        this.strategia = strategia;
    }

    public double calcolaPrezzo(Servizio servizio, Utente utente) {
        return strategia.calcolaPrezzo(servizio.getPrezzo(), utente);
    }
}