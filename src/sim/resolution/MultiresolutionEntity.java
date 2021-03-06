package sim.resolution;


import app.Context;

import java.util.ArrayList;

@Deprecated
public class MultiresolutionEntity {

    private Context context;
    private int resolutionNumber;
    private ArrayList<ResolutionLevel> resolutions;
    private ResolutionLevel activeResolution;

    public MultiresolutionEntity(Context context, int resolutionNumber){
        this.context = context;
        this.resolutionNumber = resolutionNumber;
        this.resolutions = new ArrayList<>(resolutionNumber);
    }

    public void setActiveResolution(ResolutionLevel resolution){
        this.activeResolution = resolution;
    }

    public ResolutionLevel getActiveResolution() {
        return activeResolution;
    }

    public int getResolutionNumber() {
        return resolutionNumber;
    }


}
