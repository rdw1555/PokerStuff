package ptui;

import model.RITQTNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RITUncompress {
    //Global variable - size of the image
    private static int size;

    /**
     * main method
     * @param args - system arguments of teh compressed/uncomopressed file names
     */
    public static void main(String[] args) {
        //Check for usage error
        if (args.length != 2) {
            System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
            return;
        }

        //Create a null scanner to read from the input file
        Scanner s = null;
        try {
            File in  = new File(args[0]);
            s = new Scanner(in);
        }catch(FileNotFoundException fne){
            System.err.print("File not found");
        }

        //Assert statement to make IntelliJ happy
        assert s != null;

        //Set the global size variable (size of the image)
        size = s.nextInt();

        //Create an ArrayList of the compressed image values
        ArrayList<Integer> compressed  = new ArrayList<>();
        while(s.hasNextInt()){
            compressed.add(s.nextInt());
        }

        //Print it out (testing)
        System.out.println(compressed.size());

        //Create a new RITQTNode off of the compressed image file
        RITQTNode root = parse(compressed);

        //Run the populateUncompressed method to fill in the 2D array of the board
        ArrayList<Integer> result = populateUncompressed(root, new ArrayList<>());

        //More testing stuff
        System.out.println(result);
        System.out.println(result.size());

        //Get the uncompressed file to write to it
        File uncompressed = new File(args[1]);
        try {
            FileWriter writer = new FileWriter(uncompressed);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * parse - Parses the arraylist of compressed values to create a quadtree
     * @param compressed - arraylist of compressed values
     * @return - a completed quadtree of the compressed image
     */
    public static RITQTNode parse(ArrayList<Integer> compressed){
        int temp = compressed.remove(0);
        if(temp==-1){
            return new RITQTNode(-1,parse(compressed),parse(compressed),parse(compressed),parse(compressed));
        }else{
            return new RITQTNode(temp);
        }
    }


    public static ArrayList<Integer> populateUncompressed(RITQTNode root,ArrayList<Integer> result){
        if(root.getVal()==-1){
            populateUncompressed(root.getUpperLeft(),result);
            populateUncompressed(root.getUpperRight(),result);
            populateUncompressed(root.getLowerLeft(),result);
            populateUncompressed(root.getLowerRight(),result);
        }else{
            result.add(root.getVal());
        }

        return result;
    }

}