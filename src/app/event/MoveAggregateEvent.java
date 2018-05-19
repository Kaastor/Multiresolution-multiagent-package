package app.event;


import app.entity.DroneGroupAggregate;
import sim.event.DeaggregationEvent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

import static app.Context.TIME_STEP;


public class MoveAggregateEvent extends BasicSimStateChange<DroneGroupAggregate, Object> {

    static int count = 0;

    public MoveAggregateEvent(DroneGroupAggregate aggregate, double delay) throws SimControlException{
        super(aggregate, delay);
    }

    public MoveAggregateEvent(DroneGroupAggregate aggregate) throws SimControlException{
        super(aggregate);
    }

    @Override
    protected void transition() throws SimControlException {
        getSimEntity().getAgent().setPosition(
                getSimEntity().getAgent().getPosition().add(1.0, 0.0)
        );
        System.out.println(simTime() + " Aggregate moving to: " + getSimEntity().getAgent().getPosition());
        if(++count < 3)
            new MoveAggregateEvent(getSimEntity(), TIME_STEP);
        else {
            count = 0;
            new DeaggregationEvent(getSimEntity());
        }
    }
}
