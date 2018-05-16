package app.sim.resolution;


import app.Context;
import dissim.simspace.core.BasicSimEntity;

public abstract class ResolutionLevel extends BasicSimEntity {

    private IAggregation aggregation;
    private IDeaggregation deaggregation;
    private ResolutionLevel parent;
    private ResolutionLevel child;
    private boolean hibernated = true;

    public ResolutionLevel(Context context, IDeaggregation deaggregation, IAggregation aggregation) {
        super(context);
        this.deaggregation = deaggregation;
        this.aggregation = aggregation;
    }

    public ResolutionLevel(Context context, IDeaggregation deaggregation) {
        this(context, deaggregation, null);
    }

    public ResolutionLevel(Context context,  IAggregation aggregation) {
        this(context, null, aggregation);
    }

    public ResolutionLevel(Context context){
        this(context, null, null);
    }

    public void setParent(ResolutionLevel parent) {
        this.parent = parent;
    }

    public void setChild(ResolutionLevel child) {
        this.child = child;
    }

    public abstract void stateChange(Object result);

    public void setAggregation(IAggregation aggregation) {
        this.aggregation = aggregation;
    }

    public IDeaggregation getDeaggregation() {
        return deaggregation;
    }

    public void setDeaggregation(IDeaggregation deaggregation) {
        this.deaggregation = deaggregation;
    }

    public IAggregation getAggregation() {
        return aggregation;
    }

    public ResolutionLevel getParent() {
        return parent;
    }

    public ResolutionLevel getChild() {
        return child;
    }

    public boolean isHibernated() {
        return hibernated;
    }

    public void setHibernated(boolean hibernated) {
        this.hibernated = hibernated;
    }
}
