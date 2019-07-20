/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author b6dmin
 * @param <Type>
 * @param <PrimaryKey>
 */
public interface MainDataAccessObject<Type, PrimaryKey extends Serializable> {

    public void create(Type t) throws Exception;

    public Type read(PrimaryKey id) throws Exception;

    public void update(Type t) throws Exception;

    public void delete(Type t) throws Exception;

    public List<Type> listAll() throws Exception;

}
