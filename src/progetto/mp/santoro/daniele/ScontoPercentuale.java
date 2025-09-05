package progetto.mp.santoro.daniele;

public class ScontoPercentuale implements CalcoloPrezzoStrategy {

	private final double percentuale;

	public ScontoPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}

	@Override
	public double calcolaPrezzo(double prezzoBase, Utente utente) {
		return prezzoBase * (1 - percentuale);
	}

}
