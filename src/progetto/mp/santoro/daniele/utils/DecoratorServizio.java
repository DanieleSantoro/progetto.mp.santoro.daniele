package progetto.mp.santoro.daniele.utils;

import progetto.mp.santoro.daniele.centroBenessere.Servizio;

public abstract class DecoratorServizio implements Servizio {

    protected final Servizio servizioDecorato;

    public DecoratorServizio(Servizio servizioDecorato) {
        this.servizioDecorato = servizioDecorato;
    }

    @Override
    public String getNome() {
        return servizioDecorato.getNome();
    }

    @Override
    public double getPrezzo() {
        return servizioDecorato.getPrezzo();
    }

    @Override
    public int getDurata() {
        return servizioDecorato.getDurata();
    }

    @Override
    public abstract void accetta(VisitorServizio visitor);
}