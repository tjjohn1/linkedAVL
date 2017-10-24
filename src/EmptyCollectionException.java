/* Exception class to handle an EmptyCollectionException
 *
 */
class EmptyCollectionException extends RuntimeException
{
    public EmptyCollectionException(String message)
    {
        super("The " + message + " is an empty collection");
    }
}
