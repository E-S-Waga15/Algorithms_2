import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RectangleNode {
       String name;
        int length;
        int width;
        RectangleNode left;
        RectangleNode right;
    RectangleNode up;
    int px, py;


    public RectangleNode() {
        name = "";
        length = 1;
        width = 1;
    }
    public RectangleNode(String name, int length, int width) {
            this.name = name;
            this.length = length;
            this.width = width;
            this.left = null;
            this.right = null;
        }

    public RectangleNode(String name, int length, int width, RectangleNode left, RectangleNode right) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.left = left;
        this.right = right;
    }
    // import file
    //O(n + m*n)
    public  static String importFromFile(String path) {
        String[] readFile = readFile(path);
        char[][] draw = strArrToCharArr(readFile);
        RectangleNode tree = RectangleNode.treeFromDrawing(draw);
        String str=tree.toStringTree();
        str = str.substring(0, 3) +'A'+ str.substring(4);
        System.out.println("\u001B[35m Successfully imported from file ... /\\-_-/\\");
        return str;
    }
    static String[] readFile(String fileName) {
        try {
            String[] lines = Files.readAllLines(Paths.get(fileName)).toArray(new String[0]);
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //Q3 import draw
    static boolean checkDrawSplitI(char[][] a, int i, int j1, int j2) {
        for (int j = j1; j <= j2; j++) {
            if (a[i][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    static boolean checkDrawSplitJ(char[][] a, int j, int i1, int i2) {
        for (int i = i1; i <= i2; i++) {
            if (a[i][j] == ' ') {
                return false;
            }
        }
        return true;
    }
    //O(m*n)
    static RectangleNode treeFromDrawing(char[][] a) {
        // نقوم بإنشاء ورقة تحتوي كامل الرسمة
        RectangleNode root = new RectangleNode();
        root.name = "" + a[1][1];
        root.px = 0;
        root.py = 0;
        root.length = a.length;
        root.width = a[0].length;
        SplitMap(a, root);
        return root;
    }

    static void SplitMap(char[][] a, RectangleNode root) {
        //System.out.println("splitting " + root);
        int py1 = root.py + 1;
        int px1 = root.px + 1;
        int py2 = root.py + root.length - 1;
        int px2 = root.px + root.width - 1;

        for (int i = py1; i <= py2; i++) {
            if (i == a.length - 1) {
                break;
            }
            if (checkDrawSplitI(a, i, px1, px2)) {
                //System.out.println("split @ i = " + i);
                RectangleNode n1 = new RectangleNode();
                n1.name = root.name;
                n1.width = root.width;
                n1.length = i - py1 + 1;
                n1.px = root.px;
                n1.py = root.py;
                SplitMap(a, n1);

                RectangleNode n2 = new RectangleNode();
                n2.name = "" + a[i + 1][px1];

                n2.width = root.width;
                n2.length = root.length - n1.length;
                n2.px = root.px;
                n2.py = root.py + n1.length;
                SplitMap(a, n2);

                root.left = n1;
                root.right = n2;
                root.name = "-";
                n1.up = root;
                n2.up = root;
            }
        }

        for (int j = px1; j <= px2; j++) {
            if (j == a[0].length - 1) {
                break;
            }
            if (checkDrawSplitJ(a, j, py1, py2)) {
                //System.out.println("split @ j = " + j);

                RectangleNode n1 = new RectangleNode();
                n1.name = root.name;
                n1.length = root.length;
                n1.width = j - px1 + 1;
                n1.px = root.px;
                n1.py = root.py;
                SplitMap(a, n1);

                RectangleNode n2 = new RectangleNode();
                n2.name = "" + a[py1][j + 1];
                n2.length = root.length;
                n2.width = root.width - n1.width;
                n2.px = root.px + n1.width;
                n2.py = root.py;
                SplitMap(a, n2);

                root.left = n1;
                root.right = n2;
                root.name = "|";
                n1.up = root;
                n2.up = root;
            }
        }
    }
    public String toStringTree() {
            String s = "";
            if (left != null) {
                s = "(" + left.toStringTree() + ")";
            }
            s = s + toStringNodeExport();
            if (right != null) {
                s = s + "(" + right.toStringTree() + ")";
            }
            return s;
        }
    public String toStringNodeExport() {
        if (this.isLeaf()) {
            return name + "[" + width + "," + length + "]";
        } else {
            return name;
        }

    }

    static char[][] strArrToCharArr(String[] strIn) {
        char[][] chars = new char[strIn.length][];
        for (int i = 0; i < strIn.length; i++) {
            chars[i] = strIn[i].toCharArray();
        }

        return chars;
    }
    boolean isLeaf() {
        if (this.left == null && right == null) {
            return true;
        }
        return false;
    }
       // export file

    //O(n + m * n)

    public static void exportFromFile(Node root, String filename) {
        int width = calculateTotalWidth(root);
        int height = calculateTotalHeight(root);

        char[][] canvas = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = ' ';
            }
        }

        drawNode(canvas, root, 0, 0);
        System.out.println("\u001B[33m Successfully exported to file ... \\/-_-\\/ ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (char[] row : canvas) {
                writer.write(row);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void drawNode(char[][] canvas, Node node, int x, int y) {
        if (node == null) return;

        // Draw the top and bottom borders
        for (int i = 0; i < node.length; i++) {
            canvas[y][x + i] = '-';
            canvas[y + node.width - 1][x + i] = '-';
        }

        // Draw the left and right borders
        for (int i = 0; i < node.width; i++) {
            canvas[y + i][x] = '|';
            canvas[y + i][x + node.length - 1] = '|';
        }

        // Place the name in the center of the node's rectangle
        int nameStart = x + (node.length - node.name.length()) / 2;
        for (int i = 0; i < node.name.length(); i++) {
            canvas[y + node.width / 2][nameStart + i] = node.name.charAt(i);
        }

        // Draw children based on the special node name
        if (node.name.equals("|") && node.left != null && node.right != null) {
            // Draw left child node next to right child node
            drawNode(canvas, node.left, x + node.length, y);
            drawNode(canvas, node.right, x + node.length + calculateTotalWidth(node.left), y);
        } else if (node.name.equals("-") && node.left != null && node.right != null) {
            // Draw right child node below left child node
            drawNode(canvas, node.left, x, y + node.width);
            drawNode(canvas, node.right, x, y + node.width + calculateTotalHeight(node.left));
        } else {
            // Draw left child node if exists
            if (node.left != null) {
                drawNode(canvas, node.left, x + node.length, y);
            }
            // Draw right child node if exists
            if (node.right != null) {
                drawNode(canvas, node.right, x, y + node.width);
            }
        }
    }

    private static int calculateTotalWidth(Node node) {
        if (node == null) return 0;
        int leftWidth = calculateTotalWidth(node.left);
        int rightWidth = calculateTotalWidth(node.right);
        if (node.name.equals("|") && node.left != null && node.right != null) {
            return node.length + leftWidth + rightWidth;
        }
        return node.length + Math.max(leftWidth, rightWidth);
    }

    private static int calculateTotalHeight(Node node) {
        if (node == null) return 0;
        int leftHeight = calculateTotalHeight(node.left);
        int rightHeight = calculateTotalHeight(node.right);
        if (node.name.equals("-") && node.left != null && node.right != null) {
            return node.width + leftHeight + rightHeight;
        }
        return node.width + Math.max(leftHeight, rightHeight);
    }


    //6
    // O(n + m * n)
    public static void exportAndRotate(Node root, String filename) {
        int width = calculateTotalWidth(root);
        int height = calculateTotalHeight(root);

        char[][] canvas = new char[height][width];

        // Initialize canvas with empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = ' ';
            }
        }

        // Draw the tree on the rotated canvas
        drawNode2(canvas, root, 0, 0);

        // Rotate the canvas
        char[][] rotatedCanvas = rotateCanvas(canvas, height, width);

        // Print message and export to file
        System.out.println("\u001B[37m Successfully exported to file ... \\/-_-\\/ ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (char[] row : rotatedCanvas) {
                writer.write(row);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void drawNode2(char[][] canvas, Node rectangleNode, int x, int y) {

            if (rectangleNode == null) return;

            // Draw the top and bottom borders
            for (int i = 0; i < rectangleNode.length; i++) {
                canvas[y][x + i] = '|';
                canvas[y + rectangleNode.width - 1][x + i] = '|';
            }

            // Draw the left and right borders
            for (int i = 0; i < rectangleNode.width; i++) {
                canvas[y + i][x] = '-';
                canvas[y + i][x + rectangleNode.length - 1] = '-';
            }

            // Place the name in the center of the node's rectangle
            int nameStart = x + (rectangleNode.length - rectangleNode.name.length()) / 2;
            for (int i = 0; i < rectangleNode.name.length(); i++) {
                canvas[y + rectangleNode.width / 2][nameStart + i] = rectangleNode.name.charAt(i);
            }

            // Draw children based on the special node name
            if (rectangleNode.name.equals("|") && rectangleNode.left != null && rectangleNode.right != null) {
                // Draw left child node next to right child node
                drawNode2(canvas, rectangleNode.left, x + rectangleNode.length, y);
                drawNode2(canvas, rectangleNode.right, x + rectangleNode.length + calculateTotalWidth(rectangleNode.left), y);
            } else if (rectangleNode.name.equals("-") && rectangleNode.left != null && rectangleNode.right != null) {
                // Draw right child node below left child node
                drawNode2(canvas, rectangleNode.left, x, y + rectangleNode.width);
                drawNode2(canvas, rectangleNode.right, x, y + rectangleNode.width + calculateTotalHeight(rectangleNode.left));
            } else {
                // Draw left child node if exists
                if (rectangleNode.left != null) {
                    drawNode2(canvas, rectangleNode.left, x + rectangleNode.length, y);
                }
                // Draw right child node if exists
                if (rectangleNode.right != null) {
                    drawNode2(canvas, rectangleNode.right, x, y + rectangleNode.width);
                }
            }
        }
    private static char[][] rotateCanvas(char[][] canvas, int height, int width) {
        char[][] rotatedCanvas = new char[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                rotatedCanvas[i][j] = canvas[j][i];
            }
        }
        return rotatedCanvas;
    }
//    Extension
    public static void DrawImag(){
        int width = 400;
        int height = 300;

        // Create a buffered image
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Get the graphics object
        Graphics2D g2d = bufferedImage.createGraphics();

        // Set the background color and clear the image
        g2d.setColor(Color.getHSBColor(0.78f, 0.8f, 0.7f));
        g2d.fillRect(0, 0, width, height);

        // Set the color for drawing
        g2d.setColor(Color.BLACK);

        // Draw the rectangles and labels
        g2d.drawRect(0, 0, 200, 100); // A
        g2d.drawRect(200, 0, 100, 100); // B
        g2d.drawRect(300, 0, 100, 100); // C
        g2d.drawRect(0, 100, 200, 200); // D
        g2d.drawRect(200, 100, 200, 100); // E
        g2d.drawRect(200, 200, 200, 100); // F

        // Set the font for the labels
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        // Draw the labels
        g2d.drawString("A", 90, 60);
        g2d.drawString("B", 240, 60);
        g2d.drawString("C", 340, 60);
        g2d.drawString("D", 90, 200);
        g2d.drawString("E", 290, 160);
        g2d.drawString("F", 290, 260);

        // Dispose the graphics object
        g2d.dispose();

        // Save the image to a file
        try {
            ImageIO.write(bufferedImage, "png", new File("output.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
