package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;

public class VisitorTitoli implements VisitorServizio {
    private final StringBuilder titoli = new StringBuilder();

    @Override
    public void visitaServizioSingolo(ServizioSingolo servizio) {
        titoli.append("- ").append(servizio.getNome()).append("\n");
    }
    
    @Override
    public void visitaPacchettoServizi(PacchettoServizi pacchetto) {
        titoli.append("[Pacchetto: ").append(pacchetto.getNome()).append("]\n");
        pacchetto.getServizi().forEach(servizio -> servizio.accetta(this));
    }
    
    @Override
    public void visitaDecoratorScontato(DecoratorServizioScontato decoratore) {
        titoli.append("- ").append(decoratore.getNome()).append("\n");
    }
    
    @Override
    public void visitaDecoratorConSupplemento(DecoratorServizioConSupplemento decoratore) {
        titoli.append("- ").append(decoratore.getNome()).append("\n");
    }

    public String getTitoli() {
        return titoli.toString();
    }

}
