package app;


import app.sim.resolution.MultiresolutionEntity;
import app.sim.resolution.ResolutionLevel;

public class DroneGroupAggregate extends ResolutionLevel{


    public DroneGroupAggregate(Context context, MultiresolutionEntity entity, ResolutionLevel child) {
        super(context, entity, child);
    }

    @Override
    public void stateChange(Object result) {

    }
}
