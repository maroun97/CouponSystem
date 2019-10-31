var localhost='localhost';
var app = angular.module('NGCouponManger', []);




app.controller('NGCouponMangerCtrl', function($scope,$http,$window) {
	///////////////////////////////

	
	
	
	
	$scope.addcompanyName="";$scope.addcompanyEmail="";$scope.addcompanyPass="";$scope.updatecompanyId="";
	$scope.updatecompanyName="";$scope.updatecompanyEmail="";$scope.updatecompanyPass="";
	$scope.email="";$scope.pass="";$scope.type="";$scope.adminOption="";
	
	
	
	
	
	$scope.updatecustomerId="";$scope.updatecustomerfName="";$scope.updatecustomerlName="";$scope.updatecustomerEmail="";
	$scope.updatecustomerPass="";
    $scope.AllCouponsData="";
    $scope.AllPurchasedCouponsData ="";
	
///////////////////////////////// /   Admin services    //////////////////////////
	/////////////////////////////////////////////////////////////////////

	$scope.clickShowCreateCompany = function() {
		$scope.displayStyleOfCreateCompany="block";  
		}
	/////////////////////////////////////////////////////////////////////
	$scope.clickShowUpdateCompany = function(id,name,email,pass) {
		$scope.displayStyleOfUpdateCompany="block";
		$scope.updatecompanyId=id;
		$scope.updatecompanyName=name;
		$scope.updatecompanyEmail=email;
		$scope.updatecompanyPass=pass;
		}
	//////////////////////////////////////////////////////////////////////
	$scope.clicktheX = function() {
		$scope.displayStyleOfCreateCompany="none";
		  
		}
	////////////////////////////////////////////////////////////////////////
	$scope.clicktheX2 = function() {
		$scope.displayStyleOfUpdateCompany="none";
		}
	$scope.clicktheX3 = function() {
		$scope.displayStyleOfUpdateCustomer="none";
		}
	$scope.clicktheX4 = function() {
		$scope.displayStyleOfCreateCustomer="none";
		}
	$scope.clicktheX5 = function() {
		$scope.displayStyleOfCreateCoupon="none";
		}
	$scope.clicktheX6 = function() {
		$scope.displayStyleOfUpdateCoupon="none";
		}
	///////////////////////////////////////////////////////////////////////
	$scope.init = function () {
		$scope.ShowLoginPageFlag=true;
		$scope.TITLE="Welcome";
		$scope.ShowAdminOptions=false;
		$scope.ShowCompanies=false;
		$scope.ShowCustomers=false;
		$scope.ShowCompanyOptions=false;
		$scope.ShowCompanyCoupons=false;
		$scope.ShowPurchasedCoupons=false;
		$scope.ShowCustomerOptions=false;
		$scope.ShowCouponsForCustomer=false;
		$scope.ShowPurchasedCouponsForCustomer=false;
		
		$scope.displayStyleOfCreateCompany="none";
		$scope.displayStyleOfUpdateCompany="none";
		$scope.displayStyleOfCreateCustomer="none";
		$scope.displayStyleOfUpdateCustomer="none";
		$scope.displayStyleOfCreateCoupon="none";  
		$scope.displayStyleOfUpdateCoupon="none";
		$scope.CheckLoginFunction();
		$scope.adminOption="";
		
		//End of Function init
	};
	/////////////////////////////////////////
	$scope.LoginFunction = function (email,pass,type) {
	
		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/Login',
	         params   : {type: type, email: email, pass: pass},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	 
	              //check if login success
	              if(response.data.LOGIN=="true"){
	          		if(response.data.type=="admin"){
	          			$scope.ShowAdminOptions=true;
	          			
	          		}
	          		if(response.data.type=="company"){
	          			  $scope.ShowCompanyOptions=true;
	          			
	          			
	          		}
	          		if(response.data.type=="customer"){
	          		$scope.ShowCustomerOptions=true;
	          		 $scope.ShowAllCoupons();
	          		}
	          		
	          		$scope.ShowLoginPageFlag=false;
	          		$scope.TITLE="Welcome "+response.data.email;
	              }
	              else
	              	{
	              	alert("login faild");
	              	}     
	            })
		//End of Function LoginFunction
	};
	/////////////////////////////////////////
	$scope.CheckLoginFunction = function () {
		
		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/CheckLogin',
	         params   : {},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	 
	              //check if login success
	              if(response.data.LOGIN=="true"){
	          		if(response.data.type=="admin"){
	          			$scope.ShowAdminOptions=true;
	          		}
	          		if(response.data.type=="company"){
	          			 $scope.ShowCompanyOptions=true;
	          		}
	          		if(response.data.type=="customer"){
	          			  $scope.ShowCustomerOptions=true;
	          			 $scope.ShowAllCoupons();
	          		}
	          		
	          		$scope.ShowLoginPageFlag=false;
	          		$scope.TITLE="Welcome "+response.data.email;
	              }
	              else
	              	{
	              	
	              	}     
	            })
		//End of Function CheckLoginFunction
	};
	/////////////////////////////////////////
	//////////////////////////////////////////
	$scope.LogOutFunc = function () {

		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/LogOut',
	         params   : {},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	
	        	  //check if success
	              if(response.data.LOGIN=="false"){
	            	  $scope.init();
		            }
		            else
		            	{
		            	alert("faild to log out ");
		            	$scope.TITLE="Login faild";
		            	}
	            })
		//End of Function LoginFunction
	};
	/////////////////////////////////////////
	$scope.AdminShowCompanies = function () {
		$scope.AdminCloseCustomers();
		$scope.adminOption="company";
		$scope.ShowCompanies=true;
		
		 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/AdminService/getAllCompanies").then(function (response) {
		     $scope.AllComapniesData = response.data;
		  });
		
		//End of Function LoginFunction
	};
	/////////////////////////////////////////
	$scope.testFunc = function(string){
		alert(string);
	}
	/////////////////////////////////////////
	$scope.addNewCompany=function(name,email,pass){
		$scope.displayStyleOfCreateCompany="none";		
		$http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/createCompany',
	         params   : {companyName: name,companyEmail: email,companyPassword: pass},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	         .then(function(response) {	 
	        	  $scope.AdminShowCompanies();
	        	  $scope.TITLE="New Company Added";
	            })
	}
	//////////////////////////////////////////////////////////////
	$scope.updateSelectedCompany= function(Name,Email,Pass){	
		$scope.displayStyleOfUpdateCompany="none";
		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/updateCompany',
	         params   : {companyID: $scope.updatecompanyId,companyName: Name,companyEmail: Email,companyPassword: Pass},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         }).then(function(response) {	 
	        	  $scope.AdminShowCompanies();
	            })
	}
	/////////////////////////////////////////////////////
