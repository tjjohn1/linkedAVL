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
        if(element == null || contains(element))
        {
            return;
        }
        else
        {
            root = addElementAssist(root, element);
        }
    }

    private LinkedAVLTreeNode<T> addElementAssist(LinkedAVLTreeNode<T> newRoot, T element)
    {
        if(newRoot == null)
        {
            treeSize++;
            return new LinkedAVLTreeNode<T>(element);
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        //compares element to newRoot param to determine proper placement
        if(comparableElement.compareTo(newRoot.getElement()) < 0)
        {
            newRoot.setLeftChild(addElementAssist(newRoot.getLeftChild(), element));
        }

        if(treeHeight(newRoot.getLeftChild()) - treeHeight(newRoot.getRightChild()) == 2)
        {
            if(comparableElement.compareTo((T) newRoot.getLeftChild().getElement()) < 0)
            {
                newRoot = leftTreeRotation(newRoot);
            }
            else
            {
                newRoot = rightLeftTreeRotation(newRoot);
            }
        }

        else if(comparableElement.compareTo(newRoot.getElement()) > 0)
        {
            newRoot.setRightChild(addElementAssist(newRoot.getRightChild(), element));

            if(treeHeight(newRoot.getRightChild()) - treeHeight(newRoot.getLeftChild()) == 2)
            {
                if(comparableElement.compareTo((T) newRoot.getRightChild().getElement()) > 0)
                {
                    newRoot = rightTreeRotation(newRoot);
                }
                else
                {
                    newRoot = leftRightTreeRotation(newRoot);
                }
            }
        }

        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getLeftChild()), treeHeight(newRoot.getRightChild())) + 1);

        return newRoot;
    }

    public boolean contains(T element)
    {
        LinkedAVLTreeNode<T> resultNode = findAssistant(root, element);

        if(resultNode == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public T find(T element) throws EmptyCollectionException, ElementNotFoundException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        if(element == null)
        {
            return null;
        }

        LinkedAVLTreeNode<T> resultNode = findAssistant(root, element);

        if(resultNode == null)
        {
            throw new ElementNotFoundException("Linked AVL Tree");
        }
        else
        {
            return resultNode.getElement();
        }
    }

    private LinkedAVLTreeNode<T> findAssistant(LinkedAVLTreeNode<T> newRoot, T element)
    {
        if(newRoot == null)
        {
            return null;
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        if(comparableElement.compareTo(newRoot.getElement()) < 0)
        {
            return findAssistant(newRoot.getLeftChild(), element);
        }

        else if(comparableElement.compareTo(newRoot.getElement()) > 0)
        {
            return findAssistant(newRoot.getRightChild(), element);
        }

        else
        {
            return newRoot;
        }
    }

    public T removeElement(T element) throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }
        root = removeElementAssist(root, element);

        return element;
    }

    private LinkedAVLTreeNode<T> removeElementAssist(LinkedAVLTreeNode<T> newRoot, T element) throws ElementNotFoundException
    {
        if(newRoot == null)
        {
            throw new ElementNotFoundException("Linked AVL Tree");
        }

        Comparable<T> comparableElement = (Comparable<T>) element;

        if(comparableElement.compareTo(newRoot.getElement()) < 0)
        {
            newRoot.setLeftChild(removeElementAssist(newRoot.getLeftChild(), element));

            if(treeHeight(newRoot.getRightChild()) - treeHeight(newRoot.getLeftChild()) == 2)
            {
                if(treeHeight(newRoot.getRightChild()) >= treeHeight(newRoot.getRightChild().getLeftChild()))

                {
                    newRoot = rightTreeRotation(newRoot);
                }
                else
                {
                    newRoot =leftRightTreeRotation(newRoot);
                }
            }
        }
        else if(comparableElement.compareTo(newRoot.getElement()) > 0)
        {
            newRoot.setRightChild(removeElementAssist(newRoot.getRightChild(), element));

            if(treeHeight(newRoot.getLeftChild()) - treeHeight(newRoot.getRightChild()) == 2)
            {
                if(treeHeight(newRoot.getLeftChild().getLeftChild()) >= treeHeight(newRoot.getLeftChild().getRightChild()))
                {
                    newRoot = leftTreeRotation(newRoot);
                }
                else
                {
                    newRoot = rightLeftTreeRotation(newRoot);
                }
            }
        }

        else
        {
            return getNewNode(newRoot);
        }

        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getLeftChild()), treeHeight(newRoot.getRightChild())) + 1);

        return newRoot;
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
        if(isEmpty())
        {
            throw new EmptyCollectionException ("Linked AVL Tree");
        }

        LinkedAVLTreeNode<T> minNode = findMinAssist(root);
        T minimum = minNode.getElement();

        return minimum;
    }

    private LinkedAVLTreeNode<T> findMinAssist(LinkedAVLTreeNode<T> newNode)
    {
        if(newNode.getLeftChild() == null)
        {
            return newNode;
        }
        else
        {
            return findMinAssist(newNode.getLeftChild());
        }
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

    private int treeHeight(LinkedAVLTreeNode<T> newNode)
    {
        if(newNode == null)
        {
            return -1;
        }
        else
        {
            return newNode.getTreeHeight();
        }
    }

    private LinkedAVLTreeNode<T> leftTreeRotation(LinkedAVLTreeNode<T> node)
    {
        LinkedAVLTreeNode<T> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        node.setTreeHeight(Math.max(treeHeight(node.getLeftChild()), treeHeight(node.getRightChild())) + 1);
        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getLeftChild()), node.getTreeHeight()) + 1);

        return newRoot;
    }

    private LinkedAVLTreeNode<T> rightTreeRotation(LinkedAVLTreeNode<T> node)
    {
        LinkedAVLTreeNode<T> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        node.setTreeHeight(Math.max(treeHeight(node.getLeftChild()), treeHeight(node.getRightChild())) + 1);
        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getRightChild()), node.getTreeHeight()) + 1);

        return newRoot;
    }

    private LinkedAVLTreeNode<T> rightLeftTreeRotation(LinkedAVLTreeNode<T> node)
    {
        node.setLeftChild(rightTreeRotation(node.getLeftChild()));
        return leftTreeRotation(node);
    }

    private LinkedAVLTreeNode<T> leftRightTreeRotation(LinkedAVLTreeNode<T> node)
    {
        node.setRightChild(leftTreeRotation(node.getRightChild()));
        return rightTreeRotation(node);
    }

    private LinkedAVLTreeNode<T> getNewNode(LinkedAVLTreeNode<T> node)
    {
        LinkedAVLTreeNode<T> newNode;

        if(node.getLeftChild() != null && node.getRightChild() != null)
        {
            newNode = findMinAssist(node.getRightChild());
            node.setRightChild(removeElementAssist(node.getRightChild(), newNode.getElement()));
            newNode.setLeftChild(node.getLeftChild());
            newNode.setRightChild(node.getRightChild());

            if(treeHeight(newNode.getLeftChild()) - treeHeight(newNode.getRightChild()) == 2)
            {
                if(treeHeight(newNode.getLeftChild().getLeftChild()) >= treeHeight(newNode.getLeftChild().getRightChild()))
                {
                    newNode = leftTreeRotation(newNode);
                }

                else
                {
                    newNode = rightLeftTreeRotation(newNode);
                }
                treeSize--;
            }

            newNode.setTreeHeight(Math.max(treeHeight(newNode.getLeftChild()), treeHeight(newNode.getRightChild())) + 1);
        }

        else
        {
            if(node.getLeftChild() != null)
            {
                newNode = node.getLeftChild();
            }
            else
            {
                newNode = node.getRightChild();
            }

            treeSize--;
        }

        node.setLeftChild(null);
        node.setRightChild(null);

        return newNode;
    }

}
