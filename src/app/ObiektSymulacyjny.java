package app;

import app.formation.Formation;
import app.formation.PositionVector;
import app.network.Topology;
import dissim.random.SimGenerator;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;
import javafx.geometry.Point2D;

import java.util.ArrayList;

class ObiektSymulacyjny extends BasicSimEntity {

    SimGenerator generator = new SimGenerator();

    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);

        Topology topology = new Topology();

        ArrayList<Agent> agents = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            Point2D startingPoint = new Point2D(generator.uniform(0, 10), generator.uniform(0, 10));
            agents.add(new Agent(context, topology, startingPoint));
        }
        topology.addAgents(agents);

        topology.addConnections(0, new int[]{1,3});
        topology.addConnections(1, new int[]{0,2});
        topology.addConnections(2, new int[]{1});
        topology.addConnections(3, new int[]{2});

        ArrayList<PositionVector> positionVectors = new ArrayList<>();
        positionVectors.add(new PositionVector(0,1, new Point2D(5,0)));
        positionVectors.add(new PositionVector(1,0, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(1,2, new Point2D(5,0)));
        positionVectors.add(new PositionVector(2,1, new Point2D(-5,0)));
        positionVectors.add(new PositionVector(0,3, new Point2D(5,-10)));
        positionVectors.add(new PositionVector(3,2, new Point2D(5,10)));

        Formation formation = new Formation(topology, positionVectors);
        System.out.println(formation.toString());
    }
}
