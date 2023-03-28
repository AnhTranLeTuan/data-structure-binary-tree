import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Sorted tree (Binary search tree)
public class BinaryTree {

    private class Node {

        private Integer item;
        private Node leftNode;
        private Node rightNode;

        public Node(Integer item){
            this.item = item;
        }
    }

     private Node rootNode;

    public BinaryTree(){}

    public BinaryTree(Integer item){
        rootNode = createNewNode(item);
    }

    public void insert(Integer item){
        Node node = createNewNode(item);

        if (rootNode == null) {
            rootNode = node;
            return;
        }

        traverseToInsert(node, rootNode);
    }

    public boolean find(Integer item){
        Node node = createNewNode(item);

        if (rootNode == null) {
            return false;
        }

        return traverseToFind(node, rootNode);
    }

    public void remove(Integer item){
        Node node = createNewNode(item);

        if (rootNode == null) {
            return;
        }

        // Not finish
    }

    // Root Left Right
    public void preOrderDepthFirstTraversal(Consumer<Integer> consumer){
        preOrderDepthFirstTraversal(rootNode, consumer);
    }

    // Left Root Right
    public void inOrderDepthFirstTraversal(Consumer<Integer> consumer){
        inOrderDepthFirstTraversal(rootNode, consumer);
    }

    // Left Right Root
    public void postOrderDepthFirstTraversal(Consumer<Integer> consumer){
        postOrderDepthFirstTraversal(rootNode, consumer);
    }

    public void levelOrderBreadthFirstTraversal(Consumer<Integer> consumer){
        if (rootNode == null)
            return;

        int numberOfLevels = height(rootNode.item);

        for (int i = 0; i <= numberOfLevels; i++){
            List<Integer> collection = nodesAtKDistance(i);

            for (var item : collection)
                consumer.accept(item);
        }
    }

    public int depth(Integer item){
        Node node = createNewNode(item);

        if (rootNode == null) {
            return -1;
        }

        DepthValue depthValue = new DepthValue();

        if (traverseToCalculateDepth(node, rootNode, depthValue))
            return depthValue.counter;

        return -1;
    }

    public int height(Integer item){
        Node node = createNewNode(item);

        if (rootNode == null) {
            return -1;
        }

        return traverseToCalculateHeight(node, rootNode);
    }

    public int minimumValueForSortedTree(){
        if (rootNode == null)
            return -1;

        return traverseToFindMinimumValueForSortedTree(rootNode);
    }

    // Although this class's tree is sorted, this method is for the assumption that it is not sorted
    public int minimumValueForUnsortedTree(){
        return traverseToFindMinimumValueForUnsortedTree(rootNode);
    }

    public boolean equals(BinaryTree secondTree){
        return traverseToCompareTrees(rootNode, secondTree.rootNode);
    }

    public boolean isBinarySearchTree(){
        return traverseToValidateTheBinarySearchTree(rootNode);
    }

    public List<Integer> nodesAtKDistance(int distance){
        distance = Math.abs(distance);

        if (rootNode == null)
            return null;

        List<Integer> collection = new ArrayList<>();

        return traverseToFindNodesAtKDistance(rootNode, distance, collection);
    }

    private void preOrderDepthFirstTraversal(Node currentNode, Consumer<Integer> consumer){
        if (currentNode == null)
            return;

        consumer.accept(currentNode.item);
        preOrderDepthFirstTraversal(currentNode.leftNode, consumer);
        preOrderDepthFirstTraversal(currentNode.rightNode, consumer);
    }

    private void inOrderDepthFirstTraversal(Node currentNode, Consumer<Integer> consumer){
        if (currentNode == null)
            return;

        inOrderDepthFirstTraversal(currentNode.leftNode, consumer);
        consumer.accept(currentNode.item);
        inOrderDepthFirstTraversal(currentNode.rightNode, consumer);
    }

    private void postOrderDepthFirstTraversal(Node currentNode, Consumer<Integer> consumer){
        if (currentNode == null)
            return;

        postOrderDepthFirstTraversal(currentNode.leftNode, consumer);
        postOrderDepthFirstTraversal(currentNode.rightNode, consumer);
        consumer.accept(currentNode.item);
    }

    private void traverseToInsert(Node newNode, Node currentNode){
        int result = newNode.item.compareTo(currentNode.item);

        if (result >= 0){
            if (currentNode.rightNode == null){
                currentNode.rightNode = newNode;
                return;
            }

            traverseToInsert(newNode, currentNode.rightNode);
        } else if (result < 0){
            if (currentNode.leftNode == null){
                currentNode.leftNode = newNode;
                return;
            }

            traverseToInsert(newNode, currentNode.leftNode);
        }
    }

