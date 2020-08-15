package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.Board;
import com.kinzr.apellian.entity.model.Magazine;
import com.kinzr.apellian.entity.model.MagazineSavedList;
import com.kinzr.apellian.entity.model.Users;

public interface BoardRepository {

	// 공지사항 리스트
	List<Board> noticeList();
	
	// 공지사항 상세
	Board noticeDetail(int idBoard);
	
	// faq리스트
	List<Board> faqList();
	
	// faq 상세
	Board faqDetail(int idBoard);

}
