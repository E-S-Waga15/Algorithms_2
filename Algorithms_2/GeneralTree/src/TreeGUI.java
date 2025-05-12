import javax.swing.*;
import java.awt.*;
public class TreeGUI extends JFrame {

    private Node generalTree; // General tree
    private BinaryTree binaryTree; // Binary tree
    private boolean showGeneralTree = false;
    private boolean showBinaryTree = false;

    public TreeGUI() {
        setTitle("Tree Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK); // Black background color

        // Create buttons with visible colors on black background
        JButton binaryTreeButton = new JButton("Show Binary Tree");
        binaryTreeButton.setForeground(Color.WHITE); // Text color
        binaryTreeButton.setBackground(Color.GRAY); // Button background
        JButton generalTreeButton = new JButton("Show General Tree");
        generalTreeButton.setForeground(Color.WHITE);
        generalTreeButton.setBackground(Color.GRAY);

        // Add action listeners to the buttons
        binaryTreeButton.addActionListener(e -> {
            showBinaryTree = true;
            showGeneralTree = false;
            repaint();
        });
        generalTreeButton.addActionListener(e -> {
            showGeneralTree = true;
            showBinaryTree = false;
            repaint();
        });

        // Add buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK); // Panel background
        buttonPanel.add(binaryTreeButton);
        buttonPanel.add(generalTreeButton);
        add(buttonPanel, BorderLayout.NORTH);

        String filePath = "Exported.txt";
        generalTree = TreeReadConvert.Import(filePath);
        binaryTree = TreeReadConvert.convertToBinary(generalTree);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int centerY = 100;
        // Draw the general tree at the center of the window
        if (showGeneralTree && generalTree != null) {
            drawGeneralTree(generalTree, g, getWidth() / 2, centerY, getWidth() / Math.max(generalTree.children.size(), 1));
        }
        // Draw the binary tree at the center of the window
        if (showBinaryTree && binaryTree != null) {
            drawBinaryTree(binaryTree, g, getWidth() / 2, centerY, getWidth() / 4);
        }
    }

    private void drawGeneralTree(Node node, Graphics g, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }
        // Adjusted colors for visibility on black background 
        g.setColor(new Color(236, 118, 140));
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.BLACK); 
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(node.value, x - 6, y + 6);

        int newY = y + 80;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(22, 92, 117));
        int childX = x - xOffset * (node.children.size() - 1) / 2;
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            g2.drawLine(x, y + 20, childX, newY - 20);
            drawGeneralTree(child, g, childX, newY, xOffset / node.children.size());
            childX += xOffset;
        }
    }

private void drawBinaryTree(BinaryTree node, Graphics g, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }
        g.setColor(new Color(153, 50, 204)); 
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.WHITE); 
        g.drawString(node.data, x - 6, y + 6);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(245, 219, 60));
        int newY = y + 80;
        if (node.left != null) {
            g2.drawLine(x, y + 20, x - xOffset, newY - 20);
            drawBinaryTree(node.left, g, x - xOffset, newY, xOffset / 2);
        }
        if (node.right != null) {
            g2.drawLine(x, y + 20, x + xOffset, newY - 20);
            drawBinaryTree(node.right, g, x + xOffset, newY, xOffset / 2);
        }
    
 }
}



 
