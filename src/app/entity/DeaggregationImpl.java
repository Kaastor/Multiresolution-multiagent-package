package app.entity;


import app.visualisation.Visualization;
import app.event.EstablishFormationEvent;
import sim.resolution.IDeaggregation;
import dissim.simspace.core.SimControlException;

import static app.DronesMRE.TIME_STEP;

public class DeaggregationImpl implements IDeaggregation {

    @Override
    public Object deaggregate(Object param) {
        DroneGroupAggregate droneGroupAggregate = (DroneGroupAggregate)param;
        DroneGroupDeaggregate droneGroupDeaggregate = (DroneGroupDeaggregate)droneGroupAggregate.getChild();

        Visualization.removeResolutionAgentsToDraw(droneGroupAggregate.getAgents());
        Visualization.addResolutionAgentsToDraw(droneGroupDeaggregate.getAgents());

        droneGroupDeaggregate.getAgents().forEach(agent -> agent.setPosition(
                droneGroupAggregate.getAgent().getPosition()));

        droneGroupDeaggregate.getAgents().forEach(Agent::initialize);

        droneGroupDeaggregate.getAgents().forEach(agent -> {
            try {
                new EstablishFormationEvent(agent, TIME_STEP);
            }
            catch (SimControlException e) {e.printStackTrace();}});

        return null;
    }
}
