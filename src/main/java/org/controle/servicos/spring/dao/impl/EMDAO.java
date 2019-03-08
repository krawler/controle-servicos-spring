package org.controle.servicos.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.controle.servicos.spring.dao.DAOBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation=Propagation.REQUIRED)
public abstract class EMDAO<T> implements DAOBase<T> {	
	
	@Autowired	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	private void setEntityManager(EntityManager entityManager){
		entityManager = entityManager;
	}
	
	protected abstract Class getClazz();
	
	public void persistir(T objeto) {
		getEntityManager().merge(objeto);
		getEntityManager().flush();
	}
	
	
	public void excluir(T objeto) {
		getEntityManager().remove(objeto);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> list(int offset, int max){
		List<T> result = (List<T>) getEntityManager()
				 .createQuery("from "+getClazz().getName(), getClazz())
				 .setFirstResult(offset)
				 .setMaxResults(max)
				 .getResultList();
		
		return (result != null ? result : new ArrayList<T>());
	}
	
	public List<T> list(String campoBanco, String valorCampo) throws Exception {
		return (List<T>) getEntityManager()
						.createQuery("from "+getClazz().getName()+" where "+campoBanco+"="+valorCampo)
						.getResultList();
	}
	
	
	public List<T> listAll(){	
		return (List<T>) getEntityManager()
							.createQuery("from "+getClazz().getName())
							.getResultList(); 
	}
	
	public T get(Long id) {
		return (T) getEntityManager().getReference(getClazz(), id);
	}	
}

