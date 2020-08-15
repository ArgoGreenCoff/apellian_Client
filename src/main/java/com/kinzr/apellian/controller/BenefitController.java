package com.kinzr.apellian.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.BnfClass;
import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.BnfEvent;
import com.kinzr.apellian.entity.model.BnfEventApplication;
import com.kinzr.apellian.entity.model.BnfEventBnfImages;
import com.kinzr.apellian.entity.model.BnfEventImages;
import com.kinzr.apellian.entity.model.BnfSurvey;
import com.kinzr.apellian.entity.model.MyWed;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.repository.BenefitClassRepository;
import com.kinzr.apellian.repository.BenefitEventRepository;
import com.kinzr.apellian.repository.BenefitSurveyRepository;
import com.kinzr.apellian.repository.MyWeddingRepository;
import com.kinzr.apellian.repository.UsersRepository;

import lombok.extern.java.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Log
@RequestMapping("/benefit")	//혜택(클래스,설문,이벤트) 관련
public class BenefitController {

	@Autowired BenefitClassRepository bnfRepo;	
	@Autowired BenefitEventRepository eventRepo;
	@Autowired UsersRepository userRepo;
	@Autowired MyWeddingRepository myWedRepo;	
	@Autowired BenefitSurveyRepository surveyRepo;	

	// 클래스 리스트
	@RequestMapping(value="/classList", method= {RequestMethod.POST, RequestMethod.GET})
	public String classList(Model model, HttpServletRequest request) {
		
		List<BnfClass> list = bnfRepo.classList();
		model.addAttribute("list", list);

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		ArrayList<List<BnfClassImages>> listImg = new ArrayList<List<BnfClassImages>>();
		list.forEach(item->{
			Integer idClass = item.getIdBnfClass();
			listImg.add(bnfRepo.classImages(idClass));
		});
		model.addAttribute("listImg", listImg);

		
		
		return "benefit/classList";
	}
	
	// 클래스 상세
	@RequestMapping(value="/classDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String classDetail(Model model, HttpServletRequest request) throws ParseException {
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		BnfClass classInfo = bnfRepo.classDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("classInfo", classInfo);
		
		// 지도 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-NCP-APIGW-API-KEY-ID", "3ddnz0afa0");
		headers.add("X-NCP-APIGW-API-KEY", "SZKV3SNgSb9bNGNg1RwewNTLb55Bpql5v8o6lrZi");

		RestTemplate template = new RestTemplate();
		String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+classInfo.getDsAddr();

		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        
		model.addAttribute("x", "0");
		model.addAttribute("y", "0");
        if(response.getStatusCodeValue() == 200) {
        	
        	JSONParser parser = new JSONParser(response.getBody());
        	LinkedHashMap<String, Object> obj = (LinkedHashMap<String, Object>)parser.parse();
        	ArrayList<LinkedHashMap> addr = (ArrayList<LinkedHashMap>)obj.get("addresses");
        	
        	if(!addr.isEmpty()) {
            	LinkedHashMap<String, Object> addrDetail = (LinkedHashMap<String, Object>) addr.get(0);
            	
        		model.addAttribute("x", addrDetail.get("x"));
        		model.addAttribute("y", addrDetail.get("y"));
        	}
        } 
		// 지도
		
		int classApplyCnt = bnfRepo.classApplyCnt(request);	// 신청인원수 체크
		model.addAttribute("classApplyCnt", classApplyCnt);

		String idClass = request.getParameter("idClass");	// 클래스 이미지
		List<BnfClassImages> listImg = bnfRepo.classImages(Integer.parseInt(idClass));
		model.addAttribute("listImg", listImg);
		
		List<BnfClassBnfImages> listBnfImg = bnfRepo.classBnfImages(Integer.parseInt(idClass));
		model.addAttribute("listBnfImg", listBnfImg);

		// applyState :0신청하기 :1예비부부만 :2신혼부부만 3:일반고객 4:모집종료 5:당첨자발표확인
		model.addAttribute("applyState", "0");
		// 처음 신청화면일때 해당 클래스 상태에 따라 신청버튼 처리하기
		Result applyCheck= bnfRepo.classApplyCheck(request);	// 신청 체크
		
		if(applyCheck.getCode() == "1") {	// 신청 O
			model.addAttribute("applyState", "5");
		} else {	// 신청 X
			Date curDate = new Date();
			int compare = curDate.compareTo(classInfo.getDtApplyTo());
			if(compare < 0) {	// 모집중 O
				
				// 조건에 따른 버튼표시??? 일단 신청버튼 표시
				model.addAttribute("applyState", "0");

			} else {	// 모집중 X
				model.addAttribute("applyState", "4");
			}
		}
		
		return "benefit/classDetail";
	}

