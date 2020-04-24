package Utils;

import Domain.Entity;
import Repository.Repository;
import java.util.ArrayList;

public class FilterByName<T extends Entity> extends FilterClass
{
    String name;

    public FilterByName(String name) { this.name = name; }

    public Repository filter(Repository repo) throws Exception {
        ArrayList<T> r = repo.getAll();
        int i = -1;
        for (i = 0; i < r.size(); i++)
            if (r.get(i) instanceof Entity)
                if (r.get(i).getName().contains(name)) {
                    repo.removeEntityByName(r.get(i).getName());
                    i = i - 1;
                }
        if (r.size() == 0)
            return repo;
        if (r.get(r.size() - 1).getName().contains(name))
            repo.removeEntityByName(r.get(r.size() - 1).getName());
        return repo;
    }
}
