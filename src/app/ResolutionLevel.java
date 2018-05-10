package app;


import dissim.simspace.core.BasicSimEntity;

public abstract class ResolutionLevel extends BasicSimEntity {

    private IAggregate aggregation;
    private IDeaggregate deaggregation;
    private ResolutionLevel parent;
    private ResolutionLevel child;
    private boolean hibernated = true;

    ResolutionLevel(Context context, ResolutionLevel parent, ResolutionLevel child, IDeaggregate deaggregation, IAggregate aggregation) {
        super(context);
        this.parent = parent;
        this.child = child;
        this.deaggregation = deaggregation;
        this.aggregation = aggregation;
    }

    ResolutionLevel(Context context, ResolutionLevel parent, ResolutionLevel child, IDeaggregate deaggregation) {
        this(context, parent, child, deaggregation, null);
    }

    ResolutionLevel(Context context, ResolutionLevel parent, ResolutionLevel child, IAggregate aggregation) {
        this(context, parent, child, null, aggregation);
    }


    public abstract void stateChange(Object result);

    public void setAggregation(IAggregate aggregation) {
        this.aggregation = aggregation;
    }

    public IDeaggregate getDeaggregation() {
        return deaggregation;
    }

    public void setDeaggregation(IDeaggregate deaggregation) {
        this.deaggregation = deaggregation;
    }

    public IAggregate getAggregation() {
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
