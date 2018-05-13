package app;


import app.agent.BasicAgent;
import app.communication.Message;
import dissim.broker.IEvent;
import dissim.broker.IEventPublisher;

public class Agent extends BasicAgent{

    public Agent(Context context){
        super(context);
        context.getContextEventBroker().subscribe(Message.class, this); //Tak musi być, inaczej nie dochodzą wiadomości
    }

    @Override
    public void reflect(IEvent iEvent, IEventPublisher iEventPublisher) {
        System.out.println(getId() + " Got message!");
    }

    @Override
    public void reflect(IEvent iEvent) {
        System.out.println(getId() + " Got message!");
    }
}
