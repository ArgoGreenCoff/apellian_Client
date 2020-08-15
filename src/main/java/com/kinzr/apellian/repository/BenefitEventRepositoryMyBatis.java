package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.BnfEventApplicationMapper;
import com.kinzr.apellian.entity.mapper.BnfEventBnfImagesMapper;
import com.kinzr.apellian.entity.mapper.BnfEventImagesMapper;
import com.kinzr.apellian.entity.mapper.BnfEventMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.BnfEvent;
import com.kinzr.apellian.entity.model.BnfEventApplication;
import com.kinzr.apellian.entity.model.BnfEventBnfImages;
import com.kinzr.apellian.entity.model.BnfEventImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
public class BenefitEventRepositoryMyBatis implements BenefitEventRepository{

	@Autowired private BnfEventMapper eventMapper;
	@Autowired private BnfEventApplicationMapper eventAppMapper;
	@Autowired private BnfEventImagesMapper imgMapper;
	@Autowired private BnfEventBnfImagesMapper imgBnfMapper;

	@Override
	public List<BnfEvent> eventList() {
		// TODO Auto-generated method stub
		List<BnfEvent> eventList = eventMapper.selectAll();
		return eventList;
	}
	
	// 이벤트 상세
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public BnfEvent eventDetail(HttpServletRequest request) {		
		
		String idBnfEvent = request.getParameter("idBnfEvent");
		
		BnfEvent eventInfo = eventMapper.selectByPrimaryKey(Integer.parseInt(idBnfEvent));
		
		return eventInfo;
	}

	// 해당 이벤트 신청한 인원수 가져오기
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int eventApplyCnt(HttpServletRequest request) {		
		
		String idBnfEvent = request.getParameter("idBnfEvent");
		
		int eventApplyCnt = eventAppMapper.selectByApplyCntByEvent(Integer.parseInt(idBnfEvent));
		return eventApplyCnt;
	}

	// 해당 유저의 이벤트 신청횟수
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int userApplyCnt(HttpServletRequest request) {		
		
		String idUser = request.getParameter("emailf");
		
		int userApplyCnt = eventAppMapper.selectByApplyCntByUser(idUser);
		
		return userApplyCnt;
	}

	// 해당 이벤트 신청여부
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result eventApplyCheck(HttpServletRequest request) {	// 당첨자 발표시 결제여부 체크
		
		Result result = new Result();
		String idUser = request.getParameter("email");
		String idBnfEvent = request.getParameter("idBnfEvent");

		BnfEventApplication eventApply = new BnfEventApplication();
		eventApply.setIdUser(idUser);
		eventApply.setIdBnfEvent(Integer.parseInt(idBnfEvent));
		
		
		Boolean isRequest = eventAppMapper.selectApplyRequestByUser(eventApply);
		if(isRequest) {	// 신청한경우
			result.setCode("1");
			result.setDescription("신청한 상태입니다.");
		} else {
			result.setCode("-1");
			result.setDescription("아직 신청 전입니다.");
		}
		
		return result;
	}

	// 해당 이벤트 신청 요청
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result eventApplyRequest(BnfEventApplication apply) {	// 당첨자 발표시 결제여부 체크
		
		Result result = new Result();
		int ret = eventAppMapper.insert(apply);
		result.setCode(""+ret);
		result.setDescription("클래스 신청이 완료되었습니다.");
		
		return result;
	}
	
	
	
	// 클래스 이미지 리스트 
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<BnfEventImages> eventImages(Integer idBnfEvent) {
		
		List<BnfEventImages> imgList = imgMapper.selectEventImages(idBnfEvent);
		
		return imgList;
	}
	
	// 클래스 깜짝혜택 이미지 리스트 
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<BnfEventBnfImages> eventBnfImages(Integer idBnfEvent) {
		
		List<BnfEventBnfImages> imgList = imgBnfMapper.selectEventBnfImages(idBnfEvent);
		
		return imgList;
	}

		
	
	
	//-- 당첨자 발표 --//
	// 클래스 신청정보 가져오기
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public BnfEventApplication eventApplyInfo(BnfEventApplication apply) {	
		
		// 클래스와 유저아이디로 신청정보 가져오기
		BnfEventApplication bnfAppByUser = eventAppMapper.selectApplyInfo(apply);
		
		return bnfAppByUser;
	}
	

	
}
