import java.util.Iterator;

/* LinkedAVLTreeNode class to create a new
 * LinkedAVLTree object, with which to store elements
 * in and to perform an array of operations defined
 * in the many methods below.
 * This class implements the LinkedAVLTreeADT interface
 * in order to implements the methods of that interface
 * when performing operations on the new LinkedAVLTree.
 *
 */
class LinkedAVLTree<T> implements LinkedAVLTreeADT<T>
{
    private LinkedAVLTreeNode<T> root;
    private int treeSize;

    public LinkedAVLTree()
    {
        root = null;
        treeSize = 0;
    }

    public void addElement(T element)
    {

    }

    public boolean contains(T element)
    {
        return false;
    }

    public T find(T element) throws EmptyCollectionException, ElementNotFoundException
    {
        return element;
    }

    public T removeElement(T element) throws EmptyCollectionException
    {
        return element;
    }

    public void removeAllOccurrences(T element) throws ElementNotFoundException
    {

    }

    public T getRoot() throws EmptyCollectionException
    {
        return null;
    }

    public T findMin() throws EmptyCollectionException
    {
        return null;
    }

    public T findMax() throws EmptyCollectionException
    {
        return null;
    }

    public T removeMin() throws EmptyCollectionException
    {
        return null;
    }

    public T removeMax() throws EmptyCollectionException
    {
        return null;
    }

    public boolean isEmpty()
    {
        return (treeSize() == 0);
    }

    public int treeSize()
    {
        return treeSize;
    }

    public Iterator<T> iterator()
    {
        return null;
    }

    public String inOrderTraverse()
    {
        return null;
    }

    public String preOrderTraverse()
    {
        return null;
    }

    public String postOrderTraverse()
    {
        return null;
    }

    public String levelOrderTraverse()
    {
        return null;
    }
}
