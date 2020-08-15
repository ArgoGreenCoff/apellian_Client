package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.BnfClassApplicationMapper;
import com.kinzr.apellian.entity.mapper.BnfClassBnfImagesMapper;
import com.kinzr.apellian.entity.mapper.BnfClassImagesMapper;
import com.kinzr.apellian.entity.mapper.BnfClassMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.BnfClass;
import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
@Transactional(readOnly=true) 
public class BenefitClassRepositoryMyBatis implements BenefitClassRepository{

	@Autowired private BnfClassApplicationMapper classAppMapper;
	@Autowired private BnfClassMapper classMapper;
	@Autowired private BnfClassImagesMapper imgMapper;
	@Autowired private BnfClassBnfImagesMapper imgBnfMapper;

	// 클래스 메인 리스트
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<BnfClass> classList() {		
		
		List<BnfClass> classList = classMapper.selectAll();
		
		return classList;
	}

	// 클래스 상세
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public BnfClass classDetail(HttpServletRequest request) {		
		
		String idClass = request.getParameter("idClass");
		
		BnfClass classInfo = classMapper.selectByPrimaryKey(Integer.parseInt(idClass));
		
		return classInfo;
	}

	// 해당 클래스의 신청한 인원수 가져오기
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int classApplyCnt(HttpServletRequest request) {		
		
		String idClass = request.getParameter("idClass");
		
		int classApplyCnt = classAppMapper.selectByApplyCntByClass(Integer.parseInt(idClass));
		
		return classApplyCnt;
	}

	// 해당 유저의 클래스 신청횟수
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int userApplyCnt(HttpServletRequest request) {		
		
		String idUser = request.getParameter("email");
		
		int userApplyCnt = classAppMapper.selectByApplyCntByUser(idUser);
		
		return userApplyCnt;
	}

	// 해당 클래스 신청여부
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result classApplyCheck(HttpServletRequest request) {	// 당첨자 발표시 결제여부 체크
		
		Result result = new Result();
		String idUser = request.getParameter("email");
		String idClass = request.getParameter("idClass");

		BnfClassApplication bnfApply = new BnfClassApplication();
		bnfApply.setIdUser(idUser);
		bnfApply.setIdBnfClass(Integer.parseInt(idClass));
		
		
		int requestCnt = classAppMapper.selectApplyRequestByUser(bnfApply);
		if(requestCnt > 0) {	// 신청한경우
			result.setCode("1");
			result.setDescription("신청한 상태입니다.");
		} else {
			result.setCode("-1");
			result.setDescription("아직 신청 전입니다.");
		}
		
		return result;
	}

	// 해당 클래스 신청 요청
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result classApplyRequest(BnfClassApplication apply) {	// 당첨자 발표시 결제여부 체크
		
		Result result = new Result();
		int ret = classAppMapper.insert(apply);
		result.setCode(""+ret);
		result.setDescription("클래스 신청이 완료되었습니다.");
		
		return result;
	}
	
	
	
	// 클래스 이미지 리스트 
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<BnfClassImages> classImages(Integer idClass) {
		
		List<BnfClassImages> imgList = imgMapper.selectClassImages(idClass);
		
		return imgList;
	}
	
	// 클래스 깜짝혜택 이미지 리스트 
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<BnfClassBnfImages> classBnfImages(Integer idClass) {
		
		List<BnfClassBnfImages> imgList = imgBnfMapper.selectClassBnfImages(idClass);
		
		return imgList;
	}

	
	
	
	
	
	
	
	
	//-- 당첨자 발표 --//
	// 클래스 신청정보 가져오기
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public BnfClassApplication classApplyInfo(BnfClassApplication apply) {	
		
		// 클래스와 유저아이디로 신청정보 가져오기
		BnfClassApplication bnfAppByUser = classAppMapper.selectApplyInfo(apply);
		
		return bnfAppByUser;
	}
	


}
