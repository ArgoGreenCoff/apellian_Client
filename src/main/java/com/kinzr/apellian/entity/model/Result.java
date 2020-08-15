package com.kinzr.apellian.entity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

	private String code;
	
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
	private String description;
	
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }	
	
	private Users userInfo;
	
    
 
    public void setUserInfo(Users userInfo) {
        this.userInfo = userInfo;
    }	
    
}
