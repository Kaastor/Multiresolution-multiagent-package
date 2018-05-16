package app.sim.events;

import app.sim.resolution.IDeaggregation;
import app.sim.resolution.ResolutionLevel;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

public class DeaggregationEvent extends BasicSimStateChange<ResolutionLevel, Object> {

    private IDeaggregation deaggregate;
    private ResolutionLevel resolutionLevel;

    public DeaggregationEvent(ResolutionLevel parent, double delay) throws SimControlException{
        super(parent, delay);
        this.deaggregate = parent.getDeaggregation();
        this.resolutionLevel = getSimEntity();
    }

    public DeaggregationEvent(ResolutionLevel parent) throws SimControlException{
        this(parent, 0.0);
    }

    @Override
    protected void transition() throws SimControlException {
//        resolutionLevel.getEntity().setActiveResolution(resolutionLevel.getChild());
        deaggregate.deaggregate(resolutionLevel);
    }

}
