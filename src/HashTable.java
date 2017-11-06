//next step: implement linear probing
public class HashTable
{
    Node[] nodes;
    int size;

    public HashTable()
    {
        nodes = new Node[101];
        size = 0;
    }

    public HashTable(int initCap)
    {
        nodes = new Node[initCap];
        size = 0;
    }

    public Object put(Object key, Object value)
    {
        Object output = null;
        int hash = key.hashCode() % nodes.length;
        if(nodes[hash] != null)
            output = nodes[hash].value;
        nodes[hash] = new Node(key, value);
        size++;
        return output;
    }

    //is the parameter supposed to be the key or a node?
    public Object remove()
    {
        
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
                output += "" + i + ":" + nodes[i] + " ";
        }

        return output;
    }

    public class Node
    {
        public Object key;
        public Object value;
        public boolean removed;

        public Node()
        {
            key = null;
            value = null;
            removed = false;
        }

        public Node(Object key, Object value)
        {
            this.key = key;
            this.value = value;
            removed = false;
        }

        public String toString()
        {
            return "<"+key+", "+value+">";
        }
    }
}
