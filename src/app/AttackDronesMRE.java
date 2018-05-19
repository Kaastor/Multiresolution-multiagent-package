package app;


import app.entity.AggregationImpl;
import app.entity.DeaggregationImpl;
import app.entity.DroneGroupAggregate;
import app.entity.DroneGroupDeaggregate;
import app.event.MoveAggregateEvent;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import sim.resolution.IAggregation;
import sim.resolution.IDeaggregation;

public class AttackDronesMRE {

    AttackDronesMRE(Context context, Point2D startingPosition) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

        DroneGroupAggregate droneGroupAggregate = new DroneGroupAggregate(context, dronesDeagregation, startingPosition);
//        DroneGroupDeaggregate droneGroupDeaggregate = new DroneGroupDeaggregate(context);
//        droneGroupAggregate.setChild(droneGroupDeaggregate);
//        droneGroupDeaggregate.setParent(droneGroupAggregate);

        new MoveAggregateEvent(droneGroupAggregate);
    }
}
