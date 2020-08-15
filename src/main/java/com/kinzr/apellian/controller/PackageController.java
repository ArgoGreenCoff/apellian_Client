package com.kinzr.apellian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kinzr.apellian.entity.mapper.UsersMapper;

import com.kinzr.apellian.entity.model.Pkg;
import com.kinzr.apellian.entity.model.PkgImages;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.PkgDetail;
import com.kinzr.apellian.entity.model.CdPkgTheme;
import com.kinzr.apellian.entity.model.CdCompany;


import com.kinzr.apellian.repository.PackageRepository;
import com.kinzr.apellian.repository.UsersRepository;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/package") //패키지 리스트, 패키지 상세, 업체상세 팝업, 패키지 상담신청
public class PackageController {

	@Autowired UsersMapper userMapper;
	@Autowired PackageRepository PkgRepo;
	@Autowired UsersRepository userRepo;
		
	
	@RequestMapping(value="/packageList", method= {RequestMethod.POST, RequestMethod.GET})
	public String packageList(Model model, HttpServletRequest request) {
		
		String idUser = request.getParameter("email");
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);
		
		
		// 테마 조회
		List<CdPkgTheme> cdpkgthemelist = PkgRepo.cdPkgThemeList();
		
		request.setAttribute("cdpkgthemelist", cdpkgthemelist);
		model.addAttribute("cdpkgthemelist", cdpkgthemelist);
		
		
		// 패키지 조회
		String tmpcdPkgTheme = request.getParameter("cdPkgTheme");
		
		System.out.println("tmpcdPkgTheme : " + tmpcdPkgTheme);
		
		Integer cdPkgTheme = 1;
		if ((tmpcdPkgTheme == null)||(tmpcdPkgTheme=="")) {
			cdPkgTheme = 1;
		}
		else {
			cdPkgTheme = Integer.valueOf(tmpcdPkgTheme);			// 	
		}
		
		System.out.println("cdPkgTheme : " + cdPkgTheme);
		
		List<PkgImages> themeimagesList = PkgRepo.themeimagesList(cdPkgTheme);
		request.setAttribute("themeimagesList", themeimagesList);
		model.addAttribute("themeimagesList", themeimagesList);
		
		
		System.out.println("themeimagesList.size() : " + themeimagesList.size());		
		
		System.out.println("2");
		
		// 페키지 상세내역 카테고리(업체내역) 조회
		List<PkgDetail> themedetailList = PkgRepo.themeSDMList(cdPkgTheme);
		request.setAttribute("themedetailList", themedetailList);
		model.addAttribute("themedetailList", themedetailList);
		
		
		request.setAttribute("NowcdPkgTheme", cdPkgTheme);
		model.addAttribute("NowcdPkgTheme", cdPkgTheme);
		

		System.out.println("themedetailList.size() : " + themedetailList.size());		
		
		System.out.println("3");
		
