public class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;
    //generics type variable <T>


    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        value = data; //can leave out this. as there's no confusion re data names
        this.left = left;
        this.right = right;
    }
}