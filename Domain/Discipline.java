package Domain;

public class Discipline extends Entity implements java.io.Serializable
{
    private int numberOfCredits;

    public Discipline() {}

    public Discipline(int id, String name, int numberOfCredits)
    {
        super(id, name);
        this.numberOfCredits = numberOfCredits;
    }

    public int getNumberOfCredits() { return numberOfCredits; }
    public void setNumberOfCredits(int numberOfCredits) { this.numberOfCredits = numberOfCredits; }

    @Override
    public String toString() {
        return "The discipline is " + name +
                " and for this a student may acquire the following number of credits: " + numberOfCredits;
    }

    public boolean compareCredits(Discipline d) {return this.numberOfCredits < d.getNumberOfCredits(); }
}
