package Utils;

import Domain.Discipline;
import Domain.Entity;
import Repository.Repository;
import java.util.ArrayList;

public class FilterByCredit<T extends Entity> extends FilterClass<T>
{
    int credits;

    public FilterByCredit(int credits) { this.credits = credits; }

    public Repository filter(Repository repo) throws Exception {
        ArrayList<Discipline> r = repo.getAll();
        int i = 0;
        for (i = 0; i < r.size(); i++)
            if (r.get(i) instanceof Discipline)
                if (r.get(i).getNumberOfCredits() == credits) {
                    repo.removeEntityByName(r.get(i).getName());
                    i = i - 1;
                }
        if (r.size() == 0)
            return repo;
            if (r.get(r.size() - 1).getNumberOfCredits() == credits)
                repo.removeEntityByName(r.get(r.size() - 1).getName());
        return repo;
    }
}
