package dao;

import java.lang.reflect.Field;

import java.util.Collection;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;

@Stateful
@Remote(GenericManagerInterface.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GenericManager implements GenericManagerInterface, java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "AppProjekt")
	private EntityManager em;

	private Class<?> clazz;
	private Field[] field;
	private int primaryKeyIndex;
	private String className;

	protected boolean injected = false;

	public GenericManager() {
		System.out.println("Konstruktor Default GenericManager injected = " + injected);
	}

	//
	// Bemerkung:
	// Konstruktor in Zusammenhang mit J2EE-Container nicht einsetzbar!
	//
	// Diese Rolle uebernimmt die Methode "inject". Hierdurch wird allerdings
	// eine Ueberpruefung erforderlich!
	//
	public GenericManager(Class<?> clazz) {
		this.clazz = clazz;
		field = clazz.getDeclaredFields();
		Id id;
		for (int i = 0; i < field.length; i++) {
			id = field[i].getDeclaredAnnotation(Id.class);
			if (id != null) {
				field[i].setAccessible(true);
				primaryKeyIndex = i;
				break;
			}
		}
	}

	public void inject(Class<?> clazz) {
		System.out.println("inject");

		// Finden der Oberklasse, falls eine abgeleitete Klasse vorliegt!
		//
		// Diese wird stets benoetigt, wenn generisch gesucht wird -> "save"
		// Ansonsten greift
		//
		// obj = em.find(clazz, field[primaryKeyIndex].get(arg));
		//
		// auf das mit "primaryKeyIndex" angegebene Feld der aktuellen Klasse
		// zu, was dann meist die serialVerUID darstellt!!

		Class<?> superClazz = clazz;
		while (superClazz != Object.class && superClazz.getSuperclass() != Object.class)
			superClazz = superClazz.getSuperclass();

		System.out.println("\n\ninject:: superClazz = " + superClazz + "\n\n");

		this.clazz = superClazz;

		this.className = clazz.getSimpleName();

		field = this.clazz.getDeclaredFields();
		Id id;
		for (int i = 0; i < field.length; i++) {
			id = field[i].getDeclaredAnnotation(Id.class);
			if (id != null) {
				field[i].setAccessible(true);
				primaryKeyIndex = i;
				break;
			}
		}
		injected = true;
		System.out.println("inject:: injected  = " + injected);

	}

	public Collection<?> list() throws NotInjectedException {
		System.out.println("list :: injected  = " + injected);
		System.out.println(
				"list :: injected  Ausgabe " + em.createQuery("SELECT o FROM " + className + " o").getResultList());
		if (!injected)
			throw new NotInjectedException();
		return em.createQuery("SELECT o FROM " + className + " o").getResultList();
	}

	public Object findByPrimaryKey(int primaryKey) throws NoSuchRowException, NotInjectedException {
		System.out.println("GenericManager:: this = " + this + " clazz = " + clazz);
		if (!injected)
			throw new NotInjectedException();
		Object obj = em.find(clazz, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		else
			return obj;
	}

	public Object findByPrimaryKey(String primaryKey) throws NoSuchRowException, NotInjectedException {
		System.out.println("GenericManager:: this = " + this + " clazz = " + clazz);
		if (!injected)
			throw new NotInjectedException();
		Object obj = em.find(clazz, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		else
			return obj;
	}

	public void delete(int primaryKey) throws NoSuchRowException, NotInjectedException {
		if (!injected)
			throw new NotInjectedException();
		Object obj = em.find(clazz, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchRowException();
	}

	public void delete(String primaryKey) throws NoSuchRowException, NotInjectedException {
		if (!injected)
			throw new NotInjectedException();
		Object obj = em.find(clazz, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchRowException();
	}

	public void save(Object arg) throws IncompatibleClassesException {
		// Abfrage als Ersatz fuer Typsicherheit!
		// Klasse von "arg" muss entweder zur aktuellen Klasse passen oder der
		// generischen Oberklasse, falls dieser vererbt wurde!
		//
		if (arg.getClass() != clazz && arg.getClass().getGenericSuperclass() != clazz)
			throw new IncompatibleClassesException();
		Object obj = null;
		try {
			obj = em.find(clazz, field[primaryKeyIndex].get(arg));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (obj != null) {
			// System.out.println("\n\nGenericManager.save merge ");
			em.merge(arg);
		} else {
			// System.out.println("\n\nGenericManager.save persist ");
			em.persist(arg);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	@Override
	public void checkout() {
	}

}
