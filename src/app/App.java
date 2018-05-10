package app;

import dissim.simspace.core.SimControlException;
import dissim.simspace.core.SimModel;

public class App {

    public static void main(String[] args) {
        try {
            //SimModel(SimManager) - obiekt zarządzający symulacją
            SimModel model = SimModel.getInstance();
            System.out.println("Start simulation");
            model.startSimulation();

        } catch (SimControlException var5) {
            var5.printStackTrace();
        }
    }
}
