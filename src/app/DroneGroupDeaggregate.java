package app;


import app.sim.resolution.MultiresolutionEntity;
import app.sim.resolution.ResolutionLevel;

public class DroneGroupDeaggregate extends ResolutionLevel{


    public DroneGroupDeaggregate(Context context, MultiresolutionEntity entity, ResolutionLevel parent) {
        super(context, entity, parent);
    }

        @Override
        public void stateChange(Object result) {

    }
}
