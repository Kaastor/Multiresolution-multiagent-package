package app;

import app.event.MoveAggregateEvent;
import app.event.MoveFormationEvent;
import app.sim.resolution.IAggregation;
import app.sim.resolution.IDeaggregation;
import app.entity.AggregationImpl;
import app.entity.DeaggregationImpl;
import app.entity.DroneGroupAggregate;
import app.entity.DroneGroupDeaggregate;
import dissim.simspace.core.SimControlException;

public class DronesMRE {

    public static final double TIME_STEP = 1.0;

    private DroneGroupAggregate droneGroupAggregate;
    private DroneGroupDeaggregate droneGroupDeaggregate;

    DronesMRE(Context context) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

        droneGroupAggregate = new DroneGroupAggregate(context, dronesDeagregation);
        droneGroupDeaggregate = new DroneGroupDeaggregate(context);
        droneGroupAggregate.setChild(droneGroupDeaggregate);
        droneGroupDeaggregate.setParent(droneGroupAggregate);

        new MoveAggregateEvent(droneGroupAggregate);

        new MoveFormationEvent(droneGroupDeaggregate, 41.0);
    }
}
