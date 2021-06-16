package entity;

import javax.persistence.*;

//create table artikel (
//		   artikelId integer primary key,
//		   artikelTyp references artikelTyp(artikelTypId),
//		   laden references laden (ladenId)
//		);

@Entity
@Table(name = "artikel@windb")
public class Item implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "artikelId")
	private int itemId;

	@ManyToOne
	@JoinColumn(name = "artikeltyp")
	private ItemType itemType;

	@ManyToOne
	@JoinColumn(name = "laden")
	private Shop shop;

	public Item() {
	}

	public Item(int itemId, ItemType itemType, Shop shop) {
		this.itemId = itemId;
		this.itemType = itemType;
		this.shop = shop;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemType=" + itemType + ", shop=" + shop + "]";
	}

}
