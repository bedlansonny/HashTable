//NOTE: SHOULD BE AVERAGES OF PROBES AND TIMES, NOT TOTALS!!!




//"build a name object"???? in search record steps !!
//export into .csv file instead of .txt so it can be a spreadsheet
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

        ArrayList<String> goodSearches = new ArrayList<>(1000);
        sc = new Scanner(new File("Successful Search Records.txt"));
        while(sc.hasNext())
            goodSearches.add(sc.nextLine());

        ArrayList<String> badSearches = new ArrayList<>(1000);
        sc = new Scanner(new File("Unsuccessful Search Records.txt"));
        while(sc.hasNext())
            badSearches.add(sc.nextLine());

        double[] loadfactors = {.1, .5, .8, .9, 1.0};

        for(int i = 0; i < 5; i++)
        {
            HashTable table = new HashTable(closestPrime((int)(words.size()/loadfactors[i])));

            long buildStartTime = System.nanoTime();
            for(String entry : words)
            {
                table.put(Integer.parseInt(entry.substring(0, entry.indexOf(" "))), entry.substring(entry.indexOf(" ")+1));
            }
            long buildEndTime = System.nanoTime();
            long buildDuration = buildEndTime - buildStartTime;
            long collisionCount = table.getCollisionCount();

            table.resetCollisionCount();
            long goodSearchStartTime = System.nanoTime();
            for(String entry : goodSearches)
            {
                table.get(Integer.parseInt(entry.substring(0, entry.indexOf(" "))));
            }
            long goodSearchEndTime = System.nanoTime();
            long goodSearchDuration = goodSearchEndTime - goodSearchStartTime;
            long goodCheckCount = table.getCollisionCount();

            table.resetCollisionCount();
            long badSearchStartTime = System.nanoTime();
            for(String entry : badSearches)
            {
                table.get(Integer.parseInt(entry.substring(0, entry.indexOf(" "))));
            }
            long badSearchEndTime = System.nanoTime();
            long badSearchDuration = badSearchEndTime - badSearchStartTime;
            long badCheckCount = table.getCollisionCount();

            //print this data to a text file
            //System.out.println("Collision handling: Linear probing");
            //System.out.println("Hash function: none (just key%size)");
            System.out.println("Entry count: " + words.size() + "\nTable size: " + table.getCapacity() + "\nLoad factor: " + (double)words.size()/table.getCapacity());
            //check the accuracy of these numbers... !!
            System.out.printf("Average insertion time: %d nanoseconds%n", Math.round(buildDuration/words.size()));
            System.out.printf("Number of table insertion collisions: %d%n", collisionCount);
            System.out.printf("Number of collisions vs number of insertions: %f%%%n", ((double)collisionCount/words.size())*100);
            System.out.printf("Average time to find a table entry: %d nanoseconds%n", Math.round(goodSearchDuration/goodSearches.size()));
            System.out.printf("Average number of probes to find a table entry: %.5f%n", ((double)goodCheckCount/(double)goodSearches.size()));
            System.out.printf("Average time to determine entry is not in table: %d nanoseconds%n", Math.round((badSearchDuration/badSearches.size())));
            System.out.printf("Average number of probes to determine entry is not in table: %.5f%n", (double)badCheckCount/(double)badSearches.size());
            System.out.println();
        }
    }

    public static int closestPrime(int min)
    {
        int check = min;
        while(!isPrime(check))
        {
            check++;
        }
        return check;
    }

    public static boolean isPrime(int num)
    {
        if(num == 1 || num % 2 == 0)
            return false;
        for(int i = 3; i < num; i += 2)
        {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}