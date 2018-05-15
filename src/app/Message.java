package app;


import app.agent.BasicAgent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;


public class Message extends BasicSimStateChange<Agent, Object> {

    public Message(Agent sender, double delay, Object params) throws SimControlException {
        super(sender, delay, params, new MessageFilter(sender.getNetwork()));
    }

    public Message(Agent sender) throws SimControlException {
        this(sender, 0.0, null);
    }

    @Override
    protected void transition() throws SimControlException {
//        System.out.println(simTime() + "- Message has been send from agent: " + getSender().getId());
    }

    BasicAgent getSender() {
        return getSimEntity();
    }
}
