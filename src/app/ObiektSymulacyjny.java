package app;

import app.sim.resolution.IAggregation;
import app.sim.resolution.IDeaggregation;
import dissim.simspace.core.SimControlException;

class ObiektSymulacyjny{

    static final double TIME_STEP = 1.0;

    public static DronesEntity droneFormation;
    private DroneGroupAggregate droneGroupAggregate;
    private DroneGroupDeaggregate droneGroupDeaggregate;

    ObiektSymulacyjny(Context context) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

//        droneFormation = new DronesEntity(context, 2);
        droneGroupAggregate = new DroneGroupAggregate(context, droneFormation, dronesDeagregation);
        droneGroupDeaggregate = new DroneGroupDeaggregate(context, droneFormation);
        droneGroupAggregate.setChild(droneGroupDeaggregate);
        droneGroupDeaggregate.setParent(droneGroupAggregate);
        new MoveAggregateEvent(droneGroupAggregate);


    }
}
