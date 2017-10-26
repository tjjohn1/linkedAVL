import java.util.Iterator;

/* LinkedAVLTree class to create a new
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

    /* The addElement method inserts the parameter input
	* element into the proper location in the tree,
	* if it does not already exist in the tree.
	* It calls the addElementAssist method to handle the
	* addition movements in the tree with respect to this element
	*
	*  @param T element
	*/
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

    /* The addElementAssist method inserts the param input
	* element into a sub tree using the param input newRoot
	* node, which then returns the newRoot for the tree
	* after this element insertion
	*
	* @param LinkedAVLTreeNode<T> newRoot
	* @param T element
	* @return LinkedAVLTreeNode<T> newRoot
	*/
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
            if(comparableElement.compareTo(newRoot.getLeftChild().getElement()) < 0)
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
                if(comparableElement.compareTo(newRoot.getRightChild().getElement()) > 0)
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

    /* The contains method accepts param T element
	* input and calls the findAssistant method to determine
	* if this element input is in the current tree
	*
	* @param T element
	* @return boolean value
	*/
    public boolean contains(T element)
    {
        LinkedAVLTreeNode<T> resultNode = findAssistant(root, element);

        return resultNode != null;
    }

    /* The find method takes the param element input
	* and calls the findAssistant method to locate the
	* element in the current tree, returning the element
	* reference if the element was found.  Otherwise
	* Exceptions are thrown.
	* after this element insertion
	*
	* @param T element
	* @return T resultNode.getElement()
	*/
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

    /* The findAssistant method takes the param element input
	* and uses the param input newRoot node to determine if
	* the element exists in the specified sub tree.
	* returning the reference to the element location if found,
	* and returning null if it is not found
	*
	* @param T element
	* @param LinkedAVLTreeNode<T> newRoot
	* @return LinkedAVLTreeNode<T> newRoot
	*/
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

    /* The removeElement method takes the param element input
	* and calls the removeElementAssist to find and remove the
	* element form the current tree.  The removed element is
	* returned.
    *
	* @param T element
	* @return T element
	*/
    public T removeElement(T element) throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }
        root = removeElementAssist(root, element);

        return element;
    }

    /* The removeElementAssist method takes the param element input
	* and uses the LinkedAVLTreeNode<T> newRoot to find the element
	* in the specified tree, remove the element from the tree, and
	* calls the rotation methods to re-order the tree appropriately
    *
	* @param T element
	* @param LinkedAVLTreeNode<T> newRoot
	* @return LinkedAVLTreeNode<T> newRoot
	*/
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

    /* The removeAllOccurrences method takes the param element
	* input and calls the removeAllOccurencesAssist to find
	* and remove all occurences of the specified element
	* in the current tree.
	* element form the current tree.  The removed element is
	* returned.
    *
	* @param T element
	*/
    public void removeAllOccurrences(T element) throws ElementNotFoundException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        removeAllOccurrencesAssist(root, element);
    }

    /* The  removeAllOccurencesAssist method takes the param
	* element input and uses the input LinkedAVLTreeNode<T>
	* newRoot to call the removeElementAssist to find all
	* occurrences in the specified tree of the particular
	* input element, and remove them one by one.

	* @param T element
	*/
    public void removeAllOccurrencesAssist(LinkedAVLTreeNode<T> newRoot, T element)
    {
        if(!contains(element))
        {
            return;
        }
        else
        {
            removeElementAssist(newRoot, element);
        }
    }

    /* The getRoot method calls the getrootAssist method
    * in order to obtain the current root of the current tree.
    *
	* @return T root (from getRootAssist)
	*/
    public T getRoot() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        return getRootAssist(root);
    }

    /* The getRootAssist method calls the getElement method
	* in order to obtain the element in the root of the
	* specified tree.
	*
	* @param LinkedAVLTreeNode<T> newRoot
    * @return T element (root of specified tree)
    */
    private T getRootAssist(LinkedAVLTreeNode<T> newRoot)
    {
        return newRoot.getElement();
    }

    /* The findMin method calls the findMinAssist method
	* to find the minimum element in the current tree,
	* which element is then returned by this method
	*
    * @return T minimum
    */
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

    /* The findMinAssist method takes param input of
	* the specified LinkedAVLTreeNode<T> newNode, which
	* is used to then find the minimum element of the
	* specified tree, returning a reference to the element.
	*
	* @param LinkedAVLTreeNode<T> newRoot
    * @return LinkedAVLTreeNode<T> newNode
    */
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

    /* The findMax method calls the findMaxAssist method
	* to find the maximum element in the current tree,
	* which element is then returned by this method
	*
    * @return T maximum
    */
    public T findMax() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        LinkedAVLTreeNode<T> maxNode = findMaxAssist(root);
        T maximum = maxNode.getElement();

        return maximum;
    }

    /* The findMaxAssist method takes param input of
	* the specified LinkedAVLTreeNode<T> newNode, which
	* is used to then find the maximum element of the
	* specified tree, returning a reference to the element.
	*
	* @param LinkedAVLTreeNode<T> newRoot
    * @return LinkedAVLTreeNode<T> newNode
    */
    private LinkedAVLTreeNode<T> findMaxAssist(LinkedAVLTreeNode<T> newRoot)
    {
        if(newRoot.getRightChild() == null)
        {
            return newRoot;
        }

        else
        {
            return findMaxAssist(newRoot.getRightChild());
        }
    }

    /* The removeMin method calls the findMinAssist method
	* and the removeElementAssist method, to find and
	* remove the minimum element from the current tree.
	* returns the minimum element if found, or an
	* Exception if tree is empty
	*
    * @return T minimum
    */
    public T removeMin() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        LinkedAVLTreeNode<T> minNode = findMinAssist(root);
        T minimum = minNode.getElement();
        root = removeElementAssist(root, minimum);

        return minimum;
    }

    /* The removeMax method calls the findMaxAssist method
	* and the removeElementAssist method, to find and
	* remove the maximum element from the current tree.
	* returns the maximum element if found, or an
	* Exception if tree is empty
	*
    * @return T maximum
    */
    public T removeMax() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("Linked AVL Tree");
        }

        LinkedAVLTreeNode<T> maxNode = findMaxAssist(root);
        T maximum = maxNode.getElement();
        root = removeElementAssist(root, maximum);

        return maximum;
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
        return new LinkedAVLTreeIterator<T>(root);
    }

    /* String inOrderTraverse calls the inOrderTraverseAssist
	* method to return a String representation of the
	* specified tree as an in-Order
	*
    * @return String representation of tree in-Order
    */
    public String inOrderTraverse()
    {
        if(isEmpty())
        {
            return new String("Linked AVL Tree is empty");
        }

        else
        {
            return inOrderTraverseAssist(root, 0);
        }
    }

    /* String levelOrderTraverseAssist builds a string
	* representation to return of the tree in-Order
	*
    * @return String representation of tree in-Order
    */
    public String inOrderTraverseAssist(LinkedAVLTreeNode<T> newRoot, int depth)
    {
        StringBuilder nodeString = new StringBuilder();

        if(newRoot == null)
        {
            return nodeString.toString();
        }

        nodeString.append(inOrderTraverseAssist(newRoot.getLeftChild(), depth + 1));
        nodeString.append(newRoot.getElement() + " ");
        nodeString.append(inOrderTraverseAssist(newRoot.getRightChild(), depth + 1));

        return nodeString.toString();
    }

    /* String preOrderTraverse calls the preOrderTraverseAssist
	* method to return a String representation of the
	* specified tree as a pre-Order
	*
    * @return String representation of tree pre-Order
    */
    public String preOrderTraverse()
    {
        if(isEmpty())
        {
            return new String("Linked AVL Tree is empty");
        }

        else
        {
            return preOrderTraverseAssist(root, 0);
        }
    }

    /* String preOrderTraverseAssist builds a string
	* representation to return of the tree pre-Order
	*
    * @return String representation of tree pre-Order
    */
    private String preOrderTraverseAssist(LinkedAVLTreeNode<T> newRoot, int depth)
    {
        StringBuilder nodeString = new StringBuilder();

        if(newRoot == null)
        {
            return nodeString.toString();
        }

        nodeString.append(newRoot.getElement() + " ");
        nodeString.append(preOrderTraverseAssist(newRoot.getLeftChild(), depth + 1));
        nodeString.append(preOrderTraverseAssist(newRoot.getRightChild(), depth + 1));

        return nodeString.toString();
    }

    /* String postOrderTraverse calls the postOrderTraverseAssist
	* method to return a String representation of the
	* specified tree as a post-Order
	*
    * @return String representation of tree post-Order
    */
    public String postOrderTraverse()
    {
        if(isEmpty())
        {
            return new String("Linked AVL Tree is empty");
        }

        else
        {
            return postOrderTraverseAssist(root, 0);
        }
    }

    /* String postOrderTraverseAssist builds a string
	* representation to return of the tree post-Order
	*
    * @return String representation of tree post-Order
    */
    private String postOrderTraverseAssist(LinkedAVLTreeNode<T> newRoot, int depth)
    {
        StringBuilder nodeString = new StringBuilder();

        if(newRoot == null)
        {
            return nodeString.toString();
        }

        nodeString.append(postOrderTraverseAssist(newRoot.getLeftChild(), depth + 1));
        nodeString.append(postOrderTraverseAssist(newRoot.getRightChild(), depth + 1));
        nodeString.append(newRoot.getElement() + " ");

        return nodeString.toString();
    }

    /* String levelOrderTraverse calls the
	* levelinOrderTraverseAssist
	* method to return a String representation of the
	* specified tree as a level-Order
	*
    * @return String representation of tree in-Order
    */
    public String levelOrderTraverse()
    {
        if(isEmpty())
        {
            return new String("Linked AVL Tree is empty");
        }
        else
        {
            return levelOrderTraverseAssist(root, 0);
        }
    }

    /* String levelOrderTraverseAssist builds a string
	* representation to return of the tree level-Order
	*
    * @return String representation of tree level-Order
    */
    private String levelOrderTraverseAssist(LinkedAVLTreeNode<T> newRoot, int depth)
    {
        StringBuilder nodeString = new StringBuilder();

        if(newRoot == null)
        {
            return nodeString.toString();
        }

        nodeString.append(levelOrderTraverseAssist(newRoot.getRightChild(), depth + 1));

        for(int i = 0; i < depth; i++)
        {
            nodeString.append("   ");
        }

        if(depth > 0)
        {
            nodeString.append(" ");
        }

        nodeString.append(newRoot.getElement() + "\n");
        nodeString.append(levelOrderTraverseAssist(newRoot.getLeftChild(), depth + 1));

        return nodeString.toString();
    }

    /* The treeHeight method calls the getTreeHeight method
	* to obtain the height of the specified node in the tree.
	* returns -1 if tree is empty
	*
    * @return int treeHeight (of node in tree)
    */
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

    /* The leftTreeRotation method takes in the
	* LinkedAVLTreeNode<T> node as input, and uses it
	* to rotate the node with it's left child, and return
	* a node which is the new root of the specified tree.
	*
	* @param LinkedAVLTreeNode<T> node
    * @return LinkedAVLTreeNode<T> newRoot
    */
    private LinkedAVLTreeNode<T> leftTreeRotation(LinkedAVLTreeNode<T> node)
    {
        LinkedAVLTreeNode<T> newRoot = node.getLeftChild();
        node.setLeftChild(newRoot.getRightChild());
        newRoot.setRightChild(node);
        node.setTreeHeight(Math.max(treeHeight(node.getLeftChild()), treeHeight(node.getRightChild())) + 1);
        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getLeftChild()), node.getTreeHeight()) + 1);

        return newRoot;
    }

    /* The leftTreeRotation method takes in the
	* LinkedAVLTreeNode<T> node as input, and uses it
	* to rotate the node with it's right child, and return
	* a node which is the new root of the specified tree.
	*
	* @param LinkedAVLTreeNode<T> node
    * @return LinkedAVLTreeNode<T> newRoot
    */
    private LinkedAVLTreeNode<T> rightTreeRotation(LinkedAVLTreeNode<T> node)
    {
        LinkedAVLTreeNode<T> newRoot = node.getRightChild();
        node.setRightChild(newRoot.getLeftChild());
        newRoot.setLeftChild(node);
        node.setTreeHeight(Math.max(treeHeight(node.getLeftChild()), treeHeight(node.getRightChild())) + 1);
        newRoot.setTreeHeight(Math.max(treeHeight(newRoot.getRightChild()), node.getTreeHeight()) + 1);

        return newRoot;
    }

    /* The rightLeftTreeRotation method takes in the
	* LinkedAVLTreeNode<T> node as input, and uses it
	* to double rotate the node with it's left child, and return
	* a node which is the new root of the specified tree.
	*
	* @param LinkedAVLTreeNode<T> node
    * @return leftTreeRotation(node)
    */
    private LinkedAVLTreeNode<T> rightLeftTreeRotation(LinkedAVLTreeNode<T> node)
    {
        node.setLeftChild(rightTreeRotation(node.getLeftChild()));
        return leftTreeRotation(node);
    }

    /* The leftRightTreeRotation method takes in the
	* LinkedAVLTreeNode<T> node as input, and uses it
	* to double rotate the node with it's right child, and return
	* a node which is the new root of the specified tree.
	*
	* @param LinkedAVLTreeNode<T> node
    * @return rightTreeRotation(node)
    */
    private LinkedAVLTreeNode<T> leftRightTreeRotation(LinkedAVLTreeNode<T> node)
    {
        node.setRightChild(leftTreeRotation(node.getRightChild()));
        return rightTreeRotation(node);
    }

    /* The getNewNode method removes the specified node
	* from the current tree, and returns the proper
	* replacement node.
	*
	* @param LinkedAVLTreeNode<T> newRoot
    * @return LinkedAVLTreeNode<T> newNode
    */
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

    /* String toStringAssist returns a String
	* representation of the specified tree
	*
	* @param LinkedAVLTreeNode<T> newRoot
	* @param int depth
    * @return String nodeString
    */
    public String toStringAssist(LinkedAVLTreeNode<T> newRoot, int depth)
    {
        StringBuilder nodeString = new StringBuilder();

        if(newRoot == null)
        {
            return nodeString.toString();
        }

        nodeString.append(toStringAssist(newRoot.getLeftChild(), depth + 1));
        nodeString.append(newRoot.getElement() + " ");
        nodeString.append(toStringAssist(newRoot.getRightChild(), depth + 1));

        return nodeString.toString();
    }

}
