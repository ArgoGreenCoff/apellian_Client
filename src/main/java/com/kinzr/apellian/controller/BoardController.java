package com.kinzr.apellian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.Board;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.repository.BoardRepository;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board")	// 더보기
public class BoardController {

	@Autowired BoardRepository boardRepo;
	
	// 공지사항 리스트
	@RequestMapping(value="/noticeList", method= {RequestMethod.POST, RequestMethod.GET})
    public String noticeList(Model model, HttpServletRequest request)
    {
		List<Board> noticeList = boardRepo.noticeList();
		model.addAttribute("noticeList", noticeList);
		
        return "board/noticeList";
    }

	// 공지사항 상세
	@RequestMapping(value="/noticeDetail", method= {RequestMethod.POST, RequestMethod.GET})
    public String noticeDetail(Model model, HttpServletRequest request)
    {
		
		String idBoard = request.getParameter("idBoard");	
		model.addAttribute("idBoard", idBoard);

		
		Board noticeInfo = boardRepo.noticeDetail(Integer.parseInt(idBoard));
		model.addAttribute("noticeInfo", noticeInfo);
		
        return "board/noticeDetail";
    }

	
	// faq 리스트
	@RequestMapping(value="/faqList", method= {RequestMethod.POST, RequestMethod.GET})
    public String faqList(Model model, HttpServletRequest request)
    {
		List<Board> faqList = boardRepo.faqList();
		model.addAttribute("faqList", faqList);
		
        return "board/faqList";
    }

	// faq 상세
	@RequestMapping(value="/faqDetail", method= {RequestMethod.POST, RequestMethod.GET})
    public String faqDetail(Model model, HttpServletRequest request)
    {
		
		String idBoard = request.getParameter("idBoard");	
		model.addAttribute("idBoard", idBoard);

		
		Board faqInfo = boardRepo.faqDetail(Integer.parseInt(idBoard));
		model.addAttribute("faqInfo", faqInfo);
		
        return "board/faqDetail";
    }

}
