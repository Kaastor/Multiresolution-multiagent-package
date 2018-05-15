package app;

import dissim.simspace.core.SimControlException;
import dissim.simspace.core.SimModel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    public static final int AGENTS = 5;

    public static final double sceneWidth = 1024;
    public static final double sceneHeight = 768;

    private Visualization visualization = new Visualization(sceneWidth, sceneHeight);
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Task task = new Task<Void>() {
            @Override public Void call() throws Exception{
                startSimulation();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        visualization.init(AGENTS);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    visualization.draw();
            }
        };
        timer.start();

        BorderPane root = new BorderPane();
        root.setCenter(visualization);
        Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startSimulation() throws SimControlException, Exception{
        SimModel model = SimModel.getInstance();
        model.ASTRONOMICALSimulation();
        model.setSimTimeRatio(5.0);
        System.out.println("Start simulation");
        model.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
