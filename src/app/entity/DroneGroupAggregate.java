package app.entity;


import app.Context;
import sim.resolution.IDeaggregation;
import sim.resolution.ResolutionLevel;
import javafx.geometry.Point2D;
import app.visualisation.Visualization;

import java.util.ArrayList;


public class DroneGroupAggregate extends ResolutionLevel{

    private ArrayList<Agent> agents;

    public DroneGroupAggregate(Context context, IDeaggregation deaggregation, Point2D startingPosition) {
        super(context, deaggregation);
        agents = new ArrayList<>();
        agents.add( new Agent(context, startingPosition, Visualization.randomColor()));
        visualizationInit();
    }

    private void visualizationInit(){
        Visualization.addResolutionAgentsToDraw(agents);
    }

    @Override
    public void stateChange(Object result) {

    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public Agent getAgent() {
        return agents.get(0);
    }

}
