import java.util.*;
import java.io.*;
public class Tester
{
    public static void main(String args[]) throws IOException
    {
        ArrayList<String> words = new ArrayList<>(50000);
        Scanner sc = new Scanner(new File("Large Data Set.txt"));
        while(sc.hasNext())
            words.add(sc.nextLine());

        ArrayList<String> successes = new ArrayList<>(50000);
        sc = new Scanner(new File("Successful Search Records.txt"));
        while(sc.hasNext())
            words.add(sc.nextLine());

        ArrayList<String> failures = new ArrayList<>(50000);
        sc = new Scanner(new File("Unsuccessful Search Records.txt"));
        while(sc.hasNext())
            words.add(sc.nextLine());




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