 package edu.uniandes.service.daos;

import java.lang.reflect.Field;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Listman G.A
 */
public abstract class AbstractDAO<T> {
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id,boolean detach) {
    	T entity= (T) getEntityManager().find(entityClass, id);
    	if(detach){
    		detachObject(entity);
    	}
        return entity;
    }
    protected void detachList(List<T> list){
    	if(list!=null){
	    	for(Object entity:list){
	    		detachObject(entity);    		
	    	}
    	}
    }
    protected void detachObject(Object entity){
    	getEntityManager().detach(entity);
    	Field[] fields=entity.getClass().getDeclaredFields();
    	for(Field field:fields){
    		ManyToOne manyToOne=field.getAnnotation(ManyToOne.class);
    		OneToMany oneToMany=field.getAnnotation(OneToMany.class);
    		OneToOne oneToOne=field.getAnnotation(OneToOne.class);
    		ManyToMany manyToMany=field.getAnnotation(ManyToMany.class);
    		if(oneToMany!=null||manyToMany!=null){
    			field.setAccessible(true);
    			try {
					field.set(entity,null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
    		}else if(manyToOne!=null||oneToOne!=null){    			
    			try {
    				field.setAccessible(true);
    				try {
						detachObject(field.get(entity));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				}    			
    		}    		
    	}
    }
    public List<T> findAll(boolean detach) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List list=getEntityManager().createQuery(cq).getResultList();
        if(detach){
        	detachList(list);
        }
        return list; 
    }

    public List<T> findRange(int[] range,boolean detach) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        List list=q.getResultList();
        if(detach){
        	detachList(list);
        }
        return list;
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
