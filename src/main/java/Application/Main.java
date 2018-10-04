package Application;

import View.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {

        System.setProperty("quantum.multithreaded", "false");
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage primaryStage) {


        ViewManager manager = new ViewManager();
        primaryStage = manager.getMainStage();

        primaryStage.show();
    }
}
