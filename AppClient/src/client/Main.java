package client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.*;
import entity.*;

public class Main {

	public static void main(String[] args) {
//		GenericManagerInterface userDaoInterface;
		CustomerDaoInterface customerDaoInterface;
		GenericManagerInterface cartDaoInterface;
		GenericManagerInterface itemDaoInterface;
		GenericManagerInterface shopDaoInterface;

		try {
			InitialContext ctx = new InitialContext();
			customerDaoInterface = (CustomerDaoInterface) ctx.lookup("AppProjekt/CustomerDao!dao.CustomerDaoInterface");

			for (Object customer : customerDaoInterface.list())
				System.out.println("customer = " + customer);

			customerDaoInterface.checkout();
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("NamingException erkannt");
		}try {
			InitialContext ctx = new InitialContext();
			itemDaoInterface = (GenericManagerInterface) ctx.lookup("AppProjekt/GenericManager!dao.GenericManagerInterface");
			
			itemDaoInterface.inject(Item.class);

			for (Object item : itemDaoInterface.list())
				System.out.println("item = " + item);

			itemDaoInterface.checkout();
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("NamingException erkannt");
		} catch (NotInjectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
