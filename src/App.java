import app.Visualization;
import dissim.simspace.core.SimControlException;
import dissim.simspace.core.SimModel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private static final double SIM_TIME_RATIO = 5.0;
    private static final double SCENE_WIDTH = 1200;
    private static final double SCENE_HEIGHT = 900;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Visualization visualization = new Visualization(SCENE_WIDTH, SCENE_HEIGHT);

        Task task = new Task<Void>() {
            @Override public Void call() throws Exception{
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
                    visualization.updatePositions();
            }
        }.start();

        BorderPane root = new BorderPane();
        root.setCenter(visualization);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.AZURE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startSimulation() throws SimControlException{
        SimModel model = SimModel.getInstance();
        model.ASTRONOMICALSimulation();
        model.setSimTimeRatio(SIM_TIME_RATIO);
        model.startSimulation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
