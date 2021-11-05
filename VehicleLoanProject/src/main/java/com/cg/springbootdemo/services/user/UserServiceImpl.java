package com.cg.springbootdemo.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springbootdemo.dao.UserDao;
import com.cg.springbootdemo.entities.ApprovedLoans;
import com.cg.springbootdemo.entities.LoanApplication;
import com.cg.springbootdemo.entities.LoginDetail;
import com.cg.springbootdemo.entities.UserDetails;
import com.cg.springbootdemo.entities.UserRegistration;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public UserRegistration userRegisterService(UserRegistration userbasic) {
		return userDao.userRegister(userbasic);
	}
	@Override
	public UserDetails userRegisterAdvancedDetails(UserDetails userDetails) {
		return userDao.userRegisterAdvancedDetails(userDetails);
		
		
	}
	@Override
	public void resetPasswordService(UserRegistration userbasic) {
		userDao.resetPassword(userbasic);
	}
	@Override
	public void modifyUserDetails(UserDetails userDetails) {
		userDao.modifyUserDetails(userDetails);
		
	}
	@Override
	public UserRegistration getUserRegistrationDetails(String email) {
		
		return userDao.getUserRegistrationDetails(email);
	}
	@Override
	public UserDetails getUserDetails(String email) {

		return userDao.getUserDetails(email);
	}
	@Override
	public List<LoanApplication> getAllLoanApplication(String email) {
		return userDao.getAllLoanApplication(email);
	}
	@Override
	public LoanApplication getLoanApplicationByChassis(Long chassisno) {
		return userDao.getLoanApplicationByChassis(chassisno);
	}
	@Override
	public List<LoanApplication> getAllRejectedByEmail(String email) {
		return userDao.getAllRejectedByEmail(email);
	}
	@Override
	public List<ApprovedLoans> viewAllApprovedByEmail(String email) {
		return userDao.viewAllApprovedByEmail(email);
	}
	@Override
	public ApprovedLoans viewApprovedLoanById(long loanId) {
		return userDao.viewApprovedLoanById(loanId);
	}
	@Override
	public LoanApplication applyVehicleLoan(LoanApplication loanapp) {
		return userDao.applyLoan(loanapp);
	}
	@Override
	public boolean verifyUserLogin(LoginDetail login) {
		return userDao.verifyUserLogin(login);
	}

}
