package app.communication;


import app.agent.BasicAgent;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;

public class NetworkTopology extends DirectedWeightedMultigraph<BasicAgent, Link>{

    public NetworkTopology(){
        super(Link.class);
    }

    public void addConnections(BasicAgent sourceAgent, ArrayList<BasicAgent> targetAgents){
        addVertex(sourceAgent);
        targetAgents.forEach(this::addVertex);
        targetAgents.forEach(agent -> addEdge(sourceAgent, agent));
    }
}
