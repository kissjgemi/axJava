package aDataAccess;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author b6dmin
 * @param <Type>
 * @param <PrimaryKey>
 */
public interface MainDataBaseAccessObject<Type, PrimaryKey extends Serializable> {

    public void create(Type t) throws Exception;

    public Type read(PrimaryKey id) throws Exception;

    public void update(Type t) throws Exception;

    public void delete(Type t) throws Exception;

    public List<Object> listAll() throws Exception;
}
