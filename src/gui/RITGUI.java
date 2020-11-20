package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import ptui.RITCompress;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RITGUI extends Application {
    //Label for bottom stats
    Label bottomTxt;

    //ToggleGroup for compression type
    ToggleGroup typeToggle;
    //ToggleGroup for file
    ToggleGroup fileToggle;

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

        //Set the size of the pane
        mainPane.setPrefHeight(600);
        mainPane.setPrefWidth(600);

        //-------------BOTTOM TEXT---------------------------------------------------------------
        //Set the strings the labels will contain
        String lblInfo = "Stats: ";

        //Create the label
        bottomTxt = new Label();

        //Set the font of the label to arial and give it a good font size
        bottomTxt.setFont(new Font("arial", 16));

        //Set the text of the label
        bottomTxt.setText(lblInfo);

        //Set the mainPane's info
        mainPane.setBottom(bottomTxt);

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

        //Create a canvas
        Canvas canvas = new Canvas(); //TODO THIS WILL HAVE TO BE SET TO OUR RITViewer CANVAS

        //Add the canvas to the wrapperPane
        wrapperPane.getChildren().add(canvas);

        //Set the width and height of the canvas
        canvas.widthProperty().bind(wrapperPane.widthProperty());
        canvas.heightProperty().bind(wrapperPane.heightProperty());


        //SETTING THE CANVAS TO RED JUST SO THAT WE CAN VISUALIZE WHAT IT IS FOR NOW ----------------------------------
        StackPane holder = new StackPane();
        holder.getChildren().add(canvas);
        wrapperPane.getChildren().add(holder);
        holder.setStyle("-fx-background-color: red");
        //-------------CENTER CANVAS-------------------------------------------------------------


        //-------------TOP MENU------------------------------------------------------------------
        //Create a menuBar
        MenuBar menuBar = new MenuBar();

        //Crete the first menuItem of a compression type
        Menu dropType = new Menu("Compression Type?");

        //Create items to go with the compression type
        RadioMenuItem compressingC = new RadioMenuItem("Compressing");
        RadioMenuItem uncompressingC = new RadioMenuItem("Uncompressing");

        //Set the events
        compressingC.setOnAction(buttonHandler);
        uncompressingC.setOnAction(buttonHandler);

        //Create a ToggleGroup for the type
        typeToggle = new ToggleGroup();
        typeToggle.getToggles().add(compressingC);
        typeToggle.getToggles().add(uncompressingC);

        //Add the types to the menu
        dropType.getItems().add(compressingC);
        dropType.getItems().add(uncompressingC);
        //IF WE'RE FEELING EXTRA IT'S REALLY EASY TO ADD LITTLE PICTURES/ICONS

        //Create the second menuItem of a file
        Menu dropFile = new Menu("File");

        //Create items to go with the file dropdown
        RadioMenuItem file1 = new RadioMenuItem("earth256x256");
        RadioMenuItem file2 = new RadioMenuItem("incline256x256");
        RadioMenuItem file3 = new RadioMenuItem("redsox512x512");
        RadioMenuItem file4 = new RadioMenuItem("ritlogo128x128");
        RadioMenuItem file5 = new RadioMenuItem("scotty256x256");
        RadioMenuItem file6 = new RadioMenuItem("simple1x1");
        RadioMenuItem file7 = new RadioMenuItem("simple2x2");
        RadioMenuItem file8 = new RadioMenuItem("simple4x4");
        RadioMenuItem file9 = new RadioMenuItem("simple8x8");
        RadioMenuItem file10 = new RadioMenuItem("simple8x8-2");
        RadioMenuItem file11 = new RadioMenuItem("simple16x16");
        RadioMenuItem file12 = new RadioMenuItem("simple32x32");
        RadioMenuItem file13 = new RadioMenuItem("simple256x256");
        RadioMenuItem file14 = new RadioMenuItem("smileyface256x256");
        RadioMenuItem file15 = new RadioMenuItem("stopsign256x256");

        //Set the events
        file1.setOnAction(buttonHandler);
        file2.setOnAction(buttonHandler);
        file3.setOnAction(buttonHandler);
        file4.setOnAction(buttonHandler);
        file5.setOnAction(buttonHandler);
        file6.setOnAction(buttonHandler);
        file7.setOnAction(buttonHandler);
        file8.setOnAction(buttonHandler);
        file9.setOnAction(buttonHandler);
        file10.setOnAction(buttonHandler);
        file11.setOnAction(buttonHandler);
        file12.setOnAction(buttonHandler);
        file13.setOnAction(buttonHandler);
        file14.setOnAction(buttonHandler);
        file15.setOnAction(buttonHandler);

        //Create a ToggleGroup for the files
        fileToggle = new ToggleGroup();
        fileToggle.getToggles().add(file1);
        fileToggle.getToggles().add(file2);
        fileToggle.getToggles().add(file3);
        fileToggle.getToggles().add(file4);
        fileToggle.getToggles().add(file5);
        fileToggle.getToggles().add(file6);
        fileToggle.getToggles().add(file7);
        fileToggle.getToggles().add(file8);
        fileToggle.getToggles().add(file9);
        fileToggle.getToggles().add(file10);
        fileToggle.getToggles().add(file11);
        fileToggle.getToggles().add(file12);
        fileToggle.getToggles().add(file13);
        fileToggle.getToggles().add(file14);
        fileToggle.getToggles().add(file15);

        //Add the file items to dropOne
        dropFile.getItems().add(file1);
        dropFile.getItems().add(file2);
        dropFile.getItems().add(file3);
        dropFile.getItems().add(file4);
        dropFile.getItems().add(file5);
        dropFile.getItems().add(file6);
        dropFile.getItems().add(file7);
        dropFile.getItems().add(file8);
        dropFile.getItems().add(file9);
        dropFile.getItems().add(file10);
        dropFile.getItems().add(file11);
        dropFile.getItems().add(file12);
        dropFile.getItems().add(file13);
        dropFile.getItems().add(file14);
        dropFile.getItems().add(file15);

        //Add the file menu to the menuBar
        menuBar.getMenus().add(dropType);
        menuBar.getMenus().add(dropFile);

        //Add the menuBar to the pane
        mainPane.setTop(menuBar);
        //-------------TOP MENU------------------------------------------------------------------

        //Set and add the scene to the stage
        Scene scene = new Scene(mainPane);
        stage.setTitle("Rich Image Tool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * buttonHandler - the EventHandler for when a ToggleGroup RadioMenuItem is selected
     */
    public EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            //------------------------------------------------------TESTING HOW THIS STUFF WORKS
            //Print out that a menu item was pressed
            System.out.println("\nRadioMenuItem Pressed");

            //Get the name of the item selected
            String selectedItem = ((RadioMenuItem)actionEvent.getSource()).getText();
            //Print out that name
            System.out.println("Pressed Button: " + selectedItem);

            //Find which type was selected
            String selectedType = "";
            //Make sure there's a toggled type
            if(typeToggle.getSelectedToggle() != null)
                selectedType = ((RadioMenuItem)typeToggle.getSelectedToggle()).getText();
            else
                selectedType = "No type selected";
            //Print out the selected type
            System.out.println("Toggled Type: " + selectedType);

            //Find which file was selected
            String selectedFile = "";
            //Make sure there's a toggled file
            if(fileToggle.getSelectedToggle() != null)
                selectedFile = ((RadioMenuItem)fileToggle.getSelectedToggle()).getText();
            else
                selectedFile = "No file selected";
            //Print out the selected file
            System.out.println("Toggled File: " + selectedFile);
        }
    };
}
