package app;


import app.agent.BasicAgent;
import app.network.Topology;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;


public class Agent extends BasicAgent{

    private Topology network;

    public Agent(Context context, Topology network, Point2D position){
        super(context);
        this.network = network;
        setPosition(position);
        context.getContextEventBroker().subscribe(Message.class, this);
    }

    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        int id = ((Agent)iEventPublisher).getId();
        System.out.println(getId() + " Got message! from " + id );
    }

    @Override
    public void reflect(IEvent iEvent) {
    }

    public Topology getNetwork() {
        return this.network;
    }

    public void sendMessage() throws SimControlException{
        new Message(this);
    }
}
