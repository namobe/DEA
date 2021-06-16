package dao;

import java.util.Collection;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import entity.Customer;

@Stateful
@Remote(CustomerDaoInterface.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CustomerDao implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "AppProjekt2")
	private EntityManager em;

	public Collection<Customer> findByName(String firstName) {
		return em.createQuery("SELECT x FROM Customer x WHERE x.firstName = ?1", Customer.class)
				.setParameter(1, firstName).getResultList();
	}

	public Collection<Customer> list() {
		return em.createQuery("SELECT o FROM Customer o", Customer.class).getResultList();
	}

	public void delete(int primaryKey) throws NoSuchRowException {
		Object obj = em.find(Customer.class, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchRowException();
	}

	public void save(Customer arg) {
		Customer obj = null;
		try {
			obj = em.find(Customer.class, arg.getCustomerId());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (obj != null) {
			em.merge(arg);
		} else {
			em.persist(arg);
		}
	}
	
	public void checkout() {
	}

}
