package entity;



import javax.persistence.*;

//create table warenkorbArtikel (
//		   warenkorbArtikel integer primary key,
//		   anzahl integer check (anzahl > 0 ),
//		   warenkorb references warenkorb(warenkorbId),
//		   artikel references artikel(artikelid)
//		);

@Entity
@Table(name = "warenkorbArtikel@windb")
public class CartItem implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "warenkorbArtikel")
	private int cartitem;
	
	@Column(name = "anzahl")
	private int count;
	
	@ManyToOne
	@JoinColumn(name = "warenkorb")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "artikel")
	private Item item;

	public CartItem() {
	}

	public CartItem(int cartitem, int count,Cart cart, Item item) {
		this.cartitem = cartitem;
		this.count = count;
		this.cart = cart;
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCartitem() {
		return cartitem;
	}

	public void setCartitem(int cartitem) {
		this.cartitem = cartitem;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "CartItem [cartitem=" + cartitem +  ", count=" + count + ", Cart=" + cart +  ", item=" + item + "]";
	}
	
}
