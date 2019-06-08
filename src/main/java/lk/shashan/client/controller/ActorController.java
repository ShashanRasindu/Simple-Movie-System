package lk.shashan.client.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.shashan.client.bussiness.Custom.ActorBO;
import lk.shashan.client.dto.ActorDTO;
import lk.shashan.client.main.AppInitializer;
import lk.shashan.client.util.ActorTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActorController implements Initializable {
    public TextField txtid;
    public TextField txtname;
    public TextField txtaddress;
    public Button btnsave;
    public Button btndelete;
    public TableView<ActorTM> tblActor;
    public AnchorPane root;
    public Label lblback;

    ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);

    public void btnsaveOnAction(ActionEvent actionEvent) {

    if (btnsave.getText()!="Update")
    {
        try {
            actorBO.saveActor(new ActorDTO(
                    Integer.parseInt(txtid.getText()),
                    txtname.getText(),
                    txtaddress.getText()
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor saved successfully");
            alert.show();

            tblActor.getItems().add(new ActorTM(
                    Integer.parseInt(txtid.getText()),
                    txtname.getText(),
                    txtaddress.getText()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to save actor");
            alert.show();
        }
    }else if (btnsave.getText()=="Update"){


        int selectedIndex = tblActor.getSelectionModel().getSelectedIndex();
        try {
            actorBO.updateActor(new ActorDTO(
                    Integer.parseInt(txtid.getText()),
                    txtname.getText(),
                    txtaddress.getText()
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor updated successfully");
            alert.show();

            tblActor.getItems().get(selectedIndex).setName(txtname.getText());
            tblActor.getItems().get(selectedIndex).setAddress(txtaddress.getText());
            tblActor.refresh();
           btnsave.setText("Update");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to update actor");
            alert.show();
        }
    }


    }

    public void btndeleteOnAction(ActionEvent actionEvent) {

        ActorTM selectedItem = (ActorTM) tblActor.getSelectionModel().getSelectedItem();
        try {
            actorBO.removeActor(selectedItem.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor deleted successfully");
            alert.show();

            tblActor.getItems().remove(selectedItem);

            btnsave.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to delete actor");
            alert.show();
        }
        tblActor.refresh();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtid.setDisable(true);
        btnsave.setText("Save");
        tblActor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblActor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblActor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<ActorTM> items = tblActor.getItems();

        try {
            for (ActorDTO actorDTO:actorBO.getAllActor()) {
                items.add(new ActorTM(actorDTO.getId(),actorDTO.getName(),actorDTO.getAddress()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load Actor, check your connection");
            alert.show();
        }




        txtid.setText(String.valueOf(actorBO.newId()));

        tblActor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                ActorTM selectedItem = (ActorTM) tblActor.getSelectionModel().getSelectedItem();

                txtid.setText(String.valueOf(selectedItem.getId()));
                txtname.setText(selectedItem.getName());
                txtaddress.setText(String.valueOf(selectedItem.getAddress()));

                btnsave.setText("Update");

            }
        });
    }

    public void lblBackOnClick(MouseEvent mouseEvent) {

        try {
            root = FXMLLoader.load(this.getClass().getResource("/View/DashBoard.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load maindashboard");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) lblback.getScene().getWindow();
        stage.setScene(mainScreen);

    }

}
