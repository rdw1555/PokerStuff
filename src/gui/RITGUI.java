package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class RITGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        /*
        The GUI for this project needs to contain three things:
            1) An area for the user to put in the files
            2) A place to display the image
            3) The stats of the compression/uncompression
         Just for simplicities' sake, a border pane will be used
            1) The top will be an area for the user to put in the files
            2) The center will be a the RITViewer
            3) The bottom will be the stats, in a label
         */

        //Create the BorderPane
        BorderPane mainPane = new BorderPane();



        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
