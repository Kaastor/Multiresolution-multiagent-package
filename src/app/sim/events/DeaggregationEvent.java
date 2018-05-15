package app.sim.events;

import app.sim.resolution.IDeaggregate;
import app.sim.resolution.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class DeaggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IDeaggregate deaggregate;
    private ResolutionLevel resolutionLevel;

    public DeaggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.deaggregate = parent.getChild().getDeaggregation();
        this.resolutionLevel = getSimEntity();
    }

    public DeaggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
        resolutionLevel.getEntity().setActiveResolution(resolutionLevel.getChild());
        deaggregate.deaggregate(resolutionLevel);
    }

}
