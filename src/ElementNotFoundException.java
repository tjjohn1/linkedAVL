/*Exception class to handle ElementNotFoundException
 *
 */
class ElementNotFoundException extends RuntimeException
{
    public ElementNotFoundException (String message)
    {
        super ("The element does not exist in the collection " + message);
    }
}

