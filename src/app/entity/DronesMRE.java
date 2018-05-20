package app.entity;


import app.Context;
import app.entity.state.AggregationImpl;
import app.entity.state.DeaggregationImpl;
import app.visualisation.Visualization;
import dissim.monitors.Diagram;
import dissim.random.SimGenerator;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import sim.formation.FormationControl;
import sim.formation.FormationGraph;
import sim.formation.PositionVector;
import sim.network.Network;
import sim.resolution.IAggregation;
import sim.resolution.IDeaggregation;

import java.util.ArrayList;

import static app.App.SCENE_HEIGHT;
import static app.App.SCENE_OFFSET;
import static app.App.SCENE_WIDTH;

abstract class DronesMRE {

    private SimGenerator simGenerator = new SimGenerator();
    private DroneGroupAggregate droneGroupAggregate;
    private DroneGroupDeaggregate droneGroupDeaggregate;
    private Network network = new Network();


    DronesMRE(Context context, Point2D startingPosition, int agentsNumber, double T, double K) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

        droneGroupAggregate = new DroneGroupAggregate(context, dronesDeagregation, startingPosition);
        droneGroupDeaggregate = new DroneGroupDeaggregate(context, dronesAggregation, formationInitialization(context, agentsNumber, T, K));
        droneGroupAggregate.setChild(droneGroupDeaggregate);
        droneGroupDeaggregate.setParent(droneGroupAggregate);
    }

    private ArrayList<Agent> formationInitialization(Context context, int agentsNumber, double T, double K){
        ArrayList<Agent> agents = new ArrayList<>();
        Color color =  Visualization.randomColor();
        Point2D startingPoint = new Point2D(simGenerator.uniform(SCENE_OFFSET, SCENE_WIDTH-SCENE_OFFSET),
                simGenerator.uniform(SCENE_OFFSET, SCENE_HEIGHT-SCENE_OFFSET));
        for(int i = 0 ; i < agentsNumber ; i++){
            agents.add(new Agent(context, network, startingPoint, color, Visualization.DEAGGREGATE_CIRCLE_RADIUS));
        }
        network.addAgents(agents);
        initializeNetwork();
        FormationGraph formationGraph = new FormationGraph(network, createPositionVectors());
        FormationControl formationControl = new FormationControl(formationGraph, T, K);
        agents.forEach(agent -> agent.setFormation(formationControl));

        return agents;
    }

    abstract void initializeNetwork();

    abstract ArrayList<PositionVector> createPositionVectors();

    public Network getNetwork() {
        return network;
    }

    public void simulationResults(){
        Diagram d1 = new Diagram(Diagram.DiagramType.HISTOGRAM, "Czas Deagregacji");
        d1.add(droneGroupAggregate.deagreggationTimeMonitor, java.awt.Color.GREEN);
        System.out.println();
        d1.show();
    }

    public DroneGroupAggregate getDroneGroupAggregate() {
        return droneGroupAggregate;
    }

    public DroneGroupDeaggregate getDroneGroupDeaggregate() {
        return droneGroupDeaggregate;
    }
}
