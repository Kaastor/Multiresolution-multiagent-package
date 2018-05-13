package app.communication;


import app.agent.BasicAgent;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class NetworkTopology {

    private DirectedWeightedMultigraph<BasicAgent, Link> network
            = new DirectedWeightedMultigraph<>(Link.class);
}
