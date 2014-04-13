package com.seethayya.shoppingcart.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/10/14
 * Time: 1:41 PM
 */
public interface GenericDao <T, PK extends Serializable> {

    /** Persist the newInstance object into database */
    void create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(PK id);

    /** Save changes made to a persistent object.  */
    T update(T transientObject);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);

    List<T> loadAll();
}
