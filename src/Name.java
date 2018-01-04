import java.math.BigInteger;

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

    public boolean equals(Name otherName)
    {
        return this.first == otherName.first && this.last == otherName.last;
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
        BigInteger firstAsciiCombo = new BigInteger(firstAsciiComboStr);

        String lastAsciiComboStr = "";
        for(int i = 0; i < lastChars.length; i++)
        {
            lastAsciiComboStr += "" + (int)lastChars[i];
        }
        BigInteger lastAsciiCombo = new BigInteger(lastAsciiComboStr);

        //convert the sum of the two long values of these concatenated new numbers into binary
        BigInteger sumDec = firstAsciiCombo.add(lastAsciiCombo);
        String sumBinary = sumDec.toString(2);
        if(sumBinary.length() % 2 == 1)
            sumBinary = "0" + sumBinary;

        //split the binary in half and combine them, with every other digit being from the other
        String binaryOne = sumBinary.substring(0, sumBinary.length()/2);
        String binaryTwo = sumBinary.substring(sumBinary.length()/2);

        String binaryCombo = "";
        for(int i = 0; i < sumBinary.length()/2; i++)
        {
            binaryCombo += "" + binaryOne.charAt(i) + binaryTwo.charAt(i);
        }

        //convert into base ten
        BigInteger finalHashCode = new BigInteger(binaryCombo, 2);
        finalHashCode = finalHashCode.mod(new BigInteger(Integer.toString(Integer.MAX_VALUE)));
        hashCode = finalHashCode.intValueExact();
    }
}
