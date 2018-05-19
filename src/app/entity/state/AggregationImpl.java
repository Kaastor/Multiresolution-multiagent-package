package app.entity.state;


import app.entity.DroneGroupDeaggregate;
import app.event.AggregateFormationEvent;
import dissim.simspace.core.SimControlException;
import sim.resolution.IAggregation;


import static app.Context.TIME_STEP;

public class AggregationImpl implements IAggregation {

    @Override
    public Object aggregate(Object param) {
        DroneGroupDeaggregate droneGroupDeaggregate = (DroneGroupDeaggregate) param;

        droneGroupDeaggregate.getAgents().forEach(agent -> {
            try {
                new AggregateFormationEvent(agent, TIME_STEP, droneGroupDeaggregate);
            } catch (SimControlException e) {
                e.printStackTrace();
            }
        });

        return null;
    }
}
