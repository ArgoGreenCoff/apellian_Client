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
import com.kinzr.apellian.entity.model.BnfSurvey;
import com.kinzr.apellian.entity.model.Result;

public interface BenefitSurveyRepository {

	// 설문조사 리스트
	List<BnfSurvey> surveyList();
	
	
}
