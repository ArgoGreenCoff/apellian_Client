package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.AuthCode;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

public interface UsersRepository {

	List<Users> selectAll();

	List<AuthCode> selectAuthcodeAll();

	Result LoginCheck(HttpServletRequest request);

	Result ValiCheck(HttpServletRequest request);

	Result SignupCheck(HttpServletRequest request);
	
	Result regSmsRandom(HttpServletRequest request);	
	
	Result getAuthCdKey(HttpServletRequest request);
		
	Result FindpasswordCheck(HttpServletRequest request);
	       
	Result EmailauthCheck(HttpServletRequest request);
	
	Result NewpasswordCheck(HttpServletRequest request);
	
	Result ChangepasswordCheck(HttpServletRequest request);

	Result WithdrawCheck(HttpServletRequest request);
	
	Users GetUserInfo(String arg);

	Result GetAuthId(String arg1, String arg2, String arg3);

	Result SetDelivery1(HttpServletRequest request);
	Result SetDelivery100(HttpServletRequest request);
	
	Result UpdateGender(Users user);

}
