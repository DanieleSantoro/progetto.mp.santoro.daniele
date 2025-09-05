package progetto.mp.santoro.daniele;

public class ScontoServizi implements CalcoloPrezzoStrategy {

	private final int sogliaServizi;
	private final double prezzoBonus;

	public ScontoServizi(int sogliaServizi, double prezzoBonus) {
		this.sogliaServizi = sogliaServizi;
		this.prezzoBonus = prezzoBonus;
	}
	
	@Override
    public double calcolaPrezzo(double prezzoBase, Utente utente) {
        return utente.getNumeroServizi() >= sogliaServizi
                ? prezzoBase - prezzoBonus
                : prezzoBase;
    }

}
