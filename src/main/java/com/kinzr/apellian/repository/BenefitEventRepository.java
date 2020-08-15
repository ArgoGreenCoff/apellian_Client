package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.BnfEvent;
import com.kinzr.apellian.entity.model.BnfEventApplication;
import com.kinzr.apellian.entity.model.BnfEventBnfImages;
import com.kinzr.apellian.entity.model.BnfEventImages;
import com.kinzr.apellian.entity.model.Result;

public interface BenefitEventRepository {

	// 이벤트 리스트
	List<BnfEvent> eventList();
	
	// 이벤트 상세
	BnfEvent eventDetail(HttpServletRequest request);
	
	// 해당 이벤트 신청인원
	int eventApplyCnt(HttpServletRequest request);

	// 해당 유저의 이벤트 신청횟수
	int userApplyCnt(HttpServletRequest request);
	
	// 해당 이벤트 신청여부
	Result eventApplyCheck(HttpServletRequest request);
	
	// 해당 이벤트 신청요청
	Result eventApplyRequest(BnfEventApplication apply);
	
	
	
	
	// 이벤트 이미지 
	List<BnfEventImages> eventImages(Integer idBnfEvent);
	
	// 이벤트 깜짝혜택 이미지
	List<BnfEventBnfImages> eventBnfImages(Integer idBnfEvent);

	// 클래스 신청정보 가져오기
	BnfEventApplication eventApplyInfo(BnfEventApplication apply);
	
}