		return "package/packageList";
	}	
	
	
	@RequestMapping(value="/packageDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String packageDetail(Model model, HttpServletRequest request) {
				
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		
		System.out.println("start idupser : " +idUser);
		
		// 사용자 정보
		Users userInfo = PkgRepo.GetUserInfo(idUser);		
		
		// 비정상 로그인 상황이면 로그인 화면으로 처리
		if (userInfo==null) {
			// return "member/signin";
		}
				
		request.setAttribute("userInfo", userInfo);
		model.addAttribute("userInfo", userInfo);
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);
		
		// 등록하기 위해서 사전 
		request.setAttribute("gubun", "S");
		model.addAttribute("gubun", "S");

		
		System.out.println("request idPkg : " +request.getParameter("idPkg"));	
		
		Integer idpkg = Integer.parseInt(request.getParameter("idPkg"));

		// 패키지 상세내역 조회
		// pkg  
		Pkg pkg = PkgRepo.pkgList(idpkg);
		request.setAttribute("pkg", pkg);
		model.addAttribute("pkg", pkg);
		
				
		// 이미지 가져오기
		List<PkgImages> pkgimagesList = PkgRepo.pkgimagesList(idpkg);
		request.setAttribute("pkgimagesList", pkgimagesList);
		model.addAttribute("pkgimagesList", pkgimagesList);
		
		
		System.out.println("pkgimagesList.size() : " + pkgimagesList.size());		
		
		
		// 페키지 상세내역 카테고리(업체내역) 조회
		List<PkgDetail> pkgdetailList = PkgRepo.pkgdetailList(idpkg);
		request.setAttribute("pkgdetailList", pkgdetailList);
		model.addAttribute("pkgdetailList", pkgdetailList);		
		
		System.out.println("pkgdetailList.size() : " + pkgdetailList.size());			
		
		return "package/packageDetail";
	}	
		
	
	@RequestMapping(value="/companyDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String companyDetail(Model model, HttpServletRequest request) {
		

		System.out.println("idpkg : " + request.getParameter("idPkg"));	
		System.out.println("cdCompany : " + request.getParameter("cdCompany"));	

		Integer idpkg = Integer.parseInt(request.getParameter("idPkg"));		
		Integer cdcompany = Integer.parseInt(request.getParameter("cdCompany"));
		
		
		// 패키지 상세내역 조회
		//  
		Pkg pkg = PkgRepo.pkgList(idpkg);
		request.setAttribute("pkg", pkg);
		model.addAttribute("pkg", pkg);
		
		System.out.println("================");
		
		// 이미지 가져오기
		List<PkgImages> pkgimagesList = PkgRepo.pkgimagesList(idpkg);
		request.setAttribute("pkgimagesList", pkgimagesList);
		model.addAttribute("pkgimagesList", pkgimagesList);			

		System.out.println("pkgimagesList : " + pkgimagesList.size());	

		
		// 업체 상세내역 조회
		CdCompany cdcompanydetail = PkgRepo.CdCompanydetaiil(cdcompany);
		request.setAttribute("cdcompanydetail", cdcompanydetail);
		model.addAttribute("cdcompanydetail", cdcompanydetail);
		
		System.out.println("cdcompanydetail : " + cdcompanydetail.getNmCompany());	
		
		
		
		return "package/companyDetail";
	}	
		
	
	
	


	@RequestMapping(value="/packageApply", method= {RequestMethod.POST, RequestMethod.GET})
	public String packageApply(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		
		System.out.println("=======1=========");
		
		
		System.out.println("idUser    : " + request.getParameter("email"));	
		System.out.println("idpkg     : " + request.getParameter("idPkg"));	
		System.out.println("gubun     : " + request.getParameter("gubun"));	
		
		String idUser  = request.getParameter("email");		
		Integer idpkg = Integer.parseInt(request.getParameter("idPkg"));		
		String gubun  = request.getParameter("gubun");

		String chkperinfo = request.getParameter("chkperinfo");	// 3자 개인정보 동의
		
		System.out.println("=======2=========");
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);		
		request.setAttribute("idpkg", idpkg);
		model.addAttribute("idpkg", idpkg);
		
		// 패키지 상세내역 조회
		//  
		Pkg pkg = PkgRepo.pkgList(idpkg);
		request.setAttribute("pkg", pkg);
		model.addAttribute("pkg", pkg);
		
		System.out.println("================");

		
		// 페키지 상세내역 카테고리(업체내역) 조회
		List<PkgDetail> pkgdetailList = PkgRepo.pkgdetailList(idpkg);
		request.setAttribute("pkgdetailList", pkgdetailList);
		model.addAttribute("pkgdetailList", pkgdetailList);		
		
		System.out.println("pkgdetailList.size() : " + pkgdetailList.size());			
				
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);		
		request.setAttribute("idpkg", idpkg);
		model.addAttribute("idpkg", idpkg);
		
		request.setAttribute("dsconsult", request.getParameter("dsconsult"));	// 
		model.addAttribute("dsconsult", request.getParameter("dsconsult"));	// 	
		request.setAttribute("dscompany", request.getParameter("dscompany"));	// 
		model.addAttribute("dscompany", request.getParameter("dscompany"));	//
		
		request.setAttribute("chkperinfo", chkperinfo);	// 
		model.addAttribute("chkperinfo", chkperinfo);	//
		
		System.out.println("S ================ : " + chkperinfo);
		
		if (gubun.contentEquals("S")){
			
			gubun = "I";
			
			request.setAttribute("gubun", gubun);	// 조회 후에는 등록처리를 위해
			model.addAttribute("gubun", gubun);	// 조회 후에는 등록처리를 위해	
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);			

			System.out.println("S END================");
			
			// 등록 상태로 처리
			return "package/packageApply"; // ?idPkg=\"+idpkg+\"&email=\"+idUser+\"&gubun=\"+gubun
			
		}
		else {

			// 등록 처리
			result = PkgRepo.PackageApplyCheck(request);
			
			
			// request.setAttribute("email", idUser);
			// model.addAttribute("email", idUser);		
			// request.setAttribute("idpkg", idpkg);
			// model.addAttribute("idpkg", idpkg);
			
			
			System.out.println("INS START================"); 
			
			if (result.getCode().equals("00")) {
				
				request.setAttribute("email", idUser);
				model.addAttribute("email", idUser);
				
				request.setAttribute("idpkg", idpkg);
				model.addAttribute("idpkg", idpkg);		
				
				
				request.setAttribute("result", result);
				model.addAttribute("result", result);
				
				System.out.println("I CK END================"); 

				return "package/packageApplyOk"; // ?idPkg="+idpkg+"&email="+idUser; // 로그인창으로 리다이렉트
				
				// return "package/packageList"; // ?idPkg="+idpkg+"&email="+idUser; // 로그인창으로 리다이렉트
  
			}else {
				
				
				// request.setAttribute("email", idUser);
				// model.addAttribute("email", idUser);		
				// request.setAttribute("idpkg", idpkg);
				// model.addAttribute("idpkg", idpkg);
				
				gubun = "I";
				
				request.setAttribute("gubun", gubun);	// 조회 후에는 등록처리를 위해
				model.addAttribute("gubun", gubun);	// 조회 후에는 등록처리를 위해	
				
				request.setAttribute("email", idUser);
				model.addAttribute("email", idUser);		
				request.setAttribute("idpkg", idpkg);
				model.addAttribute("idpkg", idpkg);
				
				request.setAttribute("dsconsult", request.getParameter("dsconsult"));	// 
				model.addAttribute("dsconsult", request.getParameter("dsconsult"));	// 	
				request.setAttribute("dscompany", request.getParameter("dscompany"));	// 
				model.addAttribute("dscompany", request.getParameter("dscompany"));	//
				
				/*
				request.setAttribute("ynaddtional01", request.getParameter("ynaddtional01"));	//
				request.setAttribute("ynaddtional02", request.getParameter("ynaddtional02"));	//
				request.setAttribute("ynaddtional03", request.getParameter("ynaddtional03"));	//
				request.setAttribute("ynaddtional04", request.getParameter("ynaddtional04"));	//
				request.setAttribute("ynaddtional05", request.getParameter("ynaddtional05"));	//
				request.setAttribute("ynaddtional06", request.getParameter("ynaddtional06"));	//
				request.setAttribute("ynaddtional07", request.getParameter("ynaddtional07"));	//
				request.setAttribute("ynaddtional08", request.getParameter("ynaddtional08"));	//
				request.setAttribute("ynaddtional09", request.getParameter("ynaddtional09"));	//
				request.setAttribute("ynaddtional10", request.getParameter("ynaddtional10"));	//
				                                                                      
				model.addAttribute("ynaddtional01", request.getParameter("ynaddtional01"));	//
				model.addAttribute("ynaddtional02", request.getParameter("ynaddtional02"));	//
				model.addAttribute("ynaddtional03", request.getParameter("ynaddtional03"));	//
				model.addAttribute("ynaddtional04", request.getParameter("ynaddtional04"));	//
				model.addAttribute("ynaddtional05", request.getParameter("ynaddtional05"));	//
				model.addAttribute("ynaddtional06", request.getParameter("ynaddtional06"));	//
				model.addAttribute("ynaddtional07", request.getParameter("ynaddtional07"));	//
				model.addAttribute("ynaddtional08", request.getParameter("ynaddtional08"));	//
				model.addAttribute("ynaddtional09", request.getParameter("ynaddtional09"));	//
				model.addAttribute("ynaddtional10", request.getParameter("ynaddtional10"));	//
				                                                                      				
				*/
				
				request.setAttribute("chkperinfo", chkperinfo);	// 
				model.addAttribute("chkperinfo", chkperinfo);	//
				
				
				request.setAttribute("result", result);
				model.addAttribute("result", result);

				
				System.out.println("INS EEND================");
				
				// 등록 상태로 처리
				return "package/packageApply"; // ?idPkg="+idpkg+"&email="+idUser+"&gubun="+gubun;
			}			
		}
		

		
		//thymeleaf 사용시
		//return "thymeleaf/withdraw";
	}
	
	
	
	@RequestMapping(value="/packageApplyOk", method= {RequestMethod.POST})
	public String signupOk(Model model, HttpServletRequest request) {
		
		return "package/packageApplyOk"; // withdrawOk 
		
	} 
	
	
	
	
}
