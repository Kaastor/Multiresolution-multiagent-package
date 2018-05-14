package app.formation;


import app.agent.BasicAgent;
import app.network.Link;
import app.network.Network;

import java.util.ArrayList;

public class FormationGraph {

    private Network network;

    public FormationGraph(Network network, ArrayList<PositionVector> positionVectors){
        this.network = network;
        createFormationGraph(positionVectors);
    }

    private void createFormationGraph(ArrayList<PositionVector> positionVectors){
        addVectorsToFormationGraph(positionVectors);
    }

    private void addVectorsToFormationGraph(ArrayList<PositionVector> positionVectors){
        for(PositionVector positionVector : positionVectors){
            Link link = network.getConnection(positionVector.getSourceAgentId(), positionVector.getTargetAgentId());
            link.setVector(positionVector.getVector());
        }
    }

    public Link getConnection(BasicAgent sourceAgent, BasicAgent targetAgent){
        return network.getConnection(sourceAgent, targetAgent);
    }

    public Link getConnection(int sourceAgentId, int targetAgentId){
        return network.getConnection(sourceAgentId, targetAgentId);
    }

    @Override
    public String toString() {
        return "FormationGraph{" +
                "network=" + network.getAllConnections() +
                '}';
    }
}
