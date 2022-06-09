package pl.lublin.wsei.java.cwiczenia;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ImgViewer {
    public ImageView imgView;
    public Button btnPokazInfografike;
    private Infografika selInfografika;

    public void btnPokazOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("imgViewer.fxml"));
            Parent root = loader.load();
            ImgViewer viewer = loader.getController();
            if (selInfografika != null) {
                Image img = new Image(selInfografika.adresGrafiki);
                viewer.imgView.setFitHeight(img.getHeight());
                viewer.imgView.setFitWidth(img.getWidth());
                viewer.imgView.setImage(img);
            }

            Stage stage = new Stage();
            stage.setTitle("Podglad infografiki");
            stage.setScene(new Scene(root,900,800));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
