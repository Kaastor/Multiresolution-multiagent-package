package app;

import app.agent.BasicAgent;
import app.communication.Link;
import app.communication.Message;
import app.communication.NetworkTopology;
import dissim.simspace.core.BasicSimEntity;
import dissim.simspace.core.SimControlException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

class ObiektSymulacyjny extends BasicSimEntity {

    Subskrybent subskrybent;
    Subskrybent subskrybent2;
    Subskrybent subskrybent3;
    Zdarzenie zdarzenie;
    Zdarzenie zdarzenie2;
    Zdarzenie zdarzenie3;
    int zwrotka = 2;

    ObiektSymulacyjny(Context context) throws SimControlException {
        super(context);
        subskrybent= new Subskrybent(context,1);
        subskrybent2= new Subskrybent(context,2);
        subskrybent3= new Subskrybent(context,3);
        zdarzenie= new Zdarzenie(this, 10, 1);
        zdarzenie2= new Zdarzenie(this, 10,2);
        zdarzenie2= new Zdarzenie(this, 10,3);
//        new Agregat(context, new AggregationImpl());
        new Message(new BasicAgent(context), 10.0);
        NetworkTopology networkTopology = new NetworkTopology();

        Agent agent = new Agent(context);
        networkTopology.addVertex(agent);
        ArrayList<BasicAgent> agents = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++){
            agents.add(new Agent(context));
        }
//        networkTopology.addVertex(agents.get(0)); networkTopology.addVertex(agents.get(1));
//        networkTopology.addEdge(agents.get(0), agents.get(1));
        networkTopology.addConnections(agent, agents);
        ArrayList<BasicAgent> agentArrayList = networkTopology.getNeighbours(agent);
        System.out.println(networkTopology.vertexSet());
    }

    public Subskrybent getSubskrybent2() {
        return subskrybent2;
    }

    public Subskrybent getSubskrybent() {
        return subskrybent;
    }
}
