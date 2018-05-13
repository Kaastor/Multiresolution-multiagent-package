package app;

import app.agent.BasicAgent;
import app.communication.Link;
import app.communication.Message;
import app.communication.MessageFilter;
import app.communication.NetworkTopology;
import app.resolution.MultiresolutionEntity;
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
//        subskrybent= new Subskrybent(context,1);
//        subskrybent2= new Subskrybent(context,2);
//        subskrybent3= new Subskrybent(context,3);
//        zdarzenie= new Zdarzenie(this, 10, 1);
//        zdarzenie2= new Zdarzenie(this, 10,2);
//        zdarzenie2= new Zdarzenie(this, 10,3);
//        MultiresolutionEntity entity = new MultiresolutionEntity(context, 1);
//        new Agregat(context,entity, new AggregationImpl());



        NetworkTopology networkTopology = new NetworkTopology();

        ArrayList<BasicAgent> agents = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            agents.add(new Agent(context));
        }
        networkTopology.addAgents(agents);

        networkTopology.addConnections(0, new int[]{1,3});
        networkTopology.addConnections(1, new int[]{0,2});
        networkTopology.addConnections(2, new int[]{1});
        networkTopology.addConnections(3, new int[]{2});

        MessageFilter.networkTopology = networkTopology;
        Message message = new Message(networkTopology.getAgentById(0), 10.0);
        System.out.println(networkTopology.toString());
    }

    public Subskrybent getSubskrybent2() {
        return subskrybent2;
    }

    public Subskrybent getSubskrybent() {
        return subskrybent;
    }
}
