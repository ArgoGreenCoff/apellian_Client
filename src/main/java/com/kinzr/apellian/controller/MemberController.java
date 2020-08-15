package com.kinzr.apellian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinzr.apellian.entity.model.CdWeddingHall;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.repository.MyWeddingRepository;
import com.kinzr.apellian.repository.UsersRepository;
import com.kinzr.apellian.entity.model.Users;



import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/member") //회원가입 / 로그인 / 멤버관련
public class MemberController {

	@Autowired UsersRepository userRepo;
	@Autowired MyWeddingRepository myWedRepo;	

	
	// 화면 호출시 페이지 열기 
	@RequestMapping(value="/signin", method= {RequestMethod.POST, RequestMethod.GET})
	public String signin(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		request.setAttribute("email", idUser);
		request.setAttribute("passwd", passwd);
		
		
		System.out.println("start idupser : " +idUser);
		
		/*
		if (idUser!=null && passwd!=null) {
			result = userRepo.LoginCheck(request);
		}
		model.addAttribute("result", result);
		
		System.out.println("rtn passwd : " + result.getCode() );
		
		if (result.getCode().equals("00")) {
			// return "member/signinOk";

			request.setAttribute("email", idUser);
			request.setAttribute("passwd", passwd);
			
			model.addAttribute("email", idUser);
			model.addAttribute("passwd", passwd);

			// return "mywed/myWedding"; 	// ##999 다시 살릴것
			
			return "/mywed/myWedding";
			
			
		}else {
			
			request.setAttribute("email", idUser);
			request.setAttribute("passwd", passwd);
			
			return "member/signin";
			
		}
		
		*/
		
		return "member/signin";		
	}
	
	
	// 로그인 데이터 검증처리 
	@ResponseBody 	
	@RequestMapping(value= "/ajax_signin", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_signin( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
		
		response.setContentType("text/html;charset=UTF-8"); 
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("application/json");
		
 		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		System.out.println("--------------------------");
		System.out.println("idUser" + idUser );
		System.out.println("passwd : " + passwd );
		System.out.println("--------------------------");
		
		
		if (idUser!=null && passwd!=null) {
			
			System.out.println("start idupser : " +idUser);
			
			try {
	
	
				if (idUser!=null && passwd!=null) {
					
					result = userRepo.LoginCheck(request);
					
					System.out.println("result.getCode() : " +result.getCode());
					
				}
				
				// model.addAttribute("result", result);
				
				System.out.println("rtn passwd : " + result.getCode() );
				
				// request.setAttribute("email", idUser);
				// request.setAttribute("passwd", passwd);
					
				// model.addAttribute("email", idUser);
				// model.addAttribute("passwd", passwd);
				
				JSONObject jsonobj = new JSONObject();
				
	
				jsonobj.put("code", result.getCode());
				jsonobj.put("Description", URLDecoder.decode(result.getDescription(), "UTF-8"));
				 

				response.setContentType("application/json");
				PrintWriter out =response.getWriter();
				out.print(jsonobj.toString()); 
				
				// ObjectMapper mapper = new ObjectMapper();
				// response.getWriter().print(mapper.writeValueAsString(obj)); // request)); 
				
				System.out.println("OK ------login xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				System.out.println("OK ------login xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
		
	}
	
	
	
	@RequestMapping(value="/signinOk", method= {RequestMethod.POST})
	public String signinOk(Model model, HttpServletRequest request) {
		
		return "member/signinOk";
		
	}
	
	
	@RequestMapping(value="/signup", method= {RequestMethod.POST, RequestMethod.GET})
	public String signup(Model model, HttpServletRequest request) throws java.text.ParseException ,ParseException{
		
		Result result = new Result();
		result.setCode("NA");
		
		String idUser = request.getParameter("email");			// 이메일
		String passwd = request.getParameter("passwd");			// 비밀번호
		
		System.out.println("signup start idUser : " + idUser );
		System.out.println("signup start nocountry : " + request.getParameter("nocountry") );
		System.out.println("signup start notel : " + request.getParameter("notel") );
		
		String nmuser     = request.getParameter("nmuser");		// 이름
		String nocountry  = request.getParameter("nocountry");	// 국가번호(+82, etc)
		String notel      = request.getParameter("notel");		// 휴대번호
		String certinum   = request.getParameter("certinum");	// 인증번호
		String chkperinfo = request.getParameter("chkperinfo");	// 3자 개인정보 동의

		String idauthcd   = request.getParameter("idauthcd");	// 
		String cdauth     = request.getParameter("cdauth");	//

		String certyn = request.getParameter("certyn");	// 인증확인 
		String gocert = request.getParameter("gocert");	// 인증값
		
		
		System.out.println("signup  gocert check : " + gocert );

		if (idUser!=null && passwd!=null) {
			
			// 입력 값 체크
			result = userRepo.ValiCheck(request);
			
			
			request.setAttribute("email", idUser);
			request.setAttribute("passwd", passwd);
			
			request.setAttribute("nmuser", nmuser);
			request.setAttribute("nocountry", nocountry);
			request.setAttribute("notel", notel);
			request.setAttribute("certinum", certinum);
			request.setAttribute("chkperinfo", chkperinfo);
			
			request.setAttribute("gocert", gocert);
			request.setAttribute("certyn", certyn);

			request.setAttribute("idauthcd", idauthcd);
			request.setAttribute("cdauth", cdauth);		
			
			
			System.out.println("signup  gocert check : " + gocert );
			System.out.println("signup  certyn check : " + certyn );
			System.out.println("signup  idauthcd check : " + idauthcd );
			System.out.println("signup  cdauth check : " + cdauth );				
			
			model.addAttribute("result", result);
			
			
			if (!result.getCode().equals("00")){ 
				
				//result = userRepo.regSmsRandom(request);
				//model.addAttribute("result", result);
				
				request.setAttribute("email", idUser);
				request.setAttribute("passwd", passwd);
				
				request.setAttribute("nmuser", nmuser);
				request.setAttribute("nocountry", nocountry);
				request.setAttribute("notel", notel);
				request.setAttribute("certinum", certinum);
				request.setAttribute("chkperinfo", chkperinfo);
				
				request.setAttribute("gocert", gocert);
				request.setAttribute("certyn", certyn);

				request.setAttribute("idauthcd", idauthcd);
				request.setAttribute("cdauth", cdauth);		
				
				
				System.out.println("signup  gocert check : " + gocert );
				System.out.println("signup  certyn check : " + certyn );
				System.out.println("signup  idauthcd check : " + idauthcd );
				System.out.println("signup  cdauth check : " + cdauth );				
								
				model.addAttribute("result", result);
				return "member/signup";
				
			}
			else {
				
				if (gocert.equals("1")){
					
					result = userRepo.regSmsRandom(request);
					model.addAttribute("result", result);
					
					request.setAttribute("email", idUser);
					request.setAttribute("passwd", passwd);
					
					request.setAttribute("nmuser", nmuser);
					
					request.setAttribute("nocountry", nocountry);
					model.addAttribute("nocountry", nocountry);
					
					
					request.setAttribute("notel", notel);
					request.setAttribute("certinum", certinum);
					request.setAttribute("chkperinfo", chkperinfo);
					
					String rtnidauthcd = result.getCode();
					request.setAttribute("idauthcd", rtnidauthcd);
					
					request.setAttribute("cdauth", cdauth);	
					request.setAttribute("gocert", "2");
					
					request.setAttribute("certyn", "N");
					
					System.out.println("signup 1 authid check : " + idauthcd );
					
					
					
					return "member/signup";
				}
				else if (gocert.equals("2")){
									
					System.out.println("signup 2 authid check : " + idauthcd );
					
					result = userRepo.getAuthCdKey(request);
					model.addAttribute("result", result);
					
					request.setAttribute("email", idUser);
					request.setAttribute("passwd", passwd);
					
					request.setAttribute("nmuser", nmuser);
					request.setAttribute("nocountry", nocountry);
					request.setAttribute("notel", notel);
					request.setAttribute("certinum", certinum);
					request.setAttribute("chkperinfo", chkperinfo);			
					
					request.setAttribute("idauthcd", idauthcd);
					
					String rtncdauth = result.getCode();
					request.setAttribute("cdauth", rtncdauth);
					request.setAttribute("gocert", gocert);
					
					// ##999 테스트를 위해서 임시 처리
//					request.setAttribute("certinum", rtncdauth);
					
					
					
					// 인증서 입력값과 등록되어진 값 비교
					if (certinum.equals(rtncdauth)){
						// 동일
						
						request.setAttribute("gocert", "3");
						request.setAttribute("certyn", "Y");
						//request.setAttribute("certinum", rtncdauth);
						
						result.setCode("00");
						result.setDescription("인증이 완료되었습니다.");
					}
					else {
						
						request.setAttribute("certyn", "N");
						//request.setAttribute("certinum", certinum);
						
						result.setCode("C");
						result.setDescription("인증번호가 일치하지 않습니다.");
					}
					
					
					System.out.println("signup 2 cdauth check : " + cdauth );
					
					return "member/signup";
			
				}
				else if (gocert.equals("3")){
					
					// 정상 진행의 경우만 
					if (certyn.equals("Y")) {				
				
						result = userRepo.SignupCheck(request);
					
						model.addAttribute("result", result);
						
						String rtnCd = result.getCode(); 
						
						System.out.println("rtn rtnCd : " + rtnCd + " / " + result.getCode() );
						
						if (rtnCd.equals("00")) {
							
							saveWeddingData(request);
							
							
							return "member/signin"; // 로그인창으로 리다이렉트
							
						}else {
							
							request.setAttribute("email", idUser);
							request.setAttribute("passwd", passwd);
							
							request.setAttribute("nmuser", nmuser);
							request.setAttribute("nocountry", nocountry);
							request.setAttribute("notel", notel);
							request.setAttribute("certinum", certinum);
							request.setAttribute("chkperinfo", chkperinfo);
							
							request.setAttribute("gocert", gocert);
							request.setAttribute("certyn", certyn);
		
							request.setAttribute("idauthcd", idauthcd);
							request.setAttribute("cdauth", cdauth);	
							
							
							return "member/signup";
						}
					}
					else {
							
						result.setCode("C");
						result.setDescription("인증을 먼저 진행해 주세요.");
						return "member/signup";
					
					}
				}
				else {
					result.setCode("C");
					result.setDescription("인증을 먼저 진행해 주세요.");
					return "member/signup";
				}
			}
		}
		else {
			
			if (idUser==null) {
				result.setCode("E");
				result.setDescription("이메일을 입력하세요.");
			}
			else if (passwd==null) {
				result.setCode("W");
				result.setDescription("비밀번호를 입력하세요.");
			}
			
			return "member/signup";
		}
	}
	
	
	@RequestMapping(value="/signupOk", method= {RequestMethod.POST, RequestMethod.GET})
	public String signupOk(Model model, HttpServletRequest request) {
		
		return "member/signupOk"; // withdrawOk 
		
	} 
	
	public void saveWeddingData(HttpServletRequest request) throws java.text.ParseException, ParseException {
		
		String nmUser = request.getParameter("nmuser");
		String idUser = request.getParameter("email");
		String noTel = request.getParameter("notel");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sImF1dGhvcml0aWVzIjpbIlJPTEVfVU1PTUVOVF9BUFAiXSwianRpIjoiMDVkOGRlY2UtNTkyNS00NGMxLWFmNzctYzNjOTBiNTA0ODc0IiwiY2xpZW50X2lkIjoidW1vbWVudC13aGl0ZS1hcGktY2xpZW50In0.f64RHMNbmMwGDr1FmXgIYCbVdN9adO-OowO3XwywORw");
	    
	    // 사용자의 예약리스트 가져오기
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://book.umoment.co.kr/api/reserve/reg/list/contact";
	    UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
	            .queryParam("nm", nmUser)
	            .queryParam("noPhone", noTel)
	            .build(false);    //자동으로 encode해주는 것을 막기 위해 false
	    ResponseEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);

	    JSONParser jsonParser = new JSONParser(); 
	    org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(response.getBody().toString()); 
	    JSONArray reserveData = (JSONArray)jsonObject.get("data");
	    
	    if(reserveData.size() > 0) {
		    org.json.simple.JSONObject reserve = (org.json.simple.JSONObject)reserveData.get(0);

		    // 예약번호 가져오기
		    String cdReserve = (String) ((org.json.simple.JSONObject)reserveData.get(0)).get("cdReserve");
		    
		    // 예약번호로 웨딩정보 가져오기
			RestTemplate restTemplateDetail = new RestTemplate();
			String urlDetail = "http://book.umoment.co.kr/api/reserve/reg/"+cdReserve;
		    UriComponents builderDetail = UriComponentsBuilder.fromHttpUrl(urlDetail)
		            .queryParam("cdCompany", ((org.json.simple.JSONObject)reserveData.get(0)).get("cdCompany"))
		            .queryParam("cdBizarea", ((org.json.simple.JSONObject)reserveData.get(0)).get("cdBizarea"))
		            .build(false);    //자동으로 encode해주는 것을 막기 위해 false
		    ResponseEntity<?> responseDetail = restTemplateDetail.exchange(builderDetail.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);

		    JSONParser jsonParserDetail = new JSONParser(); 
		    org.json.simple.JSONObject jsonObjectDetail = (org.json.simple.JSONObject) jsonParserDetail.parse(responseDetail.getBody().toString()); 
		    org.json.simple.JSONObject reserveDetail = (org.json.simple.JSONObject)jsonObjectDetail.get("data");
		    org.json.simple.JSONObject contactReserve = (org.json.simple.JSONObject)reserveDetail.get("contactReserve");
		    org.json.simple.JSONObject womanInfo = (org.json.simple.JSONObject)reserveDetail.get("womanInfo");
		    org.json.simple.JSONObject manInfo = (org.json.simple.JSONObject)reserveDetail.get("manInfo");
		    
		    JSONArray fileList = (JSONArray)reserveDetail.get("contractFileList");
		    org.json.simple.JSONObject contractFile = (org.json.simple.JSONObject)fileList.get(0);
		    
		    request.setAttribute("idWeddingHall", (String)reserve.get("cdHall"));
		    request.setAttribute("nmWeddingHall", (String)reserve.get("nmHall"));
		    request.setAttribute("idRef", (String)reserve.get("cdReserve"));
		    request.setAttribute("idUser", (String)idUser);
		    request.setAttribute("nmPartner", (String)contactReserve.get("nmCust"));
		    request.setAttribute("nmMan", (String)manInfo.get("nmCust"));
		    request.setAttribute("nmWoman", (String)womanInfo.get("nmCust"));
		    request.setAttribute("dtWedding", (String)reserveDetail.get("dtWedding"));
		    request.setAttribute("tmWedding", (String)reserveDetail.get("tmWedding"));
		    request.setAttribute("urlContract", (String)contractFile.get("url"));
		    
		    myWedRepo.saveWeddingData(request);
		    
		    // 예약자와 신랑신부 이름 비교를 통해 성별 반영하기
		    String gender = "M";
		    if(contactReserve.get("nmCust").equals(womanInfo.get("nmCust"))) {
		    	gender = "W";
		    }
		    Users user = new Users();
		    user.setTyGender(gender);
		    user.setUsername(idUser);
		    userRepo.UpdateGender(user);
	    }

	}
	
	

	// 화면 호출시 페이지 열기 
	@RequestMapping(value="/findPassword", method= {RequestMethod.POST, RequestMethod.GET})
	public String findPassword(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");;
		
		request.setAttribute("email", idUser);;
		
		
		System.out.println("findPassword start idupser : " +idUser);
		//7월17일
		if(idUser != null && idUser != "") {
			
		}
		//7월17일 end
		
		return "member/findPassword";		
	}
	
	// 이메일 체크 및 인증번호 INS 및  테이블 키 GET 
	@ResponseBody 	
	@RequestMapping(value= "/ajax_findpwd", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_findpwd( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
	
		response.setContentType("text/html;charset=UTF-8"); 

		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		
		String idauthcd = "";

		System.out.println("-----------------------------");
		System.out.println("ajax_findpwd idUser" + idUser );
		System.out.println("-----------------------------");
		
		if (idUser!=null) {
			
			try {			
			
				JSONObject jsonobj = new JSONObject();
				
				result = userRepo.FindpasswordCheck(request);
			
				System.out.println("result.getCode(E) : " +result.getCode() + " / " + result.getDescription());
			
				// 사용자 체크가 정상일 경우 
				if (result.getCode().equals("00")) {
				
					// 사용자 정보 가져오기  
					Users user = userRepo.GetUserInfo(idUser);
					
					/* 현재 전화번호로 인증 처리하지 않음 추가 개발 해야 됨 */
					request.setAttribute("noCountry", user.getNoCountry() );
					request.setAttribute("notel", user.getNoTel() );
					request.setAttribute("result", result );
					model.addAttribute("result", result);
					
					model.addAttribute("noCountry", user.getNoCountry());
					model.addAttribute("notel",  user.getNoTel());
					model.addAttribute("result", result);
					
					System.out.println("regSmsRandom noCountry : " +  user.getNoCountry() );
					System.out.println("regSmsRandom notel : " +  user.getNoTel() );
					
					
					// 키 생성
					result = userRepo.GetAuthId(idUser, user.getNoTel(), user.getNoCountry() );
					
					String rtnCd = result.getCode(); 
					
					System.out.println("userRepo.GetAuthId rtnCd : " + rtnCd + " / " + result.getCode() );
					

					
					// 오류케이스 2개가 아니면 정상 
					if ( (!rtnCd.equals("T"))&&(!rtnCd.equals("C")) ) {
						
						// 오류가 아니면 인증 키 값임 
						
						request.setAttribute("idauthcd", idauthcd);
						model.addAttribute("result", result);
					}
					
					request.setAttribute("result", result);
					model.addAttribute("result", result);			
					
					request.setAttribute("email", idUser );
					model.addAttribute("idUser", idUser);
					
					// return "member/emailAuth";		// 정상일경우 다음처리
					
					// 값 전달
					jsonobj.put("code", result.getCode());
					jsonobj.put("Description", URLDecoder.decode(result.getDescription(), "UTF-8"));
					jsonobj.put("idauthcd", idauthcd);
					 

					response.setContentType("application/json");
					PrintWriter out =response.getWriter();
					out.print(jsonobj.toString()); 
					
					System.out.println("OK ------succ xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );
					
				}else {
					
					request.setAttribute("result", result);
					model.addAttribute("result", result);			
					
					request.setAttribute("email", idUser);
					model.addAttribute("idUser", idUser);
					
					// 값 전달
					jsonobj.put("code", result.getCode());
					jsonobj.put("Description", URLDecoder.decode(result.getDescription(), "UTF-8"));			
					jsonobj.put("idauthcd", idauthcd);
					
					response.setContentType("application/json");
					PrintWriter out =response.getWriter();
					out.print(jsonobj.toString()); 
					
					System.out.println("OK ------eror xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );
					
					// return "member/findPassword";	// 비정상이면 원위치 처리
				}

			} catch (IOException e) {
				
				System.out.println("OK ------login xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}			
	}
	
	
	// 화면 호출시 페이지 열기 
	@RequestMapping(value="/emailAuth", method= {RequestMethod.POST, RequestMethod.GET})
	public String emailAuth(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		String idauthcd = request.getParameter("idauthcd");
		
		request.setAttribute("email", idUser);
		request.setAttribute("idauthcd", idauthcd);
		
		model.addAttribute("idUser", idUser);		                                        
		model.addAttribute("idauthcd", idauthcd);		                                        
		
		
		System.out.println("start emailAuth idupser : " +idUser + " / " + idauthcd);
		
		return "member/emailAuth";		
	}
	
	


	// 이메일 체크 및 인증번호 INS 및  테이블 키 GET 
	@ResponseBody 	
	@RequestMapping(value= "/ajax_emailAuth", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_emailAuth( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {

		response.setContentType("text/html;charset=UTF-8"); 
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		String cd1 = request.getParameter("cd1");
		String cd2 = request.getParameter("cd2");
		String cd3 = request.getParameter("cd3");
		String cd4 = request.getParameter("cd4");

		String idauthcd = request.getParameter("idauthcd");

		String CertNum = cd1 + "" + cd2 + "" + cd3 + "" + cd4;

		request.setAttribute("idUser", idUser );
		request.setAttribute("cd1", cd1 );
		request.setAttribute("cd2", cd2 );
		request.setAttribute("cd3", cd3 );
		request.setAttribute("cd4", cd4 );
		request.setAttribute("idauthcd", idauthcd );
		
		model.addAttribute("result", result);
		
		
		System.out.println("-----------------------------");
		System.out.println("ajax_emailAuth idUser" + idUser + " / certNum = " +CertNum );
		System.out.println("-----------------------------");
		

		if (idUser!=null) {
			
			try {			
			
				JSONObject jsonobj = new JSONObject();

				// 기본 입력값 확인
				if (CertNum.length() < 4) {
					
					result.setCode("E");
					result.setDescription("인증 코드를 확인 해주세요");
					
					request.setAttribute("result", result);
					model.addAttribute("result", result);			
					
				}
				else {
				
				
					// 인증 키 체크
					result = userRepo.EmailauthCheck(request);
	
					request.setAttribute("email", idUser );
					request.setAttribute("cd1", cd1 );
					request.setAttribute("cd2", cd2 );
					request.setAttribute("cd3", cd3 );
					request.setAttribute("cd4", cd4 );
					request.setAttribute("idauthcd", idauthcd );
					
					request.setAttribute("result", result);
					model.addAttribute("result", result);			
	
					System.out.println("OK chck : " + result.getCode());
					
					if (result.getCode().equals("00")) {
						
						System.out.println("OK??? ");
						
						request.setAttribute("email", idUser );
						
						request.setAttribute("result", result);
						model.addAttribute("result", result);
						
						System.out.println("OK ------succ xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );
	
						
					}else {
						
						System.out.println("OK??? NO??? !! ");
						System.out.println("OK ------error xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );

						
						request.setAttribute("result", result);
						model.addAttribute("result", result);			
						
					}
				}
				
				// 정상이든 비정상이든 값 전달 ( E:오류, 그외 정상 )
				// 값 전달
				jsonobj.put("code", result.getCode());
				jsonobj.put("Description", URLDecoder.decode(result.getDescription(), "UTF-8"));
				jsonobj.put("idauthcd", idauthcd);
				 

				response.setContentType("application/json");
				PrintWriter out =response.getWriter();
				out.print(jsonobj.toString()); 
				
				System.out.println("OK ------succ xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );
				
			
			} catch (IOException e) {
				
				System.out.println("OK ------login xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
	}
	
	
	
	// 화면 전환처리 ( 비밀번호 변경을 위한 이동 ) 
	@RequestMapping(value="/newPassword", method= {RequestMethod.POST, RequestMethod.GET})
	public String newPassword(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");

		String idUser = request.getParameter("email");
		
		request.setAttribute("email", idUser );
		
			
		return "member/newPassword";	// 비정상이면 원위치 처리
	}
	
	
	// 신규비밀번호 저장을 위한 곳 
	@ResponseBody 	
	@RequestMapping(value= "/ajax_newpwd", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_newpwd( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
	
		response.setContentType("text/html;charset=UTF-8"); 

		Result result = new Result();
		result.setCode("NA");

		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		request.setAttribute("email", idUser );
		request.setAttribute("passwd", passwd );
		
		request.setAttribute("result", result);
		model.addAttribute("result", result);
		

		System.out.println("-----------------------------");
		System.out.println("ajax_newpwd idUser" + idUser );
		System.out.println("-----------------------------");
		
		
		if (passwd!=null) {
			
			try {			
			
				JSONObject jsonobj = new JSONObject();

				
				// 비밀번호 체크
				result = userRepo.NewpasswordCheck(request);
			
			
				request.setAttribute("result", result);
				model.addAttribute("result", result);
				
				if (result.getCode().equals("00")) {
					
					request.setAttribute("email", idUser);
					request.setAttribute("passwd", "" );
					
					System.out.println("pass chg OK!! ");
					
					request.setAttribute("result", result);
					model.addAttribute("result", result);
					
					
				}else {

					// 오류 메세지 처리 되는 경우
					System.out.println("pass chg NOK!! ");
					
					
					request.setAttribute("email", idUser );
					request.setAttribute("passwd", passwd );
								
					request.setAttribute("result", result);
					model.addAttribute("result", result);
					
				}
				
			
				// 정상이든 비정상이든 값 전달 ( E:오류, 그외 정상 )
				// 값 전달
				jsonobj.put("code", result.getCode());
				jsonobj.put("Description", URLDecoder.decode(result.getDescription(), "UTF-8"));
	
				response.setContentType("application/json");
				PrintWriter out =response.getWriter();
				out.print(jsonobj.toString()); 
				
				System.out.println("OK ------succ xxxxx----- : SEND OK " + result.getCode() + " / " + result.getDescription() );
				
			
			} catch (IOException e) {
				
				System.out.println("OK ------login xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
		else {
			
		}
	}


	
	// 화면 전환처리 ( 비밀번호 변경을 위한 이동 ) 
	@RequestMapping(value="/newPasswordOk", method= {RequestMethod.POST, RequestMethod.GET})
	public String newPasswordOk(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");

		String idUser = request.getParameter("email");
		
		request.setAttribute("email", idUser );
		
			
		return "member/newPasswordOk";	// 비정상이면 원위치 처리
	}
	
	
	@RequestMapping(value="/changePassword", method= {RequestMethod.POST, RequestMethod.GET})
	public String changePassword(Model model, HttpServletRequest request) {
		
		
		Result result = new Result();
		result.setCode("NA");
		String idUser = request.getParameter("email");
		String oldpasswd = request.getParameter("oldpasswd");
		String newpasswd = request.getParameter("newpasswd");
		
		if ((oldpasswd!=null)&&(newpasswd!=null)) {
			result = userRepo.ChangepasswordCheck(request);
		}		
		
		request.setAttribute("result", result);
		model.addAttribute("result", result);
		
		if (result.getCode().equals("00")) {
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
			
			return "member/changePasswordOk";		// 정상일경우 다음처리
		}else {
			
			request.setAttribute("email", idUser);
			request.setAttribute("oldpasswd", oldpasswd);
			request.setAttribute("newpasswd", newpasswd);

			request.setAttribute("result", result);
			model.addAttribute("result", result);
			
			return "member/changePassword";	// 비정상이면 원위치 처리
		}
		
	}
	
	


	@RequestMapping(value="/withdraw", method= {RequestMethod.POST, RequestMethod.GET})
	public String withdraw(Model model, HttpServletRequest request) {
		
		Result result = new Result();
		result.setCode("NA");
		
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		String ynWithdrawReason1 = request.getParameter("ynWithdrawReason1");
		String ynWithdrawReason2 = request.getParameter("ynWithdrawReason2");
		String ynWithdrawReason3 = request.getParameter("ynWithdrawReason3");
		String ynWithdrawReason4 = request.getParameter("ynWithdrawReason4");
		String ynWithdrawReason5 = request.getParameter("ynWithdrawReason5");
		String dswithdraw		 = request.getParameter("dswithdraw");
		
		System.out.println("withdraw start email  : " + idUser );
		System.out.println("withdraw start passwd  : " + passwd );				
		
		System.out.println("withdraw start ynWithdrawReason5 : " + ynWithdrawReason5 );
		System.out.println("withdraw start dswithdraw  : " + dswithdraw );
		
		
		if (idUser!=null && passwd!=null) {
			result = userRepo.WithdrawCheck(request);
		}
		
		model.addAttribute("result", result);
		if (result.getCode().equals("00")) {
			
			request.setAttribute("email", idUser);
			model.addAttribute("email", idUser);
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
						
			return "member/withdrawOk"; // 로그인창으로 리다이렉트
		}else {
			
			// 오류시 리턴처리
			request.setAttribute("email", idUser);
			request.setAttribute("passwd", passwd);
			request.setAttribute("ynWithdrawReason1", ynWithdrawReason1);
			request.setAttribute("ynWithdrawReason2", ynWithdrawReason2);
			request.setAttribute("ynWithdrawReason3", ynWithdrawReason3);
			request.setAttribute("ynWithdrawReason4", ynWithdrawReason4);
			request.setAttribute("ynWithdrawReason5", ynWithdrawReason5);
			request.setAttribute("dswithdraw", dswithdraw);
			
			System.out.println("withdraw  email  : " + idUser );
			System.out.println("withdraw  passwd  : " + passwd );				
			
			System.out.println("withdraw  ynWithdrawReason1 : " + ynWithdrawReason1 );
			System.out.println("withdraw  dswithdraw  : " + dswithdraw );
			
			request.setAttribute("result", result);
			model.addAttribute("result", result);
			
			return "member/withdraw";
		}
		
		//thymeleaf 사용시
		//return "thymeleaf/withdraw";
	}
	
	@RequestMapping(value="/withdrawOk", method= {RequestMethod.POST})
	public String withdrawOk(Model model, HttpServletRequest request) {
		
		return "member/withdrawOk"; // withdrawOk 
		
	} 
	
	
	
	// @RequestMapping(value="/policy", method= {RequestMethod.POST})
	@RequestMapping(value="/policy", method= {RequestMethod.POST, RequestMethod.GET})	
	public String policy(Model model, HttpServletRequest request) {
		
		return "member/policy";
		
	} 
	
	@RequestMapping(value="/agreement01", method= {RequestMethod.POST, RequestMethod.GET})	
	public String agreement01(Model model, HttpServletRequest request) {
		
		return "member/agreement01";
		
	}
	
	@RequestMapping(value="/agreement02", method= {RequestMethod.POST, RequestMethod.GET})	
	public String agreement02(Model model, HttpServletRequest request) {
		
		return "member/agreement02";
		
	} 
	
	@RequestMapping(value="/agreement03", method= {RequestMethod.POST, RequestMethod.GET})	
	public String agreement03(Model model, HttpServletRequest request) {
		
		return "member/agreement03";
		
	} 	

	@RequestMapping(value="/agreement04", method= {RequestMethod.POST, RequestMethod.GET})	
	public String agreement04(Model model, HttpServletRequest request) {
		
		return "member/agreement04";
		
	}
	
	
	@RequestMapping(value="/account", method= {RequestMethod.POST, RequestMethod.GET})	
	public String account(Model model, HttpServletRequest request) {

		String idUser = request.getParameter("email");
		
		request.setAttribute("email", idUser);
		
		return "member/account";
		
	}
		
	
}
