package app;

import app.entity.AttackDronesMRE;
import app.entity.SeekDronesMRE;
import dissim.random.SimGenerator;
import dissim.simspace.core.*;
import javafx.geometry.Point2D;
import net.xeoh.plugins.base.annotations.PluginImplementation;

import static app.App.SCENE_HEIGHT;
import static app.App.SCENE_OFFSET;
import static app.App.SCENE_WIDTH;

@PluginImplementation
public class Context extends BasicSimContext implements SimContextInterface {

    public static final double TIME_STEP = 0.5;
    private SimGenerator simGenerator = new SimGenerator();
    private SeekDronesMRE seekDronesMRE;
    private AttackDronesMRE attackDronesMRE;

    public Context() {
        super();
    }

    public void initContext() {
        try {
            Point2D startingPoint = new Point2D(simGenerator.uniform(SCENE_OFFSET, SCENE_WIDTH-SCENE_OFFSET),
                    simGenerator.uniform(SCENE_OFFSET, SCENE_HEIGHT-SCENE_OFFSET));
            seekDronesMRE = new SeekDronesMRE(this, startingPoint, 5,0.1, 0.1);
            attackDronesMRE = new AttackDronesMRE(this, startingPoint, 5,0.1, 0.1);

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