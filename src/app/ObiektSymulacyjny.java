package app;

import app.sim.formation.FormationControl;
import app.sim.formation.FormationGraph;
import app.sim.formation.PositionVector;
import app.sim.network.Network;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;

class ObiektSymulacyjny extends BasicSimEntity {

    public static final double TIME_STEP = 1.0;
    SimGenerator generator = new SimGenerator();

    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);

        Network network = new Network();
        ArrayList<Point2D> startingPoints = new ArrayList<>(Arrays.asList(
                new Point2D(6,-1),new Point2D(4,4),new Point2D(0,3),new Point2D(0,0),new Point2D(0,6)
        ));
        ArrayList<Agent> agents = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++){
            Point2D startingPoint = startingPoints.get(i);
            agents.add(new Agent(context, network, startingPoint));
        }
        network.addAgents(agents);

        network.addConnections(0, new int[]{3,4});
        network.addConnections(1, new int[]{0,4});
        network.addConnections(2, new int[]{1,4});
        network.addConnections(3, new int[]{2,4});
        network.addConnections(4, new int[]{0,1,2,3});

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

        FormationGraph formationGraph = new FormationGraph(network, positionVectors);
        FormationControl formationControl = new FormationControl(formationGraph, 0.1, 0.1);
        agents.forEach(agent -> agent.setFormation(formationControl));

        agents.forEach(agent -> {
            try {
                new TestEvent(agent);
            }
        catch (SimControlException e) {e.printStackTrace();}});

//        System.out.println(network.toString());
//        System.out.println(formationGraph.toString());
    }
}
