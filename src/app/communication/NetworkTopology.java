package app.communication;


import app.agent.BasicAgent;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.Set;

public class NetworkTopology {

    private DirectedWeightedMultigraph<BasicAgent, Link> network =
            new DirectedWeightedMultigraph<>(Link.class);
    private ArrayList<BasicAgent> agents;

    public NetworkTopology(){
        this.agents = new ArrayList<>();
    }

    public void addConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        addAgent(sourceAgent);
        addAgent(targetAgent);
        network.addEdge(sourceAgent, targetAgent);
    }

    public void addConnections(BasicAgent sourceAgent, ArrayList<BasicAgent> targetAgents){
        addAgent(sourceAgent);
        targetAgents.forEach(this::addAgent);
        targetAgents.forEach(agent -> addConnection(sourceAgent, agent));
    }

    public boolean addAgent(BasicAgent agent) {
        if(!agents.contains(agent))
            agents.add(agent);
        return network.addVertex(agent);
    }

    public boolean removeAgent(BasicAgent agent) {
        agents.remove(agent);
        return network.removeVertex(agent);
    }

    public Link getConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        return network.getEdge(sourceAgent, targetAgent);
    }

    public Link removeConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        return network.removeEdge(sourceAgent, targetAgent);
    }

    public boolean changeConnection(BasicAgent oldSourceAgent, BasicAgent oldTargetAgent, BasicAgent newTargetAgent){
        if(removeConnection(oldSourceAgent, oldTargetAgent) != null){
            addConnection(oldSourceAgent, newTargetAgent);
            return true;
        }
        else
            return false;
    }

    public ArrayList<BasicAgent> getNeighbours(BasicAgent agent){
        ArrayList<BasicAgent> neighbours = new ArrayList<>();
        Set<Link> links = network.outgoingEdgesOf(agent);
        for(Link link : links){
            neighbours.add(network.getEdgeTarget(link));
        }

        return neighbours;
    }

    public ArrayList<BasicAgent> getPredecessors(BasicAgent agent){
        ArrayList<BasicAgent> predecessors = new ArrayList<>();
        Set<Link> links = network.incomingEdgesOf(agent);
        for(Link link : links){
            predecessors.add(network.getEdgeTarget(link));
        }

        return predecessors;
    }

    public ArrayList<BasicAgent> getAgents() {
        return agents;
    }
}
