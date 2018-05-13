package app;


import app.agent.BasicAgent;
import app.communication.Message;
import app.communication.NetworkTopology;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;

public class Agent extends BasicAgent{

    private NetworkTopology network;

    public Agent(Context context, NetworkTopology network){
        super(context);
        this.network = network;
        context.getContextEventBroker().subscribe(Message.class, this); //Tak musi być, inaczej nie dochodzą wiadomości
    }

    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        System.out.println(getId() + " Got message!");
    }

    @Override
    public void reflect(IEvent iEvent) {
    }

    @Override
    public ArrayList<BasicAgent> getNeighbours() {
        return network.getNeighbours(this);
    }

    public void sendMessage() throws SimControlException{
        new Message(this);
    }
}