    private boolean traverseToFind(Node newNode, Node currentNode){
        int result = newNode.item.compareTo(currentNode.item);
        boolean booleanResult;

        if (result == 0)
            return true;
        else if (result > 0){
            if (currentNode.rightNode == null)
                return false;

            booleanResult = traverseToFind(newNode, currentNode.rightNode);
        } else {
            if (currentNode.leftNode == null)
                return false;

            booleanResult = traverseToFind(newNode, currentNode.leftNode);
        }

        return booleanResult;
    }

    private class DepthValue{
        private int counter;
    }

    private boolean traverseToCalculateDepth(Node newNode, Node currentNode, DepthValue depthValue){
        int result = newNode.item.compareTo(currentNode.item);
        boolean booleanResult;

        if (result == 0)
            return true;
        else if (result > 0){
            if (currentNode.rightNode == null)
                return false;

            depthValue.counter++;
            booleanResult = traverseToCalculateDepth(newNode, currentNode.rightNode, depthValue);
        } else {
            if (currentNode.leftNode == null)
                return false;

            depthValue.counter++;
            booleanResult = traverseToCalculateDepth(newNode, currentNode.leftNode, depthValue);
        }

        return booleanResult;
    }

    private int traverseToCalculateHeight(Node newNode, Node currentNode) {
        int result = newNode.item.compareTo(currentNode.item);
        int heightValue;

        if (result == 0)
            return -1 + traverseToCalculateHeight(currentNode);
        else if (result > 0) {
            if (currentNode.rightNode == null)
                return -1;

            heightValue = traverseToCalculateHeight(newNode, currentNode.rightNode);
        } else {
            if (currentNode.leftNode == null)
                return -1;

            heightValue = traverseToCalculateHeight(newNode, currentNode.leftNode);
        }

        return heightValue;

    }

    private int traverseToCalculateHeight(Node currentNode) {
        if (currentNode == null)
            return 0;

        int left = 1 + traverseToCalculateHeight(currentNode.leftNode);
        int right = 1 + traverseToCalculateHeight(currentNode.rightNode);

        return left > right ? left : right;
    }

    private int traverseToFindMinimumValueForSortedTree(Node currentNode){
        if (currentNode.leftNode == null)
            return currentNode.item;

        return traverseToFindMinimumValueForSortedTree(currentNode.leftNode);
    }

    private int traverseToFindMinimumValueForUnsortedTree(Node currentNode) {
        if (currentNode == null)
            return -1;

        int left = traverseToFindMinimumValueForUnsortedTree(currentNode.leftNode);
        int right = traverseToFindMinimumValueForUnsortedTree(currentNode.rightNode);

        if (left != -1 && right != -1) {
            int smallerValue = left < right ? left : right;
            return currentNode.item < smallerValue ? currentNode.item : smallerValue;
        } else if (left == -1 && right == -1)
            return currentNode.item;
        else if (left == -1)
            return currentNode.item < right ? currentNode.item : right;
        else
            return currentNode.item < left ? currentNode.item : left;
    }

    private boolean traverseToCompareTrees(Node firstCurrentNode, Node secondCurrentNode){
        if (firstCurrentNode == null && secondCurrentNode == null)
            return true;

        if (firstCurrentNode == null || secondCurrentNode == null)
            return false;

        if (firstCurrentNode.item == secondCurrentNode.item){
            boolean left = traverseToCompareTrees(firstCurrentNode.leftNode, secondCurrentNode.leftNode);
            boolean right = traverseToCompareTrees(firstCurrentNode.rightNode, secondCurrentNode.rightNode);

            if (left && right)
                return true;
        }

        return false;
    }

    private boolean traverseToValidateTheBinarySearchTree(Node currentNode){
        if (currentNode == null)
            return true;

        boolean booleanResult = false;

        if (currentNode.leftNode == null && currentNode.rightNode == null)
            booleanResult = true;
        else if (currentNode.leftNode != null && currentNode.rightNode != null) {
            if (currentNode.leftNode.item < currentNode.item && currentNode.item <= currentNode.rightNode.item)
                booleanResult = true;
        } else if (currentNode.leftNode == null)
            booleanResult = currentNode.item <= currentNode.rightNode.item;
        else
            booleanResult = currentNode.leftNode.item < currentNode.item;

        return traverseToValidateTheBinarySearchTree(currentNode.leftNode) && booleanResult &&
                traverseToValidateTheBinarySearchTree(currentNode.rightNode);
    }

    private List<Integer> traverseToFindNodesAtKDistance(Node currentNode, int distance, List<Integer> collection){
        if (currentNode == null)
            return collection;

        if (distance == 0) {
            collection.add(currentNode.item);
            return collection;
        }

        traverseToFindNodesAtKDistance(currentNode.leftNode, distance - 1, collection);
        traverseToFindNodesAtKDistance(currentNode.rightNode, distance - 1, collection);

        return collection;
    }

    private Node createNewNode(Integer item){
        item = Math.abs(item);
        Node node = new Node(item);
        return node;
    }

    @Override
    public String toString(){
        return "";
    }







}
