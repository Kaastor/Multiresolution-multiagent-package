package app;


import dissim.broker.IEvent;
import dissim.broker.IEventFilter;
import dissim.broker.IEventSubscriber;

import java.util.ArrayList;
import java.util.List;


public class ZdarzenieFilter implements IEventFilter {

    private List<IEventSubscriber> filterResult = new ArrayList();

    public ZdarzenieFilter() {
    }

    @Override
    public List<IEventSubscriber> filter(IEvent iEvent) {
        if(iEvent.getClass() == Zdarzenie.class && ((Zdarzenie)iEvent).numer %2 != 0) { //nieparz
            Zdarzenie zdarzenie = (Zdarzenie)iEvent;
            IEventSubscriber subscriber = zdarzenie.getSimEntity().getSubskrybent();
            this.filterResult.add(
                    subscriber
            );
        }
        System.out.println("Filtr result - " + filterResult.toString());
        return filterResult;
    }

    public Class getThisClass() {
        return this.getClass();
    }
}