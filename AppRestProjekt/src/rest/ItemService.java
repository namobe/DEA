package rest;

import entity.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.GenericManagerInterface;
import dao.NoSuchRowException;
import dao.NotInjectedException;

import java.util.Collection;

@Path("item")
@Consumes({ "application/json" })
@Produces({ "application/json" })
// @Stateless // geht auch ...
@RequestScoped
public class ItemService implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private static final String EJBNAME = "java:global/AppProjekt/GenericManager!dao.GenericManagerInterface";

//	@EJB(mappedName = EJBNAME)
	private GenericManagerInterface genericManagerInterface;

	//
	// Bemerkung:
	// Falls es zu Problemen mit der Injektion geben sollte hülfe ggfs.
	// nachfolgende explizite Vereinbarung:
	//
	private InitialContext ctx;
//
//	// statt @EJB-Injektion
	{
		try {
			System.out.println("enter Konstruktor");
			ctx = new InitialContext();
			genericManagerInterface = (GenericManagerInterface) ctx.lookup(EJBNAME);
			System.out.println("vor Inject");
			genericManagerInterface.inject(Item.class);
			System.out.println("nach Inject");
		} catch (NamingException e) {
			System.out.println("Naming Exception nach lookup");
			e.printStackTrace();
		}
	}

	// nicht getestet (muss man nicht noch injekten?) (return null? dumme Idee)
	@GET
	@Path("/getItems")
	public Collection<?> getAllItems() {
		System.out.println("\n\n---------------------------------");
		System.out.println("AppRestProject :: GET getAllItems()");

		System.out.println("\n\n---------------------------------");

		try {
			System.out.println("genericManagerInterface.list() = " + genericManagerInterface.list());
			return  genericManagerInterface.list();
		} catch (NotInjectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// nicht getestet (auch injektion?) (return id??)
	@GET
	@Path("/item/{id}")
	public Object getItemById(@PathParam("id") int id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("AppRestProject :: GET getItemById id = " + id);
		System.out.println("\n\n---------------------------------");
		try {
			return genericManagerInterface.findByPrimaryKey(id);
		} catch (NoSuchRowException | NotInjectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@POST
	@Path("/createItem")
	public Response createItem(Item item) {

		System.out.println("\n\n---------------------------------");
		System.out.println("---------------------------------");
//		System.out.println("AppRestProject:: POST createItem itemid = " + item.getItemId());
//		System.out.println("AppRestProject:: POST createItem itemName = " + item.getItemName());
//		System.out.println("AppRestProject:: POST createItem itemName = " + item.getPrice());
//		System.out.println("AppRestProject:: POST createItem CartId = " + item.getCartId());
//		System.out.println("AppRestProject:: POST createItem ShopId = " + item.getShopId());

		boolean success = false;
		try {
			genericManagerInterface.save(item);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		System.out.println("AppRestProject:: POST createItem success = " + success);
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}

//	@GET
//	@Path("/rooms")
//	public Collection<Room> getAllRooms() {
//		System.out.println("\n\n---------------------------------");
//		System.out.println("BuildingRestProject :: GET getAllRooms()");
//		System.out.println("\n\n---------------------------------");
//		return infrastructureRemote.getAllRooms();
//	}
//
//	@GET
//	@Path("/count")
//	public long getCountRooms() {
//		return infrastructureRemote.getCountRooms();
//	}
//
//	@GET
//	@Path("/rooms/{id}")
//	public Room getRoom(@PathParam("id") int id) throws NoSuchRowException {
//		System.out.println("\n\n---------------------------------");
//		System.out.println("BuildingRestProject :: getRoom id = " + id);
//		System.out.println("\n\n---------------------------------");
//		return infrastructureRemote.getRoom(id);
//	}
//	
////	@GET
////	@Path("/buildings/create")
////	public Response createBuilding() {
//
////	@POST
////	@Path("/buildings")
//
////	@GET
////	@Path("/buildings/create")
////	public Response createBuilding(@QueryParam("buildingid") int buildingid, @QueryParam("number") String number,
////			@QueryParam("street") String street) {
//
//	@POST
//	@Path("/buildings")
//	public Response createBuilding(Building building) {
//	
//		System.out.println("\n\n---------------------------------");
//		System.out.println("---------------------------------");
//		System.out.println("BuildingRestProject:: POST createBuilding buildingid = " + building.getId());
//		System.out.println("BuildingRestProject:: POST createBuilding number = " + building.getNumber());
//		System.out.println("BuildingRestProject:: POST createBuilding street = " + building.getStreet());
//
//		boolean success = false;
//		try {
//			infrastructureRemote.save(building);
//			success = true;
//		} catch (Exception e) {
//			success = false;
//		}
//		System.out.println("BuildingRestProject:: POST createBuilding success = " + success);
//		StatusMessage msg = RestApplication.getReturnMessage(success);
//		return Response.ok(msg).build();
//	}
//
//	@DELETE
//	@Path("/buildings/{id}")
//	public Response remove(@PathParam("id") int id) throws NoSuchRowException {
//		System.out.println("\n\n---------------------------------");
//		System.out.println("BuildingService:: remove id = " + id);
//		System.out.println("\n\n---------------------------------");
//		boolean success = false;
//		try {
//			success = true;
//			infrastructureRemote.removeBuilding(id);
//		} catch (NoSuchRowException e) {
//			throw new NoSuchRowException();
//		}
//		StatusMessage msg = RestApplication.getReturnMessage(success);
//		return Response.ok(msg).build();
//	}
}
