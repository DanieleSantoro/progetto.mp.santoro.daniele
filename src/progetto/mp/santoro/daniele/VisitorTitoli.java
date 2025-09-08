package progetto.mp.santoro.daniele;

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
		pacchetto.getServizi().forEach(s -> s.accetta(this));
	}

	public String getTitoli() {
		return titoli.toString();
	}

}
