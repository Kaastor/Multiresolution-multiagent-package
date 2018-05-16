package app;


import app.sim.resolution.IDeaggregation;
import dissim.simspace.core.SimControlException;

public class DeaggregationImpl implements IDeaggregation {

    @Override
    public Object deaggregate(Object param) {
        DroneGroupDeaggregate droneGroup = (DroneGroupDeaggregate)param;

        Visualization.setAgents(droneGroup.getAgents());
        droneGroup.getAgents().forEach(agent -> agent.setPosition(
                ((DroneGroupAggregate)droneGroup.getParent()).getGroupAggregate().getPosition()));

        droneGroup.getAgents().forEach(agent -> {
            try {
                new MoveEvent(agent);
            }
            catch (SimControlException e) {e.printStackTrace();}});

        return null;
    }
}
