package app;

import app.entity.AttackDronesMRE;
import app.entity.SeekDronesMRE;
import dissim.simspace.core.*;
import javafx.geometry.Point2D;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class Context extends BasicSimContext implements SimContextInterface {

    public static final double TIME_STEP = 0.5;
    private SeekDronesMRE seekDronesMRE;
    private AttackDronesMRE attackDronesMRE;

    public Context() {
        super();
    }

    public void initContext() {
        try {
            seekDronesMRE = new SeekDronesMRE(this, new Point2D(2.0, 2.0), 5,0.1, 0.1);
            attackDronesMRE = new AttackDronesMRE(this, new Point2D(2.0, 7.0), 5,0.1, 0.1);

            SimModel.getInstance().setEndSimTime(400.0D);
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
        seekDronesMRE.simulationResults();
        attackDronesMRE.simulationResults();
    }

    public void clearContext() {
        super.clearContext();
    }
}