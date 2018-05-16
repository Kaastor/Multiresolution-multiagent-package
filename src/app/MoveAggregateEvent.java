package app;


import app.sim.events.DeaggregationEvent;
import dissim.simspace.core.BasicSimStateChange;
import dissim.simspace.core.SimControlException;

import static app.ObiektSymulacyjny.TIME_STEP;

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
        getSimEntity().getGroupAggregate().setPosition(
                getSimEntity().getGroupAggregate().getPosition().add(1.0, 1.0)
        );
        System.out.println(simTime() + " Aggregate moving to: " + getSimEntity().getGroupAggregate().getPosition());
        if(++count < 10)
            new MoveAggregateEvent(getSimEntity(), TIME_STEP);
        else
            new DeaggregationEvent(getSimEntity());
    }
}
