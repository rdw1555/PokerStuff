package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RITViewer extends Application {
    private static String fileName;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(512, 512);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        ArrayList<Integer>image = new ArrayList<>();
        Scanner in = null;
        try {
            File imageFile = new File(fileName);
            in = new Scanner(imageFile);
        }catch (FileNotFoundException fne){
            System.err.println("File not found");
        }
        while(in.hasNextInt()){
            image.add(in.nextInt());
        }
        int imageSize = (int)Math.sqrt(image.size());
        int squareSize = 512/imageSize;
        for(int i = 0; i < imageSize; i++){
            for(int j = 0; j < imageSize; j++){
                double color = image.remove(0)/256.0;
                gc.setFill(new Color(color,color,color,1.0));
                gc.fillRect(j*squareSize,i*squareSize,squareSize,squareSize);
            }
        }
        stage.setScene(new Scene(root));
        stage.show();


    }

    public static void main(String[] args) {
        fileName = args[0];
        Application.launch(args);
    }
}