	// 클래스 신청하기
	@RequestMapping(value="/classApply", method= {RequestMethod.POST, RequestMethod.GET})
	public String classApply(Model model, HttpServletRequest request) {
		
		BnfClassApplication bnfApply = new BnfClassApplication();
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		String idClass = request.getParameter("idClass");	// 클래스 id
		model.addAttribute("idClass", idClass);
		
		BnfClass classInfo = bnfRepo.classDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("classInfo", classInfo);
		
		// result :0 (신청화면) :1(성공) :-1(실패) :-2(2회이상신청한경우)
		model.addAttribute("result", "0");	// 처음 신청화면
		

		String reason = request.getParameter("reason");			// 신청사유
		String together[] = request.getParameterValues("radio01");			// 누구와 함께
		String brand = request.getParameter("brand");			// 브랜드 관련
		String name = request.getParameter("name");			// 이름
		String phone = request.getParameter("phone");			// 전화번호

		// 신청한경우
		if(reason != null && reason != "") {
			bnfApply.setIdBnfClass(Integer.parseInt(idClass));
			bnfApply.setIdUser(email);
			bnfApply.setDsApply(reason);
			bnfApply.setDsPartner(together[0]);
			bnfApply.setDsBrand(brand);
			bnfApply.setNmApply(name);
			bnfApply.setTelApply(phone);
			bnfApply.setCdWin("N");
			bnfApply.setDtReg(new Date());
			
			// 신청한 클래스가 2회 미만인지 확인 후 2회 미만일경우 신청
			int userApplyCnt = bnfRepo.userApplyCnt(request);
			if(userApplyCnt < 2) {
				
				// DB에 신청정보 입력	
				Result ret = bnfRepo.classApplyRequest(bnfApply);
				if(ret.getCode().equals("1")) {
					model.addAttribute("result", "1");
				} else {
					model.addAttribute("result", "-1");
				}

			} else {
				model.addAttribute("result", "-2");	// 2회이상신청한경우
			}
			
		}


		return "benefit/classApply";
	}

	
	// 설문조사
	@RequestMapping(value="/survey", method= {RequestMethod.POST, RequestMethod.GET})
	public String survey(Model model, HttpServletRequest request) {

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		List<BnfSurvey> list = surveyRepo.surveyList();
		model.addAttribute("list", list);

		return "benefit/survey";
	}
	
	
	

