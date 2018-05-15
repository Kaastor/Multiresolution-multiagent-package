package app.agent;

import app.Context;
import dissim.broker.IEventSubscriber;
import dissim.simspace.core.BasicSimEntity;
import javafx.geometry.Point2D;

public abstract class BasicAgent extends BasicSimEntity implements IEventSubscriber {

    private static int idCount = 0;
    private final int id;
    private Point2D position = new Point2D(0.0,0.0);

    public BasicAgent(Context context){
        this(context, null);
    }

    public BasicAgent(Context context, Point2D position){
        super(context);
        this.id = idCount++;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicAgent agent = (BasicAgent) o;

        return id == agent.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