$scope.deleteSelectedCompany= function(index){
	$scope.result = confirm("Are you sure , you want to delete "+ $scope.AllComapniesData[index].name+" ?"); 
    if ($scope.result == true) { 
        $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/removeCompany',
	         params   : {companyID: $scope.AllComapniesData[index].id},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	 
	        	  $scope.AdminShowCompanies();
	            })
    }
	}
///////////////////////////////////////////////////////////
$scope.AdminShowCustomers = function () {
	$scope.AdminCloseCompanies();
	$scope.adminOption="customer";
	$scope.ShowCustomers=true;
	
	 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/AdminService/getAllCustomers").then(function (response) {
	     $scope.AllCustomersData = response.data;
	  });
	
	
};
/////////////////////////////////////////////////////////////////////
$scope.clickShowCreateCustomer = function() {
	$scope.displayStyleOfCreateCustomer="block";  
	}
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
$scope.clickShowUpdateCustomer = function(id,fname,lname,email,pass) {
	$scope.displayStyleOfUpdateCustomer="block";
	$scope.updatecustomerId=id;
	$scope.updatecustomerfName=fname;
	$scope.updatecustomerlName=lname;
	$scope.updatecustomerEmail=email;
	$scope.updatecustomerPass=pass;
}
//////////////////////////////////////////////////////////////
$scope.updateSelectedCustomer= function(fName,lName,Email,Pass){	
	$scope.displayStyleOfUpdateCustomer="none";
	$http({
		method  : 'POST',
		url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/updateCustomer',
		params   : {customerID: $scope.updatecustomerId,customerfName: fName,customerlName: lName,customerEmail: Email,customerPassword: Pass},
		headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	}).then(function(response) {	 
		$scope.AdminShowCustomers();
	})
}
/////////////////////////////////////////
$scope.addNewCustomer=function(fname,lname,email,pass){
	$scope.displayStyleOfCreateCustomer="none";		
	$http({
		method  : 'POST',
		url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/createCustomer',
		params   : {customerfName: fname,customerlName: lname,customerEmail: email,customerPassword: pass},
		headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	})
	.then(function(response) {	 
		$scope.AdminShowCustomers();
		$scope.TITLE="New customer Added";
	})
}
//////////////////////////////////////////////////////////////
$scope.deleteSelectedCustomer= function(index){
	$scope.result = confirm("Are you sure , you want to delete "+ $scope.AllCustomersData[index].first_name+" ?"); 
	if ($scope.result == true) { 
		$http({
			method  : 'POST',
			url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/AdminService/removeCustomer',
			params   : {customerID: $scope.AllCustomersData[index].id},
			headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
		})
		.then(function(response) {	 
			$scope.AdminShowCustomers();
		})
	}
}
///////////////////////////////////////////////////////////
/////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////





$scope.AdminCloseCustomers = function () {
	$scope.ShowCustomers=false;
	
}

$scope.AdminCloseCompanies = function () {
	$scope.ShowCompanies=false;
	
}
///////////////////////////////// /   Company services    //////////////////////////
/////////////////////////////////////////////////////////////////////

