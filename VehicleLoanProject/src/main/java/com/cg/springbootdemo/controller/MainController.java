package com.cg.springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springbootdemo.entities.ApprovedLoans;
import com.cg.springbootdemo.entities.LoanApplication;
import com.cg.springbootdemo.entities.LoginDetail;
import com.cg.springbootdemo.entities.UserDetails;
import com.cg.springbootdemo.entities.UserRegistration;
import com.cg.springbootdemo.repository.LoanApplicationRepository;
import com.cg.springbootdemo.repository.LoginDetailRepository;
import com.cg.springbootdemo.repository.UserDetailsRepository;
import com.cg.springbootdemo.repository.UserRepository;
import com.cg.springbootdemo.services.user.UserService;

@RestController
@RequestMapping("/vehicleloan/login")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserRepository userRepo;
		
	@Autowired
	private LoanApplicationRepository loanRepo;
	
	@Autowired 
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private LoginDetailRepository loginRepo;
	
	
	@PostMapping("/user/newuser")
	public UserRegistration registerUser(@RequestBody UserRegistration userBasic) {		
		LoginDetail login = new LoginDetail();
		login.setEmail(userBasic.getUserEmail());
		login.setPassword(userBasic.getUserPassword());
		login.setType("user");
		loginRepo.save(login);
		return userService.userRegisterService(userBasic);		
	}
	
	//Adding Advanced Details
	@PostMapping("/user/userdetails/{email}")
	public UserDetails registerUserDetails(@PathVariable String email, @RequestBody UserDetails userDetails) {
		UserRegistration user = userRepo.findById(email).get();
		user.setUserDetails(userDetails);
		userRepo.save(user);
		return userService.userRegisterAdvancedDetails(userDetails);
	}
	
	//Reset Password Service
	@PutMapping("/user/resetpwd/{email}")
	public void resetPassword(@PathVariable String email, @RequestBody List<String> pwd){
		UserRegistration user = userRepo.findById(email).get();
		user.setUserPassword(pwd.get(2));
		userService.resetPasswordService(user);
	}
	
	//Modify User Details
	@PutMapping("/user/update/{email}")
	public void updateUserDetails(@PathVariable String email, @RequestBody UserDetails userdetails) {
		UserRegistration user = userRepo.findById(email).get();
		user.setUserDetails(userdetails);
		userService.modifyUserDetails(userdetails);
	}
	
	//Display Loan Application
	@GetMapping("/user/getLoanApplication/{email}")
	public List<LoanApplication> getAllLoanApplication(@PathVariable String email){
		return userService.getAllLoanApplication(email);
	}
	
	//Loan Application Fillup and linking with User Details
	@PostMapping("/user/loanapp/{email}")
	public LoanApplication applyLoan(@PathVariable String email, @RequestBody LoanApplication loanapp) {		
		UserDetails userdetails = userRepo.findById(email).get().getUserDetails();
		loanapp.setUserDetails(userdetails);
		return userService.applyVehicleLoan(loanapp);
		//		return loanRepo.save(loanapp);		
	}
	
	//Get Loan Application by chassis no
	@GetMapping("/user/loanapp/chassisno/{chassisNumber}")
	public LoanApplication getLoanApplicationByChassis(@PathVariable Long chassisNumber) {
		return userService.getLoanApplicationByChassis(chassisNumber);
	}
	
	//Get Rejected Loan Application by User Email
	@GetMapping("/user/loanapp/rejected/{email}")
	public List<LoanApplication> getAllRejectedApplication(@PathVariable String email){
		return userService.getAllRejectedByEmail(email);
	}
	
	//Get Approved Loan Application by User Email
	@GetMapping("/user/loanapp/approved/{email}")
	public List<ApprovedLoans> getAllApprovedLoans(@PathVariable String email){
		return userService.viewAllApprovedByEmail(email);
	}
	
	//Get Approved Loan Details by Approve loan ID
	@GetMapping("/user/approvedloandetails/{id}")
	public ApprovedLoans viewApprovedLoanById(@PathVariable Long id){
		return userService.viewApprovedLoanById(id);
	}
	
	//VerifyUser
	@GetMapping("/checkuser")
	public boolean verifyUserLogin(@RequestBody LoginDetail login) {
		return userService.verifyUserLogin(login);
	}
	
	
	//Queries for reading data from databases
	//Loan_Application Database API
	@GetMapping("/user/viewloanapp")
	public List<LoanApplication> viewLoanApplicationDB() {
		return loanRepo.findAll();
		
	}
	//User_Registration Database API
	@GetMapping("/user/viewuserregistration")
	public List<UserRegistration> viewUserRegistrationDB() {
		return userRepo.findAll();
		
	}
	
	//User_Details Database API
	@GetMapping("/user/viewuserdetails")
	public List<UserDetails> viewUserDetailsDB() {
		return userDetailsRepo.findAll();
			
	}
	
	//Login Details Database API
	@GetMapping("/logindetail")
	public List<LoginDetail> viewLoginDetailDB(){
		return loginRepo.findAll();
	}
}
