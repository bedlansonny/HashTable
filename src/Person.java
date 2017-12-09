public class Person
{
    Name name;
    String phoneNum;
    String email;
    String creditCard;

    //for testing only
    public Person(String alldata)
    {
        String[] splitdata = alldata.split("\t");
        name = new Name(splitdata[0], splitdata[1]);
        phoneNum = splitdata[2];
        email = splitdata[3];
        creditCard = splitdata[4];
    }

    public Person(String firstname, String lastname, String phoneNum, String email, String creditCard)
    {
        name = new Name(firstname, lastname);
        this.phoneNum = phoneNum;
        this.email = email;
        this.creditCard = creditCard;
    }

}
