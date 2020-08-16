package com.kinzr.apellian.repository;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.mapper.MyWedMapper;
import com.kinzr.apellian.entity.mapper.MyWedAccessoryMapper;
import com.kinzr.apellian.entity.mapper.MyWedCategoryMapper;
import com.kinzr.apellian.entity.mapper.MyWedOptionMapper;
import com.kinzr.apellian.entity.mapper.CdCategoryMapper;
import com.kinzr.apellian.entity.mapper.CdCompanyMapper;
import com.kinzr.apellian.entity.mapper.CdWeddingHallMapper;
import com.kinzr.apellian.entity.model.CdCategory;
import com.kinzr.apellian.entity.model.CdCompany;
import com.kinzr.apellian.entity.model.CdWeddingHall;
import com.kinzr.apellian.entity.model.MyWed;
import com.kinzr.apellian.entity.model.MyWedAccessory;
import com.kinzr.apellian.entity.model.MyWedCategory;
import com.kinzr.apellian.entity.model.MyWedOption;
import com.kinzr.apellian.entity.model.CdCompany;
import com.kinzr.apellian.entity.model.CdWeddingHall;
import com.kinzr.apellian.entity.model.PkgApplication;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;

import lombok.extern.java.Log;

@Log
@Repository
public class MyWeddingRepositoryMyBatis implements MyWeddingRepository{

	@Autowired private UsersMapper userMapper;
	@Autowired private MyWedMapper mywMapper;
	@Autowired private MyWedCategoryMapper mywctgMapper;
	@Autowired private MyWedOptionMapper mywoptMapper;
	@Autowired private MyWedAccessoryMapper mywasryMapper;
	@Autowired private CdCompanyMapper cdCmpyMapper;
	@Autowired private CdWeddingHallMapper cdWedHallMapper;
	@Autowired private CdCategoryMapper cdCategoryMapper;
	