	// 이벤트 리스트
	@RequestMapping(value="/eventList", method= {RequestMethod.POST, RequestMethod.GET})
	public String eventList(Model model, HttpServletRequest request) {
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		List<BnfEvent> list = eventRepo.eventList();
		model.addAttribute("list", list);

		ArrayList<List<BnfEventImages>> listImg = new ArrayList<List<BnfEventImages>>();
		list.forEach(item->{
			Integer idEvent = item.getIdBnfEvent();
			listImg.add(eventRepo.eventImages(idEvent));
		});
		model.addAttribute("listImg", listImg);

		
		// 사용자 웨딩 정보 조회
		MyWed myd = myWedRepo.MyweddingList(email);
		
		if(myd == null || myd.getDtWedding().compareTo(new Date()) > 0 ) {	// 예식날짜 전 
			model.addAttribute("myd", null);
			model.addAttribute("address1", "");
			model.addAttribute("address100", "");
		} 
		else {
			model.addAttribute("myd", myd);
			
			Users user = userRepo.GetUserInfo(email);
			String deliveryAddres1 = user.getDsAddrDelivery1();
			if(deliveryAddres1 == null || deliveryAddres1 == "") {
				model.addAttribute("address1", "");
			} else {
				model.addAttribute("address1", deliveryAddres1);
			}
			String deliveryAddres100 = user.getDsAddrDelivery100();
			if(deliveryAddres100 == null || deliveryAddres100 == "") {
				model.addAttribute("address100", "");
			} else {
				model.addAttribute("address100", deliveryAddres100);
			}
		}
		
		
		return "benefit/eventList";
	}

	
	// 이벤트 상세
	@RequestMapping(value="/eventDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String eventDetail(Model model, HttpServletRequest request) throws ParseException {
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		BnfEvent eventInfo = eventRepo.eventDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("eventInfo", eventInfo);

		// 지도 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-NCP-APIGW-API-KEY-ID", "3ddnz0afa0");
		headers.add("X-NCP-APIGW-API-KEY", "SZKV3SNgSb9bNGNg1RwewNTLb55Bpql5v8o6lrZi");

		RestTemplate template = new RestTemplate();
		String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+eventInfo.getDsAddr();

		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        
        
		model.addAttribute("x", "0");
		model.addAttribute("y", "0");
        if(response.getStatusCodeValue() == 200) {
        	
        	JSONParser parser = new JSONParser(response.getBody());
        	LinkedHashMap<String, Object> obj = (LinkedHashMap<String, Object>)parser.parse();
        	ArrayList<LinkedHashMap> addr = (ArrayList<LinkedHashMap>)obj.get("addresses");
        	
        	if(!addr.isEmpty()) {
            	LinkedHashMap<String, Object> addrDetail = (LinkedHashMap<String, Object>) addr.get(0);
            	
        		model.addAttribute("x", addrDetail.get("x"));
        		model.addAttribute("y", addrDetail.get("y"));
        	}
        } 

        // 지도

		int eventApplyCnt = eventRepo.eventApplyCnt(request);	// 신청인원수 체크
		model.addAttribute("eventApplyCnt", eventApplyCnt);

		String idBnfEvent = request.getParameter("idBnfEvent");	// 이벤트 이미지
		List<BnfEventImages> listImg = eventRepo.eventImages(Integer.parseInt(idBnfEvent));
		model.addAttribute("listImg", listImg);
		
		List<BnfEventBnfImages> listBnfImg = eventRepo.eventBnfImages(Integer.parseInt(idBnfEvent));
		model.addAttribute("listBnfImg", listBnfImg);

		
		
		// applyState :0신청하기 :1예비부부만 :2신혼부부만 3:일반고객 4:모집종료 5:당첨자발표확인
		model.addAttribute("applyState", "0");
		// 처음 신청화면일때 해당 클래스 상태에 따라 신청버튼 처리하기
		Result applyCheck= eventRepo.eventApplyCheck(request);	// 신청 체크
		
		if(applyCheck.getCode() == "1") {	// 신청 O
			model.addAttribute("applyState", "5");
		} else {	// 신청 X
			Date curDate = new Date();
			int compare = curDate.compareTo(eventInfo.getDtApplyTo());
			if(compare < 0) {	// 모집중 O
				
				// 조건에 따른 버튼표시??? 일단 신청버튼 표시
				model.addAttribute("applyState", "0");

			} else {	// 모집중 X
				model.addAttribute("applyState", "4");
			}
		}

		
		return "benefit/eventDetail";
	}

	// 이벤트 신청하기
	@RequestMapping(value="/eventApply", method= {RequestMethod.POST, RequestMethod.GET})
	public String eventApply(Model model, HttpServletRequest request) {
		
		BnfEventApplication bnfApply = new BnfEventApplication();
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		String idBnfEvent = request.getParameter("idBnfEvent");	// 이벤트 id
		model.addAttribute("idBnfEvent", idBnfEvent);
		
		BnfEvent eventInfo = eventRepo.eventDetail(request);	// 해당 이벤트 정보 가져오기
		model.addAttribute("eventInfo", eventInfo);

		// result :0 (신청화면) :1(성공) :-1(실패)
		model.addAttribute("result", "0");	// 처음 신청화면
		
		String reason = request.getParameter("reason");			// 신청사유
		String together[] = request.getParameterValues("radio01");			// 누구와 함께
		String brand = request.getParameter("brand");			// 브랜드 관련
		String name = request.getParameter("name");			// 이름
		String phone = request.getParameter("phone");			// 전화번호

		// 신청한경우
		if(reason != null && reason != "") {
			bnfApply.setIdBnfEvent(Integer.parseInt(idBnfEvent));
			bnfApply.setIdUser(email);
			bnfApply.setDsApply(reason);
			bnfApply.setDsPartner(together[0]);
			bnfApply.setDsBrand(brand);
			bnfApply.setNmApply(name);
			bnfApply.setTelApply(phone);
			bnfApply.setCdWin("N");
			bnfApply.setDtReg(new Date());
			
			// DB에 신청정보 입력	
			Result ret = eventRepo.eventApplyRequest(bnfApply);
			if(ret.getCode().equals("1")) {
				model.addAttribute("result", "1");
			} else {
				model.addAttribute("result", "-1");
			}

		}


		return "benefit/eventApply";
	}

