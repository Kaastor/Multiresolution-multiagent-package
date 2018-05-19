package app.entity;


import app.Context;

import dissim.monitors.MonitoredVar;
import sim.resolution.IAggregation;
import sim.resolution.ResolutionLevel;

import java.util.ArrayList;

public class DroneGroupDeaggregate extends ResolutionLevel{

    public MonitoredVar agreggationTimeMonitor = new MonitoredVar();

    private int agentActivationCount = 0;
    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Boolean> synchronization = new ArrayList<>();

    public DroneGroupDeaggregate(Context context, IAggregation dronesAggregation, ArrayList<Agent> droneFormation) {
        super(context, dronesAggregation);
        this.agents = droneFormation;
        for(int i = 0 ; i < agents.size() ; i++){
            synchronization.add(false);
        }
    }

    @Override
    public void stateChange(Object result) {}

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public void deactivate(){
        agentActivationCount = 0;
        synchronization.clear();
        for(int i = 0 ; i < agents.size() ; i++){
            synchronization.add(false);
        }
    }

    public void nextAgentActivated(){
        synchronization.set(agentActivationCount, true);
        agentActivationCount++;
    }

    public boolean getSynchronization(){
        synchronization.set(agentActivationCount, true);
        agentActivationCount++;
        return  !synchronization.contains(false);
    }

}
