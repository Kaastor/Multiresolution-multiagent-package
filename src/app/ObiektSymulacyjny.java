package app;

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

        System.out.println(topology.toString());
    }
}
