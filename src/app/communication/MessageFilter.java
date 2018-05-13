package app.communication;


import app.agent.BasicAgent;
import dissim.broker.IEvent;
import dissim.broker.IEventFilter;
import dissim.broker.IEventSubscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageFilter implements IEventFilter {

    public static NetworkTopology networkTopology = null; //TODO  moze byc wiele topologii
    private List<IEventSubscriber> filterResult = new ArrayList<>();

    MessageFilter() {
    }

    @Override
    public List<IEventSubscriber> filter(IEvent iEvent) {
        if (networkTopology != null && iEvent.getClass() == Message.class) {
            BasicAgent sender = ((Message) iEvent).getSender();
            filterResult.addAll(networkTopology.getNeighbours(sender));
            System.out.println("Message Filter result - " + filterResult.toString());

            return filterResult;
        } else {
            System.out.println("Network topology has not been initialized, message not delivered.");
            return filterResult;
        }
    }

    public Class getThisClass() {
        return this.getClass();
    }

}
