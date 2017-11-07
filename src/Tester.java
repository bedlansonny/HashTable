import java.util.*;
import java.io.*;
public class Tester
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(new File("input.txt"));

        HashTable table = new HashTable();

        while(sc.hasNext())
        {
            table.put(sc.nextInt(), sc.nextLine().trim());
        }

        System.out.println(table);
        System.out.println(table.get(2242));
        table.remove(2242);
        System.out.println(table);
        System.out.println(table.get(23));
        System.out.println(table.get(21));
        System.out.println(table.get(20));
        table.put(21, "Parker");
        System.out.println(table);
    }
}