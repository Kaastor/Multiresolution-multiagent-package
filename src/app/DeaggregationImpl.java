package app;


import app.sim.resolution.IDeaggregation;
import dissim.simspace.core.SimControlException;

public class DeaggregationImpl implements IDeaggregation {

    @Override
    public Object deaggregate(Object param) {
        DroneGroupAggregate droneGroupAggregate = (DroneGroupAggregate)param;
        DroneGroupDeaggregate droneGroupDeaggregate = (DroneGroupDeaggregate)droneGroupAggregate.getChild();
        Visualization.setAgents(droneGroupDeaggregate.getAgents());
        droneGroupDeaggregate.getAgents().forEach(agent -> agent.setPosition(
                droneGroupAggregate.getGroupAggregate().getPosition()));

        droneGroupDeaggregate.getAgents().forEach(Agent::initialize);

        droneGroupDeaggregate.getAgents().forEach(agent -> {
            try {
                new EstablishFormationEvent(agent);
            }
            catch (SimControlException e) {e.printStackTrace();}});

        return null;
    }
}
