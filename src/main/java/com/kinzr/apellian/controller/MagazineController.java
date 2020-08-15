package com.kinzr.apellian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.Magazine;
import com.kinzr.apellian.entity.model.MagazineSavedList;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.repository.MagazineRepository;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/magazine")	 //매거진 관련
public class MagazineController {

	@Autowired MagazineRepository magRepo;
	
	// 매거진 리스트
	@RequestMapping(value="/magList", method= {RequestMethod.POST, RequestMethod.GET})
	public String magList(Model model, HttpServletRequest request) {

		String category = request.getParameter("category");	// 
		if(category == null || category == "") {
			category = "1";
		}
		model.addAttribute("category", category);

		
		List<Magazine> magList = magRepo.magList(category);
		model.addAttribute("magList", magList);
		model.addAttribute("email", request.getParameter("email"));
		
		return "magazine/magList";
	}
	
	// 매거진 상세
	@RequestMapping(value="/magDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String magDetail(Model model, HttpServletRequest request) {

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		String idMagazine = request.getParameter("idMagazine");
		model.addAttribute("idMagazine", idMagazine);

		// 매거진 상세 정보가져오기
		Magazine magInfo= magRepo.magDetail(request);
		model.addAttribute("magInfo", magInfo);
		
		// 이전글 가져오기
		Magazine preMagazine = magRepo.magPreInfo(request);
		model.addAttribute("preMagazine", preMagazine);

		// 다음글 가져오기
		Magazine nextMagazine = magRepo.magNextInfo(request);		
		model.addAttribute("nextMagazine", nextMagazine);

		// 현재글이 북마크 되어있는지 확인
		Boolean isBookmark = magRepo.bookmarkCheck(email, idMagazine);
		model.addAttribute("isBookmark", isBookmark);

		
		return "magazine/magDetail";
	}
	
	
	
	
	// 매거진 검색
	@RequestMapping(value="/magSearch", method= {RequestMethod.POST, RequestMethod.GET})
	public String magSearch(Model model, HttpServletRequest request) {

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);
		
		return "magazine/magSearch";
	}

	
	//  
	@ResponseBody 	
	@RequestMapping(value= "/ajax_search", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_search( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
		
		response.setContentType("text/html;charset=UTF-8"); 

		JSONObject jsonobj = new JSONObject();
		
		String search = request.getParameter("search");
		System.out.println("--------------------------");
		System.out.println("search : " + search );
		System.out.println("--------------------------");
		
		
		if (search!=null) {
			
			try {
	
				List<Magazine> listSearch = new ArrayList<Magazine>();
				
				if (search!=null) {
					
					listSearch = magRepo.magSearch(search);
					
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().print(mapper.writeValueAsString(listSearch)); 
				}
				
				System.out.println("OK ------xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				
				System.out.println("OK ------xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
		
	}
	
	
	// 매거진 북마크
	@RequestMapping(value="/savedList", method= {RequestMethod.POST, RequestMethod.GET})
	public String savedList(Model model, HttpServletRequest request) {

		String email = request.getParameter("email");	// 유저 id
		model.addAttribute("email", email);

		
		List<Magazine> savedList = new ArrayList<Magazine>();
		savedList = magRepo.magSavedList(email);
		model.addAttribute("savedList", savedList);

		return "magazine/savedList";
	}

	@ResponseBody 	
	@RequestMapping(value= "/ajax_delete", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_delete( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
		
		response.setContentType("text/html;charset=UTF-8"); 

		String idMagazine = request.getParameter("idMagazine");
		String idUser = request.getParameter("email");
		
		System.out.println("--------------------------");
		System.out.println("idMagazine : " + idMagazine );
		System.out.println("--------------------------");
		
		if (idMagazine != null && idUser != null) {
			
			try {
				
				int ret = magRepo.bookmarkDelete(idUser, idMagazine);
				
				response.getWriter().print(ret); 
				
				System.out.println("OK ------xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				
				System.out.println("OK ------xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
		
	}

	@ResponseBody 	
	@RequestMapping(value= "/ajax_add", method=RequestMethod.GET, produces = "application/text; charset=utf8")  
	public void ajax_add( HttpServletResponse response, Model model, HttpServletRequest request ) throws JSONException {
		
		response.setContentType("text/html;charset=UTF-8"); 

		String idMagazine = request.getParameter("idMagazine");
		String idUser = request.getParameter("email");
		
		System.out.println("--------------------------");
		System.out.println("idMagazine : " + idMagazine );
		System.out.println("--------------------------");
		
		if (idMagazine != null && idUser != null) {
			
			try {
				
				MagazineSavedList saved = new MagazineSavedList();
				saved.setIdUser(idUser);
				saved.setIdMagazine(Integer.parseInt(idMagazine));
				saved.setDtReg(new Date());
				
				int ret = magRepo.bookmarkAdd(saved);
				
				response.getWriter().print(ret); 
				
				System.out.println("OK ------xxxxx----- : SEND OK "  );
				
			} catch (IOException e) {
				
				
				System.out.println("OK ------xxxxx----- : ERROR "  );
				
				e.printStackTrace();
				
			} 
		}
		
	}

	

}
	