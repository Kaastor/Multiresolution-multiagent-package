package app.communication;


import app.agent.BasicAgent;
import dissim.broker.IEvent;
import dissim.broker.IEventFilter;
import dissim.broker.IEventSubscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageFilter implements IEventFilter {

    private List<IEventSubscriber> filterResult = new ArrayList<>();

    MessageFilter() {
    }

    @Override
    public List<IEventSubscriber> filter(IEvent iEvent) {
        if (iEvent.getClass() == Message.class) {
            BasicAgent sender = ((Message) iEvent).getSender();
            if(sender.getNeighbours() != null)
                filterResult.addAll(sender.getNeighbours());
            System.out.println("Message Filter result: " + filterResult.toString());
            return filterResult;
        } else {
            return filterResult;
        }
    }
}
