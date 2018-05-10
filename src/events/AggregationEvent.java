package events;

import app.IAggregate;
import app.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class AggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IAggregate aggregate;
    private ResolutionLevel parentResolutionLevel;

    AggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.aggregate = parent.getAggregation();
        this.parentResolutionLevel = getSimEntity();
    }

    AggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        aggregate.aggregate(parentResolutionLevel);
    }

}
