import View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {

        //System.setProperty("quantum.multithreaded", "false");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        ViewManager manager = new ViewManager();
        primaryStage = manager.getMainStage();

        primaryStage.show();
    }
}
