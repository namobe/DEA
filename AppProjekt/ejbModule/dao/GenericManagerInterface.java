package dao;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Remove;

@Remote
public interface GenericManagerInterface {

	public void inject(Class<?> clazz);

	public Collection<?> list() throws NotInjectedException;

	public Object findByPrimaryKey(int primaryKey) throws NoSuchRowException, NotInjectedException;

	public Object findByPrimaryKey(String primaryKey) throws NoSuchRowException, NotInjectedException;

	public void delete(int primaryKey) throws NoSuchRowException, NotInjectedException;

	public void delete(String primaryKey) throws NoSuchRowException, NotInjectedException;

	public void save(Object arg) throws IncompatibleClassesException;

	@Remove
	void checkout();
}
