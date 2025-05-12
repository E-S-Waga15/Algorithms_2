import java.util.*;

public class CanFormRectangle {
    public static int maxPapers=0;
    public static int n=0;
    // o(n^2)
    public static List<Node> correctToFormRectangle4(List<Node> nodes) {
        if (canFormRectangle(nodes)) {
            exportAsString4(nodes);
            return nodes;
        }
        for (int i = 0; i < nodes.size(); i++) {
            List<Node> subList = new ArrayList<>(nodes);
            subList.remove(i);
            if (canFormRectangle(subList)) {
                System.out.println("After removing paper: " + nodes.get(i).name);
                exportAsString4(subList);
                return subList;
            }
        }
        return new ArrayList<>();
    }

    private static void exportAsString4(List<Node> nodes) {
        if (canFormRectangle(nodes)) {
            System.out.println("\u001B[32mThe papers can form a rectangle.");
        } else {
            System.out.println("The papers cannot form a rectangle.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Exported Structure:\n");

        int width = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (i > 0) {
                if (width == nodes.get(i).width) {
                    sb.append("| ");
                } else {
                    sb.append("- ");
                }
            }
            sb.append(nodes.get(i).name)
                    .append("[")
                    .append(nodes.get(i).length)
                    .append(",")
                    .append(nodes.get(i).width)
                    .append("] ");
            width = nodes.get(i).width;
        }
        System.out.println(sb.toString());

    }
    // Function to export the structure as a string
    //o(n*sq_n)
    public static boolean canFormRectangle(List<Node> papers) {
        if (papers == null || papers.size() == 0) {
            return false;
        }

        // Calculate the total area of the small papers
        int totalArea = papers.stream().mapToInt(p -> p.length * p.width).sum();
        List<Integer> dimensions = new ArrayList<>();

        // Find all possible dimension pairs
        for (int i = 1; i <= Math.sqrt(totalArea); i++) {
            if (totalArea % i == 0) {
                dimensions.add(i);
                dimensions.add(totalArea / i);
            }
        }

        // Check each dimension pair
        for (int i = 0; i < dimensions.size(); i += 2) {
            int width = dimensions.get(i);
            int height = dimensions.get(i + 1);

            if (canArrange(papers, width, height) || canArrange(papers, height, width)) {
                return true;
            }
        }

        return false;
    }

    // Helper function to check if papers can be arranged in a given width and height
    //O(n * m^2)
    private static boolean canArrange(List<Node> papers, int width, int height) {
        Collections.sort(papers, (a, b) -> b.length * b.width - a.length * a.width); // Sort papers by area in descending order
        return canArrangeHelper(papers, width, height, 0, new boolean[height][width]);
    }

    private static boolean canArrangeHelper(List<Node> papers, int width, int height, int index, boolean[][] grid) {
        if (index == papers.size()) {
            return true;
        }
        Node paper = papers.get(index);
        for (int i = 0; i <= height - paper.width; i++) {
            for (int j = 0; j <= width - paper.length; j++) {
                if (canPlace(grid, i, j, paper)) {
                    place(grid, i, j, paper, true);
                    if (canArrangeHelper(papers, width, height, index + 1, grid)) {
                        return true;
                    }
                    place(grid, i, j, paper, false);
                }
            }
        }
        return false;
    }

