/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class extra{
 
    public static int countTotalLeaves(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.children.isEmpty()) {
            return 1;
        }

        int count = 0;
        for (Node child : root.children) {
            count += countTotalLeaves(child);
        }

        return count;
    }
    public static void addLeaf(Node root, String parentValue, String leafValue) {
        if (root == null) {
            return;
        }

        if (root.value.equals(parentValue)) {
            Node newLeaf = new Node(leafValue);
            root.children.add(newLeaf);
            return;
        }

        for (Node child : root.children) {
            addLeaf(child, parentValue, leafValue);
        }

    }
    public static void deleteLeaf(Node root, String leafValue) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            if (child.value.equals(leafValue) && child.children.isEmpty()) {
                root.children.remove(i);
                return;
            } else {
                deleteLeaf(child, leafValue);
            }
        }

    }


    
}