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
import com.kinzr.apellian.entity.mapper.BnfSurveyMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.BnfEvent;
import com.kinzr.apellian.entity.model.BnfEventApplication;
import com.kinzr.apellian.entity.model.BnfEventBnfImages;
import com.kinzr.apellian.entity.model.BnfEventImages;
import com.kinzr.apellian.entity.model.BnfSurvey;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
public class BenefitSurveyRepositoryMyBatis implements BenefitSurveyRepository{

	@Autowired private BnfSurveyMapper surveyMapper;

	@Override
	public List<BnfSurvey> surveyList() {
		// TODO Auto-generated method stub
		List<BnfSurvey> surveyList = surveyMapper.selectAll();
		return surveyList;
	}
	

		
}
