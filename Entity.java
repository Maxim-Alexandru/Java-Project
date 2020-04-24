package Domain;

public class Entity
{
    protected int id;
    protected String name;

    public Entity() {}

    public Entity(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean compareByName(Entity e)
    {
        int index = 0;
        while(index < this.name.length() && index < e.getName().length() && this.name.charAt(index) == e.getName().charAt(index))
            index++;
        if (index == this.name.length() || index == e.getName().length())
            return true;
        return this.name.charAt(index) > e.getName().charAt(index);
    }

}