package com.kinzr.apellian.repository;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.mapper.PkgMapper;
import com.kinzr.apellian.entity.mapper.PkgDetailMapper;
import com.kinzr.apellian.entity.mapper.PkgImagesMapper;
import com.kinzr.apellian.entity.mapper.CdPkgThemeMapper;
import com.kinzr.apellian.entity.mapper.CdCompanyMapper;
import com.kinzr.apellian.entity.mapper.PkgApplicationMapper;

import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.Pkg;
import com.kinzr.apellian.entity.model.PkgDetail;
import com.kinzr.apellian.entity.model.PkgImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.CdCompany;
import com.kinzr.apellian.entity.model.CdPkgTheme;
import com.kinzr.apellian.entity.model.PkgApplication;

import lombok.extern.java.Log;

@Log
@Repository
public class PackageRepositoryMyBatis implements PackageRepository{

	@Autowired private UsersMapper userMapper;
	@Autowired private PkgMapper pkgMapper;
	@Autowired private PkgDetailMapper pkgdetailMapper;
	@Autowired private PkgImagesMapper pkgimagesMapper;
	@Autowired private CdPkgThemeMapper cdpkgthemeMapper;
	@Autowired private CdCompanyMapper cdcompanyMapper;
	@Autowired private PkgApplicationMapper pkgapplicationMapper;
	
	
	
	@Override
	public List<Users> selectAll() {
		// TODO Auto-generated method stub
		List<Users> userList = userMapper.selectAll();
		return userList;
	}
	
	
	@Override
	public Users GetUserInfo(String argmt) {
		// TODO Auto-generated method stub
		// 유저체크
		String idUser = argmt;
		Users userInfo = userMapper.selectByPrimaryKey(idUser);
		return userInfo;
	}
	
	

	
	
	
	// 기본 테마 항목 가져오기 
	@Override
	public List<CdPkgTheme> cdPkgThemeList() {		// 클래스 메인 리스트
		
		// TODO Auto-generated method stub
		// 기본 항목 세팅 ( 테마 )
		List<CdPkgTheme> cdPkgThemeList = cdpkgthemeMapper.selectFirstFive();
		
		return cdPkgThemeList;
	}
	
	
	
	// 기본 항목 가져오기 
	@Override
	public List<PkgImages> themeimagesList(Integer argmt) {		// 클래스 메인 리스트
		
		// 기본 항목 세팅
		Integer cdPkgTheme			= argmt;

		// 기본 항목 세팅 ( 테마 )
		List<PkgImages> themeimagesList = pkgimagesMapper.selectByCdpkgTheme(cdPkgTheme);
		
		return themeimagesList;
	}
	
	
	
	// 기본 항목 가져오기 
	@Override
	public List<PkgImages> pkgimagesList(Integer argmt) {		// 클래스 메인 리스트
		
		// 기본 항목 세팅
		Integer cdPkgTheme			= argmt;

		// 기본 항목 세팅 ( 테마 )
		List<PkgImages> pkgimagesList = pkgimagesMapper.selectByIdpkg(cdPkgTheme);
		
		return pkgimagesList;
	}
	
	

	// 기본 항목 가져오기 
	@Override
	public Pkg pkgList(Integer argmt) {		// 페키지 내역 
		
		// 기본 항목 세팅
		Integer idpkg			= argmt;

		// 기본 항목 세팅 ( 테마 )
		Pkg pkgList = pkgMapper.selectByPrimaryKey(idpkg);
		
		return pkgList;
	}
		

	// 기본 항목 가져오기 
	@Override
	public List<PkgDetail> pkgdetailList(Integer argmt) {		// 페키지 내역 
		
		// 기본 항목 세팅
		Integer idpkg			= argmt;

		// 기본 항목 세팅 ( 테마 )
		List<PkgDetail> pkgdetailList = pkgdetailMapper.selectByidPkg(idpkg);
		
		return pkgdetailList;
	}
	
	
	// 기본 항목 가져오기 
	@Override
	public List<PkgDetail> themeSDMList(Integer argmt) {		// 테마별 메인(S,D,M)상세 페키지 내역 
		
		// 기본 항목 세팅
		Integer idtheme			= argmt;

		// 기본 항목 세팅 ( 테마 )
		List<PkgDetail> themeSDMList = pkgdetailMapper.selectBySDMtheme(idtheme);
		
		return themeSDMList;
	}	
	
	
	// 기본 항목 가져오기 
	@Override
	public List<PkgDetail> themedetailList(Integer argmt) {		// 테마별 상세 페키지 내역 
		
		// 기본 항목 세팅
		Integer idtheme			= argmt;

		// 기본 항목 세팅 ( 테마 )
		List<PkgDetail> themedetailList = pkgdetailMapper.selectByCdtheme(idtheme);
		
		return themedetailList;
	}	
	
