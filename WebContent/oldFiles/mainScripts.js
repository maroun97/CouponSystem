/**
 * 
 */
var localhost='localhost';

var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope,$http) {
	$scope.ShowLoginForm=true;
	$scope.ShowWelcome=false;
	$scope.email="";
	$scope.type="admin";
	 $scope.myLoginData={};
	 
	 
	
	
	$scope.init = function () {
	  
		$scope.ShowTable=true
		$scope.checkLogin();
		
		
	};
	$scope.initAfterLogin = function () {
		  
if($scope.type=="admin"){
	//show admin data
	$scope.ShowCompaniesTable=true;
	
}
	if($scope.type=="customer"){}
		//show customer data
		if($scope.type=="company"){}
			//show company data
		
	};
	$scope.checkLogin = function() {
		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/CheckLogin',
	         params   : {},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	
	        	  //check if success
	              if(response.data.LOGIN=="true"){
		            	$scope.ShowLoginForm=false;
		            	$scope.email=response.data.email;
		                $scope.ShowWelcome=true;
		                $scope.initAfterLogin();
		            }
	            })
 
	}
	
	$scope.Login = function() {

        $http({
          method  : 'POST',
          url     : 'http://'+localhost+':8080/CouponMangerWeb/Login',
         params   : {type: $scope.type, email: $scope.email, pass:$scope.pass},
          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
         })
          .then(function(response) {	 
              //check if login success
              if(response.data.LOGIN=="true"){
              	$scope.ShowLoginForm=false;
              	$scope.email=response.data.email;
                  $scope.ShowWelcome=true;
                  $scope.initAfterLogin();
              }
              else
              	{
              	alert("login faild");
              	}     
            })
            
          
        
          
		
	}
	
	
	$scope.LogOut = function(){
		  $http({
	          method  : 'POST',
	          url     : 'http://'+localhost+':8080/CouponMangerWeb/LogOut',
	         params   : {},
	          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
	         })
	          .then(function(response) {	
	        	  //check if success
	              if(response.data.LOGIN=="false"){
		            	$scope.ShowLoginForm=true;
		                $scope.ShowWelcome=false;
		                $scope.initVariables();
		            }
		            else
		            	{
		            	alert("faild to log out ");
		            	}
	            })
 
	}
	
	 $scope.ShowCompaniesTable=false;
	 $scope.homePageTitle="Home Page"
	
	$scope.initVariables = function(){
		 $scope.ShowCompanies=false;
		 $scope.ShowOneCompany=false;
		 $scope.ShowAddOneCompany=false;
		 $scope.ShowDeleteCompany=false;
		 $scope.ShowGetOneCompanyToUpdateForm=false;
		 $scope.ShowUpdateOneCompanyForm=false;
		 $scope.ShowCompaniesTable=false;
	} 
	$scope.initVariables();
	
	 
	$scope.ShowCompaniesData = function() {
		 $scope.ShowTable=false;
		 $scope.Data="Showing Compaines Data";
		 $scope.ChosenType="Companies";
		 $scope.ShowCompaniesTable=true;
	
	  }
	$scope.ShowCustomersData = function() {
		
		 $scope.ShowTable=false;
		 $scope.Data="Showing Customers Data";
		 $scope.ChosenType="Customers"
			 $scope.ShowCompaniesTable=false;
	  }
	$scope.ShowAdminData = function() {

		 $scope.ShowTable=false;
		 $scope.Data="Showing Admin Data";
		 $scope.ChosenType="Admin";
		 $scope.ShowCompaniesTable=false;
	}
	$scope.ShowAllComapanies= function() {
		 $scope.homePageTitle="Show Companies";
		 $http.get("http://"+localhost+":8080/CouponMangerWeb/rest/admin/showallcompanies").then(function (response) {
		     $scope.myData = response.data;
		  });
			$scope.initVariables();
		 $scope.ShowCompanies=true;
		 $scope.ShowCompaniesTable=true;
		
	  }
	
	
	$scope.GetOneCompany= function() {
	$scope.initVariables();
		$scope.ShowOneCompany=true;
		 $scope.homePageTitle="Show One Company"
			 $scope.ShowCompaniesTable=true;
    };

	$scope.submitForm = function() {
		        $http({
		          method  : 'POST',
		          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/admin/showonecompany',
		        //  data    : $scope.companyID,
		         params   : {companyID: $scope.companyID},
		          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
		         })
		          .then(function(response) {	 
		              $scope.myData = response.data;
		         	 
		            })
		          };

		          
		        
		      	$scope.AddOneCompany= function() {
		      	$scope.initVariables();
		    		 $scope.ShowAddOneCompany=true;
		    		 $scope.ShowCompaniesTable=true;
		        };
		        
		        
		        
		        $scope.AddOneCompanyForm = function() {
			        $http({
			          method  : 'POST',
			          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/admin/addonecompany',
			        //  data    : $scope.companyID,
			         params   : {companyName: $scope.companyName,companyEmail: $scope.companyEmail,companyPassword: $scope.companyPassword},
			          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
			         })
			          .then(function(response) {	 
			              $scope.myData = response.data;
			     		 $scope.ShowCompaniesTable=true;
			            })
			          };
			          
			          $scope.Delete_Company = function(){
			        	 	$scope.initVariables();
			        	 	 $scope.ShowDeleteCompany=true;
			        		 $scope.ShowCompaniesTable=true;
			          };
			          
			          
			          $scope.DeleteOneCompanyForm = function() {
					        $http({
						          method  : 'POST',
						          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/admin/deleteonecompany',
						         params   : {companyID: $scope.companyID},
						          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
						         })
						          .then(function(response) {	 
						              $scope.myData = response.data;
						     		 $scope.ShowCompaniesTable=true;
						            })
						          };
			          
			          
		        
			        $scope.Update_Company = function(){
			        	 	$scope.initVariables();
			        	 	$scope.ShowGetOneCompanyToUpdateForm=true;
			        		 $scope.ShowCompaniesTable=true;
			          };
			          
			           
			        $scope.GetOneCompanyToUpdateForm = function(){
			        	
			        	 $http({
					          method  : 'POST',
					          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/admin/showonecompany',

					         params   : {companyID: $scope.companyID},
					          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
					         })
					          .then(function(response) {	 
					              $scope.myData = response.data;
					              $scope.companyName= $scope.myData.name;
					              $scope.companyEmail= $scope.myData.email;
					              $scope.companyPassword= $scope.myData.password;
					              $scope.ShowUpdateOneCompanyForm=true;
					     		 $scope.ShowCompaniesTable=true;
					              
					              
					            })
			        	
			        };
			        
			        $scope.UpdateOneCompanyForm = function(){
			        	  $http({
					          method  : 'POST',
					          url     : 'http://'+localhost+':8080/CouponMangerWeb/rest/admin/updateonecompany',
					         params   : {companyID: $scope.companyID,companyName: $scope.companyName,companyEmail: $scope.companyEmail,companyPassword: $scope.companyPassword},
					          headers : { 'Content-Type': 'application/x-www-form-urlencoded ' } 
					         })
					          .then(function(response) {	 
					              $scope.myData = response.data;
					     		 $scope.ShowCompaniesTable=true;
					            })
			        	
			        }
		        
		        
	        
		});
