package app.entity;


import app.Context;

import sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class DroneGroupDeaggregate extends ResolutionLevel{

    private ArrayList<Agent> agents = new ArrayList<>();

    public DroneGroupDeaggregate(Context context, ArrayList<Agent> droneFormation) {
        super(context);
        this.agents = droneFormation;
    }

    @Override
    public void stateChange(Object result) {}

    public ArrayList<Agent> getAgents() {
        return agents;
    }



}
