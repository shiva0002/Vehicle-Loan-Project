package com.cg.springbootdemo.dao;

import java.util.List;

import com.cg.springbootdemo.entities.ApprovedLoans;
import com.cg.springbootdemo.entities.LoanApplication;
import com.cg.springbootdemo.entities.LoginDetail;
import com.cg.springbootdemo.entities.UserDetails;
import com.cg.springbootdemo.entities.UserRegistration;

public interface UserDao {
	public UserRegistration userRegister(UserRegistration userbasic);

	public UserDetails userRegisterAdvancedDetails(UserDetails userDetails);
	public LoanApplication applyLoan(LoanApplication loanapp);
	public void resetPassword(UserRegistration userbasic);

	public void modifyUserDetails(UserDetails userDetails);
	
	public UserRegistration getUserRegistrationDetails(String email);
	public UserDetails getUserDetails(String email);
	public List<LoanApplication> getAllLoanApplication(String email);
	public List<ApprovedLoans> viewAllApprovedByEmail(String email);
	public LoanApplication getLoanApplicationByChassis(Long chassisno);
	public ApprovedLoans viewApprovedLoanById(long loanId);
	public List<LoanApplication> getAllRejectedByEmail(String email);
	public boolean verifyUserLogin(LoginDetail login);
}
