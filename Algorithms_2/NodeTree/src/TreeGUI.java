import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TreeGUI extends JFrame {
    private JTextField textField;
    private JTextField nameField;
    private JTextField lengthField;
    private JTextField widthField;
    private JButton button;
    private JPanel treePanel;
    private Tree tree;
    private JFrame treeFrame;
    String name;
    private int length;
    private int width;
    static List<Node> dataList = new ArrayList<>();
    GridBagConstraints gbc = new GridBagConstraints();

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int length = 0;
            int width = 0;
            try {
                length = Integer.parseInt(lengthField.getText());
                width = Integer.parseInt(widthField.getText());
                Node newData = new Node(name, length, width);
                System.out.println( Tree.Export(newData));
                dataList.add(newData);
                nameField.setText("");
                lengthField.setText("");
                widthField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "الرجاء إدخال أرقام صحيحة للطول والعرض.");
            }
        }
    }

    class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Node N=CanFormRectangle.buildTree(dataList);
            System.out.println(Tree.Export(N));
            nameField.setText("");
            lengthField.setText("");
            widthField.setText("");
        }
    }

    public TreeGUI() {


        JFrame inputFrame = new JFrame("Enter");
        JPanel inputPanel = new JPanel(new FlowLayout());
        textField = new JTextField(50);
        button = new JButton("Import");
        button.setForeground(Color.BLUE);
        button.setBackground(Color.WHITE);

        JLabel label = new JLabel("Enter The Series:");
        label.setForeground(Color.BLUE);
        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.add(button);
        inputPanel.setBackground(Color.LIGHT_GRAY);
        inputFrame.add(inputPanel, BorderLayout.NORTH);
        nameField = new JTextField(20);
        lengthField = new JTextField(20);
        widthField = new JTextField(20);

        JPanel dataInputPanel = new JPanel(new FlowLayout());
        dataInputPanel.add(new JLabel("Name:"));
        dataInputPanel.add(nameField);
        dataInputPanel.add(new JLabel("Length:"));
        dataInputPanel.add(lengthField);
        dataInputPanel.add(new JLabel("Width:"));
        dataInputPanel.add(widthField);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonListener());
        buttonPanel.add(backButton);



        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Adjusted tree position and size
                int treeX = panelWidth / 8;
                int treeY = panelHeight - 300;
                int hGap = 60;
                int vGap = 60;

                // Draw a binary tree at the bottom left with 10 nodes
                g.setColor(Color.BLACK);
                drawBinaryTree(g, treeX, treeY, hGap, vGap, 1, 10);

                // Adjusted rectangles position
                int rectWidth = 80;
                int rectHeight = 40;
                int startX = panelWidth - 300;
                int firstRowY = panelHeight - 100;
                int secondRowY = firstRowY - rectHeight;
                Color[] colors = {Color.getHSBColor(0.35f, 0.5f, 0.95f),
                        Color.getHSBColor(0.0f, 0.6f, 1.0f),
                        Color.getHSBColor(0.15f, 1.0f, 1.0f),
                        Color.getHSBColor(0.08f, 0.5f, 0.9f) ,
                        Color.getHSBColor(0.60f, 0.4f, 0.9f),
                        Color.getHSBColor(0.78f, 0.8f, 0.7f)};
                for (int i = 0; i < colors.length; i++) {
                    int currentX = startX + (i % 3) * rectWidth;
                    int currentY = (i < 3) ? secondRowY : firstRowY;
                    g.setColor(colors[i]);
                    g.fillRect(currentX, currentY, rectWidth, rectHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(currentX, currentY, rectWidth, rectHeight);
                }
            }

            public static   void drawBinaryTree(Graphics g, int x, int y, int hGap, int vGap, int current, int max) {
                if (current > max) return;

                // Draw the current node
                g.setColor(Color.BLACK);
                g.fillOval(x, y, 30, 30);
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(current), x + 10, y + 18);

                // Calculate positions for left and right children
                int leftX = x - hGap;
                int rightX = x + hGap;
                int childY = y + vGap;

                // Draw the left child and connecting line
                if (current * 2 <= max) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x + 15, y + 30, leftX + 15, childY);
                    drawBinaryTree(g, leftX, childY, hGap / 2, vGap, current * 2, max);
                }

                // Draw the right child and connecting line
                if (current * 2 + 1 <= max) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x + 15, y + 30, rightX + 15, childY);
                    drawBinaryTree(g, rightX, childY, hGap / 2, vGap, current * 2 + 1, max);
                }
            }
        };


        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(dataInputPanel, BorderLayout.NORTH);
        centerPanel.add(customPanel, BorderLayout.CENTER);
        inputFrame.add(centerPanel, BorderLayout.CENTER);

        inputFrame.add(buttonPanel, BorderLayout.SOUTH);

        inputFrame.setSize(800, 600);
        inputFrame.setVisible(true);

        inputFrame.setSize(800, 600);
        inputFrame.setVisible(true);
// واجهة الشجرة
        treeFrame = new JFrame("Binary Tree");
        JLabel titleLabel = new JLabel("Binary Tree", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        treeFrame.add(titleLabel, BorderLayout.NORTH);
        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (tree != null && tree.root != null) {
                    drawTree(g, getWidth() / 2, 40, tree.root, getWidth() / 4);
                }
            }
            private void drawTree(Graphics g, int x, int y, Node node, int horizontalGap) {
                if (node == null) {
                    return;
                }

                int dy = 80;

                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));

                Color darkBlue = new Color(7, 4, 4);

                if (node.left != null) {
                    g2.setColor(darkBlue);
                    g2.drawLine(x, y + 30, x - horizontalGap, y + dy);
                    drawTree(g, x - horizontalGap, y + dy, node.left, horizontalGap / 2);
                }

                if (node.right != null) {
                    g2.setColor(darkBlue);
                    g2.drawLine(x, y + 30, x + horizontalGap, y + dy);
                    drawTree(g, x + horizontalGap, y + dy, node.right, horizontalGap / 2);
                }
                g2.setColor(new Color(255, 182, 193));
                g2.fillOval(x - 30, y, 60, 60);
                g2.setColor(darkBlue);
                g2.drawOval(x - 30, y, 60, 60);
                g.setFont(new Font("Serif", Font.BOLD, 18));
                g.drawString(String.valueOf(node.name), x - 5, y + 35);
            }
        };
        treePanel.setPreferredSize(new Dimension(600, 400));
        treePanel.setBackground(new Color(245, 245, 220));
        treeFrame.add(treePanel);
        treeFrame.setSize(800, 600);
        treeFrame.setVisible(false);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = textField.getText().trim();
                if (!code.isEmpty()) {
                    tree = new Tree();
                    Node root = tree.ImportfromFormula(code);
                    treePanel.repaint();
                    treeFrame.setVisible(true);
                    RectangleNode.exportFromFile(root,"wajout.txt");
                } else {
                    JOptionPane.showMessageDialog(inputFrame, "Please enter a valid code.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}