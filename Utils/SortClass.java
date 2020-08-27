package Utils;

import Domain.Entity;
import Repository.Repository;
import java.util.ArrayList;

public abstract class SortClass<T extends Entity>
{
    SortClass() {}
    public abstract ArrayList<T> sort(Repository repo);
}

