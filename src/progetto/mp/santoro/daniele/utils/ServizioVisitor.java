package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.PacchettoServizi;
import progetto.mp.santoro.daniele.centroBenessere.ServizioSingolo;

public interface ServizioVisitor {
	
	void visitaServizio(ServizioSingolo servizio);

	void visitaPacchetto(PacchettoServizi pacchetto);
}
