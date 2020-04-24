package Repository;

import Domain.Entity;
import java.util.ArrayList;

public class Repository<T extends Entity> implements RepositoryInterface<T>
{
    private ArrayList<T> list = new ArrayList<>();

    public Repository() {}
    public Repository(ArrayList list) {this.list = list;}

    public T getEntity(String name)
    {
        for (T t: list)
            if (t.getName().equals(name))
                return t;
            return null;
    }

    public int getIndex(T t)
    {
        int index = -1;
        if (this.list.size() == 0)
            return -1;
        for (int i=0;i<this.list.size();i++)
            if (this.list.get(i).getName().equals(t.getName()))
                index = i;
        return index;
    }

    public ArrayList<T> getAll() { return this.list; }

    public int addEntity(T t) throws Exception {
        int index = this.getIndex(t);
        if (index == -1)
            this.list.add(t);
        else
            throw new Exception("The entity already exists. ");
        return 1;
    }

    public int removeEntityByName(String name) throws Exception {
        T t = (T) new Entity();
        t.setName(name);
        t.setId(-1);
        int index = this.getIndex(t);
        if (index == -1)
            throw new Exception("The entity does not exist.");
        this.list.remove(index);
        return 1;
    }

    public int updateEntity(T t1, T t2)
    {
        int index = this.getIndex(t1);
        if (index == -1)
            return 0;
        if (this.getIndex(t2) != -1)
            return 0;
        this.list.set(index, t2);
        return 1;
    }
}
