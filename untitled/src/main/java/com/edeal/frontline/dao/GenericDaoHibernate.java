package com.edeal.frontline.dao;

import com.edeal.frontline.core.Application;
import com.edeal.frontline.core.EdealApplication;
import com.edeal.frontline.enums.SortOrder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.*;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.appfuse.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 */
public abstract class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }


	/**
	 * Return all persistent instances of the given entity class.
	 * Note: Use queries or criteria for retrieving a specific subset.
	 * @param entityClass a persistent class
	 */
    public List<T> getAll() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
    }

    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(getAll());
        return new ArrayList(result);
    }

    public T get(PK id) {
		T entity = getSession().get(this.persistentClass, id);
        if (entity == null) {
            log.warn( this.persistentClass + "' object with id '" + id + "' not found...");
        }
        return entity;
    }

    public boolean exists(PK id) {
        T entity = this.get(id);
        return entity != null;
    }

    public T save(T object) {
		return (T) this.getSession().merge(object);
    }

    public void delete(Object entity) {
    	Session session = getSession();
		//session.lock(entity, lockMode);
		session.delete(entity);
    }

    public int countByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Query query = getNamedQuery(getSession(), queryName);

        for (String s : queryParams.keySet()) {
            query.setParameter(s, queryParams.get(s));
        }

        return ((Long) query.uniqueResult()).intValue();
    }

    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams, int start, int nb) {

        Query query = getNamedQuery(getSession(), queryName);
        query.setMaxResults(nb);
        query.setFirstResult(start);

        for (String s : queryParams.keySet()) {
            query.setParameter(s, queryParams.get(s));
        }

        return query.list();
    }

    private Query getNamedQuery(final Session session, final String queryName) {

        try {
            return session.getNamedQuery(queryName);
        } catch (MappingException mappingException) {

            return null;
        }
    }

    public List<T> getAll(String propertyName, SortOrder order) {
        return createCriteria()
                .addOrder(order == SortOrder.ASC ? Order.asc(propertyName) : Order.desc(propertyName))
                .list();
    }

    public List<T> getAllWithPagination(int start, int count, String sortPropertyName, boolean sortAscendingOrder, String filterPropertyName, Object filter) {
        return createCriteria()
                .add(Restrictions.like(filterPropertyName, filter instanceof String ? "%" + filter + "%" : filter))
                .setFirstResult(start)
                .setMaxResults(count)
                .addOrder(sortAscendingOrder ? Order.asc(sortPropertyName) : Order.desc(sortPropertyName))
                .list();
    }

    public List<T> getAllWithPagination(int start, int count, String sortPropertyName, boolean sortAscendingOrder, T filter) {
        return createCriteria()
                .add(Example.create(filter)
                        .ignoreCase()
                        .enableLike())
                .setFirstResult(start)
                .setMaxResults(count)
                .addOrder(sortAscendingOrder ? Order.asc(sortPropertyName) : Order.desc(sortPropertyName))
                .list();
    }


    public List<T> getAllWithPagination(int start, int count, String sortPropertyName, boolean sortAscendingOrder) {
        return createCriteria()
                .setFirstResult(start)
                .setMaxResults(count)
                .addOrder(sortAscendingOrder ? Order.asc(sortPropertyName) : Order.desc(sortPropertyName))
                .list();
    }

    public List<T> getAllWithPagination(int start, int count) {
        return createCriteria()
                .setFirstResult(start)
                .setMaxResults(count)
                .list();
    }

    public Set<T> getAllByProperty(String property, Object value) {
        Criteria crit = createCriteria();
        if (value instanceof List) {
            crit.add(Restrictions.in("id", ((List<Long>) value).toArray()));
        } else if (value instanceof Boolean) {
            crit.add(Restrictions.eq(property, value));
        } else {
            crit.add(Restrictions.like(property, value instanceof String ? "%" + value + "%" : value));
        }
        return new HashSet<T>(crit.list());
    }

    public Set<T> getAllStartByProperty(String property, Object value) {
        Criteria crit = createCriteria();
        if (value instanceof List) {
            crit.add(Restrictions.in("id", ((List<Long>) value).toArray()));
        } else if (value instanceof Boolean) {
            crit.add(Restrictions.eq(property, value));
        } else {
            crit.add(Restrictions.like(property, value instanceof String ? value + "%" : value));
        }
        return new HashSet<T>(crit.list());
    }

    public List<T> getAllByPropertyOrdered(String property, Object value, String sortPropertyName, boolean sortAscendingOrder) {
        Criteria crit = createCriteria();
        if (value instanceof List) {
            crit.add(Restrictions.in("id", ((List<Long>) value).toArray()));
        } else if (value instanceof Boolean) {
            crit.add(Restrictions.eq(property, value));
        } else {
            crit.add(Restrictions.like(property, value instanceof String ? "%" + value + "%" : value));
        }
        crit.addOrder(sortAscendingOrder ? Order.asc(sortPropertyName) : Order.desc(sortPropertyName));

        return crit.list();
    }


    public List<T> getAllByProperties(HashMap<String, Object> hm) {
        Criteria criteria = createCriteria();

        Set entries = hm.entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() instanceof List)
                criteria.add(Restrictions.in((String) entry.getKey(), (List) entry.getValue()));
            else if (entry.getValue() instanceof Boolean)
                criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
            else
                criteria.add(Restrictions.like((String) entry.getKey(), entry.getValue() instanceof String ? "%" + entry.getValue() + "%" : entry.getValue()));
        }

        return criteria.list();
    }

    public List<T> getAllByPropertiesOrdered(Map<String, Object> hm, String sortPropertyName, boolean sortAscendingOrder) {
        Criteria criteria = createCriteria();

        Set entries = hm.entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() instanceof List)
                criteria.add(Restrictions.in((String) entry.getKey(), (List) entry.getValue()));
            else if (entry.getValue() instanceof Boolean)
                criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
            else
                criteria.add(Restrictions.like((String) entry.getKey(), entry.getValue() instanceof String ? "%" + entry.getValue() + "%" : entry.getValue()));
        }

        criteria.addOrder(sortAscendingOrder ? Order.asc(sortPropertyName) : Order.desc(sortPropertyName));

        return criteria.list();

    }


    public T getByProperty(String property, Object value, boolean strict) {
        List<T> result = new ArrayList<T>();
        if (strict) {
            result = createCriteria().add(Restrictions.eq(property, value)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } else {
            result = createCriteria().add(Restrictions.like(property, value instanceof String ? "%" + value + "%" : value)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public T getByProperties(HashMap<String, Object> hm) {
        Criteria criteria = createCriteria();
        Set entries = hm.entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() instanceof List)
                criteria.add(Restrictions.in((String) entry.getKey(), (List) entry.getValue()));
            else if (entry.getValue() instanceof Boolean)
                criteria.add(Restrictions.eq((String) entry.getKey(), entry.getValue()));
            else
                criteria.add(Restrictions.like((String) entry.getKey(), entry.getValue() instanceof String ? "%" + entry.getValue() + "%" : entry.getValue()));
        }
        if (criteria.list().size() > 0) {
            return (T) criteria.list().get(0);
        } else {
            return null;
        }
    }

    public int count() {
        Criteria criteria = createCriteria();
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.list().get(0)).intValue();
    }

    public int count(T filter) {
        Criteria criteria = createCriteria()
                .add(Example.create(filter)
                        .ignoreCase()
                        .enableLike());
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.list().get(0)).intValue();
    }

    public int countByProperty(String property, Object value) {
        Criteria criteria = createCriteria()
                .add(Restrictions.like(property, value instanceof String ? "%" + value + "%" : value));
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.list().get(0)).intValue();
    }


    protected Session getSession() {
        return EdealApplication.getInstance().getModelSession();
    }


    protected Criteria createCriteria() {
        return getSession().createCriteria(getEntityClass());
    }

    public Class<T> getEntityClass() {
        return this.persistentClass;
    }

    public List<T> findByExample(final T exampleInstance) {
        Criteria crit = getSession().createCriteria(getEntityClass());
        crit.add(Example.create(exampleInstance));
        final List<T> result = crit.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getEntityClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }
}

