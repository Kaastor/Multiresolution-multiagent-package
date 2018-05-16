package app.event;


import app.sim.agent.BasicAgent;
import app.sim.network.Network;
import dissim.broker.IEvent;
import dissim.broker.IEventFilter;
import dissim.broker.IEventSubscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageFilter implements IEventFilter {

    private List<IEventSubscriber> filterResult = new ArrayList<>();
    private Network network;

    MessageFilter(Network network) {
        this.network = network;
    }

    @Override
    public List<IEventSubscriber> filter(IEvent iEvent) {
        if (iEvent.getClass() == Message.class) {
            BasicAgent sender = ((Message) iEvent).getSender();
            filterResult.addAll(network.getNeighbours(sender));
            return filterResult;
        } else {
            return filterResult;
        }
    }
}
