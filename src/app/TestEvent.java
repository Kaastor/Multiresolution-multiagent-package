package app;


import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class TestEvent extends BasicSimStateChange<Agent, Object> {

    public TestEvent(Agent agent, double delay) throws SimControlException{
        super(agent, delay);
    }

    public TestEvent(Agent agent) throws SimControlException{
        super(agent);
    }

    @Override
    protected void transition() throws SimControlException {
        getSimEntity().nextFormationPosition();
        System.out.println(getSimEntity().toString());
        new TestEvent(getSimEntity());
    }
}
