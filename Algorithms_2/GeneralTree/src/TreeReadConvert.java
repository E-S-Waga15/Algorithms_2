/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.util.Collections;

public class TreeReadConvert {

    public static Node Import(String filePath) {
          Map<String, Node> nodesMap = new HashMap<>();
        Node root = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            
                line = line.replaceAll("\\s", "");
                String[] parts = line.split("->");
                  String parentValue = parts[0];
                if(line.length()<4){
                    Node parentNode=new Node(parentValue);
                    if(root==null){ 
                        root=parentNode;
                    }
                    continue;
                }
          
                if (parts.length > 1) {
                    String[] childrenValues = parts[1].trim().split(",");
                    if (!nodesMap.containsKey(parentValue)) {
                        Node parentNode = new Node(parentValue.trim());
                        nodesMap.put(parentValue, parentNode);
                        if (root == null) {
                            root = parentNode;
                        }
                    }

                    for (String childValue : childrenValues) {
                        childValue = childValue.trim(); 
                        if (!nodesMap.containsKey(childValue)) {
                            Node childNode = new Node(childValue);
                            nodesMap.put(childValue, childNode);
                        }
                        nodesMap.get(parentValue).children.add(nodesMap.get(childValue));
                    }
                } else {
                  
                    if (!nodesMap.containsKey(parentValue)) {
                        Node parentNode = new Node(parentValue);
                        nodesMap.put(parentValue, parentNode);
                        if (root == null) {
                            root = parentNode;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }


    public static void Export(Node root, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            exportTreeHelper(root, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void exportTreeHelper(Node node, PrintWriter writer) {
        if (node == null) {
            return;
        }

        writer.print(node.value + " - > ");
        for (int i = 0; i < node.children.size(); i++) {
            writer.print(node.children.get(i).value);
            if (i < node.children.size() - 1) {
                writer.print(", ");
            }
        }
        writer.println();

        for (Node child : node.children) {
            if (!child.children.isEmpty()) {
                exportTreeHelper(child, writer);
            }
        }
    }

    public static BinaryTree convertToBinary(Node root) {
        if (root == null) {
            return null;
        }
        BinaryTree binaryTree = new BinaryTree(root.value);
        if (!root.children.isEmpty()) {
            Collections.reverse(root.children);
            binaryTree.right = convertToBinary(root.children.get(0));
            BinaryTree current = binaryTree.right;
            for (int i = 1; i < root.children.size(); i++) {
                current.left = convertToBinary(root.children.get(i));
                current = current.left;
            }

        }
        return binaryTree;
    }

    public static void printBinaryTree(BinaryTree root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null && root.right != null) {
            System.out.print(root.data + " - > ");
            System.out.println(root.left.data + ", " + root.right.data);
        } else if (root.right == null) {
            System.out.print(root.data + " - > ");
            System.out.println(root.left.data);
        } else {
            System.out.print(root.data + " - > ");
            System.out.println(root.right.data);
        }
        printBinaryTree(root.left);
        printBinaryTree(root.right);

    }

    public static Node convertToGeneral(BinaryTree root) {
        Node general = new Node(root.data);
        if (root.right != null) {
            general.children.add(convertToGeneral(root.right));
            BinaryTree current = root.right.left;
            int i = 1;

            while (current != null) {
                general.children.add(convertToGeneral(current));
                current = current.left;
                i++;
            }
 Collections.reverse(general.children);
        }
        return general;
    }


    public static void printGeneral(Node root) {
        if (root == null) {
            return;
        }
        if (!root.children.isEmpty()) {
            System.out.print(root.value + " - > ");
            for (int i = 0; i < root.children.size(); i++) {
                System.out.print(root.children.get(i).value);
                if (i < root.children.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
            for (int i = 0; i < root.children.size(); i++) {
                printGeneral(root.children.get(i));
            }
        }
    }
}
