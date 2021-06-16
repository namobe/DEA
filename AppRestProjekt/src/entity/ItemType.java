package entity;

import javax.persistence.*;

//create table artikelTyp (
//		   artikelTypId integer primary key,
//		   artikelName varchar2(20),
//		   preis integer
//		);

public class ItemType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
	private int itemTypeId;

	private String artikelName;

	
	private int price;

	public ItemType() {
	}

	public ItemType(int itemTypeId, String artikelName, int price) {
		this.itemTypeId = itemTypeId;
		this.artikelName = artikelName;
		this.price = price;
	}

	public int getitemTypeId() {
		return itemTypeId;
	}

	public void setitemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getString() {
		return artikelName;
	}

	public void setString(String artikelName) {
		this.artikelName = artikelName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart [itemTypeId=" + itemTypeId + /* ", price=" + price + */ "]";
	}

}
