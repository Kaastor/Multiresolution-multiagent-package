package app;

import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class DeaggregationEvent extends BasicSimStateChange<Level, Object> {

    private IDeaggregate deaggregate;
    private Level parentLevel;

    DeaggregationEvent(Level parent) throws SimControlException{
        super(parent);
        this.deaggregate = parent.getDeaggregation();
        this.parentLevel = getSimEntity();
    }

    DeaggregationEvent(Level parent, double delay) throws SimControlException{
        super(parent, delay);
        this.deaggregate = parent.getDeaggregation();
        this.parentLevel = getSimEntity();
    }

    @Override
    protected void transition() throws SimControlException {
        Object result = deaggregate.deaggregate();
        parentLevel.getChild().stateChange(result);
    }

}
