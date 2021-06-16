package entity;

import javax.persistence.*;
import java.util.Collection;

//create table kunde ( 
//		   kundenId integer primary key,
//		   vorname varchar2(20),
//		   nachname varchar2(20),
//		   vermögen integer
//		);


public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int customerId;


	private Collection<Cart> carts;

	private String firstName;

	
	private String lastName;

	
	private int money;

	public Customer() {
	}

	public Customer(int customerId, String firstName, String lastName, int money) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.money = money;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", carts=" + carts + ", firstName=" + firstName + ", lastName="
				+ lastName + ", money=" + money + "]";
	}

//	public Collection<Cart> getCarts() {
//		return carts;
//	}
//
//	public void setCarts(Collection<Cart> carts) {
//		this.carts = carts;
//	}

}
