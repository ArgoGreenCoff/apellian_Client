package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.BnfClass;
import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

public interface BenefitClassRepository {

	
	// 클래스 메인
	List<BnfClass> classList(); 
	
	// 클래스 상세
	BnfClass classDetail(HttpServletRequest request); 

	// 해당 클래스 신청인원
	int classApplyCnt(HttpServletRequest request);
	
	// 해당 유저의 클래스 신청횟수
	int userApplyCnt(HttpServletRequest request);
	
	// 해당 클래스 신청여부
	Result classApplyCheck(HttpServletRequest request);
	
	// 해당 클래스 신청요청
	Result classApplyRequest(BnfClassApplication apply);
	
	
	
	
	// 클래스 이미지 
	List<BnfClassImages> classImages(Integer idClass);
	
	// 클래스 깜짝혜택 이미지
	List<BnfClassBnfImages> classBnfImages(Integer idClass);

	
	// 클래스 신청정보 가져오기
	BnfClassApplication classApplyInfo(BnfClassApplication apply);
	
	
}
