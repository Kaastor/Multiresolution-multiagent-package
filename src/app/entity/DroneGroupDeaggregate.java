package app.entity;


import app.Context;

import sim.resolution.IAggregation;
import sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class DroneGroupDeaggregate extends ResolutionLevel{

    private ArrayList<Agent> agents = new ArrayList<>();

    public DroneGroupDeaggregate(Context context, IAggregation dronesAggregation, ArrayList<Agent> droneFormation) {
        super(context, dronesAggregation);
        this.agents = droneFormation;
    }

    @Override
    public void stateChange(Object result) {}

    public ArrayList<Agent> getAgents() {
        return agents;
    }



}
