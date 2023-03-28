public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(3);
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.insert(3);
        //tree.insert(4);
        //tree.insert(6);
        //tree.insert(6);
        //tree.insert(9);

        BinaryTree tree2 = new BinaryTree();

        tree2.insert(3);
        tree2.insert(1);
        tree2.insert(0);
        tree2.insert(2);
        tree2.insert(5);
        tree2.insert(4);
        tree2.insert(6);
        tree2.insert(6);
        tree2.insert(9);

        //System.out.println(tree.equals(tree2));

        //tree.postOrderDepthFirstTraversal(number -> System.out.println(number));
        //System.out.println("\n" + tree.depth(7));

        //System.out.println("\n" + tree.height(3));

        //System.out.println(tree.minimumValueForUnsortedTree());

        //System.out.println(tree.isBinarySearchTree());

        //System.out.println(tree.nodesAtKDistance(2));

        tree.levelOrderBreadthFirstTraversal(item -> System.out.println(item));

    }

}