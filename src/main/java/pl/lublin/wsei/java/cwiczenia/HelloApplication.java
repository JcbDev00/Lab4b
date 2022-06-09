package pl.lublin.wsei.java.cwiczenia;
import javafx.application.HostServices;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.lublin.wsei.java.cwiczenia.pl.lublin.wsei.java.cwiczenia.Controller;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage stage;
    private HostServices hostServices;




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("gusInfoGraphic.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        controller.setHostServices(this.getHostServices());
        controller.setStage(stage);


        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 800,700));
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gusInfoGraphic.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}