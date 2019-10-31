package com.cmw.services;

import java.io.IOException;
import java.util.ArrayList;

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

import DAO.CouponDBDAO;
import beans.Coupon;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

@Path("/CustomerService")
public class CustomerService {
	/// in the page of customer .. how he could buy if he can't see what to but///

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("getAllCoupons")
	public Response getAllCoupons( @Context HttpServletRequest request){
		 CouponDBDAO couponDBDAO=new CouponDBDAO();
		 ArrayList<Coupon> coupons = (ArrayList<Coupon>) couponDBDAO.getAllCoupon();
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
	public Response purchaseCoupon( @Context HttpServletRequest request,@QueryParam("id") int couponID){
		 HttpSession session = request.getSession();
		 CustomerFacade customerFacade= (CustomerFacade) session.getAttribute("customerFacade");
		 CouponDBDAO couponDBDAO=new CouponDBDAO();
		 Coupon coupon=couponDBDAO.getOneCoupon(couponID);
		 customerFacade.purchaseCoupon(coupon);
		
		
		
		String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupon);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
	}
	

}
