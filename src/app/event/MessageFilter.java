package app.event;


import sim.agent.BasicAgent;
import sim.network.Network;
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
            System.out.println(sender.simTime() + "- Message has been send from agent: " + sender.getId() + " to: " + network.getNeighbours(sender).toString());
            return filterResult;
        } else {
            return filterResult;
        }
    }
}