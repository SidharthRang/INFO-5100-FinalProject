package com.project.imagemanagement;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageHandler implements ImageManagement {

    private static String Directory = Paths.get("src","main","resources","ImageDirectory").toString()+"/" ;

    private static String convertedImagesDirectory = Paths.get("src","main","resources","ConvertedImages").toString()+"/" ;

    private static  String downloadDirectory = System.getProperty("user.home")+"/Downloads/";

    public static String getDirectory(){
        return Directory;
    }

    public static String getConvertedImagesDirectory(){return convertedImagesDirectory;}

    public static String getDownloadDirectory(){return downloadDirectory;}


    @Override
    public void setThumbnail(HBox thumbNailView, Image img, EventHandler event){
        //Store in ImageView
        ImageView thumbNail = new ImageView(img);

        //Resize Image
        thumbNail.setFitHeight(100);
        thumbNail.setFitWidth(100);

        //Show dimensions and location on clicking thumbnail
        thumbNail.setOnMouseClicked(event);

        //add thumbnail to Pane
        thumbNailView.getChildren().add(thumbNail);
    }

    //Method can be reused for conversion as well
    @Override
    public void exportImage(File file,String extension, String Directory){
        try {
            String filename = file.getName().substring(0,file.getName().lastIndexOf("."));
            //Read Bytes from Image and write into ByteStream
            BufferedImage bImg = ImageIO.read(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImg, extension, bos );

            //Write ByteStream into new file in directory;
            byte [] data = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);

            BufferedImage bImg2 = ImageIO.read(bis);
            File uploadedImg = new File(Directory+filename+"."+extension);
            ImageIO.write(bImg2, extension, uploadedImg );
        } catch (IOException e) {
            System.out.println("File is empty");
        }
    }

    //Get Extension of File
    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
