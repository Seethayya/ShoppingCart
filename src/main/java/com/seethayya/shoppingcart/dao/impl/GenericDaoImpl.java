package com.seethayya.shoppingcart.dao.impl;

import com.seethayya.shoppingcart.dao.FinderExecutor;
import com.seethayya.shoppingcart.dao.GenericDao;

import javax.persistence.Parameter;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/10/14
 * Time: 1:40 PM
 */
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>, FinderExecutor {

    protected EntityManager entityManager;

    private Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public void create(T o) {
        this.entityManager.persist(o);
    }

    public T read(PK id) {
         return (T) this.entityManager.find(type, id);
    }

    public T update(T o) {
        return this.entityManager.merge(o);
    }

    public void delete(T o) {
        this.entityManager.remove(o);
    }

    public List<T> loadAll() {
        return this.entityManager.createQuery("from "+type.getSimpleName()+" order by id").getResultList();
    }

    public List<T> executeFinder(Method method, final Object[] queryArgs) {
        final String queryName = queryNameFromMethod(method);
        final javax.persistence.Query namedQuery = this.entityManager.createNamedQuery(queryName);
        Set<Parameter<?>> namedParameters = namedQuery.getParameters();
        boolean isSingleNamedQuery = true;
        for (Parameter parameter: namedParameters) {
            if (parameter.getPosition() != null) {
                namedQuery.setParameter(parameter.getPosition(), queryArgs[parameter.getPosition() - 1]);
            } else if (parameter.getName() != null && isSingleNamedQuery) {
                namedQuery.setParameter(parameter.getName(), queryArgs[0]);
                isSingleNamedQuery = false;
            } else {
                /* Multiple named queries the order of parameters may change */
                //
            }
        }
        /*for (int i = 0; i < queryArgs.length; i++) {
            Object arg = queryArgs[i];
            Query argType = namedQuery.setParameter(i+1, arg);
        }*/
        return (List<T>) namedQuery.getResultList();
    }

    public String queryNameFromMethod(Method finderMethod) {
        return type.getSimpleName() + "." + finderMethod.getName();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
