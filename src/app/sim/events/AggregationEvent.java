package app.sim.events;

import app.sim.resolution.IAggregate;
import app.sim.resolution.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class AggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IAggregate aggregate;
    private ResolutionLevel resolutionLevel;

    public AggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.aggregate = parent.getAggregation();
        this.resolutionLevel = getSimEntity();
    }

    public AggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        resolutionLevel.getEntity().setActiveResolution(resolutionLevel.getParent());
        Object result = aggregate.aggregate(resolutionLevel);
        resolutionLevel.getParent().stateChange(result);
    }

}
