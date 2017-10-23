/* LinkedAVLTreeNode class to create a new node object
 * for the LinkedAVLTreeClass.  These nodes are key
 * to many of the LinkedAVLTree methods, as they are
 * used to reference properly the elements of the
 * LinkedAVLTree and maintaining proper order when
 * performing operations on them in the LinkedAVLTree
 * of a LinkedAVLTree.
 */
public class LinkedAVLTreeNode<T>
{
    private LinkedAVLTreeNode<T> leftChild;
    private LinkedAVLTreeNode<T> rightChild;
    private final T element;
    private int treeHeight;

    public LinkedAVLTreeNode(T newElement)
    {
        element = newElement;
        leftChild = null;
        rightChild = null;
        treeHeight = 0;
    }

    public LinkedAVLTreeNode(T newElement, LinkedAVLTreeNode<T> newLeftChild, LinkedAVLTreeNode<T> newRightChild)
    {
        element = newElement;
        leftChild = newLeftChild;
        rightChild = newRightChild;
        treeHeight = 0;
    }

    T getElement()
    {
        return element;
    }

    LinkedAVLTreeNode<T> getLeftChild()
    {
        return leftChild;
    }

    LinkedAVLTreeNode<T> getRightChild()
    {
        return rightChild;
    }

    public void setLeftChild(LinkedAVLTreeNode<T> node)
    {
        leftChild = node;
    }

    public void setRightChild(LinkedAVLTreeNode<T> node)
    {
        rightChild = node;
    }

    public int getTreeHeight()
    {
        return treeHeight;
    }

    public void setTreeHeight(int height)
    {
        treeHeight = height;
    }
}
