package Utils;

import Domain.Entity;
import Repository.Repository;

public abstract class FilterClass<T extends Entity>
{
    FilterClass() {}

    public abstract Repository filter(Repository repo) throws Exception;
}
