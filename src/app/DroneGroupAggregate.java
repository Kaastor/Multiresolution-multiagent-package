package app;


import app.sim.resolution.IDeaggregation;
import app.sim.resolution.MultiresolutionEntity;
import app.sim.resolution.ResolutionLevel;
import javafx.geometry.Point2D;

import java.util.ArrayList;


public class DroneGroupAggregate extends ResolutionLevel{

    private Agent groupAggregate;

    public DroneGroupAggregate(Context context, MultiresolutionEntity entity, IDeaggregation deaggregation) {
        super(context, entity, deaggregation);
        groupAggregate = new Agent(context, new Point2D(4.0,4.0));
        initialization();
    }

    private void initialization(){
        ArrayList<Agent> agent = new ArrayList<>();
        agent.add(groupAggregate);
        Visualization.setAgents(agent);
    }

    @Override
    public void stateChange(Object result) {

    }

    public Agent getGroupAggregate() {
        return groupAggregate;
    }
}
