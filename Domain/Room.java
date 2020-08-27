package Domain;

public class Room extends Entity implements java.io.Serializable
{
    String building;

    public Room() {}
    public Room(int id, String name, String building)
    {
        super(id, name);
        this.building = building;
    }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    @Override
    public String toString() { return  "The room " + name + " is located in the building " + building; }
}
