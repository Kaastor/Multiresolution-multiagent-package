package app;

import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;

class ObiektSymulacyjny extends BasicSimEntity {

    Subskrybent subskrybent;
    Zdarzenie zdarzenie;
    Zdarzenie zdarzenie2;
    Zdarzenie zdarzenie3;
    int zwrotka = 2;

    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);
//        subskrybent= new Subskrybent(context);
//        zdarzenie= new Zdarzenie(this, 10, 1);
//        zdarzenie2= new Zdarzenie(this, 10,2);
//        zdarzenie2= new Zdarzenie(this, 10,3);
        new Agregat(context, new AggregationImpl());

    }

    public Subskrybent getSubskrybent() {
        return subskrybent;
    }
}
