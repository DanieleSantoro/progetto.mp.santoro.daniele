package progetto.mp.santoro.daniele.centroBenessere;

import progetto.mp.santoro.daniele.utils.VisitorServizio;

public interface Servizio {

    String getNome();

    double getPrezzo();

    int getDurata();
    
    void accetta(VisitorServizio visitor);
}