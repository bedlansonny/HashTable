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
    }
}