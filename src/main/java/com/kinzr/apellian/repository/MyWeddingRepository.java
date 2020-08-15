package com.kinzr.apellian.repository;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.MyWed;
import com.kinzr.apellian.entity.model.MyWedCategory;
import com.kinzr.apellian.entity.model.MyWedOption;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.MyWedAccessory;
import com.kinzr.apellian.entity.model.CdCompany;
import com.kinzr.apellian.entity.model.CdWeddingHall;

public interface MyWeddingRepository {

	List<Users> selectAll();
	
	Users MywedUser(HttpServletRequest request);
	
	MyWed MyweddingList(String idUser);		// 클래스 메인 리스트

	List<MyWedCategory> MywedcategoryList(long argmt); // HttpServletRequest request);

	List<MyWedOption> MywedoptionList(long argmt);

	List<MyWedAccessory> MywedaccessoryList(long argmt);
	
	Result MywedEditCheck(HttpServletRequest request) ;
	
	int optionSum(long argmt); 
	
	List<MyWedAccessory> mywedacrylist(long argmt);
	
	long categorySum(long argmt);
		
	List<CdCompany> SearchCompanyList(HttpServletRequest request);
	
	Result updatemywedctgy(HttpServletRequest request);
	
	List<CdWeddingHall> SearchWeddingHallList(HttpServletRequest  request);
	
	public int saveWeddingData(HttpServletRequest request) throws ParseException;
}





