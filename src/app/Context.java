package app;

import dissim.simspace.core.*;
import javafx.geometry.Point2D;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class Context extends BasicSimContext implements SimContextInterface {

    public static final double TIME_STEP = 1.0;

    public Context() {
        super();
    }

    public void initContext() {
        try {
            new SeekDronesMRE(this, new Point2D(2.0, 2.0));
//            new AttackDronesMRE(this, new Point2D(9.0, 9.0));
            SimModel.getInstance().setEndSimTime(100.0D);
        } catch (SimControlException e) {
            e.printStackTrace();
        }
    }

    public boolean isReadyToASAPtimeFlow() {
        return true;
    }

    public SimCalendarInterface getSimCalendar() {
        return super.getSimCalendar();
    }

    public void stopContext() {
    }

    public void clearContext() {
        super.clearContext();
    }
}