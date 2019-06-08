package lk.shashan.client.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    public Label dash;
    public ImageView imgMM;
    public ImageView imgMA;
    public ImageView imgRA;
    public AnchorPane root;

    public void navigate(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch(icon.getId()){

                case "imgMM":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageMovie.fxml"));
                    break;
                case "imgMA":
                    root = FXMLLoader.load(this.getClass().getResource("/view/ManageActor.fxml"));
                    break;
                case "imgRA":
                    root = FXMLLoader.load(this.getClass().getResource("/view/RegisterActor.fxml"));
                    break;
                case "uyt" :
                    return;
            }

            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

//                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
//                tt.setFromX(-subScene.getWidth());
//                tt.setToX(0);
//                tt.play();

            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}