$scope.clickShowCreateCoupon = function() {
	$scope.displayStyleOfCreateCoupon="block";  
	}
/////////////////////////////////////////////////////////////////////

$scope.showCoupons = function() {
	$scope.ShowCompanyCoupons=true;
	 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/CompanyService/getAllCoupons").then(function (response) {
	     $scope.AllCompanyCouponsData = response.data;
	  });	

}

$scope.addNewCoupon=function (title,category,description,amount,price,startdate,enddate){
	 
var StartDateYear = startdate.getFullYear();
var	StartDateMonth=startdate.getMonth();
var	StartDateDay=startdate.getDate();
var	EndDateYear=enddate.getFullYear();
var	EndDateMonth=enddate.getMonth();
var	EndDateDay=enddate.getDate();

		$scope.displayStyleOfCreateCoupon="none";  
	
		$http({
			method  : 'POST',
			url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/CompanyService/createCoupon',
			params  : {title: title,category: category,description: description,amount: amount,price: price,StartDateYear:StartDateYear,StartDateMonth:StartDateMonth,StartDateDay:StartDateDay,EndDateYear:EndDateYear,EndDateMonth:EndDateMonth,EndDateDay:EndDateDay,image:"/ / no image"},
			//params :{},
			headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
		})
		.then(function(response) {	 
			$scope.showCoupons();
			$scope.TITLE="New Coupon Added";
		})
	 
	 
	 
}
//////////////////////////////////////////////////////////////
$scope.deleteSelectedCoupon= function(index){
	var result = confirm("Are you sure , you want to delete "+ $scope.AllCompanyCouponsData[index].title+" ?"); 
	if (result == true) { 
		$http({
			method  : 'POST',
			url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/CompanyService/removeCoupon',
			params   : {couponID: $scope.AllCompanyCouponsData[index].id},
			headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
		})
		.then(function(response) {	 
			$scope.showCoupons();
		})
	}
}
///////////////////////////////////////////////////////////
$scope.clickShowUpdateCoupon=function(id,title,category,description,amount,price,startDate,endDate){
	$scope.displayStyleOfUpdateCoupon="block";
	$scope.updatecouponid=id;
	$scope.updatecoupontitle=title;
	$scope.updatecouponcategory=category;
	$scope.updatecoupondescription=description;
	$scope.updatecouponamount=amount;
	$scope.updatecouponprice=price;
	$scope.updatecouponstartdate=startDate;
	$scope.updatecouponenddate=endDate;
	
}
$scope.updateNewCoupon=function(id,title,category,description,amount,price,startdate,enddate)
{
	var StartDateYear = startdate.getFullYear();
	var	StartDateMonth=startdate.getMonth();
	var	StartDateDay=startdate.getDate();
	var	EndDateYear=enddate.getFullYear();
	var	EndDateMonth=enddate.getMonth();
	var	EndDateDay=enddate.getDate();

	
	$http({
		method  : 'POST',
		url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/CompanyService/updateCoupon',
		params  : {id:id,title: title,category: category,description: description,amount: amount,price: price,StartDateYear:StartDateYear,StartDateMonth:StartDateMonth,StartDateDay:StartDateDay,EndDateYear:EndDateYear,EndDateMonth:EndDateMonth,EndDateDay:EndDateDay,image:"/ / no image"},
		headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	})
	.then(function(response) {	 
		$scope.showCoupons();
		$scope.TITLE="Coupon updated";
		$scope.displayStyleOfUpdateCoupon="none";
	})
	

	
}
////////////////////////////////////////////Customer//////////////////////



$scope.ShowAllCoupons=function(){
	 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/CustomerService/getAllCoupons").then(function (response) {
	     $scope.AllCouponsData = response.data;
	     $scope.ShowCouponsForCustomer=true;
	  });
};
///////////////////////////////////////////////////////////////////
$scope.ClickPurchase=function(index){
	$http({
		method  : 'POST',
		url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/CustomerService/purchaseCoupon',
		params  : {id:$scope.AllCouponsData[index].id},
		headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	})
	.then(function(response) {	 
		$scope.TITLE="Coupon purchased ";
		$scope.ShowAllCoupons();
		
	}, function (response) {

		// this function handles error
	//	alert("couldn't buy this coupon again!");
		alert(response.data);

		})
}
//////////////////////////////////////
$scope.clickShowPurchasedCoupons=function(){
	$scope.ShowPurchasedCoupons=true;
	$scope.ShowCouponsForCustomer=false;
	 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/CustomerService/getAllPurchasedCoupons").then(function (response) {
	     $scope.AllPurchasedCouponsData = response.data;
	  
	  });
	
}
$scope.showCouponsToPurchase=function(){
	$scope.ShowAllCoupons();
	$scope.ShowPurchasedCoupons=false;
	
}
//End of Function app.controller
});







