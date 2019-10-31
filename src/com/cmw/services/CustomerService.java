package com.cmw.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.hani.DAO.CouponsDBDAO;
import com.hani.beans.Coupon;
import com.hani.exception.CouponSystemException;
import com.hani.facade.CustomerFacade;



@Path("/CustomerService")
public class CustomerService {
	/// in the page of customer .. how he could buy if he can't see what to but///

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("getAllCoupons")
	public Response getAllCoupons( @Context HttpServletRequest request) throws CouponSystemException{
		 CouponsDBDAO couponDBDAO=new CouponsDBDAO();
		 ArrayList<Coupon> coupons = (ArrayList<Coupon>) couponDBDAO.getAllCoupons();
		 System.out.println("getting all coupons ");
		 String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupons);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
		 
	 }
	 /////////////////////////////////////////////////////////////////
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("purchaseCoupon")
	public Response purchaseCoupon( @Context HttpServletRequest request,@QueryParam("id") int couponID) throws CouponSystemException{
		 HttpSession session = request.getSession();
		 CustomerFacade customerFacade= (CustomerFacade) session.getAttribute("customerFacade");
		 CouponsDBDAO couponDBDAO=new CouponsDBDAO();
		 Coupon coupon=couponDBDAO.getOneCoupon(couponID);
		 customerFacade.purchaseCoupon(coupon);
		
		
		
		String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupon);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
	}
	/////////////////////////////////////////////////////////////////
	
	
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("getAllPurchasedCoupons")
	public Response getAllPurchasedCoupons( @Context HttpServletRequest request) throws CouponSystemException{
		 HttpSession session = request.getSession();
		 CustomerFacade customerFacade= (CustomerFacade) session.getAttribute("customerFacade");	
		 List<Coupon> coupons = customerFacade.getCustomerCoupons();
			
		 System.out.println("getting all purchased coupons ");
		 System.out.println(coupons);
		 String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupons);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
		 
	 }

}
