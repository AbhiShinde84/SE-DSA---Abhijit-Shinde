import java.util.Scanner;
import java.util.Stack;

class TreeNode {
    char val;
    TreeNode left, right;

    TreeNode(char val) {
        this.val = val;
        left = right = null;
    }
}

public class DSA4 {
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    public static TreeNode constructTree(String prefix) {
        if (prefix == null || prefix.length() == 0)
            return null;

        Stack<TreeNode> stack = new Stack<>();

        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (isOperator(c)) {
                TreeNode node = new TreeNode(c);
                node.left = stack.pop();
                node.right = stack.pop();
                stack.push(node);
            } else {
                stack.push(new TreeNode(c));
            }
        }

        return stack.pop();
    }

    public static void PostfixTraversal(TreeNode root) {
        if (root == null)
            return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    System.out.print(temp.val + " ");
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        System.out.print(temp.val + " ");
                    }
                } else {
                    current = temp;
                }
            }
        }
    }

    public static void deleteTree(TreeNode root) {
        if (root == null)
            return;

        deleteTree(root.left);
        deleteTree(root.right);

        root.left = null;
        root.right = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeNode root = null;

        while (true) {
            System.out.println("------------- MENU -------------");
            System.out.println("\nExpression Tree Operations:");
            System.out.println("1. Construct Expression Tree");
            System.out.println("2. Traverse Expression Tree (Postfix)");
            System.out.println("3. Delete Expression Tree");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the prefix expression: ");
                    String prefixExpression = scanner.next();
                    root = constructTree(prefixExpression);
                    System.out.println("Expression tree constructed successfully.");
                    break;
                case 2:
                    if (root != null) {
                        System.out.println("Postfix Traversal:");
                        PostfixTraversal(root);
                    } else {
                        System.out.println("Expression tree is empty. Construct a tree first.");
                    }
                    break;
                case 3:
                    if (root != null) {
                        deleteTree(root);
                        root = null;
                        System.out.println("Expression tree deleted successfully.");
                    } else {
                        System.out.println("Expression tree is already empty.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