	// 이벤트 배송지 주소 입력
	@RequestMapping(value="/eventAddress1", method= {RequestMethod.POST, RequestMethod.GET})
	public String eventAddress1(Model model, HttpServletRequest request) {
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		// result :0 (신청화면) :1(성공) :-1(실패)
		model.addAttribute("result", "0");	// 처음 신청화면
	
		String name = request.getParameter("name");		
		if(name != null && name != "") {
			Result ret = userRepo.SetDelivery1(request);
			if(ret.getCode().equals("00")) {
				model.addAttribute("result", "1");
			} else {
				model.addAttribute("result", "-1");
			}
			
			model.addAttribute("code", ret.getCode());
		}
		model.addAttribute("type", "eventAddress1");

		return "benefit/eventAddress";
	}

	// 이벤트 배송지 주소 입력
	@RequestMapping(value="/eventAddress100", method= {RequestMethod.POST, RequestMethod.GET})
	public String eventAddress100(Model model, HttpServletRequest request) {
		
		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		// result :0 (신청화면) :1(성공) :-1(실패)
		model.addAttribute("result", "0");	// 처음 신청화면
	
		String name = request.getParameter("name");		
		if(name != null && name != "") {
			Result ret = userRepo.SetDelivery100(request);
			if(ret.getCode().equals("00")) {
				model.addAttribute("result", "1");
			} else {
				model.addAttribute("result", "-1");
			}
			
			model.addAttribute("code", ret.getCode());
		}
		
		model.addAttribute("type", "eventAddress100");

		return "benefit/eventAddress";
	}

	
	
	@RequestMapping(value="/winner", method= {RequestMethod.POST, RequestMethod.GET})
	public String winner(Model model, HttpServletRequest request) {
		
		BnfClassApplication bnfApply = new BnfClassApplication();

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);
		bnfApply.setIdUser(email);
		
		String idClass = request.getParameter("idClass");
		bnfApply.setIdBnfClass(Integer.parseInt(idClass));

		BnfClassApplication applyInfo = bnfRepo.classApplyInfo(bnfApply);	
		model.addAttribute("applyInfo", applyInfo);
		
		BnfClass classInfo = bnfRepo.classDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("classInfo", classInfo);
		
		int classApplyCnt = bnfRepo.classApplyCnt(request);	// 신청인원수 체크
		model.addAttribute("applyCnt", classApplyCnt);

		
		return "benefit/winner";	 
	}
	
	@RequestMapping(value="/winnerEvent", method= {RequestMethod.POST, RequestMethod.GET})
	public String winnerEvent(Model model, HttpServletRequest request) {
		
		BnfEventApplication bnfApply = new BnfEventApplication();

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);
		bnfApply.setIdUser(email);
		
		String idEvent = request.getParameter("idBnfEvent");
		bnfApply.setIdBnfEvent(Integer.parseInt(idEvent));

		
		BnfEventApplication applyInfo = eventRepo.eventApplyInfo(bnfApply);	
		model.addAttribute("applyInfo", applyInfo);
		
		BnfEvent eventInfo = eventRepo.eventDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("classInfo", eventInfo);
		
		int eventApplyCnt = eventRepo.eventApplyCnt(request);	// 신청인원수 체크
		model.addAttribute("applyCnt", eventApplyCnt);

		
		return "benefit/winner";	 
	}
	
	@RequestMapping(value="/payment", method= {RequestMethod.POST, RequestMethod.GET})
	public String payment(Model model, HttpServletRequest request) {
		
		BnfClassApplication bnfApply = new BnfClassApplication();

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		String idClass = request.getParameter("idClass");
		bnfApply.setIdBnfClass(Integer.parseInt(idClass));

		BnfClassApplication applyInfo = bnfRepo.classApplyInfo(bnfApply);	
		model.addAttribute("applyInfo", applyInfo);
		
		BnfClass classInfo = bnfRepo.classDetail(request);	// 해당 클래스 정보 가져오기
		model.addAttribute("classInfo", classInfo);
		
		String strPrice = String.format("%,d", Integer.parseInt(classInfo.getAmPrice()));
		model.addAttribute("price", strPrice);

		
		return "benefit/payment";	 
	}
	
	

}
