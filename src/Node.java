public class Node
{
    public Object key;
    public Object value;

    public Node()
    {
        key = null;
        value = null;
    }

    public Node(Object key, Object value)
    {
        this.key = key;
        this.value = value;
    }

    public String toString()
    {
        return "<"+key+", "+value+">";
    }
}
