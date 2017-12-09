public class Name
{
    String first;
    String last;
    int hashCode;

    public Name(String first, String last)
    {
        this.first = first;
        this.last = last;

        updateHashCode();

    }

    public int hashCode()
    {
        return hashCode;
    }

    //replace each letter of first and last with their ascii numbers, and concatenate their digits.
    //convert the sum of the two long values of these concatenated new numbers into binary
    //split the binary in half and combine them, with every other digit being from the other
    //convert into base ten
    public void updateHashCode()
    {
        char[] firstChars = first.toCharArray();
        char[] lastChars = last.toCharArray();

        //replace each letter of first and last with their ascii numbers, and concatenate their digits.
        String firstAsciiComboStr = "";
        for(int i = 0; i < firstChars.length; i++)
        {
            firstAsciiComboStr += "" + (int)firstChars[i];
        }
        long firstAsciiCombo = Long.valueOf(firstAsciiComboStr);

        String lastAsciiComboStr = "";
        for(int i = 0; i < lastChars.length; i++)
        {
            lastAsciiComboStr += "" + (int)lastChars[i];
        }
        long lastAsciiCombo = Long.valueOf(lastAsciiComboStr);

        //convert the sum of the two long values of these concatenated new numbers into binary
        long sumDec = firstAsciiCombo + lastAsciiCombo;
        String sumBinary = Long.toBinaryString(sumDec);
        if(sumBinary.length() % 2 == 1)
            sumBinary = "0" + sumBinary;

        //split the binary in half and combine them, with every other digit being from the other
        String binaryOne = sumBinary.substring(0, sumBinary.length()/2);
        String binaryTwo = sumBinary.substring(sumBinary.length()/2);
        String binaryCombo = "";
        for(int i = 0; i < sumBinary.length()/2; i++)
        {
            binaryCombo += binaryOne.charAt(i) + binaryTwo.charAt(i);
        }

        //convert into base ten
        int finalHashCode = Integer.valueOf(binaryCombo, 2);
        hashCode = finalHashCode;
    }
}
