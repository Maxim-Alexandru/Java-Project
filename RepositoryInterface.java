package Repository;

import Domain.Entity;
import java.util.ArrayList;

public interface RepositoryInterface<T extends Entity>
{
    ArrayList<T> getAll();
    int addEntity(T t) throws Exception;
    int removeEntityByName(String name) throws Exception;
    int updateEntity(T t1, T t2);
}
