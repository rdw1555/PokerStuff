package ptui;

import model.RITQTNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RITUncompress {
    private static int size;
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
            return;
        }
        Scanner s = null;
        try {
            File in  = new File(args[0]);
            s = new Scanner(in);
        }catch(FileNotFoundException fne){
            System.err.print("File not found");
        }
        assert s != null;
        size = s.nextInt();
        ArrayList<Integer> compressed  = new ArrayList<>();
        while(s.hasNextInt()){
            compressed.add(s.nextInt());
        }
        System.out.println(compressed.size());
        RITQTNode root = parse(compressed);
        ArrayList<Integer> result = preorder(root, new ArrayList<>());
        System.out.println(result);
        System.out.println(result.size());
        File uncompressed = new File(args[1]);
        try {
            FileWriter writer = new FileWriter(uncompressed);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static RITQTNode parse(ArrayList<Integer> compressed){
        int temp = compressed.remove(0);
        if(temp==-1){
            return new RITQTNode(-1,parse(compressed),parse(compressed),parse(compressed),parse(compressed));
        }else{
            return new RITQTNode(temp);
        }
    }
    public static ArrayList<Integer> preorder(RITQTNode root,ArrayList<Integer> result){
        if(root.getVal()==-1){
            preorder(root.getUpperLeft(),result);
            preorder(root.getUpperRight(),result);
            preorder(root.getLowerLeft(),result);
            preorder(root.getLowerRight(),result);
        }else{
            result.add(root.getVal());
        }

        return result;
    }

}