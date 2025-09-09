package progetto.mp.santoro.daniele.utils;

import java.util.ArrayList;
import java.util.Collection;
import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.Servizio;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;
import progetto.mp.santoro.daniele.centroBenessere.Utente;

public final class FactoryCentroBenessere {
    
    private FactoryCentroBenessere() {}

    public static Servizio creaServizioSingolo(String nome, double prezzo, int durata) {
        return new ServizioSingolo(nome, prezzo, durata);
    }

    public static Servizio creaPacchettoServizi(String nome, Collection<Servizio> servizi) {
        return new PacchettoServizi(nome, new ArrayList<>(servizi));
    }

    public static Utente creaUtente(String nome) {
        return new Utente(nome, new ArrayList<>());
    }
}