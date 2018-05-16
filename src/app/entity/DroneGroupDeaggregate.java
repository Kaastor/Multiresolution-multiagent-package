package app.entity;


import app.Context;
import app.sim.formation.FormationControl;
import app.sim.formation.FormationGraph;
import app.sim.formation.PositionVector;
import app.sim.network.Network;
import app.sim.resolution.ResolutionLevel;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class DroneGroupDeaggregate extends ResolutionLevel{

    private ArrayList<Agent> agents = new ArrayList<>();
    private Network network = new Network();

    public DroneGroupDeaggregate(Context context) {
        super(context);
        initialization(context);
    }

    @Override
    public void stateChange(Object result) {}

    private void initialization(Context context){
        for(int i = 0 ; i < 5 ; i++){
            agents.add(new Agent(context, network, new Point2D(0.0,0.0)));
        }
        network.addAgents(agents);
        initializeNetwork();

        FormationGraph formationGraph = new FormationGraph(network, createPositionVectors());
        FormationControl formationControl = new FormationControl(formationGraph, 0.1, 0.1);
        agents.forEach(agent -> agent.setFormation(formationControl));
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    private void initializeNetwork(){
        network.addConnections(0, new int[]{3,4});
        network.addConnections(1, new int[]{0,4});
        network.addConnections(2, new int[]{1,4});
        network.addConnections(3, new int[]{2,4});
        network.addConnections(4, new int[]{0,1,2,3});
    }

    private ArrayList<PositionVector> createPositionVectors(){
        ArrayList<PositionVector> positionVectors = new ArrayList<>();
        positionVectors.add(new PositionVector(0,3, new Point2D(5,-5)));
        positionVectors.add(new PositionVector(0,4, new Point2D(0,-5)));
        positionVectors.add(new PositionVector(1,0, new Point2D(5,5)));
        positionVectors.add(new PositionVector(1,4, new Point2D(5,0)));
        positionVectors.add(new PositionVector(2,1, new Point2D(-5,5)));
        positionVectors.add(new PositionVector(2,4, new Point2D(0,5)));
        positionVectors.add(new PositionVector(3,2, new Point2D(-5,-5)));
        positionVectors.add(new PositionVector(3,4, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(4,0, new Point2D(0,5)));
        positionVectors.add(new PositionVector(4,1, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(4,2, new Point2D(0,-5)));
        positionVectors.add(new PositionVector(4,3, new Point2D(5,0)));

        return positionVectors;
    }
}
