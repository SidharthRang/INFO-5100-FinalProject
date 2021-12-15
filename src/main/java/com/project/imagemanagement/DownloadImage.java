package com.project.imagemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.io.File;

public class DownloadImage extends ImageHandler {

    @FXML
    private Button downloadImage;

    @FXML
    private HBox thumbNails;

    @FXML
    private Label fileName;

    private File selectedFile;

    @FXML
    protected void initialize(){
        //Set Directory as File Object
        File folder = new File(ImageHandler.getConvertedImagesDirectory());

        //Get All Images from Directory
        File[] images = folder.listFiles();

        //Check if Directory is empty
        if(images.length>0){
            for(int i = 0;i< images.length;i++){
                //get Image Name and Path
                String imageName = images[i].getName();
                String imagePath = images[i].getAbsolutePath();

                //Convert file to Image object
                Image img = new Image(imagePath, 100, 100, false, false);

                //Store in ImageView and add to thumbnail
                setThumbnail(thumbNails,img,event -> {
                    fileName.setText(imageName);
                    selectedFile = new File(imagePath);
                });
            }
        }

        //Download image in Downloads folder on click
        downloadImage.setOnMouseClicked(mouseEvent -> {
            String extension = getExtension(fileName.getText());

            //If File is Selected, download it
            if(selectedFile != null){
                exportImage(selectedFile,extension,ImageHandler.getDownloadDirectory());
            }
        });
    }

}
