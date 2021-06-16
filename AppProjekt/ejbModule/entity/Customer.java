package entity;

import javax.persistence.*;
import java.util.Collection;

//create table kunde ( 
//		   kundenId integer primary key,
//		   vorname varchar2(20),
//		   nachname varchar2(20),
//		   vermögen integer
//		);

@Entity
@Table(name = "kunde@windb2")
public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "kundenId")
	private int customerId;

//	@OneToMany(mappedBy = "customerId", fetch = FetchType.EAGER)
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name =  "kundenId")
	private Collection<Cart> carts;

	@Column(name = "vorname")
	private String firstName;

	@Column(name = "nachname")
	private String lastName;

	@Column(name = "vermoegen")
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
