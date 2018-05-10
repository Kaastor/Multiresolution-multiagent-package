package app.events;

import app.resolution.IDeaggregate;
import app.resolution.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class DeaggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IDeaggregate deaggregate;
    private ResolutionLevel parentResolutionLevel;

    public DeaggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.deaggregate = parent.getChild().getDeaggregation();
        this.parentResolutionLevel = getSimEntity();
    }

    public DeaggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        deaggregate.deaggregate(parentResolutionLevel);
    }

}
