import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.importFromString("((A[10,10]-B[10,10])|(C[15,20]|(D[5,5]-(E[5,5]-F[5,10]))))-((G[15,10]|N[15,10])-(J[8,10]|(K[22,5]-L[22,5]))))");
        bt.exportAsFile("text1.txt");
        BinaryTree btFromFile = new ImportFromFile("text1.txt").get();
        btFromFile.exportAsFile("text2.txt");
        System.out.println("Importing : " + btFromFile.exportAsString());

        ArrayList<Rectangle> rec = new ArrayList<>();
        rec.add(new Rectangle(20, 10, "b"));
        rec.add(new Rectangle(20, 10, "n"));
        rec.add(new Rectangle(30, 10, "C"));
        rec.add(new Rectangle(30, 50, "D"));
        rec.add(new Rectangle(40, 30, "E"));
        rec.add(new Rectangle(40, 20, "F"));
        MergeRectangles m = new MergeRectangles(rec);
        System.out.println("Total Rectangle is : " + m.answers.size());
        m.answers.get(0).bt.exportAsFile("rec1.txt");
        m.answers.get(1).bt.exportAsFile("rec2.txt");
        m.answers.get(2).bt.exportAsFile("rec3.txt");
        m.answers.get(3).bt.exportAsFile("rec4.txt");
        bt.flipBinaryTree(bt.root);
        bt.exportAsFile("flip.txt");
        System.out.println("Flib : " + bt.exportAsString());
    }
}