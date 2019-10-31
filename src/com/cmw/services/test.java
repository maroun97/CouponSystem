package com.cmw.services;

import com.hani.exception.CouponSystemException;

public class test {

	public static void main(String[] args) throws CouponSystemException {
		// TODO Auto-generated method stub
		AdminService adminService = new AdminService();
		adminService.createCompany("maroun", "ema@asas.com","pass");
	}

}
