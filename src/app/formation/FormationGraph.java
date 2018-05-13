package app.formation;


import app.network.Link;
import app.network.Topology;

import java.util.ArrayList;

public class FormationGraph {

    private Topology graph;

    public FormationGraph(Topology graphTopology, ArrayList<PositionVector> positionVectors){
        this.graph = graphTopology;
        createFormationGraph(positionVectors);
    }

    private void createFormationGraph(ArrayList<PositionVector> positionVectors){
        addVectorsToFormationGraph(positionVectors);
    }

    private void addVectorsToFormationGraph(ArrayList<PositionVector> positionVectors){
        for(PositionVector positionVector : positionVectors){
            Link link = graph.getConnection(positionVector.getSourceAgentId(), positionVector.getTargetAgentId());
            link.setVector(positionVector.getVector());
        }
    }


    @Override
    public String toString() {
        return "FormationGraph{" +
                "graph=" + graph.getAllConnections() +
                '}';
    }
}
