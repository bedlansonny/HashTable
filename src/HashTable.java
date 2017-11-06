//no collision handling
public class HashTable
{
    Node[] nodes;

    public HashTable()
    {
        nodes = new Node[101];
    }

    public HashTable(int initCap)
    {
        nodes = new Node[initCap];
    }

    public Object put(Object key, Object value)
    {
        Object output = null;
        int hash = key.hashCode() % nodes.length;
        if(nodes[hash] != null)
            output = nodes[hash].value;
        nodes[hash] = new Node(key, value);
        return output;
    }

    public Object get(Object key)
    {
        int hash = key.hashCode() % nodes.length;
        return nodes[hash].value;
    }

    public String toString()
    {
        String output = "";

        for(int i = 0; i < nodes.length; i++)
        {
            if(nodes[i] != null)
                output += nodes[i];
        }

        return output;
    }
}
