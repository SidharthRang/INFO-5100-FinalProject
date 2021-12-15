package com.project.imagemanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChangeFormatController extends ImageHandler {
    @FXML
    private HBox imageList;

    @FXML
    private Button export;

    @FXML
    private Button download;

    @FXML
    private ChoiceBox extension;

    @FXML
    private Label selectedFile;

    private String selectedFilePath;

    @FXML
    protected void initialize(){
        //Set Directory as File Object
        File folder = new File(ImageHandler.getDirectory());

        //Get All Images from Directory
        File[] images = folder.listFiles();

        //Check if Directory is empty
        if(images.length > 0){
            for(int i = 0;i<images.length;i++){

                //get Image Name and Path
                String imageName = images[i].getName();
                String imagePath = images[i].getAbsolutePath();

                //Convert file to Image object
                Image img = new Image(imagePath, 100, 100, false, false);

                //Set Image as Thumbnail
                setThumbnail(imageList,img,event -> {
                    //Display name of selected image
                    selectedFile.setText(imageName);

                    //Set image extension as choice
                    extension.setValue(getExtension(imageName));

                    //store path of selected image
                    selectedFilePath = imagePath;
                });
            }

            //Convert Image into the selected format
            export.setOnMouseClicked(mouseEvent -> {
                try {
                    convertImageFormat();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        extension.getItems().addAll("jpg", "png", "gif");
    }

    @FXML
    private void convertImageFormat() throws IOException {
        //If File is selected, export it
        if(selectedFilePath != null){
            String ext = extension.getValue().toString();
            String filepath = selectedFilePath;
            File file = new File(filepath);
            exportImage(file,ext,ImageHandler.getConvertedImagesDirectory());
        }
    }

    @FXML
    private void downloadConvertedImages() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("download-img.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Download Converted Images");
        stage.setScene(scene);
        stage.show();
    }
}
