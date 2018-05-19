package app.event;


import app.entity.Agent;
import sim.agent.BasicAgent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;


public class Message extends BasicSimStateChange<Agent, Object> {

    public Message(Agent sender, double delay, Object params) throws SimControlException {
        super(sender, delay, params, new MessageFilter(sender.getNetwork()));
        setSimPriority(0);
    }

    public Message(Agent sender, double delay) throws SimControlException {
        this(sender, delay, null);
    }

    public Message(Agent sender) throws SimControlException {
        this(sender, 0.0, null);
    }

    @Override
    protected void transition() throws SimControlException {

    }

    BasicAgent getSender() {
        return getSimEntity();
    }
}
