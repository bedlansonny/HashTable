//build a Name class, with a hashcode method, equals method to compare first and last, and get/set for first and last names
import java.util.*;
import java.io.*;
public class Tester
{
    public static void main(String args[]) throws IOException
    {
        ArrayList<String> words = new ArrayList<>(500000);
        Scanner sc = new Scanner(new File("Large Data Set.txt"));
        while(sc.hasNext())
            words.add(sc.nextLine());

        ArrayList<String> goodSearches = new ArrayList<>(10000);
        sc = new Scanner(new File("Successful Search Records.txt"));
        while(sc.hasNext())
            goodSearches.add(sc.nextLine());

        ArrayList<String> badSearches = new ArrayList<>(10000);
        sc = new Scanner(new File("Unsuccessful Search Records.txt"));
        while(sc.hasNext())
            badSearches.add(sc.nextLine());

        PrintWriter fileWriter = new PrintWriter(new File("personchainingoutput.csv"));


        //double[] loadfactors = {.1, .5, .8, .9, 1.0};

        fileWriter.printf("Load Factor,Average Number of probes to build table,Average insertion time,Average Number of probes to find an entry,Average time to find table entry,Average Number of probes to determine entry isn't present,Average time to determine entry isn't present%n");

        for(double i = .1; i <= 1; i+=.1)
        {
            HashTableChain table = new HashTableChain(closestPrime((int)(words.size()/i)));

            long buildStartTime = System.currentTimeMillis();
            for(String entry : words)
            {
                String[] splitInput = entry.split("\t");
                Person person = new Person(splitInput[0], splitInput[1], splitInput[2], splitInput[3], splitInput[4]);
                table.put(person.name, person);
                
            }
            long buildEndTime = System.currentTimeMillis();
            long buildDuration = buildEndTime - buildStartTime;
            long collisionCount = table.getCollisionCount();

            table.resetCollisionCount();
            long goodSearchStartTime = System.currentTimeMillis();
            for(String entry : goodSearches)
            {
                String[] splitInput = entry.split("\t");
                table.get(new Name(splitInput[0], splitInput[1]));
            }
            long goodSearchEndTime = System.currentTimeMillis();
            long goodSearchDuration = goodSearchEndTime - goodSearchStartTime;
            long goodCheckCount = table.getCollisionCount();

            table.resetCollisionCount();
            long badSearchStartTime = System.currentTimeMillis();
            for(String entry : badSearches)
            {
                String[] splitInput = entry.split("\t");
                table.get(new Name(splitInput[0], splitInput[1]));
            }
            long badSearchEndTime = System.currentTimeMillis();
            long badSearchDuration = badSearchEndTime - badSearchStartTime;
            long badCheckCount = table.getCollisionCount();

            //print this data to a text file
            /*
            //fileWriter.println("Collision handling: Linear probing");
            //fileWriter.println("Hash function: none (just key%size)");
            fileWriter.println("Entry count: " + words.size() + "\nTable size: " + table.getCapacity() + "\nLoad factor: " + (double)words.size()/table.getCapacity());
            //check the accuracy of these numbers... !!
            fileWriter.printf("Average insertion time: %d nanoseconds%n", Math.round(buildDuration/words.size()));
            fileWriter.printf("Number of table insertion collisions: %d%n", collisionCount);
            fileWriter.printf("Number of collisions vs number of insertions: %f%%%n", ((double)collisionCount/words.size())*100);
            fileWriter.printf("Average time to find a table entry: %d nanoseconds%n", Math.round(goodSearchDuration/goodSearches.size()));
            fileWriter.printf("Average number of probes to find a table entry: %.5f%n", ((double)goodCheckCount/(double)goodSearches.size()));
            fileWriter.printf("Average time to determine entry is not in table: %d nanoseconds%n", Math.round((badSearchDuration/badSearches.size())));
            fileWriter.printf("Average number of probes to determine entry is  not in table: %.5f%n", (double)badCheckCount/(double)badSearches.size());
            fileWriter.println();
            */
            fileWriter.printf("%f,%.10f,%f,%f,%.10f,%f,%.10f%n", i, (double)collisionCount/(double)words.size(), (double)buildDuration/(double)words.size(), ((double)goodCheckCount/(double)goodSearches.size()), (double)goodSearchDuration/(double)goodSearches.size(), (double)badCheckCount/(double)badSearches.size(), ((double)badSearchDuration/(double)badSearches.size()));

        }
        
        fileWriter.close();
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