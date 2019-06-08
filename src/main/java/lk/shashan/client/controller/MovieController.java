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
import lk.shashan.client.bussiness.Custom.MovieBO;
import lk.shashan.client.dto.MoviesDTO;
import lk.shashan.client.main.AppInitializer;
import lk.shashan.client.util.MovieTM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieController implements Initializable {
    public TextField txtid;
    public TextField txtname;
    public Button btnsave;
    public Button btndelete;
    public TableView<MovieTM> tblMovie;
    public AnchorPane root;
    public Label lblBack;

    MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);

    public void btnsaveOnAction(ActionEvent actionEvent) {
        if(btnsave.getText()!="Update")
        {
            try {
                movieBO.saveMovie(new MoviesDTO(
                        Integer.parseInt(txtid.getText()),
                        txtname.getText()
                ));
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Movie saved successfully");
                alert.show();

                tblMovie.getItems().add(new MovieTM(
                        Integer.parseInt(txtid.getText()),
                        txtname.getText()
                ));

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to save movie");
                alert.show();
            }
        }else if (btnsave.getText()=="Update"){

            try {
                movieBO.saveMovie(new MoviesDTO(
                        Integer.parseInt(txtid.getText()),
                        txtname.getText()
                ));
                Alert alert = new Alert(Alert.AlertType.ERROR,"Movie successfully updated");
                alert.show();

                tblMovie.getSelectionModel().getSelectedItem().setId(Integer.parseInt(txtid.getText()));
                tblMovie.getSelectionModel().getSelectedItem().setName(txtname.getText());

                tblMovie.refresh();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to update movie");
                alert.show();
            }


        }


    }

    public void btndeleteOnAction(ActionEvent actionEvent) {

        try {
            movieBO.removeMovie(
                    tblMovie.getSelectionModel().getSelectedItem().getId()
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Movie successfully deleted");
            alert.show();

            tblMovie.getItems().remove(new MovieTM(
                    tblMovie.getSelectionModel().getSelectedItem().getId(),
                    tblMovie.getSelectionModel().getSelectedItem().getName()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to delete movie");
            alert.show();
        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtid.setText(String.valueOf(movieBO.getNewId()));

        txtid.setDisable(true);

        tblMovie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMovie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<MovieTM> items = tblMovie.getItems();

        try {
            for (MoviesDTO movieDTO:movieBO.getAllMovies()) {
                items.add(new MovieTM(movieDTO.getId(),movieDTO.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load movies");
            alert.show();
        }


        tblMovie.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                btnsave.setText("Update");
                btndelete.setDisable(false);

                MovieTM selectedItem = (MovieTM) tblMovie.getSelectionModel().getSelectedItem();
                txtid.setText(String.valueOf(selectedItem.getId()));
                txtname.setText(selectedItem.getName());
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

        Stage stage = (Stage) lblBack.getScene().getWindow();
        stage.setScene(mainScreen);

    }
    }

