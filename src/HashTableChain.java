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

        if(lists[currentIndex] == null)
            lists[currentIndex] = new LinkedList<>();

        if(lists[currentIndex].contains(new Node(key, null)))
            return null;

        lists[currentIndex].addLast(new Node(key, value));
        size++;
        collisionCount++;
        return value;
    }

    //is the parameter supposed to be the key or a node?
    public boolean remove(Object key)
    {
        int currentIndex = Math.abs(key.hashCode()) % lists.length;

        //if node isn't present return false
        //if node isn't removed and is correct key, remove, decrement size, and return true
        if(lists[currentIndex].removeFirstOccurrence(new Node(key, null)))
        {
            size--;
            return true;
        }
        else
            return false;


    }

    public Object get(Object key)
    {
        int currentIndex = Math.abs(key.hashCode()) % lists.length;

        /////////////////////////////////////////////////////////////////////////////unfinished/////////////////////////////////////////////////////////////////////////

        //use foreach loop
        if(lists[currentIndex] == null)
            return null;

        for(Node n : lists[currentIndex])
        {
            collisionCount++;
            if(n.key.equals(key))
                return n.value;
        }
        return null;
    }

    public String toString()
    {
        String output = "";


        return output;
    }

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

        public boolean equals(Node otherNode)
        {
            return this.key.equals(otherNode.key);
        }

        public String toString()
        {
            return "<"+key+", "+value+">";
        }
    }
}
