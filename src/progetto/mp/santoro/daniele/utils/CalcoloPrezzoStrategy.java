package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Utente;

public interface CalcoloPrezzoStrategy {
    double calcolaPrezzo(double prezzoBase, Utente utente);
}