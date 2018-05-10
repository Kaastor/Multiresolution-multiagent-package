package app.events;

import app.resolution.IAggregate;
import app.resolution.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class AggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IAggregate aggregate;
    private ResolutionLevel parentResolutionLevel;

    public AggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.aggregate = parent.getAggregation();
        this.parentResolutionLevel = getSimEntity();
    }

    public AggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        Object result = aggregate.aggregate(parentResolutionLevel);
        parentResolutionLevel.getParent().stateChange(result);
    }

}
