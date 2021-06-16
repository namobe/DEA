package entity;

import javax.persistence.*;
//
//create table shop ( 
//		   shopId integer primary key,
//		   shopName varchar2(200) not null 
//		);

@Entity
@Table(name = "laden@windb")
public class Shop implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ladenId")
	private int shopId;

	@Column(name = "ladenName")
	private String shopName;

	public Shop() {
	}

	public Shop(int shopId, String shopName) {
		this.shopId = shopId;
		this.shopName = shopName;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + /* ", items=" + items + */"]";
	}

}
