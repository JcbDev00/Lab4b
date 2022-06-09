package pl.lublin.wsei.java.cwiczenia;

import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class HelloController {
    public ListView stInfografiki;
    public ImageView imgMiniaturka;
    public Button btnPokazInfografike;
    public TextField txtAdresStrony;
    public Button btnPrzejdzDoStrony;
    ObservableList<String> tytuly = FXCollections.observableArrayList();
    GusInfoGraphicList igList;
    private Infografika selInfografika;

    private HostServices hostServices;
    private Stage stage;




    @FXML
    private Label lbFile;

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml)", "*.xml");

    @FXML
    public void initialize() {
        fileChooser.getExtensionFilters().add(xmlFilter);
        stInfografiki.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        int index = t1.intValue();
                        if (index != -1) {
                            selInfografika = igList.infografiki.get(index);
                            txtAdresStrony.setText(selInfografika.adresStrony);
                            Image image = new Image(selInfografika.adresMiniaturki);
                            imgMiniaturka.setImage(image);
                        }
                        else {
                            txtAdresStrony.setText("");
                            imgMiniaturka.setImage(null);
                            selInfografika = null;
                        }
                    }
                }
        );
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            igList = new GusInfoGraphicList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            for (Infografika ig : igList.infografiki) tytuly.add(ig.tutul);
            stInfografiki.setItems(tytuly);
        }
        else {
            lbFile.setText("Prosze wczytac plik...");
        }
    }

    public void btnZaladujStrone(ActionEvent actionEvent) {
        if (selInfografika != null) {
        hostServices.showDocument(selInfografika.adresStrony);
        }


    }


    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


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