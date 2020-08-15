package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.CdPkgTheme;
import com.kinzr.apellian.entity.model.Pkg;
import com.kinzr.apellian.entity.model.PkgDetail;
import com.kinzr.apellian.entity.model.PkgImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.CdCompany;

public interface PackageRepository {

	List<Users> selectAll();
	
	Users GetUserInfo(String argmt);						// 사용자 정보
	
	List<CdPkgTheme> cdPkgThemeList();						// 클래스 메인 리스트
	
	List<PkgImages> themeimagesList(Integer argmt);			// 클래스 메인 리스트 테마별 패키지이미지	
	
	List<PkgImages> pkgimagesList(Integer argmt);			// 패키지 이미지 디테일 
	
	List<PkgDetail> themedetailList(Integer argmt);			// 테마별 상세 페키지 내역	
	
	List<PkgDetail> themeSDMList(Integer argmt);			// 테마별 메인(S,D,M)상세 페키지 내역 
	
	Pkg pkgList(Integer argmt);								// 페키지 내역 

	List<PkgDetail> pkgdetailList(Integer argmt);			// 페키지 상세내역 

	CdCompany CdCompanydetaiil(Integer argmt);				// 페키지 내역 
	
	Result PackageApplyCheck(HttpServletRequest request);	// 상담신청
	
}
