package com.project.imagemanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class HomeController extends ImageHandler {

    @FXML
    private Label dimensions;

    @FXML
    private Label fileLocation;

    @FXML
    private HBox thumbNails;


    @FXML
    public void uploadImage() {

        //Create New Stage to Display File Selector
        Stage stg = new Stage();
        FileChooser fileChooser = new FileChooser();

        //Set Title and FileType filters for File selector
        fileChooser.setTitle("Select Image");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        //Display the dialog box
        File selectedFile = fileChooser.showOpenDialog(stg);

        //If file is selected, upload to directory and show thumbnail
        if(selectedFile != null && selectedFile.isFile()){

            Image img = new Image("File:"+selectedFile.getAbsolutePath());

            //Get image properties
            double height = img.getHeight();
            double width = img.getWidth();

            //resize image and show as thumbnail
            setThumbnail(thumbNails,img,event -> {
                dimensions.setText(String.valueOf(height)+" x "+String.valueOf(width));
                fileLocation.setText(selectedFile.getAbsolutePath().toString());
            });

            //Upload to ImageDirectory
            String fileExtension = getExtension(selectedFile.getName());
            exportImage(selectedFile,fileExtension,ImageHandler.getDirectory());
        }
    }

    public void changeFormat() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("change-format.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Change Image Format");
        stage.setScene(scene);
        stage.show();
    }
}