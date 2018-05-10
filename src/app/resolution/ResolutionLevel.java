package app.resolution;


import app.Context;
import dissim.simspace.core.BasicSimEntity;

public abstract class ResolutionLevel extends BasicSimEntity {

    private MultiresolutionEntity entity;
    private IAggregate aggregation;
    private IDeaggregate deaggregation;
    private ResolutionLevel parent;
    private ResolutionLevel child;
    private boolean hibernated = true;

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IDeaggregate deaggregation, IAggregate aggregation) {
        super(context);
        this.entity = entity;
        this.parent = parent;
        this.child = child;
        this.deaggregation = deaggregation;
        this.aggregation = aggregation;
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IDeaggregate deaggregation) {
        this(context, entity, parent, child, deaggregation, null);
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IAggregate aggregation) {
        this(context, entity, parent, child, null, aggregation);
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

    public MultiresolutionEntity getEntity() {
        return entity;
    }
}