	@Override
	public List<Users> selectAll() {
		// TODO Auto-generated method stub
		List<Users> userList = userMapper.selectAll();
		return userList;
	}
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Users MywedUser(HttpServletRequest request) {
		
		Result result = new Result();
		String idUser = request.getParameter("email");
		
		System.out.println("idUser : " + idUser);
			
		Users user = userMapper.selectByPrimaryKey(idUser);
		
		// System.out.println("rtn bbb passwd : " + user.getUsername() );
		
		if (user==null) {
			result.setCode("E");
			result.setDescription("조회 오류입니다(email 비정상)"); // 세션처리하거나 파라메터로 받으면 문제 없음
		}	

		return user;
	}
	
	
	// 기본 항목 가져오기 
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public MyWed MyweddingList(String idUser) {		// 클래스 메인 리스트
				
		Result result = new Result();
		
		MyWed myd = mywMapper.selectByIdUser(idUser);
		
		if (myd==null) {
			
			System.out.println("myWed is null ");
			
			result.setCode("E");
			result.setDescription("등록되지 않은 고객입니다"); // 세션처리하거나 파라메터로 받으면 문제 없음
		}	
		
		return myd;
	}
	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void addMywedcategoryList(String idUser) {		

		List<CdCategory> categoryList = cdCategoryMapper.selectAll();
		MyWed myd = mywMapper.selectByIdUser(idUser);

		if(myd != null) {
			for(CdCategory cate : categoryList) {
				MyWedCategory myCate = new MyWedCategory();
				myCate.setCdCategory(cate.getCdCategory());
				myCate.setNmCategory(cate.getNmCategory());
				myCate.setIdWedding(myd.getIdWedding());
				
				mywctgMapper.insert(myCate);
			}
		}
	}

	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<MyWedCategory> MywedcategoryList(long argmt) {		// 클래스 메인 리스트

		long idWedding			= argmt; 
		
		List<MyWedCategory> mywedcategoryList = mywctgMapper.selectByidWedding(idWedding);

		return mywedcategoryList;
		
	}
	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<MyWedAccessory> MywedaccessoryList(long argmt) {		// 클래스 메인 리스트
		
		// 기본 항목 세팅
		long idWedding			= argmt; 
		
		List<MyWedAccessory> mywedaccessoryList = mywasryMapper.selectByAsryidWedding(idWedding);
		
		return mywedaccessoryList;
	}
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<MyWedOption> MywedoptionList(long argmt) {		// 클래스 메인 리스트
		
		// 기본 항목 세팅
		long idWedding			= argmt; 
		
		//long idWedding			=  Long.parseLong(request.getParameter("idWedding"));
		//Integer cdCategory		= Integer.parseInt(request.getParameter("cdCategory"));
		//Integer idWeddingHall	= Integer.parseInt(request.getParameter("idWeddingHall"));
		
		List<MyWedOption> mywedoptionList = mywoptMapper.selectByOptnidWedding(idWedding);
		
		return mywedoptionList;
	}
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result MywedEditCheck(HttpServletRequest request) {
		
		Integer irst = 0; 
		Result result = new Result();
		
		String idUser			= request.getParameter("email");			// email
		String nmPartner		= (String) request.getAttribute("nmPartner");		// 배우자이름
		String nmMan			= request.getParameter("nmMan");			// 신랑이름
		String nmWoman			= request.getParameter("nmWoman");			// 신부이름
		String stridWeddingHall	= request.getParameter("idWeddingHall");	// 웨딩홀정보Id
		String nmWeddingHall	= request.getParameter("nmWeddingHall");	// 웨딩홀명

		String dtweddate		= request.getParameter("wedDate");	// 웨딩날짜		
		String dtwedtime		= request.getParameter("wedTime");	// 웨딩시간
		
	    if ((idUser == null)||(idUser.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E41");
			result.setDescription("email 불명");
        	return result;       
	    }

	    System.out.println("E41  ----------- : " + idUser);
		

        // 약관동의 체크유무 체크
        if ((nmWoman == null)||(nmWoman.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E41");
			result.setDescription("신부이름을 입력해주세요.");
        	return result;        	
        }   
        
	    System.out.println("E41 11 ----------- : " + nmWoman);

        
        // 약관동의 체크유무 체크
        if ((nmMan == null)||(nmMan.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E42");
			result.setDescription("신랑이름을 입력해주세요.");
        	return result;        	
        }        
        
	    System.out.println("E42  ----------- : " + nmMan);

                
        // 약관동의 체크유무 체크
        if ((nmWeddingHall == null)||(nmWeddingHall.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E43");
			result.setDescription("웨딩홀을 입력해주세요.");
        	return result;        	
        }    
        
        
        Integer idWeddingHall = 0; 
        // id 없어도 등록 가능
        if (stridWeddingHall != null && !stridWeddingHall.equals("")) {
        	idWeddingHall = Integer.valueOf(stridWeddingHall);		// 숫자처리
        }
        
	    System.out.println("E43  ----------- : " + nmWeddingHall);
        		
	    
	    System.out.println("dtweddate  ----------- : " + dtweddate);
	    System.out.println("dtwedtime  ----------- : " + dtwedtime);
	    
	    if ((dtweddate == null)||(dtweddate.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E44");
			result.setDescription("날짜를 선택하여 주세요");
        	return result;       
	    }
	    
	    
	    if ((dtwedtime == null)||(dtwedtime.equals(""))) {
        	// 정규식 불일치
        	result.setCode("E45");
			result.setDescription("시간을 선택하여 주세요");
        	return result;       
	    }
	    	    
	    
	    
	    // 웨딩일시 세팅
	    String dtwedding		= "";
		
		Date dtweddt = new Date();
		
		if ((dtweddate != null) && (dtwedtime != null)) {
			
			dtwedding       = dtweddate.substring(0,4)    		// 웨딩일시 (2020.01.10) -> 변환
				      + "-" + dtweddate.substring(5,7) 
				      + "-" + dtweddate.substring(8,10)
				      + " " + dtwedtime.substring(0,2)          // 
				      + ":" + dtwedtime.substring(2,4)
			          + ":" + "00.0";
			
			
			System.out.println("dtwedding ----------- : " + dtweddate + " / " + dtwedtime + " / " + dtwedding);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

			try {

	        	dtweddt = sdf.parse(dtwedding);	// 날짜
	            System.out.println("dtweddt ---- : " + dtweddt);

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	        
			System.out.println("dtwedding ----------- : " + dtwedding);
			System.out.println("dtweddt ----------- : " + dtweddt);
			
		}
		
	    System.out.println("E44  ----------- : " + dtwedding);
		
		
	    // 기존 등록 (MY_WED)되어 있는 사람은 불가
		
		System.out.println("idUser : " + idUser);
		
		
		MyWed myd = mywMapper.selectByIdUser(idUser);
		
		if (myd != null) {
			
			
			MyWed InsMyWed = new MyWed(); //데이터 타입
			
			InsMyWed.setIdWedding(myd.getIdWedding());
			InsMyWed.setNmWeddingHall(nmWeddingHall); // 웨딩홀명
			
			InsMyWed.setIdUser(idUser); 
			InsMyWed.setNmPartner(nmPartner); // nmPartner
			InsMyWed.setNmMan(nmMan);
			InsMyWed.setNmWoman(nmWoman);
			InsMyWed.setIdWeddingHall(""+idWeddingHall); // 웨딩홀 팝업이 되면 등록 가능 
			InsMyWed.setDtWedding(dtweddt);
			InsMyWed.setTmWedding(dtwedtime);
		    Date dt = new Date();
		    InsMyWed.setDtReg(dt); 
		    
			irst = mywMapper.updateByPrimaryKey(InsMyWed);
			
			System.out.println("irst ----------- : " + irst);
			
			if (irst == 1) {
				result.setCode("00");
				result.setDescription("업데이트 성공");
				//result.setUserInfo(user);
				return result;
			}
			else {
				result.setCode("E41");
				result.setDescription("등록처리 중 오류가 발생하였습니다.");
				//result.setUserInfo(user);
				return result;
			}	
		} else {
			// 신규의 경우 등록처리
			MyWed InsMyWed = new MyWed(); //데이터 타입
			
			InsMyWed.setNmWeddingHall(nmWeddingHall); // 웨딩홀명
			
			InsMyWed.setIdUser(idUser); 
			InsMyWed.setNmPartner(nmPartner); // nmPartner
			InsMyWed.setNmMan(nmMan);
			InsMyWed.setNmWoman(nmWoman);
			InsMyWed.setIdWeddingHall(""+idWeddingHall); // 웨딩홀 팝업이 되면 등록 가능 
			InsMyWed.setDtWedding(dtweddt);
			InsMyWed.setTmWedding(dtwedtime);
		    Date dt = new Date();
		    InsMyWed.setDtReg(dt); 
		    
			irst = mywMapper.insert(InsMyWed);
			
			System.out.println("irst ----------- : " + irst);
			
			if (irst == 1) {
				result.setCode("00");
				result.setDescription("신규등록성공");
				//result.setUserInfo(user);
				return result;
			}
			else {
				result.setCode("E41");
				result.setDescription("등록처리 중 오류가 발생하였습니다.");
				//result.setUserInfo(user);
				return result;
			}	
		}
	    
		
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ) 
	public int saveWeddingData(HttpServletRequest request) throws ParseException {
		
		
		Integer irst = 0; 
		Result result = new Result();
		
		String idWeddingHall			= (String)request.getAttribute("idWeddingHall");			
		String nmWeddingHall			= (String)request.getAttribute("nmWeddingHall");			
		String idRef			= (String)request.getAttribute("idRef");			
		String idUser			= (String)request.getAttribute("idUser");			
		String nmPartner			= (String)request.getAttribute("nmPartner");			
		String nmMan			= (String)request.getAttribute("nmMan");			
		String nmWoman			= (String)request.getAttribute("nmWoman");			
		String dtWedding			= (String)request.getAttribute("dtWedding");			
		String tmWedding			= (String)request.getAttribute("tmWedding");			
		String urlContract			= (String)request.getAttribute("urlContract");			

		

		
		
		MyWed InsMyWed = new MyWed(); //데이터 타입
		
		InsMyWed.setIdWeddingHall(idWeddingHall);
		InsMyWed.setNmWeddingHall(nmWeddingHall); // 웨딩홀명
		InsMyWed.setIdRef(idRef);
		InsMyWed.setIdUser(idUser); 
		InsMyWed.setNmPartner(nmPartner); // nmPartner
		InsMyWed.setNmMan(nmMan);
		InsMyWed.setNmWoman(nmWoman);
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		InsMyWed.setDtWedding(transFormat.parse(dtWedding));
		InsMyWed.setTmWedding(tmWedding);
		InsMyWed.setUrlContract(urlContract);
		
	    Date dt = new Date();
	    InsMyWed.setDtReg(dt); 
	    
		irst = mywMapper.insert(InsMyWed);
		
		System.out.println("irst ----------- : " + irst);
		
		return irst;	// 1 = Success
	}
	
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public int optionSum(long argmt) {		// 
		
		// 기본 항목 세팅
		long idWedding			= argmt; 
		
		System.out.println("idWedding ----------- : " + idWedding);

		int itotAmp = 0;

		MyWedOption MyWedOptionDtail  = mywoptMapper.selectSumByOptnidWedding(idWedding);
		
		if ( MyWedOptionDtail == null )
		{
			itotAmp = 0;
		}
		else {

			itotAmp = MyWedOptionDtail.getAmPrice();
		}
		
		System.out.println("itotAmp : " + itotAmp);
		
		return itotAmp;
	}
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<MyWedAccessory> mywedacrylist(long argmt) {		// 
		
		// 기본 항목 세팅
		long idWedding			= argmt; 
		
		System.out.println("idWedding ----------- : " + idWedding);

		List<MyWedAccessory> MyWedAcoryDtail  = mywasryMapper.selectByAsryidWedding(idWedding);
		
		return MyWedAcoryDtail;
	}

	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public long categorySum(long argmt) {		// 
		
		// 기본 항목 세팅
		long idWedding			= argmt; 
		
		long itotAmp = 0;
		
		MyWedCategory MyWedCategoryDtail  = mywctgMapper.selectSumByCtgyidWedding(idWedding);
		
		if ( MyWedCategoryDtail == null )
		{
			itotAmp = 0;
		}
		else {

			itotAmp = MyWedCategoryDtail.getAmPrice();
		}
		
		System.out.println("itotAmp : " + itotAmp);		
		
		return itotAmp;
	}
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<CdCompany> SearchCompanyList(HttpServletRequest request) { 
		
		// 기본 항목 세팅
		String schNmCompany = request.getParameter("schNmCompany"); // 웨딩홀명
		
		System.out.println("schNmCompany : " + schNmCompany);
		
		List<CdCompany> rtnSchCompany  = cdCmpyMapper.selectLikeCompany(schNmCompany);
		
		System.out.println("rtnSchCompany : " + rtnSchCompany);
		
		
		return rtnSchCompany;
	}
	
	
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result updatemywedctgy(HttpServletRequest request) { 
		
		Integer irst = 0; 
		Result result = new Result();
		
		long	amPrice		= 0;
		
			
		// 기본 항목 세팅
		String strAmPrice = request.getParameter("amPrice");
		if (strAmPrice != null) {
			strAmPrice = strAmPrice.replace(",", "");
			amPrice = Long.parseLong(strAmPrice);
		}
		
		
		long	idWedding	= Long.parseLong(request.getParameter("idWedding"));
		Integer cdCategory = Integer.valueOf(request.getParameter("cdCategory"));
		
		// 기존 목록에 없는 없체의 경우 0처리
		Integer	cdCompany	= 0;
		if ((request.getParameter("cdCompany") != null) && (request.getParameter("cdCompany") != "")) {
			cdCompany	= Integer.valueOf(request.getParameter("cdCompany"));	
		}
		
		
		String 	nmCompany	= request.getParameter("nmCompany");	// 회사명 ( 선택이든 미지정이든 무조건 들어와야 함 : 회사코드가 0이 아니면 사용하지 않음)
				
		MyWedCategory Uptmywedctgy = new MyWedCategory(); //데이터 타입

		Uptmywedctgy.setAmPrice(amPrice);
		Uptmywedctgy.setCdCompany(cdCompany);
		Uptmywedctgy.setNmCompany(nmCompany);
		Uptmywedctgy.setIdWedding(idWedding);
		Uptmywedctgy.setCdCategory(cdCategory);
		
		irst = mywctgMapper.updateByAmPrice(Uptmywedctgy);
		
		if (irst == 1) {
			result.setCode("00");
			result.setDescription("저장성공");
			//result.setUserInfo(user);
		}
		else {
			result.setCode("p");
			result.setDescription("업체등록 중 오류가 발생하였습니다.");
			//result.setUserInfo(user);
		}
			
		/*
		}	
		else {
			
			// null 로 입력 되었을 경우 처리
			result.setCode("p");
			result.setDescription("금액을 입력하십시요.");
		}
		*/
			
		return result;
	}
	

	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public List<CdWeddingHall> SearchWeddingHallList(HttpServletRequest  request) { //
		
		
		List<CdWeddingHall> rtnschNmWeddingHallList  = null;
	  
		try {
			
			// 기본 항목 세팅
			String schNmWeddingHall = request.getParameter("schnmWeddingHall"); // 웨딩홀명
			
			System.out.println("schnmWeddingHall : " + schNmWeddingHall);

			rtnschNmWeddingHallList  = cdWedHallMapper.selectLikeWedHall(schNmWeddingHall);
			
			System.out.println("rtnnmWeddingHall : " + rtnschNmWeddingHallList);
			

		} catch (Exception e) {

			System.out.println(e.toString());

		}
		
		return rtnschNmWeddingHallList;

	}
	
	
	
}
