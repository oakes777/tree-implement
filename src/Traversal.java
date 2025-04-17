import java.util.*;

public class Traversal {
  public static void main(String[] args) {
    int threshold = 50;

    TreeNode<Integer> root = new TreeNode<>(77, null, null);
    root.left = new TreeNode<>(22, null, null);
    root.left.right = new TreeNode<>(33, null, null);
    root.left.left = new TreeNode<Integer>(4, null, null);
    root.right = new TreeNode<>(87, null, null);
    root.right.left = new TreeNode<>(9, null, null);

    TreeNode<String> stringRoot = new TreeNode<>("hello", null, null);
    stringRoot.left = new TreeNode("hi", null, null);
    stringRoot.right = new TreeNode("hey", null, null);

    TreeNode<Integer> megaRoot = new TreeNode<>(0, null, null);
    TreeNode<Integer> current = megaRoot;

    for (int i = 1; i <= 999; i++) {
      current.left = new TreeNode<>(i, null, null);
      current = current.left;
    }

    //standard pre-, post-, in- order traversal
    System.out.print("Pre-Order Traversal: ");
    preOrder(root);
    System.out.println();
    System.out.print("Post-Order Traversal: ");
    postOrder(root);
    System.out.println();
    System.out.print("In-Order Traversal: ");
    inOrder(root);
    System.out.println();

    //preOrder with stringRoot; greaterThan
    System.out.print("preOrder with strings: ");
    preOrder(stringRoot);
    System.out.println();
    System.out.print("greaterThan25: ");
    greaterThan(root, 25);
    System.out.println();
    System.out.print("countNodes: ");
    System.out.print(countNodes(root));

    System.out.println();
    //wk7 thursday class
    // System.out.print("countLevels(root): " + countLevels(root));

    // preOrder(megaRoot);

    //wk8 Tuesday
    System.out.println();
    System.out.print("toSet method: ");
    System.out.println(toSet(root));
  }

  //return set of integers
  public static Set<Integer> toSet(TreeNode<Integer> node) {
    Set<Integer> result = new HashSet<>();

    //add everything to set - I want toSetHelper to do this
    toSetHelper(node, result);

    return result;
  }

  //add everything to set
  public static void toSetHelper(TreeNode<Integer> node, Set<Integer> result) {
    if(node == null) return;

    result.add(node.value);
    toSetHelper(node.left, result);
    toSetHelper(node.right, result);
  }

  //stack overflow could break this method
  //space complexity is O(n) because as we recurse we 'instantiate' the entire nodal structure!
  //depth-first search typically uses Stack to store values?
  public static <T> void preOrder(TreeNode<T> node) {
    if (node == null) return;
    
    System.out.print(node.value + " ");
    preOrder(node.left);
    preOrder(node.right);
  }

  public static <T> void preOrderIter(TreeNode<T> node) {
    // if (node == null) return;
    Stack<TreeNode<T>> nodeStack = new Stack<>();

    nodeStack.push(node);

    while(!nodeStack.empty()) {
      TreeNode<T> current = nodeStack.pop();
      //if current contains a 'null child' this if statement
      //punts us back up to top of while loop and the next
      //value in stack is pushed onto current, losing its null
      //and continues down the line.
      //if TreeNode<T> node has no children
      //after printing value of node, both children
      //are null, so this will loop from here twice then nodeStack
      //will be empty (no children of a null node!)
      //and we're done
      if(current == null) {
        continue; 
      } 
      System.out.println(current.value);
      nodeStack.push(current.right);
      nodeStack.push(current.left);
    }
  }

  //breadth-first search typically uses Queue
  public static <T> void printLevelOrder(TreeNode<T> node) {
    Queue<TreeNode<T>> queue = new LinkedList<>();

    queue.add(node);

    while(!queue.isEmpty()) {
      TreeNode<T> current = queue.poll();
      if(current == null) continue;

      System.out.println(current.value);
      queue.add(current.left);
      queue.add(current.right);
    }
  }

  public static <T> void postOrder(TreeNode<T> node) {
    if (node == null) return;
    
    postOrder(node.left);
    postOrder(node.right);
    System.out.print(node.value + " ");
  }

  public static <T> void inOrder(TreeNode<T> node) {
    if (node == null) return;
    
    inOrder(node.left);
    System.out.print(node.value + " ");
    inOrder(node.right);
  }

  // public static <Integer> void greaterThan50(TreeNode<Integer> node) {
  //   /*
  //    * if null return
  //    * if node.value > 50
  //    *  print node.value
  //    * greaterThan50(node.left)
  //    * greaterThan50(node.right)
  //    */
  //   if (node == null) return;
  //   if (node.value = 50) {
  //     System.out.println(node.value);
  //   }
  //   greaterThan50(node.left);
  //   greaterThan50(node.right);

  // }

  public static void greaterThan(TreeNode<Integer> node, int threshold) {
    /*
     * if null return
     * if node.value > 50
     *  print node.value
     * greaterThan50(node.left)
     * greaterThan50(node.right)
     */
    if (node == null) return;
    if (node.value > threshold) {
      System.out.print(node.value + " ");
    }
    greaterThan(node.left, threshold);
    greaterThan(node.right, threshold);

  }

  public static <T> int countNodes(TreeNode<T> node) {
    /* if node null return 0
     * count = 1
     * count = count + # nodes/left countNodes(node.left)
     * count = count + # nodes/right countNodes(node.right) */
    if (node == null) return 0;
    int count = 1;

    count += countNodes(node.left);
    count += countNodes(node.right);
    return count;
  }

  public static <T> int countLevels(TreeNode<T> node) {
    if(node == null) return 0;
    // ht of larger subtree +1
    int leftHeight = countLevels(node.left);
    int rightHeight = countLevels(node.right);
    int bigger = Math.max(leftHeight, rightHeight);

    return bigger + 1;
  }

  public static boolean allOdd(TreeNode<Integer> node) {
    if(node==null)return true; //we can choose either case, true makes
      //further implementation easier apparently; here, null=true

      //all the following could be simplified to this:
//return node.value % 2 == 0 && allOdd(node.left) && allOdd(node.right);
      //or...
      //return node.value % 2 == 0 && 
      //  allOdd(node.left) && 
      //  allOdd(node.right);
    if(node.value % 2 == 0) return false;
    boolean leftOdd = allOdd(node.left);
    boolean rightOdd = allOdd(node.right);
    if(leftOdd && rightOdd) {
      return true;
    }
    return false;
  }
}
