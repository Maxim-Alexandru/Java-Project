package Utils;

import Domain.Entity;
import Repository.Repository;
import java.util.ArrayList;
import java.util.Collections;

public class SortByName<T extends Entity> extends SortClass<T>
{
    public SortByName() {}
    public ArrayList<T> sort(Repository repo)
    {
        ArrayList<T> result = repo.getAll();
        for(int i = 0; i < result.size();i++)
            for(int j = i; j < result.size();j++)
                if (result.get(i).compareByName(result.get(j)))
                    Collections.swap(result, result.indexOf(result.get(i)), result.indexOf(result.get(j)));
        return result;
    }
}
