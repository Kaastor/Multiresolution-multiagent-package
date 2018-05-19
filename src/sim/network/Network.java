package sim.network;


import sim.agent.BasicAgent;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.Set;

public class Network {

    private DirectedWeightedMultigraph<BasicAgent, Link> network =
            new DirectedWeightedMultigraph<>(Link.class);
    private ArrayList<BasicAgent> agents;

    public Network(){
        this.agents = new ArrayList<>();
    }

    public void addConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        network.addEdge(sourceAgent, targetAgent);
    }

    public void addConnections(BasicAgent sourceAgent, ArrayList<BasicAgent> targetAgents){
        targetAgents.forEach(this::addAgent);
        targetAgents.forEach(agent -> addConnection(sourceAgent, agent));
    }

    public void addConnections(int sourceAgentId, int[] targetAgentsIds){
        BasicAgent sourceAgent = getAgentById(sourceAgentId);
        for(Integer agentId : targetAgentsIds){
            addConnection(sourceAgent, getAgentById(agentId));
        }
    }

    public Set<Link> getAllConnections(){
        return network.edgeSet();
    }

    public boolean addAgent(BasicAgent agent) {
        agents.add(agent);
        return network.addVertex(agent);
    }

    public void addAgents(ArrayList<? extends BasicAgent> agents) {
        this.agents.addAll(agents);
        for(BasicAgent agent : agents)
            network.addVertex(agent);
    }

    public boolean removeAgent(BasicAgent agent) {
        agents.remove(agent);
        return network.removeVertex(agent);
    }

    public Link getConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        return network.getEdge(sourceAgent, targetAgent);
    }

    public Link getConnection(int sourceAgentId, int targetAgentId){
        return network.getEdge(getAgentById(sourceAgentId), getAgentById(targetAgentId));
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

    public BasicAgent getAgentById(int agentId){
        return agents.get(agentId);
    }

    public ArrayList<BasicAgent> getAgents() {
        return agents;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Network{[agents=").append(agents).append(" ], \n");
        for(BasicAgent agent : agents){
            builder.append("[NeighboursOf ").append(agent.toString()).append(" : ").append(getNeighbours(agent).toString()).append("\n");
        }
        return builder.toString();
    }
}
