package com.project.imagemanagement;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.io.File;

public interface ImageManagement {

    public void setThumbnail(HBox thumbNailView, Image img, EventHandler event);
    public void exportImage(File file,String extension, String Directory);
}
