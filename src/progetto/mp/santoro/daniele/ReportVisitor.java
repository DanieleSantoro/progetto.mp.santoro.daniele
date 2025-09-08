package progetto.mp.santoro.daniele;

public class ReportVisitor implements ServizioVisitor {

	private final StringBuilder report;
	
	public ReportVisitor(StringBuilder report) {
		this.report = report;
	}

	@Override
	public void visitaServizio(ServizioSingolo servizio) {
		report.append(servizio.mostraDettagli()).append("\n");
	}

	@Override
	public void visitaPacchetto(PacchettoServizi pacchetto) {
		report.append("Pacchetto: ").append(pacchetto.getNome()).append("\n");
		pacchetto.getServizi().forEach(s -> s.accetta(this));
		report.append("Totale: ").append(pacchetto.getPrezzo())
			  .append(" euro | Durata: ").append(pacchetto.getDurata())
			  .append(" min\n");
	}
	
	public String getReport() {
		return report.toString();
	}
	
	
	
}
