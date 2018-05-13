package app;

import app.network.Topology;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;

class ObiektSymulacyjny extends BasicSimEntity {


    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);

        Topology topology = new Topology();

        ArrayList<Agent> agents = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            agents.add(new Agent(context, topology));
        }
        topology.addAgents(agents);

        topology.addConnections(0, new int[]{1,3});
        topology.addConnections(1, new int[]{0,2});
        topology.addConnections(2, new int[]{1});
        topology.addConnections(3, new int[]{2});

        agents.get(0).sendMessage();
    }
}
