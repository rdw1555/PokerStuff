package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;

public class RITGUI extends Application {
    //Label for bottom stats
    Label bottomTxt;

    //ToggleGroup for compression type
    ToggleGroup typeToggle;
    //ToggleGroup for file
    ToggleGroup fileToggle;

    //global start button
    Button btnStart;

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
        typeToggle.getToggles().addAll(compressingC, uncompressingC);

        //Add the types to the menu
        dropType.getItems().addAll(compressingC, uncompressingC);
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
        //We set the individual events instead of just the menuItem event because this way we can find which individual file was selected
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
        //add the files to the ToggleGroup
        fileToggle.getToggles().addAll(file1, file2, file3, file4, file5, file6, file7, file8, file9, file10, file11, file12, file13, file14, file15);

        //Add the file items to dropOne
        dropFile.getItems().addAll(file1, file2, file3, file4, file5, file6, file7, file8, file9, file10, file11, file12, file13, file14, file15);

        //Add the menus to the menuBar
        menuBar.getMenus().addAll(dropType, dropFile);

        //Create a start button to start the simulation
        btnStart = new Button("Start");
        //Give it the customized start action handler
        btnStart.setOnAction(startHandler);
        //Set it initially disabled (to prevent issues of not having a file)
        btnStart.setDisable(true);

        //Create a HBox to store the menuBar and start button in
        HBox hbox = new HBox(menuBar, btnStart);
        //Give the button the horizontal growth priority so that it is positioned right next to the menu bar
        HBox.setHgrow(menuBar, Priority.NEVER);
        HBox.setHgrow(btnStart, Priority.ALWAYS);

        mainPane.setTop(hbox);

        //Add the menuBar to the pane
        //mainPane.setTop(menuBar);
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


            //Check if there are two toggled options in both ToggleGroups
            if(typeToggle.getSelectedToggle() != null){
                if(fileToggle.getSelectedToggle() != null){
                    //Enable the start button
                    btnStart.setDisable(false);
                }
            }
        }
    };

    /**
     * buttonHandler - let's us know when the start menuItem was selected
     * @precondition - the compression type and files are already selected (toggled)
     */
    public EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            //------------------------------------------------------TESTING HOW THIS STUFF WORKS
            //Print out that the start button was pressed
            System.out.println("\n\"Started\"");

            //Disable the start button
            btnStart.setDisable(true);

            //Untoggle the two toggled options
            typeToggle.selectToggle(null);
            fileToggle.selectToggle(null);
        }
    };
}
