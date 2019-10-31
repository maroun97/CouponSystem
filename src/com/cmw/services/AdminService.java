package com.cmw.services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.hani.beans.Company;
import com.hani.beans.Customer;
import com.hani.exception.CouponSystemException;
import com.hani.facade.AdminFacade;




@Path("/AdminService")
public class AdminService {
	 ///////////////////////////////////////////////////////////////////
	
AdminFacade adminFacade=new AdminFacade();
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("getAllCompanies")
		 public Response getAllCompanies() throws CouponSystemException {
			System.out.println("i was here");
			System.out.println(adminFacade.getAllCompanies());
				String json="";
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				try {
					 json = ow.writeValueAsString(adminFacade.getAllCompanies());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return Response.status(201).entity(json).build();
		 }
		 ///////////////////////////////////////////////////////////////////
		 @POST
		 @Produces(MediaType.APPLICATION_JSON)
		 @Path("createCompany")
		 public Response createCompany(@QueryParam("companyName") String Cname,@QueryParam("companyEmail") String Cemail,@QueryParam("companyPassword") String Cpass) throws CouponSystemException {
			 
			 System.out.println("printing...");
			 System.out.println(Cname);
			 System.out.println(Cemail);
			 System.out.println(Cpass);
			Company company = new Company(Cname, Cemail,Cpass);
			System.out.println(company);
			adminFacade.addCompany(company);
			 String json="";
			  try { ObjectWriter ow = new
			  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
			  ow.writeValueAsString(company);
			  ; } catch (IOException e) { e.printStackTrace(); }
			 return Response.status(201).entity(json).build();
		 }
		///////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////
		 @POST
		 @Produces(MediaType.APPLICATION_JSON)
		 @Path("removeCompany")
		 public Response removeCompany(@QueryParam("companyID")  int companyID) throws CouponSystemException {
			 
			 Company company = adminFacade.getOneCompany(companyID);
			adminFacade.deleteCompany(companyID);
			 String json="";
			  try { ObjectWriter ow = new
			  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
			  ow.writeValueAsString(company);
			  ; } catch (IOException e) { e.printStackTrace(); }
			 return Response.status(201).entity(json).build();
		 }
		 ///////////////////////////////////////////////////////////////////
		 
		 ///////////////////////////////////////////////////////////////////
		 @POST
		 @Produces(MediaType.APPLICATION_JSON)
		 @Path("updateCompany")
		 public Response updateCompany(@QueryParam("companyID")  int id,@QueryParam("companyName") String name,@QueryParam("companyEmail") String email,@QueryParam("companyPassword") String pass) throws CouponSystemException {
			 
			 Company company = new Company(id, name, email, pass);
			 adminFacade.updateCompany(company);
			 String json="";
			  try { ObjectWriter ow = new
			  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
			  ow.writeValueAsString(company);
			  ; } catch (IOException e) { e.printStackTrace(); }
			 return Response.status(201).entity(json).build();
		 }
		 ///////////////////////////////////////////////////////////////////
			@GET
			@Produces(MediaType.APPLICATION_JSON)
			@Path("getAllCustomers")
			 public Response getAllCustomers() throws CouponSystemException {
					String json="";
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					try {
						 json = ow.writeValueAsString(adminFacade.getAllCustomers());
					} catch (IOException e) {
						e.printStackTrace();
					}
					return Response.status(201).entity(json).build();
			 }
			 ///////////////////////////////////////////////////////////////////
			 ///////////////////////////////////////////////////////////////////////
			 @POST
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("updateCustomer")
			 public Response updateCustomer(@QueryParam("customerID")  int id,@QueryParam("customerfName") String Cfname,@QueryParam("customerlName") String Clname,@QueryParam("customerEmail") String Cemail,@QueryParam("customerPassword") String Cpass) throws CouponSystemException {
				 
				 Customer customer =new Customer(id,Cfname, Clname, Cemail, Cpass,null);
				 adminFacade.updateCustomer(customer);
				 String json="";
				  try { ObjectWriter ow = new
				  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
				  ow.writeValueAsString(customer);
				  ; } catch (IOException e) { e.printStackTrace(); }
				 return Response.status(201).entity(json).build();
			 }
		 ////////////////////////////////////////////////////////////////////////////////
			 @POST
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("createCustomer")
			 public Response createCustomer(@QueryParam("customerfName") String Cfname,@QueryParam("customerlName") String Clname,@QueryParam("customerEmail") String Cemail,@QueryParam("customerPassword") String Cpass) throws CouponSystemException {
		
		  Customer customer =new Customer(Cfname, Clname, Cemail, Cpass);
		  
		  adminFacade.addCustomer(customer); 
		  String json=""; 
		  try { 
			  ObjectWriter ow =new ObjectMapper().writer().withDefaultPrettyPrinter();
		  json = ow.writeValueAsString(customer);
		  } catch (IOException e) {
		  e.printStackTrace();
		  } 
		  return Response.status(201).entity(json).build();
		  
		 
			
			 }
			 ///////////////////////////////////////////////////////////////////////
		 
			 ///////////////////////////////////////////////////////////////////////
			 @POST
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("removeCustomer")
			 public Response removeCustomer(@QueryParam("customerID")  int customerID) throws CouponSystemException {
				 
				 Customer customer = adminFacade.getoneCustomer(customerID);
				adminFacade.deleteCustomer(customerID);
				 String json="";
				  try { ObjectWriter ow = new
				  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
				  ow.writeValueAsString(customer);
				  ; } catch (IOException e) { e.printStackTrace(); }
				 return Response.status(201).entity(json).build();
			 }
			 ///////////////////////////////////////////////////////////////////////
		 
			 @GET
			 @Path("test")
				public void runit() {
					System.out.println("hello world yeah its running");
				}

			 ///////////////////////////////////////////////////////////////////
		 

}
