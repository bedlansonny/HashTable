//"build a name object"???? in search record steps !!
//export into .csv file instead of .txt so it can be a spreadsheet
import java.util.*;
import java.io.*;
public class Tester
{
    public static void main(String args[]) throws IOException
    {
        int entryCount = 1000;

        ArrayList<String> words = new ArrayList<>(entryCount);
        Scanner sc = new Scanner(new File("Large Data Set.txt"));
        while(sc.hasNext())
            words.add(sc.nextLine());

        ArrayList<String> goodSearches = new ArrayList<>(entryCount);
        sc = new Scanner(new File("Successful Search Records.txt"));
        while(sc.hasNext())
            goodSearches.add(sc.nextLine());

        ArrayList<String> badSearches = new ArrayList<>(entryCount);
        sc = new Scanner(new File("Unsuccessful Search Records.txt"));
        while(sc.hasNext())
            badSearches.add(sc.nextLine());

        for(double loadfactor = .1; loadfactor <= 1.0; loadfactor += .1)
        {
            HashTable table = new HashTable(closestPrime((int)(words.size()/loadfactor)));

            long buildStartTime = System.nanoTime();
            for(String entry : words)
            {
                table.put(Integer.parseInt(entry.substring(0, entry.indexOf(" "))), entry.substring(entry.indexOf(" ")+1));
            }
            long buildEndTime = System.nanoTime();
            long buildDuration = buildEndTime - buildStartTime;
            long collisionCount = table.getCollisionCount();

            long goodSearchStartTime = System.nanoTime();
            for(String entry : goodSearches)
            {
                table.get(Integer.parseInt(entry.substring(0, entry.indexOf(" "))));
            }
            long goodSearchEndTime = System.nanoTime();
            long goodSearchDuration = goodSearchEndTime - goodSearchStartTime;
            long goodCheckCount = table.getCheckCount();

            table.resetCheckCount();
            long badSearchStartTime = System.nanoTime();
            for(String entry : badSearches)
            {
                table.get(Integer.parseInt(entry.substring(0, entry.indexOf(" "))));
            }
            long badSearchEndTime = System.nanoTime();
            long badSearchDuration = badSearchEndTime - badSearchStartTime;
            long badCheckCount = table.getCheckCount();

            //print this data to a text file
            //System.out.println("Collision handling type: Linear probing");
            //System.out.println("Hash function: none (just key%size)");
            System.out.println("Entry count: " + entryCount + "\nTable size: " + table.getCapacity() + "\nLoad factor: " + (double)entryCount/table.getCapacity());
            //check the accuracy of these numbers... !!
            System.out.printf("Average insertion time: %d nanoseconds%n", buildDuration/entryCount);
            System.out.printf("Number of table insertion collisions: %d%n", collisionCount);
            System.out.printf("Number of collisions vs number of insertions: %f%%%n", ((double)collisionCount/entryCount)*100);
            //System.out.println("Average list length: not applicable (no chaining)");
            System.out.printf("Average time to find a table entry: %d nanoseconds%n", goodSearchDuration/entryCount);
            System.out.printf("Average number of probes to find a table entry: %d%n", goodCheckCount/entryCount);
            System.out.printf("Average time to determine entry is not in table: %d nanoseconds%n", badSearchDuration/entryCount);
            System.out.printf("Average number of probes to determine entry is not in table: %d%n", badCheckCount/entryCount);
            System.out.println();
        }
    }

    public static int closestPrime(int min)
    {
        int check = min;
        while(true)
        {
            if(isPrime(check))
                return check;
            check++;
        }
    }

    public static boolean isPrime(int num)
    {
        if(num % 2 == 0)
            return false;
        for(int i = 3; i < num; i += 2)
        {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}