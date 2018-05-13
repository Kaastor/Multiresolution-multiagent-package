package app.formation;


import app.network.Link;
import app.network.Topology;

import java.util.ArrayList;

public class Formation {

    private Topology formationGraph;

    public Formation(Topology formationGraph, ArrayList<PositionVector> positionVectors){
        this.formationGraph = formationGraph;
        addVectorsToFormationGraph(positionVectors);
    }

    private void addVectorsToFormationGraph(ArrayList<PositionVector> positionVectors){
        for(PositionVector positionVector : positionVectors){
            Link link = formationGraph.getConnection(positionVector.getSourceAgentId(), positionVector.getTargetAgentId());
            link.setVector(positionVector.getVector());
        }
    }

    @Override
    public String toString() {
        return "Formation{" +
                "formationGraph=" + formationGraph.getAllConnections() +
                '}';
    }
}
