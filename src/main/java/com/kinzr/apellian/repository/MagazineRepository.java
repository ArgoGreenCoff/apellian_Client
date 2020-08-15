package com.kinzr.apellian.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kinzr.apellian.entity.model.Magazine;
import com.kinzr.apellian.entity.model.MagazineSavedList;
import com.kinzr.apellian.entity.model.Users;

public interface MagazineRepository {

	// 매거진 리스트 메인
	List<Magazine> magList(String category);
	
	// 매거진 상세
	Magazine magDetail(HttpServletRequest request);
	
	Magazine magPreInfo(HttpServletRequest request);
	Magazine magNextInfo(HttpServletRequest request);
	
	// 매거진 검색
	List<Magazine> magSearch(String searchWord);
	// 매거진 북마크
	List<Magazine> magSavedList(String idUser);
	
	int bookmarkDelete(String idUser, String idMagazine);

	int bookmarkAdd(MagazineSavedList magSave);

	Boolean bookmarkCheck(String idUser, String idMagazine);
}
