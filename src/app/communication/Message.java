package app.communication;


import app.agent.BasicAgent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;


public class Message extends BasicSimStateChange<BasicAgent, Object> {

    public Message(BasicAgent sender, double delay, Object params) throws SimControlException {
        super(sender, delay, params, new MessageFilter());
    }

    public Message(BasicAgent sender, double delay) throws SimControlException {
        this(sender, delay, null);
    }

    public Message(BasicAgent sender, Object params) throws SimControlException {
        this(sender, 0.0, params);
    }

    @Override
    protected void transition() throws SimControlException {
        System.out.println(simTime() + "- Message has been send from agent: " + getSender().getId());
    }

    BasicAgent getSender() {
        return getSimEntity();
    }
}
