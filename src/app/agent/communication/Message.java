package app.agent.communication;


import app.agent.BasicAgent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;


public class Message extends BasicSimStateChange<BasicAgent, Object>{

    public Message(BasicAgent agent, double delay, Object params) throws SimControlException{
        super(agent, delay, params);
    }

    public Message(BasicAgent agent) throws SimControlException{
        this(agent, 0.0, null);
    }

    public Message(BasicAgent agent, Object params) throws SimControlException{
        this(agent, 0.0, params);
    }
    @Override
    protected void transition() throws SimControlException {
        //spr czy istnieje formacja
    }

    public BasicAgent getSender() {
        return getSimEntity();
    }
}
