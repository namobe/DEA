package entity;

import java.util.Date;

import javax.persistence.*;

//create table warenkorb ( -- auftrag
//		   warenkorbId integer primary key,
//		   kundenId integer,
//		   datumEröffnung date not null,
//		   datumBezahlt date 
//		);

@Entity
@Table(name = "warenkorb@windb")
public class Cart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "warenkorbId")
	private int cartId;

	@ManyToOne
	@JoinColumn(name = "kundenId")
	private Customer customer;

	@Column(name = "datumEröffnung")
	private Date startdate;

	@Column(name = "datumBezahlt")
	private Date paydate;

	public Cart() {
	}

	public Cart(int cartId, Customer customer, Date startdate, Date paydate) {
		this.cartId = cartId;
		this.customer = customer;
		this.startdate = startdate;
		this.paydate = paydate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + "startdate=" + startdate + "]";
	}

}
