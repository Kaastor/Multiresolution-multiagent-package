package app;

import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class Zdarzenie extends BasicSimStateChange<ObiektSymulacyjny, Object> {

    public int numer;

    Zdarzenie(ObiektSymulacyjny parent, double delay, int numer) throws SimControlException{
        super(parent, delay, new ZdarzenieFilter());
        this.numer = numer;
    }

    @Override
    protected void transition() throws SimControlException {
        System.out.println("transitioning...");

    }
}
