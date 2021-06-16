package dao;

import java.util.Collection;
import javax.ejb.Remote;

import entity.Customer;

@Remote
public interface CustomerDaoInterface extends GenericManagerInterface {

	public Collection<Customer> findByName(String firstName);

	public Collection<Customer> list();

	public void delete(int primaryKey);

	public void save(Customer arg);
	
	public void checkout();
}
