/* LinkedAVLTreeADT Interface to Instantiate methods which will be
 * implemented and used by the LinkedAVLTree classes
 * Being only an interface to be implemented, there are
 * no method bodies, only the method headers themselves waiting
 * to be implemented by other classes
 */
public interface LinkedAVLTreeADT<T> {
    void addElement(T Element);

    T removeElement(T targetElement);

    void removeAllOccurrences(T targetElement);

    T removeMin();

    T removeMax();

    T findMin();

    T findMax();

    T getRoot();

    boolean isEmpty();

    int treeSize();

    boolean contains(T targetElement);

    T find(T targetElement);

    Iterator<T> iterator();

    String inOrderTraverse();

    String preOrderTraverse();

    String postOrderTraverse();

    String levelOrderTraverse();

    String toString();

}
