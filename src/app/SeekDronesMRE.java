package app;

import app.entity.*;
import app.event.MoveAggregateEvent;
import app.visualisation.Visualization;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import sim.formation.FormationControl;
import sim.formation.FormationGraph;
import sim.formation.PositionVector;
import sim.network.Network;
import sim.resolution.IAggregation;
import sim.resolution.IDeaggregation;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;

public class SeekDronesMRE {

    private Network network = new Network();

    SeekDronesMRE(Context context, Point2D startingPosition) throws SimControlException {

        IAggregation dronesAggregation = new AggregationImpl();
        IDeaggregation dronesDeagregation = new DeaggregationImpl();

        ArrayList<Agent> droneFormation = formationInitialization(context);

        DroneGroupAggregate droneGroupAggregate = new DroneGroupAggregate(context, dronesDeagregation, startingPosition);
        DroneGroupDeaggregate droneGroupDeaggregate = new DroneGroupDeaggregate(context, droneFormation);
        droneGroupAggregate.setChild(droneGroupDeaggregate);
        droneGroupDeaggregate.setParent(droneGroupAggregate);

        new MoveAggregateEvent(droneGroupAggregate);
    }

    private ArrayList<Agent> formationInitialization(Context context){
        ArrayList<Agent> agents = new ArrayList<>();
        Color color =  Visualization.randomColor();
        for(int i = 0 ; i < 5 ; i++){
            agents.add(new Agent(context, network, new Point2D(0.0,0.0), color));
        }
        network.addAgents(agents);
        initializeNetwork();

        FormationGraph formationGraph = new FormationGraph(network, createPositionVectors());
        FormationControl formationControl = new FormationControl(formationGraph, 0.1, 0.1);
        agents.forEach(agent -> agent.setFormation(formationControl));

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
