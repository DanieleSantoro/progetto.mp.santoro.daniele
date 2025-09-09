package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;

public interface VisitorServizio {
    
    void visitaServizioSingolo(ServizioSingolo servizio);

    void visitaPacchettoServizi(PacchettoServizi pacchetto);
    
    void visitaDecoratorScontato(DecoratorServizioScontato decoratore);
    
    void visitaDecoratorConSupplemento(DecoratorServizioConSupplemento decoratore);
}