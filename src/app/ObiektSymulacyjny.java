package app;

import app.network.NetworkTopology;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;

class ObiektSymulacyjny extends BasicSimEntity {


    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);

        NetworkTopology networkTopology = new NetworkTopology();

        ArrayList<Agent> agents = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            agents.add(new Agent(context, networkTopology));
        }
        networkTopology.addAgents(agents);

        networkTopology.addConnections(0, new int[]{1,3});
        networkTopology.addConnections(1, new int[]{0,2});
        networkTopology.addConnections(2, new int[]{1});
        networkTopology.addConnections(3, new int[]{2});

        agents.get(0).sendMessage();
    }
}
