package app;

import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.broker.IEventSubscriber;
import dissim.simspace.core.BasicSimEntity;

public class Subskrybent extends BasicSimEntity implements IEventSubscriber {

    int numer;

    Subskrybent(Context context, int numer){
        super(context);
        this.numer = numer;
        context.getContextEventBroker().subscribe(Zdarzenie.class, this);
        System.out.println( "Created sub");
    }

    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        Zdarzenie zdarzenie = (Zdarzenie)iEvent;
        int numer = zdarzenie.numer;
        System.out.println(this.numer + " Reflect1 - " + numer);
    }

    @Override
    public void reflect(IEvent iEvent) {
        System.out.println( "Reflect2");
    }

    @Override
    public String toString() {
        return "Subskrybent{" +
                "numer=" + numer +
                '}';
    }
}
