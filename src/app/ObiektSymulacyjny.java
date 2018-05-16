package app;

import app.sim.events.DeaggregationEvent;
import app.sim.resolution.IAggregation;
import app.sim.resolution.IDeaggregation;
import app.sim.resolution.MultiresolutionEntity;
import dissim.simspace.core.SimControlException;

class ObiektSymulacyjny{

    static final double TIME_STEP = 1.0;

    public static DronesEntity droneFormation;
    private DroneGroupAggregate droneGroupAggregate;
    private DroneGroupDeaggregate droneGroupDeaggregate;

    ObiektSymulacyjny(Context context) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

        droneFormation = new DronesEntity(context, 2);
        droneGroupAggregate = new DroneGroupAggregate(context, droneFormation, droneGroupDeaggregate);
        droneGroupDeaggregate = new DroneGroupDeaggregate(context, droneFormation, droneGroupAggregate);

        new DeaggregationEvent(droneGroupAggregate);


    }
}
