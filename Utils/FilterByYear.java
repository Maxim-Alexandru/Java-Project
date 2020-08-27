package Utils;

import Domain.Entity;
import Domain.Teacher;
import Repository.Repository;
import java.util.ArrayList;

public class FilterByYear<T extends Entity> extends FilterClass
{
    String year;

    public FilterByYear(String year) { this.year = year; }

    public Repository filter(Repository repo) throws Exception {
        ArrayList<T> r = repo.getAll();
        int i = -1;
        for (i = 0; i < r.size(); i++)
            if (r.get(i) instanceof Teacher)
                if (((Teacher) r.get(i)).getDateOfBirth().contains(year)) {
                    repo.removeEntityByName(r.get(i).getName());
                    i = i - 1;
                }
        if (r.size() == 0)
            return repo;
        if (((Teacher) r.get(r.size() - 1)).getDateOfBirth().contains(year))
            repo.removeEntityByName((r.get(r.size() - 1)).getName());
        return repo;
    }
}
