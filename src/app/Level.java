package app;


import dissim.simspace.core.BasicSimEntity;

abstract class Level extends BasicSimEntity {

    private IAggregate aggregation;
    private IDeaggregate deaggregation;
    private Level parent;
    private Level child;
    private boolean hibernated = true;

    Level(Context context, Level parent, Level child, IDeaggregate deaggregation, IAggregate aggregation) {
        super(context);
        this.parent = parent;
        this.child = child;
        this.deaggregation = deaggregation;
        this.aggregation = aggregation;
    }

    Level(Context context, Level parent, Level child, IDeaggregate deaggregation) {
        this(context, parent, child, deaggregation, null);
    }

    Level(Context context, Level parent, Level child, IAggregate aggregation) {
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

    public Level getParent() {
        return parent;
    }

    public Level getChild() {
        return child;
    }

    public boolean isHibernated() {
        return hibernated;
    }

    public void setHibernated(boolean hibernated) {
        this.hibernated = hibernated;
    }
}