	// 기본 항목 가져오기 
	@Override
	public CdCompany CdCompanydetaiil(Integer argmt) {		// 페키지 내역 
		
		// 기본 항목 세팅
		Integer cdcompany			= argmt;

		// 기본 항목 세팅 ( 테마 )
		CdCompany cdcompanydetail = cdcompanyMapper.selectByPrimaryKey(cdcompany);
		
		return cdcompanydetail;
	}
	
	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result PackageApplyCheck(HttpServletRequest request) {
		
		Integer irst = 0; 
		Result result = new Result();
		
		String idUser = request.getParameter("email");			// 이메일
				
		String chkperinfo = request.getParameter("chkperinfo");	// 3자 개인정보 동의 
		
		String dsConsult = request.getParameter("dsconsult").trim(); 
		String dsCompany = request.getParameter("dscompany").trim();
		
		System.out.println("chkperinfo================ " + chkperinfo);
		System.out.println("dsConsult================ " + dsConsult + " / " + dsConsult.length());
		System.out.println("dsCompany================ " + dsCompany + " / " + dsCompany.length());
		
		System.out.println("cdbudget================ " + request.getParameter("cdbudget"));
		System.out.println("ynaddtional01================ " + request.getParameter("ynaddtional01"));
		System.out.println("ynaddtional10================ " + request.getParameter("ynaddtional10"));
		
		String ynad01 = "N";
		String ynad02 = "N";
		String ynad03 = "N";
		String ynad04 = "N";
		String ynad05 = "N";
		String ynad06 = "N";
		String ynad07 = "N";
		String ynad08 = "N";
		String ynad09 = "N";
		String ynad10 = "N";
			
		if (request.getParameter("ynaddtional01") != null) { ynad01 = request.getParameter("ynaddtional01"); }
		if (request.getParameter("ynaddtional02") != null) { ynad02 = request.getParameter("ynaddtional02"); }
		if (request.getParameter("ynaddtional03") != null) { ynad03 = request.getParameter("ynaddtional03"); }
		if (request.getParameter("ynaddtional04") != null) { ynad04 = request.getParameter("ynaddtional04"); }
		if (request.getParameter("ynaddtional05") != null) { ynad05 = request.getParameter("ynaddtional05"); }
		if (request.getParameter("ynaddtional06") != null) { ynad06 = request.getParameter("ynaddtional06"); }
		if (request.getParameter("ynaddtional07") != null) { ynad07 = request.getParameter("ynaddtional07"); }
		if (request.getParameter("ynaddtional08") != null) { ynad08 = request.getParameter("ynaddtional08"); }
		if (request.getParameter("ynaddtional09") != null) { ynad09 = request.getParameter("ynaddtional09"); }
		if (request.getParameter("ynaddtional10") != null) { ynad10 = request.getParameter("ynaddtional10"); }
		
		
		System.out.println("ynaddtional01================ " + ynad01);
		System.out.println("ynaddtional02================ " + ynad02);
		System.out.println("ynaddtional03================ " + ynad03);
		System.out.println("ynaddtional04================ " + ynad04);		
		System.out.println("ynaddtional05================ " + ynad05); 
		System.out.println("ynaddtional06================ " + ynad06);
		System.out.println("ynaddtional07================ " + ynad07);
		System.out.println("ynaddtional09================ " + ynad08);		
		System.out.println("ynaddtional09================ " + ynad09);
		System.out.println("ynaddtional10================ " + ynad10);
		
		/*
        // 의견 값 넣었을 경우 체크
		if ((dsConsult != null) || !dsConsult.equals("")){
			
	        if (dsConsult.length() < 5) {
	        	// 정규식 불일치
	        	result.setCode("I");
				result.setDescription("의견에 대해서 5자 이상 입력해주세요.");
	        	return result;        	
	        }    
		}
		
		        
		
        // 관심업체 값 넣었을 경우 체크
		if ((dsCompany != null) || !dsCompany.equals("")){
			
	        if (dsCompany.length() < 5) {
	        	// 정규식 불일치
	        	result.setCode("I");
				result.setDescription("관심 업체에 대해서 5자 이상 입력해주세요.");
	        	return result;        	
	        }    
		}
		
		*/
		

		
        // 약관동의 체크유무 체크
        if ((chkperinfo == null)||(!chkperinfo.equals("Y"))) {        
        	// 정규식 불일치
        	result.setCode("I");
			result.setDescription("약관에 동의해주세요.");
        	return result;        	
        } 
          
        

		
        		
		System.out.println("================ insert start");        
		// 정상의 경우 등록처리 
		
		PkgApplication InsPkgApplication = new PkgApplication(); //데이터 타입
		
	    //InsPkgApplication.setIdPkgApplication(request.getParameter("idPkgApplication"));
	    InsPkgApplication.setIdUser(idUser);
	    InsPkgApplication.setIdPkg(Integer.parseInt(request.getParameter("idPkg")));
	    InsPkgApplication.setCdBudget(request.getParameter("cdbudget")); 
	    InsPkgApplication.setYnAddtional01(ynad01); 
	    InsPkgApplication.setYnAddtional02(ynad02); 
	    InsPkgApplication.setYnAddtional03(ynad03); 
	    InsPkgApplication.setYnAddtional04(ynad04); 
	    InsPkgApplication.setYnAddtional05(ynad05); 
	    InsPkgApplication.setYnAddtional06(ynad06); 
	    InsPkgApplication.setYnAddtional07(ynad07); 
	    InsPkgApplication.setYnAddtional08(ynad08); 
	    InsPkgApplication.setYnAddtional09(ynad09); 
	    InsPkgApplication.setYnAddtional10(ynad10); 			
	    Date dt = new Date();
	    InsPkgApplication.setDtReg(dt); 
	    InsPkgApplication.setDsConsult(request.getParameter("dsconsult")); 
	    InsPkgApplication.setDsCompany(request.getParameter("dscompany")); 

		irst = pkgapplicationMapper.insert(InsPkgApplication);
		
		System.out.println("================ insert ok ");
		if (irst == 1) {
			result.setCode("00");
			result.setDescription("상담 등록성공");
			//result.setUserInfo(user);
			return result;
		}
		else {
			result.setCode("I");
			result.setDescription("등록처리 중 오류가 발생하였습니다.");
			//result.setUserInfo(user);
			return result;
		}		
		
		// return result;
	}
	
	
	
	
	
}
