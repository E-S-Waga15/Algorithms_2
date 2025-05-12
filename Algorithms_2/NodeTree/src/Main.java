import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tree tree = new Tree();
        Node node = new Node();
//        System.out.println("ENTER ");
//        String s =in.next();
      String code = "((A[10,10]-B[10,10])|(C[15,20]|(D[5,5]-(E[5,5]-F[5,10]))))-((G[15,10]|N[15,10])-(J[8,10]|(K[22,5]-p[22,5]))))";

      //((A[10,10]-B[10,10])|(C[15,20]|(D[5,5]-(E[5,5]-F[5,10]))))|N[15,10])-(J[8,10]|(K[22,5]-L[22,5])))
        // ((A[10,10]-B[10,10])|(C[15,20]|(D[5,5]-(E[5,5]-F[5,10]))))-((G[15,10]|N[15,10])-(J[8,10]|(K[22,5]-L[22,5]))))
        node = tree.ImportfromFormula(code);
        String export=Tree.Export(node);
        System.out.println(export);

//        //TODO PreOrder

        System.out.println("\u001B[34m export_2");
        String str = tree.exportToFormula(node);
        System.out.println(str);
        System.out.println();

        RectangleNode n2 =new RectangleNode();
     //  String t= n2.importFromFile("input.txt");
    //    System.out.println(" \n"+t);
        RectangleNode.exportFromFile(node, "output.txt");
        RectangleNode.DrawImag();
        List<Node> rectangleNodes = new ArrayList<>();
//        rectangleNodes.add(new Node("-", 0, 0));
        rectangleNodes.add(new Node("A", 20, 10));
        rectangleNodes.add(new Node("B", 20, 10));
        rectangleNodes.add(new Node("C", 30, 10));
        rectangleNodes.add(new Node("D", 30, 50));
        rectangleNodes.add(new Node("E", 40, 30));
        rectangleNodes.add(new Node("F", 40, 20));

      CanFormRectangle.correctToFormRectangle4(rectangleNodes);

        Node a = new Node("A", 10, 20);
        Node b = new Node("B", 10, 20);
        Node c = new Node("C", 10, 30);
        Node d = new Node("D", 50, 30);
        Node e = new Node("E", 30, 40);
        Node f = new Node("F", 20, 40);
        d.left=a;
        a.right=b;
        d.right=e;
        e.left=f;
       b.right=c;

        RectangleNode.exportFromFile(d, "NewRectangle.txt");
     CanFormRectangle.Largestnumberofcases(rectangleNodes);
        System.out.println("\u001B[35mMax number of papers used to form the rectangle: " + CanFormRectangle.maxPapers);
        System.out.println("\u001B[36mMax number of papers the rectangle: " + (CanFormRectangle.n));

          RectangleNode.exportAndRotate(node, "Rotate.txt");
        System.out.println("\u001B[39m");
        SwingUtilities.invokeLater(() -> new TreeGUI());

//          Node nm=CanFormRectangle.BuildRectangleFromNodes(rectangleNodes);
//        System.out.println("nnn:"+Tree.Export(nm));
//        node = tree.ImportfromFormula(Tree.Export(nm));
//        RectangleNode.exportFromFile(node, "NewRectangle.txt");

    }
}
//    String n="(D[50,30]-(E[30,40]|F[20,40]))|(C[10,30]-(A[10,20]-B[10,20]))";
//   String m="(C[30,10]-D[30,50])|((m[20,10]|B[20,10])-(E[40,30]-F[40,20]))";

//        d.left=a;
//        a.right=b;
//        d.right=e;
//        e.left=f;
//       b.right=c;
//c2
// e.left=f;
//         f.left=c;
//         c.right=b;
//         e.right=d;
//         b.right=a;
//c2
//c.left=e;
//        e.left=f;
//
//        c.right=b;
//        e.right=d;
//        b.right=a;

// Node r = new Node("-", 0, 0);
//        Node A = new Node("A", 20, 10);
//        Node B = new Node("B", 20, 10);
//        Node C = new Node("C", 30, 10);
//        Node D = new Node("D", 30, 50);
//        Node E = new Node("E", 40, 30);
//        Node F = new Node("F", 40, 20);
//       // r.right=a;
//        A.left = B;
//        B.left = C;
//        A.right = D;
//        D.left = E;
//        E.right = F;