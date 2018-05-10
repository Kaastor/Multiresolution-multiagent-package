package app;

import app.agent.BasicAgent;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;

class ObiektSymulacyjny extends BasicSimEntity {

    Subskrybent subskrybent;
    Subskrybent subskrybent2;
    Subskrybent subskrybent3;
    Zdarzenie zdarzenie;
    Zdarzenie zdarzenie2;
    Zdarzenie zdarzenie3;
    int zwrotka = 2;

    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);
        subskrybent= new Subskrybent(context,1);
        subskrybent2= new Subskrybent(context,2);
        subskrybent3= new Subskrybent(context,3);
        zdarzenie= new Zdarzenie(this, 10, 1);
        zdarzenie2= new Zdarzenie(this, 10,2);
        zdarzenie2= new Zdarzenie(this, 10,3);
//        new Agregat(context, new AggregationImpl());
        new MyMessage(new BasicAgent(context), 10.0);

    }

    public Subskrybent getSubskrybent2() {
        return subskrybent2;
    }

    public Subskrybent getSubskrybent() {
        return subskrybent;
    }
}
