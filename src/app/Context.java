package app;

import dissim.simspace.core.*;
import net.xeoh.plugins.base.annotations.PluginImplementation;

@PluginImplementation
public class Context extends BasicSimContext implements SimContextInterface {

    ObiektSymulacyjny obiektSymulacyjny;

    public Context() {
        super();
        System.out.println("Init context1");
    }

    public void initContext(){
        try {
            System.out.println("Init context2");
            obiektSymulacyjny = new ObiektSymulacyjny(this);
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