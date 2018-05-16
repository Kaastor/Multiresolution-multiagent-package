package app;

import app.sim.resolution.MultiresolutionEntity;
import app.sim.resolution.ResolutionLevel;


public class DronesEntity extends MultiresolutionEntity {

    public DronesEntity(Context context, int resolutionNumber){
        super(context,2 );
    }

    @Override
    public void setActiveResolution(ResolutionLevel resolution) {

        super.setActiveResolution(resolution);
//        Visualization.setAgents(resolution);
    }
}
