import java.util.Iterator;

/* LinkedAVLTreeADT Interface to Instantiate methods which will be
 * implemented and used by the LinkedAVLTree classes
 * Being only an interface to be implemented, there are
 * no method bodies, only the method headers themselves waiting
 * to be implemented by other classes
 *
 *  @author [Thomas Johnson]
 *  @programName [LinkedAVLTreeADT<T>.java]
 */
public interface LinkedAVLTreeADT<T>
{
    public void addElement(T Element);

    public T removeElement(T targetElement);

    public void removeAllOccurrences(T targetElement);

    public T removeMin();

    public T removeMax();

    public T findMin();

    public T findMax();

    public T getRoot();

}
