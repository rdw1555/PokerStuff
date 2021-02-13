package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * IMPORTANT INFO
 * WHEN PLAYERS ARE JOINING, HAVE THEM INPUT A PLAYER NAME
 * THEN, AFTER THE HOST STARTS THE GAME, CREATE ALL THE PLAYER OBJECTS
 *      USE AN ARRAYLIST OF THE PLAYER NAMES
 */

public class PokerGUI extends Application {
    //Label for bottom stats
    Label bottomTxt;

    //global start button
    Button btnStart;

    //global BorderPane
    BorderPane mainPane;

    int pos = 0;
    final int minPos = 0;
    final int maxPos = 100;

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
        mainPane = new BorderPane();

        //Set the size of the pane
        mainPane.setPrefHeight(600);
        mainPane.setPrefWidth(512);

        //-------------BOTTOM TEXT---------------------------------------------------------------
        //Set the strings the labels will contain
        String lblInfo = "Stats: N/A";

        //Create the label
        bottomTxt = new Label();

        //Set the font of the label to arial and give it a good font size
        bottomTxt.setFont(new Font("arial", 16));

        //Set the text of the label
        bottomTxt.setText(lblInfo);

        //Center the label
        BorderPane.setAlignment(bottomTxt, Pos.CENTER);

        //Vertically center node of a BorderPane
        //(Insets are spaced as: (top, right, bottom, left), so since we only want vertical spacing only the bottom value is set)
        BorderPane.setMargin(bottomTxt, new Insets(0,0,40,0));
        //-------------BOTTOM TEXT---------------------------------------------------------------


        //-------------CENTER CANVAS-------------------------------------------------------------
        //Create a wrapper Pane
        Pane wrapperPane = new Pane();

        //Set the center of the border pane to the wrapper
        mainPane.setCenter(wrapperPane);
        //-------------CENTER CANVAS-------------------------------------------------------------


        //-------------TOP MENU------------------------------------------------------------------
        //Create a menuBar
        MenuBar menuBar = new MenuBar();

        //Create a start button to start the simulation
        btnStart = new Button("Start");

        //Set it initially disabled (to prevent issues of not having a file)
        btnStart.setDisable(true);

        //Create a HBox to store the menuBar and start button in
        HBox hbox = new HBox(menuBar, btnStart);

        //Give the button the horizontal growth priority so that it is positioned right next to the menu bar
        HBox.setHgrow(menuBar, Priority.NEVER);
        HBox.setHgrow(btnStart, Priority.ALWAYS);

        mainPane.setTop(hbox);
        //-------------TOP MENU------------------------------------------------------------------

        //Set and add the scene to the stage
        Scene scene = new Scene(mainPane);
        stage.setTitle("Poker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
