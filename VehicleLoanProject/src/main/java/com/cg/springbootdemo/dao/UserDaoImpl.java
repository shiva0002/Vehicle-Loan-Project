package com.cg.springbootdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.springbootdemo.entities.ApprovedLoans;
import com.cg.springbootdemo.entities.LoanApplication;
import com.cg.springbootdemo.entities.LoginDetail;
import com.cg.springbootdemo.entities.UserDetails;
import com.cg.springbootdemo.entities.UserRegistration;
import com.cg.springbootdemo.repository.ApprovedLoanRepository;
import com.cg.springbootdemo.repository.LoanApplicationRepository;
import com.cg.springbootdemo.repository.UserDetailsRepository;
import com.cg.springbootdemo.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private LoanApplicationRepository loanAppRepo;
	
	@Autowired
	private ApprovedLoanRepository approvedLoanRepo;
	
	@Override
	public UserRegistration userRegister(UserRegistration userbasic) {
		
		return userRepo.save(userbasic);
	}
	
	@Override
	public UserDetails userRegisterAdvancedDetails(UserDetails userDetails) {
		return userDetailsRepo.save(userDetails);
		
	}
	
	@Override
	public void resetPassword(UserRegistration userbasic) {
		userRepo.save(userbasic);
		
	}
	
	@Override
	public void modifyUserDetails(UserDetails userDetails) {
		userDetailsRepo.save(userDetails);
		
	}
	
	@Override
	public UserRegistration getUserRegistrationDetails(String email) {
		return userRepo.findById(email).get();
	}
	
	@Override
	public UserDetails getUserDetails(String email) {
		return userRepo.findById(email).get().getUserDetails();
		
	}
	
	@Override
	public List<LoanApplication> getAllLoanApplication(String email) {
		UserDetails userDetails = userRepo.findById(email).get().getUserDetails();
		return loanAppRepo.findAllByUserDetails(userDetails);
	}
	
	@Override
	public LoanApplication getLoanApplicationByChassis(Long chassisno) {
		return loanAppRepo.findById(chassisno).get();
	}

	@Override
	public List<LoanApplication> getAllRejectedByEmail(String email) {
		UserDetails userDetails = userRepo.findById(email).get().getUserDetails();
		List<LoanApplication> list = loanAppRepo.findAllByUserDetails(userDetails);
		
		for(int i=0;i<list.size();i++) {
			if(!(list.get(i).getLoanApplicationStatus().equals("Rejected")))
				list.remove(i);
		}
		
		return list;
	}

	@Override
	public List<ApprovedLoans> viewAllApprovedByEmail(String email) {
		UserDetails userDetails = userRepo.findById(email).get().getUserDetails();
		List<LoanApplication> list = loanAppRepo.findAllByUserDetails(userDetails);
		List<ApprovedLoans> approvelist = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getApprovedLoan()==null)
				approvelist.add(list.get(i).getApprovedLoan());
		}
		
		return approvelist;
	}

	@Override
	public ApprovedLoans viewApprovedLoanById(long loanId) {
		return approvedLoanRepo.findById(loanId).get();
	}

	@Override
	public LoanApplication applyLoan(LoanApplication loanapp) {
		return loanAppRepo.save(loanapp);
	}

	@Override
	public boolean verifyUserLogin(LoginDetail login) {
		if(login.getType().equals("user"))
			return true;
		
		return false;
	} 
	
}
