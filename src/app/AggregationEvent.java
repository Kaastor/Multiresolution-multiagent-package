package app;

import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class AggregationEvent extends BasicSimStateChange<Level, Object> {

    private IAggregate aggregate;
    private Level parentLevel;

    AggregationEvent(Level parent) throws SimControlException{
        super(parent);
        this.aggregate = parent.getAggregation();
        this.parentLevel = getSimEntity();
    }

    AggregationEvent(Level parent, double delay) throws SimControlException{
        super(parent, delay);
        this.aggregate = parent.getAggregation();
        this.parentLevel = getSimEntity();
    }

    @Override
    protected void transition() throws SimControlException {
        System.out.println("transitioning...");
        Object result = aggregate.aggregate();
        parentLevel.getChild().stateChange(result);
    }

}
