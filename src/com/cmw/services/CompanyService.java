package com.cmw.services;

import java.io.IOException;

import java.sql.Date;
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

import beans.Category;
import beans.Coupon;
import beans.Customer;
import facade.CompanyFacade;

@Path("/CompanyService")
public class CompanyService {
	 
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("getAllCoupons")
	public Response getAllCoupons( @Context HttpServletRequest request){
		 System.out.println("getAllCoupons");
		 HttpSession session = request.getSession();
		 CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		 if(companyFacade!=null) {
		 ArrayList<Coupon> coupons = companyFacade.getCompanyCoupons();
		 
		 String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupons);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
		 }
		 else {
			 System.out.println("companyFacade is null");
		 }
		return null;
		 
		 
	 }
	 /////////////////////////////////////////////////////////////////
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("createCoupon")
	 public Response createCoupon(@Context HttpServletRequest request,@QueryParam("title") String title,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("amount") int amount,@QueryParam("price") double price,@QueryParam("StartDateYear") int StartDateYear,@QueryParam("StartDateMonth") int StartDateMonth,@QueryParam("StartDateDay") int StartDateDay,@QueryParam("EndDateYear") int EndDateYear,@QueryParam("EndDateMonth") int EndDateMonth,@QueryParam("EndDateDay") int EndDateDay,@QueryParam("image") String image){  
		 System.out.println("createingCoupon");
		 
		 @SuppressWarnings("deprecation")
		Date Start_Date = new Date(StartDateYear-1900, StartDateMonth, StartDateDay);
		 @SuppressWarnings("deprecation")
		Date End_Date= new Date(EndDateYear-1900, EndDateMonth, EndDateDay);
		 
		 System.out.println("start : "+Start_Date);
		 System.out.println("end : "+End_Date);
		 
		 HttpSession session = request.getSession();
		 CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		Coupon coupon =new Coupon(companyFacade.getCompanyID(), category, title, description, Start_Date, End_Date, amount, price, image);
		companyFacade.addCoupon(coupon);
		
		
		  String json=""; try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupon); ; } catch (IOException e) {
		  e.printStackTrace(); } return Response.status(201).entity(json).build();
		 
		 
	 }
//////////////////////////////////////////////////////////////////////////	 
	 ///////////////////////////////////////////////////////////////////////
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("removeCoupon")
	 public Response removeCoupon(@Context HttpServletRequest request,@QueryParam("couponID")  int couponID) {
		 
		 HttpSession session = request.getSession();
		 CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		 companyFacade.deleteCoupon(couponID);
		 ArrayList<Coupon> c=companyFacade.getCompanyCoupons();
		 String json="";
		  try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(c);
		  ; } catch (IOException e) { e.printStackTrace(); }
		 return Response.status(201).entity(json).build();
	 }
	 ///////////////////////////////////////////////////////////////////////
	 
	 /////////////////////////////////////////////////////////////////
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("updateCoupon")
	 public Response updateCoupon(@Context HttpServletRequest request,@QueryParam("id") int id,@QueryParam("title") String title,@QueryParam("category") String category,@QueryParam("description") String description,@QueryParam("amount") int amount,@QueryParam("price") double price,@QueryParam("StartDateYear") int StartDateYear,@QueryParam("StartDateMonth") int StartDateMonth,@QueryParam("StartDateDay") int StartDateDay,@QueryParam("EndDateYear") int EndDateYear,@QueryParam("EndDateMonth") int EndDateMonth,@QueryParam("EndDateDay") int EndDateDay,@QueryParam("image") String image){  
		 System.out.println("updatingCoupon");
		 
		 @SuppressWarnings("deprecation")
		Date Start_Date = new Date(StartDateYear-1900, StartDateMonth, StartDateDay);
		 @SuppressWarnings("deprecation")
		Date End_Date= new Date(EndDateYear-1900, EndDateMonth, EndDateDay);
		 
		 System.out.println("start : "+Start_Date);
		 System.out.println("end : "+End_Date);
		 
		 HttpSession session = request.getSession();
		 CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
	
		Coupon coupon= new Coupon(id, companyFacade.getCompanyID(), null, title, description, Start_Date, End_Date, amount, price, image);
		System.out.println(category);
		coupon.setCategory(category);
		companyFacade.updateCoupon(coupon);
		System.out.println("companyFacde id="+companyFacade.getCompanyID());
		System.out.println("coupon company id="+coupon.getCompanyID());
		
		  String json=""; try { ObjectWriter ow = new
		  ObjectMapper().writer().withDefaultPrettyPrinter(); json =
		  ow.writeValueAsString(coupon); ; } catch (IOException e) {
		  e.printStackTrace(); } return Response.status(201).entity(json).build();
		 
		 
	 }
//////////////////////////////////////////////////////////////////////////	 

	 

}
