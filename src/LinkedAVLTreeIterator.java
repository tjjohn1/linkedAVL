import java.util.Iterator;
import java.util.LinkedList;

/* LinkedAVLTreeIterator class to create a new
 * Iterator object with which to Iterate through the
 * elements of a LinkedAVLTree.
 * This class implements the Iterator Interface in order
 * to create a new Iterator object to use for this purpose.
 */
public class LinkedAVLTreeIterator<T> implements Iterator<T> {
    private LinkedList<T> linkedList;

    public LinkedAVLTreeIterator(LinkedAVLTreeNode<T> root) {
        linkedList = createLinkedList(root);
    }

    //Creates a linked list of elements in-Order
    public LinkedList<T> createLinkedList(LinkedAVLTreeNode<T> root) {
        LinkedList<T> result = new LinkedList<T>();

        if (root == null) {
            return result;
        }

        result.addAll(createLinkedList(root.getLeftChild()));

        result.add(root.getElement());

        result.addAll(createLinkedList(root.getRightChild()));

        return result;
    }

    public boolean hasNext() {
        return (!linkedList.isEmpty());
    }

    public T next() {
        return linkedList.pop();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}