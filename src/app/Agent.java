package app;


import app.agent.BasicAgent;
import app.network.NetworkTopology;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;
import dissim.simspace.core.SimControlException;


public class Agent extends BasicAgent{

    private NetworkTopology network;

    public Agent(Context context, NetworkTopology network){
        super(context);
        this.network = network;
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

    public NetworkTopology getNetwork() {
        return this.network;
    }

    public void sendMessage() throws SimControlException{
        new Message(this);
    }
}
