package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;

public class VisitorReport implements VisitorServizio {
    
    private final StringBuilder report = new StringBuilder();
    private int livelloIndentazione = 0;

    @Override
    public void visitaServizioSingolo(ServizioSingolo servizio) {
        report.append("  ".repeat(livelloIndentazione))
              .append("- ")
              .append(servizio.getNome())
              .append(" | Prezzo: ").append(servizio.getPrezzo()).append(" euro")
              .append(" | Durata: ").append(servizio.getDurata()).append(" min\n");
    }

    @Override
    public void visitaPacchettoServizi(PacchettoServizi pacchetto) {
        report.append("  ".repeat(livelloIndentazione))
              .append("- Pacchetto: ").append(pacchetto.getNome()).append("\n");
        
        livelloIndentazione++;
        pacchetto.getServizi().forEach(servizio -> servizio.accetta(this));
        livelloIndentazione--;
        
        report.append("  ".repeat(livelloIndentazione))
              .append("Totale Pacchetto: ").append(pacchetto.getPrezzo()).append(" euro")
              .append(" | Durata: ").append(pacchetto.getDurata()).append(" min\n");
    }

    @Override
    public void visitaDecoratorScontato(DecoratorServizioScontato decoratore) {
        report.append("  ".repeat(livelloIndentazione))
              .append("- ")
              .append(decoratore.getNome())
              .append(" | Prezzo: ").append(decoratore.getPrezzo()).append(" euro") 
              .append(" | Durata: ").append(decoratore.getDurata()).append(" min\n");
    }

    @Override
    public void visitaDecoratorConSupplemento(DecoratorServizioConSupplemento decoratore) {
        report.append("  ".repeat(livelloIndentazione))
              .append("- ")
              .append(decoratore.getNome()) 
              .append(" | Prezzo: ").append(decoratore.getPrezzo()).append(" euro") 
              .append(" | Durata: ").append(decoratore.getDurata()).append(" min\n");
    }

	public String getReport() {
        return report.toString();
    }

}