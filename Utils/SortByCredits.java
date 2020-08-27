package Utils;

import Domain.Discipline;
import Domain.Entity;
import Repository.Repository;
import java.util.ArrayList;
import java.util.Collections;

public class SortByCredits<T extends Entity> extends SortClass<T>
{
    public SortByCredits() {}

    public ArrayList<T> sort(Repository repo)
    {
        ArrayList<T> result = repo.getAll();
        for (int i=0;i<result.size();i++)
            for (int j=i;j<result.size();j++)
                if (result.get(i) instanceof Discipline && result.get(j) instanceof Discipline)
                    if (((Discipline) result.get(i)).getNumberOfCredits() > ((Discipline) result.get(j)).getNumberOfCredits())
                        Collections.swap(result, result.indexOf(result.get(i)), result.indexOf(result.get(j)));
        return result;
    }

}
