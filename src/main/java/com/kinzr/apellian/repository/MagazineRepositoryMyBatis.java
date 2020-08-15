package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.MagazineMapper;
import com.kinzr.apellian.entity.mapper.MagazineSavedListMapper;
import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.model.Magazine;
import com.kinzr.apellian.entity.model.MagazineSavedList;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
public class MagazineRepositoryMyBatis implements MagazineRepository{

	@Autowired private MagazineMapper magMapper;
	@Autowired private MagazineSavedListMapper savedMapper;

	
	// 매거진 리스트
	@Override
	public List<Magazine> magList(String category) {
		
		// TODO Auto-generated method stub
		List<Magazine> magList = magMapper.selectAll(category);
		return magList;
	}
	
	// 매거진 상세
	@Override
	public Magazine magDetail(HttpServletRequest request) {
		
		String idMagazine = request.getParameter("idMagazine");

		// TODO Auto-generated method stub
		Magazine magDetail = magMapper.selectByPrimaryKey(Integer.parseInt(idMagazine));
		
		return magDetail;
	}

	@Override
	public Magazine magPreInfo(HttpServletRequest request) {

		String idMagazine = request.getParameter("idMagazine");

		// TODO Auto-generated method stub
		Magazine preMagazine = magMapper.selectPreMagazine(Integer.parseInt(idMagazine));
		
		return preMagazine;
	}

	@Override
	public Magazine magNextInfo(HttpServletRequest request) {
		String idMagazine = request.getParameter("idMagazine");

		// TODO Auto-generated method stub
		Magazine nextMagazine = magMapper.selectNextMagazine(Integer.parseInt(idMagazine));
		
		return nextMagazine;
	}
	
	// 매거진 검색
	@Override
	public List<Magazine> magSearch(String searchWord) {
		
		System.out.println("searchWord : " + searchWord );

		// TODO Auto-generated method stub
		List<Magazine> magList = magMapper.selectSearch(searchWord);
		return magList;
	}

	// 매거진 북마크
	@Override
	public List<Magazine> magSavedList(String idUser) {
		
		// TODO Auto-generated method stub
		List<Magazine> magList = magMapper.selectSavedList(idUser);
		return magList;
	}
	
	// 북마크 삭제
	@Override
	public int bookmarkDelete(String idUser, String idMagazine) {
		
		int ret = savedMapper.deleteByPrimaryKey(idUser, Integer.parseInt(idMagazine));
		
		return ret;
	}
	
	// 북마크 추가
	public int bookmarkAdd(MagazineSavedList magSave) {
		
		int ret = savedMapper.insert(magSave);
		
		return ret;
	}
	
	// 북마크 체크
	@Override
	public Boolean bookmarkCheck(String idUser, String idMagazine) {
		
		MagazineSavedList ret = savedMapper.selectByPrimaryKey(idUser, Integer.parseInt(idMagazine));
		
		if(ret != null) {
			return true;
		} else {
			return false;
		}
		
	}


}
