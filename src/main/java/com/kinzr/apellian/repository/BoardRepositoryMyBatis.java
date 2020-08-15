package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.BoardMapper;
import com.kinzr.apellian.entity.mapper.MagazineMapper;
import com.kinzr.apellian.entity.mapper.MagazineSavedListMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.Board;
import com.kinzr.apellian.entity.model.Magazine;
import com.kinzr.apellian.entity.model.MagazineSavedList;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
public class BoardRepositoryMyBatis implements BoardRepository{

	@Autowired private BoardMapper boardMapper;

	
	// 공지사항 리스트
	@Override
	public List<Board> noticeList() {
		
		// TODO Auto-generated method stub
		List<Board> noticeList = boardMapper.selectListType("N");
		return noticeList;
	}
	
	// 공지사항 상세
	@Override
	public Board noticeDetail(int idBoard) {

		Board noticeInfo = boardMapper.selectByPrimaryKey((long) idBoard);
		
		return noticeInfo;
	}
	
	
	// faq 리스트
	@Override
	public List<Board> faqList() {
		
		// TODO Auto-generated method stub
		List<Board> faqList = boardMapper.selectListType("F");
		return faqList;
	}
	
	
	// faq 상세
	@Override
	public Board faqDetail(int idBoard) {

		Board faqInfo = boardMapper.selectByPrimaryKey((long) idBoard);
		
		return faqInfo;
	}


}
