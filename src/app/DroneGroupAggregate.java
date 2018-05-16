package app;


import app.sim.resolution.MultiresolutionEntity;
import app.sim.resolution.ResolutionLevel;
import javafx.geometry.Point2D;

public class DroneGroupAggregate extends ResolutionLevel{

    private Agent groupAggregate;

    public DroneGroupAggregate(Context context, MultiresolutionEntity entity, ResolutionLevel child) {
        super(context, entity, child);
        groupAggregate = new Agent(context, new Point2D(4.0,4.0));
    }

    @Override
    public void stateChange(Object result) {

    }

    public Agent getGroupAggregate() {
        return groupAggregate;
    }
}
