package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;

public class DecoratorServizioScontato extends DecoratorServizio {
    
    private final double percentualeSconto;

    public DecoratorServizioScontato(Servizio servizioDecorato, double percentualeSconto) {
        super(servizioDecorato);
        this.percentualeSconto = percentualeSconto;
    }

    @Override
    public double getPrezzo() {
        return super.getPrezzo() * (1 - percentualeSconto);
    }

	@Override
	public void accetta(VisitorServizio visitor) {
		visitor.visitaDecoratorScontato(this);
	}
}