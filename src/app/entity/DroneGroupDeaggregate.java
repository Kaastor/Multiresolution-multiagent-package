package app.entity;


import app.Context;

import sim.resolution.IAggregation;
import sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class DroneGroupDeaggregate extends ResolutionLevel{

    private int agentActivationCount = 0;
    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Boolean> activated = new ArrayList<>();

    public DroneGroupDeaggregate(Context context, IAggregation dronesAggregation, ArrayList<Agent> droneFormation) {
        super(context, dronesAggregation);
        this.agents = droneFormation;
        for(int i = 0 ; i < agents.size() ; i++){
            activated.add(false);
        }
    }

    @Override
    public void stateChange(Object result) {}

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void deactivate(){
        agentActivationCount = 0;
        activated.forEach(activation  -> activation = false);
    }

    public void nextAgentActivated(){
        activated.set(agentActivationCount, true);
        agentActivationCount++;
    }

    public boolean isActivated(){
        return  !activated.contains(false);
    }

}
