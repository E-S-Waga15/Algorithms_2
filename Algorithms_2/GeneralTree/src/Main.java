/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) throws IOException {

   String filename="tree.txt";
       
       Node rootGeneral = TreeReadConvert.Import(filename);
            
       TreeReadConvert.Export(rootGeneral, "Exported.txt");
       BinaryTree rootBinary = TreeReadConvert.convertToBinary(rootGeneral);
        System.out.println("\u001B[34m BinaryTree : \n");
    TreeReadConvert.printBinaryTree(rootBinary);
        System.out.println("\n");
        System.out.println("\u001B[33m-------------------------------\n");
        System.out.println("GeneralTree :\n ");
       rootGeneral =TreeReadConvert.convertToGeneral(rootBinary);
 TreeReadConvert.printGeneral(rootGeneral );
      System.out.println("\u001B[35m++++++++++++++++++++++++++++++++++\n");
        System.out.println("---EXTRA---\n");
         int n=extra.countTotalLeaves(rootGeneral);
       System.out.println("count of leave" +"="+n+"\n");
 
        extra.addLeaf(rootGeneral,"K","m");
        System.out.println("\u001B[36m Addition completed"+"\n");
       TreeReadConvert.printGeneral(rootGeneral);
      
        extra.deleteLeaf(rootGeneral,"H");
        System.out.println("\u001B[32mDeletion complete"+"\n");
        TreeReadConvert.printGeneral(rootGeneral);
        
             SwingUtilities.invokeLater(() -> {
            TreeGUI gui = new TreeGUI();
            gui.setVisible(true);
        });
        

    }


    }
    
