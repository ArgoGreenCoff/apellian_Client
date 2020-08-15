package com.kinzr.apellian.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class BnfSurvey {

	private Integer idSurvey;		// DB Index
	private String dsSurveyUrl;
	private String ynSurvey;
	private String idReg;
	private Date dtReg;
	private String nmSurvey;
	private String prizeImg;
	private String dsPrize;
	
	// ID_SURVEY
	public void setIdSurvey(Integer idSurvey) {
		this.idSurvey = idSurvey;
	}
	public Integer getIdSurvey() {
		return idSurvey;
	}
	
	// DS_SURVEY_URL
	public void setDsSurveyUrl(String dsSurveyUrl) {
		this.dsSurveyUrl = dsSurveyUrl;
	}
	public String getDsSurveyUrl() {
		return dsSurveyUrl;
	}
	
	// YN_SURVEY
	public void setYnSurvey(String ynSurvey) {
		this.ynSurvey = ynSurvey;
	}
	public String getYnSurvey() {
		return ynSurvey;
	}
	
	// ID_REG
	public void setIdReg(String idReg) {
		this.idReg = idReg;
	}
	public String getIdReg() {
		return idReg;
	}
	
	// DT_REG
	public void setDtReg(Date dtReg) {
		this.dtReg = dtReg;
	}
	public Date getDtReg() {
		return dtReg;
	}
	
	// NM_SURVEY
	public void setNmSurvey(String nmSurvey) {
		this.nmSurvey = nmSurvey;
	}
	public String getNmSurvey() {
		return nmSurvey;
	}
	
	// PRIZE_IMG
	public void setPrizeImg(String prizeImg) {
		this.prizeImg = prizeImg;
	}
	public String getPrizeImg() {
		return prizeImg;
	}
	
	// DS_PRIZE
	public void setDsPrize(String dsPrize) {
		this.dsPrize = dsPrize;
	}
	public String getDsPrize() {
		return dsPrize;
	}
	

	

}