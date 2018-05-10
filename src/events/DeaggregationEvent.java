package events;

import app.IDeaggregate;
import app.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class DeaggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IDeaggregate deaggregate;
    private ResolutionLevel parentResolutionLevel;

    DeaggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.deaggregate = parent.getChild().getDeaggregation();
        this.parentResolutionLevel = getSimEntity();
    }

    DeaggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        deaggregate.deaggregate(parentResolutionLevel);
    }

}
