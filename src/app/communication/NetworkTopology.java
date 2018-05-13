package app.communication;


import app.agent.BasicAgent;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.Set;

public class NetworkTopology extends DirectedWeightedMultigraph<BasicAgent, Link>{

    private ArrayList<BasicAgent> agents;

    public NetworkTopology(){
        super(Link.class);
        this.agents = new ArrayList<>();
    }

    public void addConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        addVertex(sourceAgent);
        addVertex(targetAgent);
        addEdge(sourceAgent, targetAgent);
    }

    public void addConnections(BasicAgent sourceAgent, ArrayList<BasicAgent> targetAgents){
        addVertex(sourceAgent);
        targetAgents.forEach(this::addVertex);
        targetAgents.forEach(agent -> addEdge(sourceAgent, agent));
    }

    @Override
    public boolean addVertex(BasicAgent agent) {
        if(!agents.contains(agent))
            agents.add(agent);
        return super.addVertex(agent);
    }

    public ArrayList<BasicAgent> getNeighbours(BasicAgent agent){
        ArrayList<BasicAgent> neighbours = new ArrayList<>();
        Set<Link> links = outgoingEdgesOf(agent);
        for(Link link : links){
            neighbours.add(this.getEdgeTarget(link));
        }

        return neighbours;
    }

    public ArrayList<BasicAgent> getAgents() {
        return agents;
    }
}
