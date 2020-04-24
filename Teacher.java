package Domain;

import java.util.concurrent.atomic.AtomicReference;

public class Teacher extends Entity implements java.io.Serializable
{
    private String email;
    private String dateOfBirth;
    private String rank;

    public Teacher() {}

    public Teacher(int id, String name, String email, String dateOfBirth, String rank)
    {
        super(id, name);
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.rank = rank;
    }

    public Teacher(int id, AtomicReference<String> newName, AtomicReference<String> newEmail, AtomicReference<String> newDOB, AtomicReference<String> newRank) {
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    @Override
    public String toString() {
        return "This is the teacher " + name + " and he is a " + rank +". (S)He was born in: " + dateOfBirth
                + ". Here you have some credentials in order to reach out for him: " + email;
    }
}
