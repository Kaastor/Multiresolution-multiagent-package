package app;

import dissim.simspace.core.*;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class Context extends BasicSimContext implements SimContextInterface {
        public Context() {
        super();
    }

    public void initContext(){
        try {
            new DronesMRE(this);
            SimModel.getInstance().setEndSimTime(100.0D);
        }
        catch (SimControlException e){e.printStackTrace();}
    }

    public boolean isReadyToASAPtimeFlow() {
        return true;
    }

    public SimCalendarInterface getSimCalendar() {
        return super.getSimCalendar();
    }

    public void stopContext() {}

    public void clearContext() {
        super.clearContext();
    }
}