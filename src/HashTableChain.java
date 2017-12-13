import java.util.LinkedList;

public class HashTableChain
{
    LinkedList<Node>[] lists;
    int size;

    //testing purposes
    long collisionCount;

    public HashTableChain()
    {
        lists = new LinkedList[101];
        size = 0;
        collisionCount = 0;
    }

    public HashTableChain(int initCap)
    {
        lists = new LinkedList[initCap];
        size = 0;
        collisionCount = 0;
    }

    public long getCollisionCount()
    {
        return collisionCount;
    }

    public void resetCollisionCount()
    {
        collisionCount = 0;
    }

    public int getCapacity()
    {
        return lists.length;
    }

    public Object put(Object key, Object value)
    {
        int currentIndex = Math.abs(key.hashCode()) % lists.length;
        lists[currentIndex].addLast(new Node(key, value));
        size++;
    }

    //is the parameter supposed to be the key or a node?
    public Object remove(Object key)
    {
        int currentIndex = Math.abs(key.hashCode()) % lists.length;

        //if node isn't return null
        //if node isn't removed and is correct key, remove, decrement size, and return value
        if(lists[currentIndex].contains(key))
        {
            /////////////////////////////////////////////////////////////////////////////unfinished/////////////////////////////////////////////////////////////////////////
        }
        else
        {
            return null;
        }

    }

    public Object get(Object key)
    {
        int currentIndex = Math.abs(key.hashCode()) % nodes.length;

        //if node is null return null
        //if node isn't removed and is correct key, return value
        //if else check next
        while(true)
        {
            if(nodes[currentIndex] == null)
                return null;
            else if(!nodes[currentIndex].removed && nodes[currentIndex].key.equals(key))
                return nodes[currentIndex].value;
            else
            {
                currentIndex = (currentIndex+1)%nodes.length;
                collisionCount++;
            }

        }
    }

    public String toString()
    {
        String output = "";

        for(int i = 0; i < nodes.length; i++)
        {
            if(nodes[i] != null)
            {
                output += "" + i + ": ";
                if(nodes[i].removed)
                    output += "<dummy>\n";
                else
                    output += nodes[i] + "\n";
            }

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
