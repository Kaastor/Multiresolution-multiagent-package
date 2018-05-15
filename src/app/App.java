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

    public static final double sceneWidth = 1024;
    public static final double sceneHeight = 768;

    private Visualization visualization = new Visualization(sceneWidth, sceneHeight);

    @Override
    public void start(Stage primaryStage) throws Exception {

        visualization.init();

        BorderPane root = new BorderPane();
        root.setCenter(visualization);

        Task task = new Task<Void>() {
            @Override public Void call() throws SimControlException{
                startSimulation();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    Thread.sleep(50);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

        Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startSimulation() throws SimControlException{
        SimModel model = SimModel.getInstance();
        System.out.println("Start simulation");
        model.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
