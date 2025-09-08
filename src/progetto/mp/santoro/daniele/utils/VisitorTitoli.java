package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;

public class VisitorTitoli implements ServizioVisitor {
    private final StringBuilder titoli;

    public VisitorTitoli(StringBuilder titoli) { 
        this.titoli = titoli; 
    }

    @Override
    public void visitaServizio(ServizioSingolo servizio) {
        titoli.append("- ").append(servizio.getNome()).append("\n");
    }

    @Override
    public void visitaPacchetto(PacchettoServizi pacchetto) {
        titoli.append("[Pacchetto: ").append(pacchetto.getNome()).append("]\n");        
        pacchetto.visitaFigli(this);
    }

    public String getTitoli() { 
        return titoli.toString(); 
    }
}