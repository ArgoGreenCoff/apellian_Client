package com.kinzr.apellian.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.MyWed;
import com.kinzr.apellian.entity.model.MyWedAccessory;
import com.kinzr.apellian.entity.model.MyWedCategory;
import com.kinzr.apellian.entity.model.MyWedOption;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.CdCompany;
import com.kinzr.apellian.entity.model.CdWeddingHall;
import com.kinzr.apellian.repository.UsersRepository;
import com.kinzr.apellian.repository.MyWeddingRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import lombok.extern.java.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
@Log
@RequestMapping("/mywed") //마이웨딩, 정보등록, 업체선택, 비용입력, 웨딩홀 선택
public class MyWeddingController {

	@Autowired UsersMapper userMapper;
	@Autowired UsersRepository userRepo;
	@Autowired MyWeddingRepository myWedRepo;	
	
	
	@RequestMapping(value="/myWedding", method= {RequestMethod.POST, RequestMethod.GET})
	public String myWedding(Model model, HttpServletRequest request) {
		
		String idUser = request.getParameter("email");
		
		model.addAttribute("email", idUser);
		request.setAttribute("email", idUser);	
		
		// 사용자 정보 조회
		Users users = myWedRepo.MywedUser(request);
		
		if (users == null) {
			return "mywed/myWedding";
		}
		
		model.addAttribute("users", users);
		request.setAttribute("users", users);
		
		// 마이웨딩 정보 조회
		MyWed myd = new MyWed();			
		myd = myWedRepo.MyweddingList(users.getUsername());		

		model.addAttribute("email", idUser);
		request.setAttribute("email", idUser);
		
		// 일수 계산민 웨딩 날짜 정리
		Date beginDate = new Date(); 		// 현재일자
		Date endDate = new Date(); 			// 웨딩일자 ( 미계약고객은 1년뒤 오늘 )
		
		long diffDay = 0;
		
		// 미가입 고객은 등록 처리부터  
		if (myd == null) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());;
			cal.add(Calendar.DATE,  365);
			endDate = cal.getTime();

		}
		else {
			
			endDate = myd.getDtWedding();
		}
		
        
		long difftime = endDate.getTime() - beginDate.getTime();
		diffDay = (difftime / (24 * 60 * 60 * 1000)); //  + NdiffDay; // 가입고객(웨딩일자 차이 계산), 미가입고객(+365)
		
		request.setAttribute("diffDay", diffDay);
		model.addAttribute("diffDay", diffDay);

		// 월, 일 구하기
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일"); 
		SimpleDateFormat format4 = new SimpleDateFormat("HH:mm");

		String wdDt = format1.format(endDate);
		String wedmonth = wdDt.substring(5,7);
		String weddate  = wdDt.substring(8,10) ;
		
		String wedday = format2.format(endDate);
		String wddtim = format4.format(endDate);
		String dtWedDay = wedday + " " + wddtim;  
		
		model.addAttribute("email", idUser);
		request.setAttribute("email", idUser);		
		
		request.setAttribute("wedmonth", wedmonth);
		request.setAttribute("weddate", weddate);
		model.addAttribute("wedmonth", wedmonth);
		model.addAttribute("weddate", weddate);
		model.addAttribute("dtWedDay", dtWedDay);
		model.addAttribute("dtWedDay", dtWedDay);
		
		// 미가입 고객은 등록 처리부터   
		if (myd == null) {
						
			return "mywed/myWedding";
		}
		

		
		
		model.addAttribute("myd", myd);
		request.setAttribute("myd", myd);
		
		long idWedding = myd.getIdWedding();
		
		// 카테고리 정보 조회		
		List<MyWedCategory> mycategorylist = myWedRepo.MywedcategoryList(idWedding);
		
		// 미가입 고객은 등록 처리부터  
		if (mycategorylist == null) {
			
			return "mywed/myWedding";
		}
		
		request.setAttribute("mycategorylist", mycategorylist);
		model.addAttribute("mycategorylist", mycategorylist);
		
		
		// 선택사항 합계액
		long totCtgySumAmp = myWedRepo.categorySum(idWedding);
		
		// 미가입 고객은 등록 처리부터
		
		// DecimalFormat formatter = new DecimalFormat("###,###,###,###");
		
		// String strtotCtgySumAmp = formatter.format(totCtgySumAmp);
		System.out.println("totCtgySumAmp ------xxxxx----- : " + totCtgySumAmp);
		
		request.setAttribute("totCtgySumAmp", totCtgySumAmp);
		model.addAttribute("totCtgySumAmp", totCtgySumAmp);
		
		request.setAttribute("totCtgySumAmp1", totCtgySumAmp);
		model.addAttribute("totCtgySumAmp1", totCtgySumAmp);
		
		
		// 부다생품 정보 조회
		List<MyWedAccessory> mwdaccessorylist = myWedRepo.MywedaccessoryList(idWedding);
		request.setAttribute("mwdaccessorylist", mwdaccessorylist);
		model.addAttribute("mwdaccessorylist", mwdaccessorylist);

		// 선택사항 조회
		List<MyWedOption> optionlist = myWedRepo.MywedoptionList(idWedding);
		request.setAttribute("optionlist", optionlist);
		model.addAttribute("optionlist", optionlist);		
			
		request.setAttribute("email", request.getParameter("email"));	// 왔던값 되돌리기
		
		
		// 선택사항 합계액
		int totOptnSumAmp = myWedRepo.optionSum(idWedding);
		
		//DecimalFormat formatter = new DecimalFormat("###,###,###,###");
		
		// String strtotOptnSumAmp = formatter.format(totOptnSumAmp);
		
		
		System.out.println("totOptnSumAmp ------xxxxx----- : " + totOptnSumAmp);
		
		request.setAttribute("totOptnSumAmp", totOptnSumAmp);
		model.addAttribute("totOptnSumAmp", totOptnSumAmp);

		request.setAttribute("totOptnSumAmp1", totOptnSumAmp);
		model.addAttribute("totOptnSumAmp1", totOptnSumAmp);
		
		
		
		  		
		
		return "mywed/myWedding";
	}	
	
	
	@RequestMapping(value="/edit", method= {RequestMethod.POST, RequestMethod.GET})
	public  String edit(Model model, HttpServletRequest request) { // @ResponseBody
		
		// MyWed myd = new MyWed();	
		
		// myd = myWedRepo.MyweddingList(request);
		// model.addAttribute("myd", myd);
		
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		Users user = userRepo.GetUserInfo(idUser);

		String nmWoman = request.getParameter("nmWoman");
		String nmMan = request.getParameter("nmMan");
		String nmPartner = "";
		
		if(user.getNmUser().equals(nmMan)) {
			nmPartner = nmWoman;
		} else {
			nmPartner = nmMan;
		}
		request.setAttribute("nmPartner", nmPartner);

		
		String idWeddingHall = request.getParameter("idWeddingHall");
		String nmWeddingHall = request.getParameter("nmWeddingHall");
		String wedDate = request.getParameter("WedDate");
		String wedTime = request.getParameter("wedTime");
		
		String NextPrc = request.getParameter("NextPrc");	// 처음 들어오면 조회여서 입력으로 넘어갸야 됨
		
		
		if ((idUser==null) || NextPrc.equals("S")) {
			
			System.out.println("idUser  first  ----- : " + idUser);
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
						
			// MY_WED 테이블에서 정보 가져와서 edit.jsp에 넘겨주기
			MyWed wed = myWedRepo.MyweddingList(idUser);
			if(wed != null) {
				model.addAttribute("nmMan", wed.getNmMan());
				model.addAttribute("nmWoman", wed.getNmWoman());
				model.addAttribute("idWeddingHall", wed.getIdWeddingHall());
				model.addAttribute("nmWeddingHall", wed.getNmWeddingHall());
				
				Date from = wed.getDtWedding();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");
				String to = transFormat.format(from);
				model.addAttribute("wedDate", to);
				model.addAttribute("wedTime", wed.getTmWedding());
			}
			
			model.addAttribute("NextPrc", "I");
			model.addAttribute("result", result);
			
			
			return "mywed/edit";			
			
		}
		else {
	
			System.out.println("idUser ------ BEFORE INS xxx----- : " + idUser);
	
			// 등록처리 
			result = myWedRepo.MywedEditCheck(request);
			
			// Wedding 정보 입력된경우 MY_WED_CATEGORY 생성
			myWedRepo.addMywedcategoryList(idUser);

			System.out.println("idUser ------NEXT ----- : " + idUser);
			
			
	
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
			
			if (result.getCode().equals("00")) {
				
				System.out.println("idUser  success ----- : " + idUser);
				
				request.setAttribute("email", idUser);
				model.addAttribute("email", idUser);
				
				request.setAttribute("result", result);
				model.addAttribute("result", result);
				
					
				return "mywed/editOk"; //  리다이렉트
				
			}else {
				
				System.out.println("idUser  fail ----- : " + idUser);
				
				model.addAttribute("email", idUser);
							
	
				model.addAttribute("nmPartner", nmPartner);
				model.addAttribute("nmMan", nmMan);
				model.addAttribute("nmWoman", nmWoman);
				model.addAttribute("idWeddingHall", idWeddingHall);
				model.addAttribute("nmWeddingHall", nmWeddingHall);
				model.addAttribute("wedDate", wedDate);
				model.addAttribute("wedTime", wedTime);
				
				model.addAttribute("NextPrc", "I");
				model.addAttribute("result", result);
				
				
				return "mywed/edit";
			}
		}
	}	
	
	

	
	@RequestMapping(value="/optionList", method= {RequestMethod.POST, RequestMethod.GET})
	public String optionList(Model model, HttpServletRequest request) {
		
		String tmpidWedding = request.getParameter("idWedding");
		long idWedding =  Long.parseLong(tmpidWedding); 
		
		// 선택사항 조회
		List<MyWedOption> optionlist = myWedRepo.MywedoptionList(idWedding);
		request.setAttribute("optionlist", optionlist);
		model.addAttribute("optionlist", optionlist);
		
		
		
		// 선택사항 합계액
		// System.out.println("idWedding ----------- : " + idWedding);
		
		int totOptnSumAmp = myWedRepo.optionSum(idWedding);
		
		// System.out.println("totOptnSumAmp ------xxxxx----- : " + totOptnSumAmp);
		
		
		request.setAttribute("totOptnSumAmp", totOptnSumAmp);
		model.addAttribute("totOptnSumAmp", totOptnSumAmp);
		
		 
		return "mywed/optionList";
	}	 
	
	
	
	@RequestMapping(value="/accessoryList", method= {RequestMethod.POST, RequestMethod.GET})
	public String accessoryList(Model model, HttpServletRequest request) {
		
		String tmpidWedding = request.getParameter("idWedding");
		long idWedding =  Long.parseLong(tmpidWedding); 
		
		// 선택사항 조회
		List<MyWedAccessory> Mywedacrylist = myWedRepo.mywedacrylist(idWedding);
		request.setAttribute("Mywedacrylist", Mywedacrylist);
		model.addAttribute("Mywedacrylist", Mywedacrylist);
		
		 
		return "mywed/accessoryList";
	}
	
	
	

	
	@RequestMapping(value="/categoryList", method= {RequestMethod.POST, RequestMethod.GET})
	public String categoryList(Model model, HttpServletRequest request) {
		
		
		String idUser = request.getParameter("email");


		
		String tmpidWedding = request.getParameter("idWedding");
		long idWedding =  Long.parseLong(tmpidWedding); 
		
		// 선택사항 조회
		List<MyWedCategory> popcategorylist = myWedRepo.MywedcategoryList(idWedding);
		
		
		System.out.println("schNmCompany ------xxxxx----- : " + popcategorylist);
		
		
		request.setAttribute("popcategorylist", popcategorylist);
		model.addAttribute("popcategorylist", popcategorylist);
		
		// 선택사항 합계액
		long totCtgySumAmp = myWedRepo.categorySum(idWedding);
		
		request.setAttribute("totCtgySumAmp", totCtgySumAmp);
		model.addAttribute("totCtgySumAmp", totCtgySumAmp);
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);
				
		 
		return "mywed/categoryList";
	}	 
	
		
	

	
	// 비용입력시 해당 my_wed_category 의 금액을 update 한다 : 이화면은 무조건 my_wed_category에 항목이 있어야 됨
	@RequestMapping(value="/cost", method= {RequestMethod.POST, RequestMethod.GET})
	public String cost(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		
		String idUser  = request.getParameter("email");
		
		String idWedding  = request.getParameter("idWedding");
		String cdCategory = request.getParameter("cdCategory");
		String cdCompany  = request.getParameter("cdCompany");
		String nmCompany  = request.getParameter("nmCompany");
		String amPrice    = request.getParameter("amPrice");
		
		String nextChk   = request.getParameter("NextChk");	// 등록 가능체크
		
		System.out.println("NextChk ------xxxxx----- : " + nextChk);		
		
		if (!nextChk.equals("I")) {
			
			// 첫화면 조회에 대해서는 불가능하게 단계처리 리턴처리
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
			
			request.setAttribute("idWedding", idWedding);
			model.addAttribute("idWedding", idWedding);
			
			request.setAttribute("cdCategory", cdCategory);
			model.addAttribute("cdCategory", cdCategory);
			
			request.setAttribute("cdCompany", cdCompany);
			model.addAttribute("cdCompany", cdCompany);	
			
			request.setAttribute("nmCompany", nmCompany);
			model.addAttribute("nmCompany", nmCompany);		
			
			request.setAttribute("amPrice", amPrice);
			model.addAttribute("amPrice", amPrice);		
			
			request.setAttribute("NextChk", "I");
			model.addAttribute("NextChk", "I");		
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
						
						
			return "mywed/cost";
			
		}
		
		// null 값이 올수 있음
		if (nmCompany == null) {
			nmCompany = "";
		}
		
		
		System.out.println("amPrice ------xxxxx----- : " + amPrice);
		
		// 금액 update 처리
		result = myWedRepo.updatemywedctgy(request);
				
		model.addAttribute("result", result);
		
		//if (result.getCode().equals("00")) {
		if (result.getCode().equals("00")) {
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);	
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
			
			System.out.println("idUser ------xxxxx----- : " + idUser);			
						
			return "mywed/costOk"; // 로그인창으로 리다이렉트
			
		}else {
			
			// 오류시 리턴처리
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
			
			request.setAttribute("idWedding", idWedding);
			model.addAttribute("idWedding", idWedding);
			
			request.setAttribute("cdCategory", cdCategory);
			model.addAttribute("cdCategory", cdCategory);
			
			request.setAttribute("cdCompany", cdCompany);
			model.addAttribute("cdCompany", cdCompany);	
			
			request.setAttribute("nmCompany", nmCompany);
			model.addAttribute("nmCompany", nmCompany);		
			
			request.setAttribute("amPrice", amPrice);
			model.addAttribute("amPrice", amPrice);	
			
			request.setAttribute("NextChk", "I");
			model.addAttribute("NextChk", "I");		
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
						
			
			return "mywed/cost";
		}

	}
	
		

	
	@RequestMapping(value="/weddingHall", method= {RequestMethod.POST} ) // , produces="application / json ; charset =UTF-8"  , RequestMethod.GET
	public String weddingHall(Model model, HttpServletRequest request) throws Exception {
		
		//Result result = new Result();
		//result.setCode("NA");
		
		String idUser = request.getParameter("email");
		
		String schNmWeddingHall = request.getParameter("sch1"); // schNmWeddingHall
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);			
		
		System.out.println("email ------xxxxx----- : " + idUser);
		System.out.println("sch ------xxxxx----- : " + schNmWeddingHall);		
		
		System.out.println("email ------xxxxx----- : " + idUser);
		System.out.println("schNmWeddingHall ------xxxxx----- : " + schNmWeddingHall);
		
		
		request.setAttribute("email", idUser);
		model.addAttribute("email", idUser);	
		
		request.setAttribute("schNmWeddingHall", schNmWeddingHall);
		model.addAttribute("schNmWeddingHall", schNmWeddingHall);
		
		request.setAttribute("sch", schNmWeddingHall);
		model.addAttribute("sch", schNmWeddingHall);

		request.setAttribute("sch1", schNmWeddingHall);
		model.addAttribute("sch1", schNmWeddingHall);
		
		
		
		if ((schNmWeddingHall == "") || (schNmWeddingHall == null)) {
						
			return "mywed/weddingHall";
		}
		
		// 선택사항 조회
		List<CdWeddingHall> rtnWeddingHallList = myWedRepo.SearchWeddingHallList(request); //
		

		request.setAttribute("rtnWeddingHallList", rtnWeddingHallList);
		model.addAttribute("rtnWeddingHallList", rtnWeddingHallList);

		request.setAttribute("schNmWeddingHall", schNmWeddingHall);
		model.addAttribute("schNmWeddingHall", schNmWeddingHall);		
		
		return "mywed/weddingHall";
		
	}
	 
	
	
	@RequestMapping(value="/SchweddHall", method= {RequestMethod.POST, RequestMethod.GET})
	public String SchweddHall(Model model, HttpServletRequest request) {
		
		
		String gogo = request.getParameter("gogo");
		
		String idUser = request.getParameter("email");
		String nmPartner = request.getParameter("nmPartner");
		String nmuser = request.getParameter("nmuser");
		String nmWeddingHall = request.getParameter("nmWeddingHall");
		String Weddate = request.getParameter("Weddate");
		String wedtime = request.getParameter("wedtime");

		
		
		System.out.println("idUser ------xxxxx----- : " + idUser);
		System.out.println("nmPartner ------xxxxx----- : " + nmPartner);
		System.out.println("nmuser ------xxxxx----- : " + nmuser);
		System.out.println("nmWeddingHall ------xxxxx----- : " + nmWeddingHall);
		System.out.println("Weddate ------xxxxx----- : " + Weddate);
		System.out.println("wedtime ------xxxxx----- : " + wedtime);
		System.out.println("gogo ------xxxxx----- : " + gogo);

		request.setAttribute("email", idUser);
		request.setAttribute("nmPartner1", nmPartner);
		request.setAttribute("nmuser1", nmuser);
		request.setAttribute("nmWeddingHall1", nmWeddingHall);
		request.setAttribute("Weddate1", Weddate);
		request.setAttribute("wedtime1", wedtime);
		
		model.addAttribute("email", idUser);
		model.addAttribute("nmPartner1", nmPartner);
		model.addAttribute("nmuser1", nmuser);
		model.addAttribute("nmWeddingHall1", nmWeddingHall);
		model.addAttribute("Weddate1", Weddate);
		model.addAttribute("wedtime1", wedtime);
		
		
		request.setAttribute("gogo", gogo);		
		model.addAttribute("gogo", gogo);
		
			
		return "mywed/weddingHallOk";
		
	}
	
	
	
	@ResponseBody 
	@RequestMapping(value= "/ajaxtest", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void AjaxView( HttpServletResponse response, Model model, HttpServletRequest request ) {
		
		response.setContentType("text/html;charset=UTF-8"); 
		// response.setHeader("Content-Type", "application/xml");
		// response.setContentType("text/xml;charset=UTF-8");
		// response.setCharacterEncoding("utf-8"); 
			
		String SchData = request.getParameter("schnmWeddingHall");

		System.out.println("schnmWeddingHall ------xxxxx----- : " + SchData);
		
		// return "/mywed/weddingHallOk1";
		
		if ((SchData != null) && !SchData.equals("")) {
			
			// 선택사항 조회
			List<CdWeddingHall> rtnWeddingHallList = myWedRepo.SearchWeddingHallList(request); //
			
			
			if(rtnWeddingHallList != null){
				
				
				System.out.println("S ------xxxxx----- : " + rtnWeddingHallList.size() );		
			} 
			
			try {
				
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(rtnWeddingHallList)); 
				
				System.out.println("OK ------xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				System.out.println("OK ------xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
	} 

	

	// 회사 검색으로 이동 처리 
	@RequestMapping(value="/Schcompany", method= {RequestMethod.POST, RequestMethod.GET})
	public String Schcompany(Model model, HttpServletRequest request) {
		
		
		String idUser  = request.getParameter("email");
		String idWedding  = request.getParameter("idWedding");
		String cdCategory = request.getParameter("cdCategory");
		String cdCompany = request.getParameter("cdCompany");

		
		request.setAttribute("email", idUser);
		request.setAttribute("idWedding", idWedding);
		request.setAttribute("cdCategory", cdCategory);
		request.setAttribute("cdCompany", cdCompany);
		
		model.addAttribute("email", idUser);
		model.addAttribute("idWedding", idWedding);
		model.addAttribute("cdCategory", cdCategory);
		model.addAttribute("cdCompany", cdCompany);
			
		return "mywed/company";
		
	}
	

	// 회사 검색처리
	@ResponseBody 
	@RequestMapping(value= "/ajaxCompany", method=RequestMethod.GET)  
	public void AjaxCompanyView( HttpServletResponse response, Model model, HttpServletRequest request ) {
		response.setContentType("text/html;charset=UTF-8"); 		
		
		// String schNmCompany = request.getParameter("nmCompany");

		String schNmCompany = request.getParameter("schNmCompany");

		
		System.out.println("schNmCompany ------xxxxx----- : " + schNmCompany);
		
		if ((schNmCompany != null) && !schNmCompany.equals("")) {
			
			// 선택사항 조회
			List<CdCompany> rtnSchNmCompanylist = myWedRepo.SearchCompanyList(request);
			request.setAttribute("rtnSchNmCompanylist", rtnSchNmCompanylist);
			model.addAttribute("rtnSchNmCompanylist", rtnSchNmCompanylist);			
			
			
			if(rtnSchNmCompanylist != null){
				
				
				System.out.println("S ---company---xxxxx----- : " + rtnSchNmCompanylist.size() );		
			} 
			
			try {
				
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(rtnSchNmCompanylist)); 
				
				System.out.println("OK ---company---xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				System.out.println("OK ---company---xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
	}
	


	@RequestMapping(value="/companyOk", method= {RequestMethod.POST, RequestMethod.GET})
	public String schcompany(Model model, HttpServletRequest request) {
		
		String schNmCompany = request.getParameter("schNmCompany");
		
		String idWedding  = request.getParameter("idWedding");
		String cdCategory = request.getParameter("cdCategory");
		String cdCompany = request.getParameter("cdCompany");
		
		
		System.out.println("RE schNmCompany ------xxxxx----- : " + schNmCompany);
		
		if ((schNmCompany != "") && (schNmCompany != null)){
		
			// 선택사항 조회
			List<CdCompany> rtnSchNmCompanylist = myWedRepo.SearchCompanyList(request);
			request.setAttribute("rtnSchNmCompanylist", rtnSchNmCompanylist);
			model.addAttribute("rtnSchNmCompanylist", rtnSchNmCompanylist);
		}
		
		request.setAttribute("schNmCompany", schNmCompany);
		model.addAttribute("schNmCompany", schNmCompany);
		
		request.setAttribute("idWedding", idWedding);
		model.addAttribute("idWedding", idWedding);
		
		request.setAttribute("cdCategory", cdCategory);
		model.addAttribute("cdCategory", cdCategory);	
		
		request.setAttribute("cdCompany", cdCompany);
		model.addAttribute("cdCompany", cdCompany);		
				
		
		return "mywed/company";
		
	}
	
}
