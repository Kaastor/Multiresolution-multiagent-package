package app;


import app.agent.BasicAgent;
import app.network.Network;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;

import java.util.HashSet;
import java.util.Set;


public class Agent extends BasicAgent{

    private Network network;
    private Set<Agent> predecessors;

    public Agent(Context context, Network network, Point2D position){
        super(context);
        this.network = network;
        this.predecessors = new HashSet<>();
        setPosition(position);
        context.getContextEventBroker().subscribe(Message.class, this);
    }

    //TODO zrobic przesylanie wiadomosci i update poprzednikow na aktualnych
    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        Agent sender = (Agent)iEventPublisher;
        predecessors.add(sender);
        System.out.println(getId() + " Predecessors: " + this.predecessors.toString());
    }

    @Override
    public void reflect(IEvent iEvent) {
    }

    public Network getNetwork() {
        return this.network;
    }

    public void sendMessages() throws SimControlException{
        new Message(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Agent{");
        sb.append("id=").append(getId());
        sb.append(", position=").append(getPosition());
        sb.append('}');
        return sb.toString();
    }
}