    private static boolean canPlace(boolean[][] grid, int row, int col, Node paper) {
        for (int i = row; i < row + paper.width; i++) {
            for (int j = col; j < col + paper.length; j++) {
                if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void place(boolean[][] grid, int row, int col, Node paper, boolean value) {
        for (int i = row; i < row + paper.width; i++) {
            for (int j = col; j < col + paper.length; j++) {
                grid[i][j] = value;
            }
        }
    }



    // Function to correct the leaves to form a rectangle by deleting one leaf if necessary
    public static void Largestnumberofcases(List<Node> papers) {
        List<List<Node>> allCombinations = new ArrayList<>();
        findCombinations(papers, new ArrayList<>(), allCombinations);
        for (List<Node> combination : allCombinations) {
            if (canFormRectangle(combination)) {
                System.out.println("Found a valid combination:");
                exportAsString4(combination);
                printUsedPapers(combination);
                maxPapers = getMaxNumberOfPapers(allCombinations);
                n++;
                System.out.println();

            }
        }
    }
    // Function to export the structure as a string
    private static void exportAsString5(List<Node> papers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Exported Structure:\n");

        for (Node paper : papers) {
            sb.append(paper.name)
                    .append("[")
                    .append(paper.length)
                    .append(",")
                    .append(paper.width)
                    .append("] ");
        }

        System.out.println(sb.toString());

    }
    private static void printUsedPapers(List<Node> chosenPapers) {
        System.out.println("Number of papers used to form the rectangle: " + chosenPapers.size());
    }
    private static int getMaxNumberOfPapers(List<List<Node>> combinations) {
        int maxNumberOfPapers = 0;
        for (List<Node> combination : combinations) {
            if (canFormRectangle(combination) && combination.size() > maxNumberOfPapers) {
                maxNumberOfPapers = combination.size();
            }
        }
        return maxNumberOfPapers;
    }
    // O(2^n)
    private static void findCombinations(List<Node> papers, List<Node> currentCombination, List<List<Node>> allCombinations) {
        if (papers.isEmpty()) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        Node currentPaper = papers.remove(0);

        // Include current paper in combination
        currentCombination.add(currentPaper);
        findCombinations(papers, currentCombination, allCombinations);
        currentCombination.remove(currentCombination.size() - 1);

        // Exclude current paper from combination
        findCombinations(papers, currentCombination, allCombinations);
        papers.add(0, currentPaper);
    }


//
    static Node mergeNodeswidth(Node n1, Node n2) {
        if (n1.width == n2.width) {
            Node root = new Node(n1, n2, null, "-");
            root.width = n1.width;
            root.length = n1.length + n2.length;

            return root;
        } else {
            return null;
        }
    }
    static Node mergeNodeslength(Node n1, Node n2) {
        if (n1.length == n2.length) {
            Node root = new Node(n1, n2, null, "|");
            root.length = n1.length;
            root.width = n1.width + n2.width;

            return root;
        } else {
            return null;
        }
    }

    // تابع يبني ورقة من مجموعة أوراق صغيرة
    // يجب أن تكون جميع العقد على شكل مستطيل
    static Node BuildRectangleFromNodes(List<Node> al) {
        if (al.size() == 1) {
            return al.get(0);
        }
        for (int i = 0; i < al.size(); i++) {
            for (int j = i + 1; j < al.size(); j++) {
                Node n1 = al.get(i);
                Node n2 = al.get(j);

                Node mv = CanFormRectangle.mergeNodeswidth(n1, n2);
                Node mh = CanFormRectangle.mergeNodeslength(n1, n2);

                if (mv != null) {

                    List<Node> clone1 = new ArrayList<>(al);
                    clone1.remove(n1);
                    clone1.remove(n2);
                    clone1.add(mv);
                    Node t1 = BuildRectangleFromNodes(clone1);
                    if (t1 != null) {
                        return t1;
                    }
                }

                if (mh != null) {
                    List<Node> clone2 = new ArrayList<>(al);
                    clone2.remove(n1);
                    clone2.remove(n2);
                    clone2.add(mh);
                    Node t2 = BuildRectangleFromNodes(clone2);
                    if (t2 != null) {
                        return t2;
                    }
                }
            }
        }
        return null;
    }
    public static Node buildTree(List<Node> nodeList) {
        if (nodeList == null || nodeList.isEmpty()) {
            return null;
        }

        Node root = null;
        for (Node node : nodeList) {
            if (node.name!= null && node.length!= 0 && node.width!= 0) {
                if (root == null) {
                    root = node;
                } else {
                    Node current = root;
                    while (true) {
                        if (node.length < current.length) {
                            if (current.left == null) {
                                current.left = node;
                                break;
                            }
                            current = current.left;
                        } else {
                            if (current.right == null) {
                                current.right = node;
                                break;
                            }
                            current = current.right;
                        }
                    }
                }
            }
        }
        return root;
    }
}