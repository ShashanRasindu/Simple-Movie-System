package lk.shashan.client.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.shashan.client.bussiness.Custom.ActorBO;
import lk.shashan.client.bussiness.Custom.MovieBO;
import lk.shashan.client.bussiness.Custom.RegisterBO;
import lk.shashan.client.dto.ActorDTO;
import lk.shashan.client.dto.MoviesDTO;
import lk.shashan.client.dto.RegisterMovieDTO;
import lk.shashan.client.main.AppInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RgisterActorController implements Initializable {
    public AnchorPane root;
    public ComboBox cmbMovie;
    public TextField txtMovieName;
    public TextField txtROle;
    public Button btnSave;
    public TextField txtActorName;
    public ComboBox cmdActor;
    public Label lblBack;


    ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);
    MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);
    RegisterBO actorRegisterBO = AppInitializer.ctx.getBean(RegisterBO.class);



    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            if(actorRegisterBO.getAllActor()!=null){
                for (RegisterMovieDTO registerMovieDTO : actorRegisterBO.getAllActor()) {
                    if ((registerMovieDTO.getActor() == Integer.parseInt(cmdActor.getValue().toString()))&&(registerMovieDTO.getMovie() == Integer.parseInt(cmbMovie.getValue().toString()))) {
                        new Alert(Alert.AlertType.INFORMATION, "BuDu Ammo...! That Actor is Already Registered With The Movie", ButtonType.OK).showAndWait();
                    }else {
                        actorRegisterBO.saveRegisterMovie(new RegisterMovieDTO(Integer.parseInt(cmdActor.getValue().toString()),Integer.parseInt(cmbMovie.getValue().toString()),txtROle.getText()));
                    }
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            try {
                actorRegisterBO.saveRegisterMovie(new RegisterMovieDTO(Integer.parseInt(cmdActor.getValue().toString()),Integer.parseInt(cmbMovie.getValue().toString()),txtROle.getText()));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            List<ActorDTO> allActor = actorBO.getAllActor();
            if (allActor != null) {
                for (ActorDTO actorDTO : allActor) {
                    cmdActor.getItems().add(actorDTO.getId());
                }
            }

            List<MoviesDTO> allMovies = movieBO.getAllMovies();
            if (allMovies != null) {
                for (MoviesDTO movieDTO : allMovies) {
                    cmbMovie.getItems().add(movieDTO.getId());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        cmdActor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    if (newValue != null) {
                        List<ActorDTO> all = actorBO.getAllActor();
                        for (ActorDTO actorDTO : all) {
                            if (newValue.toString().equalsIgnoreCase(Integer.toString(actorDTO.getId()))) {
                                txtActorName.setText(actorDTO.getName());
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        cmbMovie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                try {
                    List<MoviesDTO> all = movieBO.getAllMovies();

                    if (newValue != null) {

                        for (MoviesDTO movieDTO : all) {
                            if (newValue.toString().equalsIgnoreCase(Integer.toString(movieDTO.getId()))) {
                                txtMovieName.setText(movieDTO.getName());
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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



