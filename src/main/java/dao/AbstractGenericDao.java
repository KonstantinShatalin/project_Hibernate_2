package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public abstract class AbstractGenericDao<T>  {

    private final Class<T> entityClass;
    private final SessionFactory sessionFactory;

    public AbstractGenericDao(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.entityClass = clazzToSet;
        this.sessionFactory = sessionFactory;
    }
    public T getById (final int id){
        return getCurrentSession().get(entityClass,id);
    }

    public List<T> getItems (int offset, int count){
        Query<T> query = getCurrentSession().createQuery("from " + entityClass.getName(), entityClass);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll (){
        return getCurrentSession().createQuery("from " + entityClass.getName(), entityClass).list();
    }

    public T save (final T entity){
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update (final T entity){
        return (T) getCurrentSession().merge(entity);
    }

    public void delete (final T entity){
        getCurrentSession().delete(entity);
    }

    public void deleteById (final int entityId){
        final T entity = getById(entityId);
        delete(entity);
    }
    protected Session getCurrentSession (){
        return sessionFactory.getCurrentSession();
    }

}