package progetto.mp.santoro.daniele;

public interface ServizioVisitor {
	
	void visitaServizio(ServizioSingolo servizio);

	void visitaPacchetto(PacchettoServizi pacchetto);
}
