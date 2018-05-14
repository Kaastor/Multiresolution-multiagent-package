package app;


import app.agent.BasicAgent;
import app.network.Network;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;


public class Agent extends BasicAgent{

    private Network network;

    public Agent(Context context, Network network, Point2D position){
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

    public Network getNetwork() {
        return this.network;
    }

    public void sendMessage() throws SimControlException{
        new Message(this);
    }
}
