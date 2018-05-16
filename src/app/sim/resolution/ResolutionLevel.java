package app.sim.resolution;


import app.Context;
import dissim.simspace.core.BasicSimEntity;

public abstract class ResolutionLevel extends BasicSimEntity {

    private MultiresolutionEntity entity;
    private IAggregation aggregation;
    private IDeaggregation deaggregation;
    private ResolutionLevel parent;
    private ResolutionLevel child;
    private boolean hibernated = true;

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IDeaggregation deaggregation, IAggregation aggregation) {
        super(context);
        this.entity = entity;
        this.parent = parent;
        this.child = child;
        this.deaggregation = deaggregation;
        this.aggregation = aggregation;
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IDeaggregation deaggregation) {
        this(context, entity, parent, child, deaggregation, null);
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, ResolutionLevel child, IAggregation aggregation) {
        this(context, entity, parent, child, null, aggregation);
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel parent, IAggregation aggregation){
        this(context, entity, parent, null, null, aggregation);
    }

    public ResolutionLevel(Context context, MultiresolutionEntity entity, ResolutionLevel child){
        this(context, entity, null, child, null, null);
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

    public MultiresolutionEntity getEntity() {
        return entity;
    }
}
