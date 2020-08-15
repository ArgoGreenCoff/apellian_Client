package com.kinzr.apellian.repository;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kinzr.apellian.entity.mapper.UsersMapper;
import com.kinzr.apellian.entity.mapper.AuthCodeMapper;
import com.kinzr.apellian.entity.model.Result;
import com.kinzr.apellian.entity.model.Users;
import com.kinzr.apellian.entity.model.AuthCode;


import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.Date;


@Log
@Repository
@Transactional(readOnly=true) 
public class UsersRepositoryMyBatis implements UsersRepository{

	@Autowired
	private UsersMapper userMapper;
	@Autowired
	private AuthCodeMapper authcodeMapper;

	@Override
	public List<Users> selectAll() {
		// TODO Auto-generated method stub
		List<Users> userList = userMapper.selectAll();
		return userList;
	}

	@Override
	public List<AuthCode> selectAuthcodeAll() {
		// TODO Auto-generated method stub
		List<AuthCode> authcodeList = authcodeMapper.selectAll();
		return authcodeList;
	}
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result LoginCheck(HttpServletRequest request) {
		
		Result result = new Result();
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		System.out.println("idUser : " + idUser);
		System.out.println("passwd : " + passwd);
			
		Users user = userMapper.selectByPrimaryKey(idUser);
		
		// System.out.println("rtn bbb passwd : " + user.getUsername() );
		
		if (user==null) {
			result.setCode("E");
			result.setDescription("이메일이 맞지 않습니다.");
		}else {
			if (!user.getEnabled().equals("1")) {
				result.setCode("E");
				result.setDescription("정지된 계정입니다.");
			}else {
				if (user.getYnWithdraw().equals("Y")) {
					result.setCode("E");
					result.setDescription("탈퇴한 계정입니다.");
				}else {
					if (user.getPassword().equals(passwd)) {
						result.setCode("00");
						result.setDescription("로그인성공");
						result.setUserInfo(user);
					}else {
						result.setCode("W");
						result.setDescription("비밀번호가 맞지 않습니다.");
					}
				}
			}
		}

		return result;
	}
	
	

	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result ValiCheck(HttpServletRequest request) {
				
		Result result = new Result();
		
		String idUser = request.getParameter("email");			// 이메일
		String passwd = request.getParameter("passwd");			// 비밀번호
		
		System.out.println("find idUser xxxx : " + idUser );
		
		
		String nmuser     = request.getParameter("nmuser");		// 이름
		String nocountry  = request.getParameter("nocountry");	// 국가번호(+82, etc)
		String notel      = request.getParameter("notel");		// 휴대번호
		String certinum   = request.getParameter("certinum");	// 인증번호
		String chkperinfo = request.getParameter("chkperinfo");	// 3자 개인정보 동의
		
		// String certyn     = request.getParameter("certyn");	 	// 인증여부
				
		
		System.out.println("find idUser : " + idUser );
		System.out.println("find passwd : " + passwd );
		System.out.println("find nmuser : " + nmuser );
		System.out.println("find nocountry : " + nocountry );
		System.out.println("find notel : " + notel );
		System.out.println("find certinum : " + certinum );
		System.out.println("find chkperinfo : " + chkperinfo );
		
		// 정상의 경우 등록처리 
		
		// Email 형식 체크
		// String regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";    // Regular Expression
		
		String regExp = "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+";
		
        boolean rslt = Pattern.matches(regExp, idUser);
                
		System.out.println("find idUser(rslt) : " + idUser + " / " + rslt );
		 
		
        if (!rslt) { 
        	
        	// 정규식 불일치
        	result.setCode("E");
			result.setDescription("올바르지 않은 이메일 주소입니다.");
        	// return result;
        }
        
        else {
	        
			/*
			// 비밀번호 입력유무 체크
	        if (passwd==null) {
	        	// 정규식 불일치
	        	result.setCode("P");
				result.setDescription("비밀번호를 입력해주세요.");
	        	return result;
			}
	        
	        */
	        // 패스워드 형식 체크
	        String rtnPw = passwordValidator(passwd);
	        
	        if (!rtnPw.contentEquals("OK")) {
	        	// 정규식 불일치
	        	result.setCode("P");
				result.setDescription(rtnPw);
				// return result;
	        	 
			} else {
		        
		        // 이름항목 입력 체크
		        if (nmuser.length() < 2) {
		        	// 정규식 불일치
		        	result.setCode("N");
					result.setDescription("이름을 2자 이상 입력해주세요.");
					// return result;        	
		        }
		        
		        else {
		        	
		            // 이름항목 입력 체크
		    		
		            if (notel.length() < 10) {
		            	// 정규식 불일치
		            	result.setCode("T");
		    			result.setDescription("휴대전화 번호를 확인하세요.");
		    			// return result;        	
					} else {
		            	
		            	/*
		            	if (!certyn.equals("Y")) {
				        	// 정규식 불일치
				        	result.setCode("C");
							result.setDescription("인증을 진행 해주세요.");		            		
		            	}
		            	else {
					        
					        // 약관동의 체크유무 체크
					        if ((chkperinfo == null)||(!chkperinfo.equals("on"))) {
					        	// 정규식 불일치
					        	result.setCode("I");
								result.setDescription("약관에 동의해주세요.");
								// return result;        	
					        }   
					        else {
					        
		            	 	*/		        	

					        	
								
								// 30일 체크 부터 체크 로직 처리
								Users user = userMapper.selectByPrimaryKey(idUser);
								
								// 신규유저로 등록처리
								if (user!=null) {
									
									if (!user.getEnabled().equals("1")) {
										
										result.setCode("E");
										result.setDescription("정지된 계정입니다.");
										// return result;
									}else {			
						
										// 탈퇴한 계정 (재가입 가능) 체크
										if (user.getYnWithdraw().equals("Y")) {
											
											// 현재일자 - 30 < 탈퇴일자 의 케이스만 재가입 처리
											// user.getDtWithdraw() 
											if (bef30dayCheck(user.getDtWithdraw()) == false) {
												result.setCode("E");
												result.setDescription("탈퇴한 이메일 주소입니다.\n30일 이후 재가입이 가능합니다.");
												// return result;
											} else {
												
												// 등록 가능 처리 
												result.setCode("00");
												
											}
										} else {
											
											// 기존 존재하는 고객
											
											result.setCode("E");
											result.setDescription("이미 등록된 E-mail입니다.");
											//result.setUserInfo(user);
											// return result;
										}
									}
								} else {
									
									result.setCode("00");
									result.setDescription("신규등록가능");
										
								}
					        // }
		            	// }			            
		        	}
		        }
		    }
		
        }
			
		
		return result;
	}
	
	
	
	
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result SignupCheck(HttpServletRequest request) {
				
		Integer irst = 0; 
		Result result = new Result();
		
		String idUser = request.getParameter("email");			// 이메일
		String passwd = request.getParameter("passwd");			// 비밀번호
		
		System.out.println("find idUser xxxx : " + idUser );
		
		System.out.println("signup chk nocountry : " + request.getParameter("nocountry") );
		System.out.println("signup chk notel : " + request.getParameter("notel") );		
		
		String nmuser     = request.getParameter("nmuser");		// 이름
		String nocountry  = request.getParameter("nocountry");	// 국가번호(+82, etc)
		String notel      = request.getParameter("notel");		// 휴대번호
		String certinum   = request.getParameter("certinum");	// 인증번호
		String chkperinfo = request.getParameter("chkperinfo");	// 3자 개인정보 동의
		
		String certyn     = request.getParameter("certyn");	 	// 인증여부
		
				
		
		System.out.println("find idUser : " + idUser );
		System.out.println("find passwd : " + passwd );
		System.out.println("find nmuser : " + nmuser );
		System.out.println("find nocountry : " + nocountry );
		System.out.println("find notel : " + notel );
		System.out.println("find certinum : " + certinum );
		System.out.println("find chkperinfo : " + chkperinfo );
		
		// 정상의 경우 등록처리 
		
		Users InsUsers = new Users(); //데이터 타입
		
		InsUsers.setUsername(request.getParameter("email"));                        // userid
		InsUsers.setPassword(request.getParameter("passwd"));                       // 비밀번호
		InsUsers.setNmUser(request.getParameter("nmuser"));                         // 이름 
		InsUsers.setYnWithdraw("N");                                                // 탈퇴여부 ( 'Y' 세팅 ) // request.getParameter("yn_withdraw")
		InsUsers.setTyGender("M");                                                 // 성별
		InsUsers.setTyUser("NM");                                                   // 타입
		//InsUsers.setCdCompany(0);                                                 // 회사
		InsUsers.setEnabled("1");                                                   // 서용유  
		InsUsers.setNoCountry(request.getParameter("nocountry"));
		InsUsers.setNoTel(request.getParameter("notel"));
		InsUsers.setYnWithdraw("N");
		InsUsers.setYnWithdrawReason1("N");
		InsUsers.setYnWithdrawReason2("N");
		InsUsers.setYnWithdrawReason3("N");
		InsUsers.setYnWithdrawReason4("N");
		InsUsers.setYnWithdrawReason5("N");
		InsUsers.setNmUserDelivery1("");
		InsUsers.setNoTelDelivery1("");
		InsUsers.setDsAddrDelivery1("");
		InsUsers.setNmUserDelivery100("");
		InsUsers.setNoTelDelivery100("");
		InsUsers.setDsAddrDelivery100("");
		
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //Calendar c1 = Calendar.getInstance();
        //String strToday = sdf.format(c1.getTime());
        Date dt = new Date();
        
        // 현재일자
        InsUsers.setDtReg(dt); // request.getParameter("dt_withdraw"));                // 탈퇴일자(현재일자처리:8자리)
												
        

		// Email 형식 체크
		//String regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";    // Regular Expression
		
		String regExp = "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+";
		
        boolean rslt = Pattern.matches(regExp, idUser);
                
		System.out.println("find idUser(rslt) : " + idUser + " / " + rslt );
		 
		
        if (!rslt) { 
        	
        	// 정규식 불일치
        	result.setCode("E");
			result.setDescription("올바르지 않은 이메일 주소입니다.");
        	// return result;
        } else {
	        
			/*
			// 비밀번호 입력유무 체크
	        if (passwd==null) {
	        	// 정규식 불일치
	        	result.setCode("P");
				result.setDescription("비밀번호를 입력해주세요.");
	        	return result;
			}
	        
	        */
	        // 패스워드 형식 체크
	        String rtnPw = passwordValidator(passwd);
	        
	        if (!rtnPw.contentEquals("OK")) {
	        	// 정규식 불일치
	        	result.setCode("P");
				result.setDescription(rtnPw);
				// return result;
	        	 
	        } else {
		        
		        // 이름항목 입력 체크
		        if (nmuser.length() < 2) {
		        	// 정규식 불일치
		        	result.setCode("N");
					result.setDescription("이름을 2자 이상 입력해주세요.");
					// return result;        	
		        } else {
		        	
		            // 이름항목 입력 체크
		    		
		            if (notel.length() < 10) {
		            	// 정규식 불일치
		            	result.setCode("T");
		    			result.setDescription("휴대전화 번호를 확인하세요.");
		    			// return result;        	
		            }  else {
		            	
		            	if (!certyn.equals("Y")) {
				        	// 정규식 불일치
				        	result.setCode("C");
							result.setDescription("인증을 진행 해주세요.");		            		
		            	} else {
				        
					        // 약관동의 체크유무 체크
					        if ((chkperinfo == null)||(!chkperinfo.equals("on"))) {
					        	// 정규식 불일치
					        	result.setCode("I");
								result.setDescription("약관에 동의해주세요.");
								// return result;        	
					        } else {
					        	
								
								// 30일 체크 부터 체크 로직 처리
								Users user = userMapper.selectByPrimaryKey(idUser);
								
								// 신규유저로 등록처리
								if (user!=null) {
									
									if (!user.getEnabled().equals("1")) {
										
										result.setCode("E");
										result.setDescription("정지된 계정입니다.");
										// return result;
									}else {			
						
										// 탈퇴한 계정 (재가입 가능) 체크
										if (user.getYnWithdraw().equals("Y")) {
											
											// 현재일자 - 30 < 탈퇴일자 의 케이스만 재가입 처리
											// user.getDtWithdraw() 
											if (bef30dayCheck(user.getDtWithdraw()) == false) {
												result.setCode("E");
												result.setDescription("탈퇴한 이메일 주소입니다.\n30일 이후 재가입이 가능합니다.");
												// return result;
											} else {
												
												// 등록처리 
												
		
												irst = userMapper.insert(InsUsers);
												
												
												System.out.println("find idUser ( irst ) : " + idUser + " / " + irst);
												
												if (irst == 1) {
													result.setCode("00");
													result.setDescription("신규등록성공");
													//result.setUserInfo(user);
													// return result;
												} else {
													result.setCode("E");
													result.setDescription("등록처리 중 오류가 발생하였습니다.");
													//result.setUserInfo(user);
													// return result;
												}	
												
											}
										} 	else {
											
											// 기존 존재하는 고객
											
											result.setCode("E");
											result.setDescription("이미 등록된 E-mail입니다.");
											//result.setUserInfo(user);
											// return result;
										}
									}
								} else {
									
									irst = userMapper.insert(InsUsers);
									
									
									System.out.println("find idUser ( irst ) : " + idUser + " / " + irst);
									
									if (irst == 1) {
										result.setCode("00");
										result.setDescription("신규등록성공");
										//result.setUserInfo(user);
										// return result;
									} else {
										result.setCode("E");
										result.setDescription("등록처리 중 오류가 발생하였습니다.");
										//result.setUserInfo(user);
										// return result;
									}	
								}
					        }
		            	}
		            }
		        }
		    }
        }
			
		
		return result;
	}
	
	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result regSmsRandom(HttpServletRequest request) {
				
		long irst = 0; 
		Result result = new Result();
		
        // 이름항목 입력 체크
		String notel = request.getParameter("notel");
		
		System.out.println("regSmsRandom in notel : " +  notel );		
		
        if (notel.length() < 10) {
        	// 정규식 불일치
        	result.setCode("T");
			result.setDescription("휴대전화 번호를 확인하세요.");
			// return result;
		} else {

			// 등록처리

			AuthCode InsAhcd = new AuthCode(); // 데이터 타입

			long idAuthcd = 0;

			InsAhcd.setIdAuthCode(idAuthcd); // CD_AUTH
			InsAhcd.setCdAuth(""); // CD_AUTH
			InsAhcd.setTyAuth("1"); // 인증타입코드 1.SMS 2.Email

			// 현재일자 ( 사용하지 않음 : db에서 now 처리 )
			Date dt = new Date();
			InsAhcd.setDtReg(dt); // request.getParameter("dt_withdraw")); // 탈퇴일자(현재일자처리:8자리)

			System.out.println("authcodeMapper.insertAfterGetKey : " + " / " + InsAhcd);

			irst = authcodeMapper.insertAfterGetKey(InsAhcd);
			// AuthCode rtnAhcd = authcodeMapper.insertAfterGetKey(InsAhcd);

			irst = InsAhcd.getIdAuthCode(); // 추출 가능
			// irst -> Authcode index
			AuthCode authCode = authcodeMapper.selectByPrimaryKey(irst);
			String randomCode = authCode.getCdAuth();

			
			AuthCode msgAuthCode = new AuthCode(); // 보내려는 메시지 코드
			msgAuthCode.setCdAuth("인증번호는 ["+randomCode+"] 입니다");	// 인증번호 문자마세지내용으로 임시사용 
			msgAuthCode.setTyAuth(notel);	// 인증번호 보내는 대상자번호 임시사용
			// irst (AUTH_CODE code 값을 가지고 해당코드로 MSG_DATA에 Insert)
			authcodeMapper.insertSendMsg(msgAuthCode);
            // end 200716 
			System.out.println("find( return : irst ) : " + " / " + irst);
			
			if (irst > 0) {
				result.setCode(String.valueOf(irst));
				result.setDescription("신규등록성공");
				//result.setUserInfo(user);
				// return result;
			} else {
				result.setCode("C");
				result.setDescription("인증번호 등록처리 중 오류가 발생하였습니다.");
				//result.setUserInfo(user);
				// return result;
			}		
        }
		
		return result;
	}
	
	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result getAuthCdKey(HttpServletRequest request) {
				
		String Srlt = ""; 	
		Result result = new Result();
		
		long Idauthcd = Long.parseLong(request.getParameter("idauthcd"));
		// Integer Idauthcd = Integer.parseInt(request.getParameter("authid"));
		
		System.out.println("sys start Idauthcd ( return : irst ) : " + Idauthcd + " / " + Srlt);
		
		AuthCode authCdDtl = authcodeMapper.selectByPrimaryKey(Idauthcd);
		
		// System.out.println("sys start Idauthcd ( return : irst ) : " + Idauthcd + " / " + authCdDtl.getCdAuth());
		
		
		if (authCdDtl==null) {			
			result.setCode("C");
			result.setDescription("인증번호 등록처리 중 오류가 발생하였습니다.");
		} else {

			// 정상 키 받기
			Srlt = authCdDtl.getCdAuth();
			result.setCode(Srlt);
			result.setDescription("신규등록성공");			
		}
		
		System.out.println("sys print Idauthcd ( return : irst ) : " + Idauthcd + " / " + Srlt);

			
		return result;
	}
	


	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result FindpasswordCheck(HttpServletRequest request) {
		
// 활성화 조건 처리
// Email : 검증 체크

// - 이메일 주소 유효성 검사
// 영문,숫자 8자이상

// 가입되지 않은 이메일 주소입니다.               
		
		Result result = new Result();
		
		String idUser = request.getParameter("email");			// 이메일
		
		// email 입력유무 체크
		if (idUser==null) {
        	// 정규식 불일치
        	result.setCode("E");
			result.setDescription("이메일 주소를 입력해주세요.");
        	return result;
		}
		

		// Email 형식 체크
		// String regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";    // Regular Expression
		
		String regExp = "[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+";
		
		System.out.println("rtn regExp : " + regExp + idUser );
		
		
        boolean rslt = Pattern.matches(regExp, idUser);
        
        System.out.println("rtn rslt : " + rslt );
                
        if (!rslt) { 
        	
        	// 정규식 불일치
        	result.setCode("E");
			result.setDescription("올바르지 않은 이메일 주소입니다.");
        	return result;
        }
        
		
		// 유저 확인 
		Users user = userMapper.selectByPrimaryKey(idUser);
		
		// 신규유저로 등록처리
		if (user==null) {
			
			result.setCode("E");
			result.setDescription("가입되지 않은 이메일 주소입니다.");
			return result;
		} else {

			result.setCode("00");
			result.setDescription("유저 확인 성공");
			result.setUserInfo(user);
		}
		
		return result;
	}
	
	
	
// 이메일 확인 코드 체크
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result EmailauthCheck(HttpServletRequest request) {
		
		Result result = new Result();
		
		String strCd1 = request.getParameter("cd1");			// 입력숫자1
		String strCd2 = request.getParameter("cd2");			// 입력숫자2
		String strCd3 = request.getParameter("cd3");			// 입력숫자3
		String strCd4 = request.getParameter("cd4");			// 입력숫자4
		
		String idauthcd = request.getParameter("idauthcd");			// auth code 키 
		
		System.out.println("strCd1 " + strCd1);
		System.out.println("strCd2 " + strCd2);
		System.out.println("strCd3 " + strCd3);
		System.out.println("strCd4 " + strCd4);
		System.out.println("idauthcd " + idauthcd);
		
		// long lchk = 0;
		// lchk = Long.parseLong(strCd1 + "" + strCd2 + "" + strCd3 + "" + strCd4); 
		
		String lchk = strCd1 + "" + strCd2 + "" + strCd3 + "" + strCd4;
		
		// email 입력유무 체크
		if ((strCd1==null)||(strCd2==null)||(strCd3==null)||(strCd4==null)) {
        	// 정규식 불일치
        	result.setCode("E");
			result.setDescription("발송된 숫자를 입력해주세요.");
        	return result;
		}
        
		
		// 등록된 키 확인 
		AuthCode AuthCode = authcodeMapper.selectByPrimaryKey(Long.parseLong(idauthcd));
		
		System.out.println("getCdAuth : " + lchk + " = " +AuthCode.getCdAuth());
		
		// 신규유저로 등록처리
		if (AuthCode.getCdAuth().equals(lchk)) {
			
			
			result.setCode("00");
			result.setDescription("코드 확인 성공");
			// return result;
		} else {

			result.setCode("E");
			result.setDescription("올바르지 않은 코드입니다. 다시 확인해주세요.");
			
		}
		
		return result;
	}
	
	
// 비밀번호 잃어버렸을 경우 비밀번호 변경
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result NewpasswordCheck(HttpServletRequest request) {
		
		Result result = new Result();
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		Users user = userMapper.selectByPrimaryKey(idUser);
		if (user==null) {
			result.setCode("E");
			result.setDescription("이메일이 맞지 않습니다.");
		}else {
			if (!user.getEnabled().equals("1")) {
				result.setCode("E");
				result.setDescription("정지된 계정입니다.");
			}else {
				if (user.getYnWithdraw().equals("Y")) {
					result.setCode("E");
					result.setDescription("탈퇴한 계정입니다.");
				}else {
					
					// 패스워드 형식 체크
			        String rtnPw = passwordValidator(passwd);
			        
			        if (!rtnPw.contentEquals("OK")) {
			        	// 정규식 불일치
			        	result.setCode("E");
						result.setDescription(rtnPw);
						// return result;
			        	 
			        } else {
			        
						if (user.getPassword().equals(passwd)) {
							result.setCode("E");
							result.setDescription("이미 사용한 비밀번호입니다. 보안을 위해 다른 비밀번호를 입력해주세요.");
						} else {

							Users UptUsers = new Users(); //데이터 타입
							
							UptUsers.setUsername(request.getParameter("email"));                        // userid
							UptUsers.setPassword(request.getParameter("passwd"));                       // 비밀번호
	
							// 비밀번호 UPDATE 처리 
							int irst = userMapper.updateByPassword(UptUsers);
							
							if (irst == 1) {
								result.setCode("00");
								result.setDescription("변경성공");
								result.setUserInfo(user);
							} else {
								result.setCode("E");
								result.setDescription("비밀번호 변경처리 중 오류가 발생하였습니다.");
								result.setUserInfo(user);
							}
						}
			        }
				}
			}
		}
		return result;
	}
	
	
	
// 단순 비밀번호 변경
		@Override
		@Transactional(isolation=Isolation.REPEATABLE_READ)
		public Result ChangepasswordCheck(HttpServletRequest request) {
			
			Result result = new Result();
			String idUser = request.getParameter("email");
			String oldpasswd = request.getParameter("oldpasswd");
			String newpasswd = request.getParameter("newpasswd");
			
			
			
			if (oldpasswd==null) {
				result.setCode("W1");
				result.setDescription("현 비밀번호를 입력해주세요.");
				return result;
			}
			
			if (newpasswd==null) {
				result.setCode("W2");
				result.setDescription("새 비밀번호를 입력해주세요.");
				return result;
			}
			
			
			
			Users user = userMapper.selectByPrimaryKey(idUser);
			if (user==null) {
				result.setCode("W1");
				result.setDescription("이메일이 맞지 않습니다.");
			}else {
				if (!user.getEnabled().equals("1")) {
					result.setCode("W1");
					result.setDescription("정지된 계정입니다.");
				}else {
					if (user.getYnWithdraw().equals("Y")) {
						result.setCode("W1");
						result.setDescription("탈퇴한 계정입니다.");
					}else {
						
						if (!user.getPassword().equals(oldpasswd)) {
							result.setCode("W1");
							result.setDescription("현재 비밀번호가 맞지 않습니다.");
					} else {
							
							// 동일한 비밀번호 등록 불가처리
							if (user.getPassword().equals(newpasswd)) {
								result.setCode("W2");
							result.setDescription("이미 사용한 비밀번호입니다. 보안을 위해 다른 비밀번호를 입력해주세요.");
						} else {
								
								// 패스워드 형식 체크
						        String rtnPw = passwordValidator(newpasswd);
						        
						        if (!rtnPw.contentEquals("OK")) {
						        	// 정규식 불일치
						        	result.setCode("W2");
									result.setDescription(rtnPw);
									// return result;
						        	 
						        } else {

		
									// 비밀번호 변경처리
									
									Users UptUsers = new Users(); //데이터 타입
									
									UptUsers.setUsername(idUser);                        // userid
							        UptUsers.setPassword(newpasswd); 
							        
							        // System.out.println("chgpss idUser  : " + idUser );
							     // System.out.println("chgpss newpasswd  : " + newpasswd );
									
									// 비밀번호 UPDATE 처리 
									int irst = userMapper.updateByPassword(UptUsers);
									
									if (irst == 1) {
										result.setCode("00");
										result.setDescription("변경성공");
										Users user1 = userMapper.selectByPrimaryKey(idUser);
										result.setUserInfo(user1);
								}	else {
										result.setCode("W2");
										result.setDescription("비밀번호 변경처리 중 오류가 발생하였습니다.");
										result.setUserInfo(user);
									}
						        }
							}
						}
					}
				}
			}
			return result;
		}
		
		
	
	/**
	 * 패스워드 유효성 검증
	 *
	 * @param passwd
	 * @return
	 */
	public String passwordValidator(String passwd){
		String returnValue = "OK";
	  
		String pattern = "[a-zA-Z0-9]{8,20}";

		if(spaceCheck(passwd)){	//패스워드 공백 문자열 체크
			returnValue = "비밀번호에 공백문자를 허용하지 않습니다.";	    
		// }else if(passwd.length() < 8 || passwd.length() > 20){	//자릿수 검증
		//   returnValue = "비밀번호는 8자 이상, 20자 이하로 구성하세요.";	    
		}else {

			boolean bool = passwd.matches(pattern);
		
			if (!bool) {
				returnValue = "영문,숫자 8자리 이상, 20자리 이하로 입력해주세요.";
			}			
		}
		  
		return returnValue;
	}

	
	/**
	 * 공백 문자 체크
	 *
	 * @param spaceCheck
	 * @return
	 */
	public boolean spaceCheck(String spaceCheck){
	  for(int i = 0 ; i < spaceCheck.length() ; i++) {
	    if(spaceCheck.charAt(i) == ' ')
	      return true;
	  }

	  return false;
	}

	/**
	 * 연속된 숫자 체크
	 *
	 * @param numberCheck
	 * @return
	 */
	public boolean continueNumberCheck(String numberCheck){
	  int o = 0;
	  int d = 0;
	  int p = 0;
	  int n = 0;		
	  int limit = 4;

	  for(int i = 0 ; i < numberCheck.length() ; i++) {
	    char tempVal = numberCheck.charAt(i);
	    if (i > 0 && (p = o - tempVal) > -2 && p < 2 && (n = p == d ? n + 1 : 0) > limit - 3)
	      return true;
	    d = p;
	    o = tempVal;
	  }

	  return false;
	}
	
	
	/**
	 * 퇼퇴일자 체크
	 *
	 * @param numberCheck
	 * @return
	 */
	public boolean bef30dayCheck(Date regdt){
		boolean bool = false;

        // calendar1(현재일-30) > calendar2(탈퇴일) : 1  --- 재등록가능 케이스 그 외는 불가처리
        // calendar1 == calendar2 : 0
        // calendar1 < calendar2 : -1
        
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());		// 현재일자 세팅
        calendar1.set(Calendar.DATE, -30);	// 현재일 - 30일 일자 세팅
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(regdt);			// 탈퇴일자 세팅
        
        //앞에 변수가 크면 1, 작으면 -1, 같으면 0
        int result1 = calendar1.compareTo(calendar2);
        
        // 현재일자에서 - 30의 일자가 탈퇴일자보다 커야지 가능
        // 현재일 20200501 - 30 = 20200401 /  20200430
        // 탈퇴일 20200420 
        
        if (result1 == 0) {
        	bool = true;
        }
        
		return bool;
	}

	


	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result WithdrawCheck(HttpServletRequest request) {
		
		Integer irst = 0; 
		Result result = new Result();
		String idUser = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		String ynwithdraw1 = request.getParameter("ynWithdrawReason1");
		String ynwithdraw2 = request.getParameter("ynWithdrawReason2");
		String ynwithdraw3 = request.getParameter("ynWithdrawReason3");
		String ynwithdraw4 = request.getParameter("ynWithdrawReason4");		
		String ynwithdraw5 = request.getParameter("ynWithdrawReason5");		
		String dswithdraw  = request.getParameter("dswithdraw");
		
		// 탈퇴사유는 1개이상 필수처리
		if ((ynwithdraw1 == null) && (ynwithdraw2 == null) && (ynwithdraw3 == null) && (ynwithdraw4 == null)
				&& (ynwithdraw5 == null)) {
					
			result.setCode("C");
			result.setDescription("탈퇴사유는 1개이상 선택하여주십시요.");
		} else if ((ynwithdraw5 != null) && ((dswithdraw == null) || (dswithdraw == ""))) {
			
			// 기타를 체크하고 사유를 넣지 않을경우 
			result.setCode("D");
			result.setDescription("탈퇴사유를 입력하여 주십시요.");			
		} // 필수처리
		else {		
			
			//사용자 정보 체크(비밀번호 확인) : 기본체크는 그대로 사용하고 비밀번호 확인만 최종으로 함
			Users user = userMapper.selectByPrimaryKey(idUser);
			
			// System.out.println("rtn bbb passwd : " + user.getUsername() );
			
			// String AAA = user.getUsername();
			
			// System.out.println("rtn ???  : " + idUser + " // " + " // " + AAA + " // pw : " + passwd);
			
			System.out.println("idUser : " + idUser + " // passwd : " + passwd);
			 
			
			if (user==null) {
				result.setCode("W");
				result.setDescription("이메일이 맞지 않습니다.");
			}else {
				if (!user.getEnabled().equals("1")) {
					result.setCode("W");
					result.setDescription("정지된 계정입니다.");
				}else {
					if (user.getYnWithdraw().equals("Y")) {
						result.setCode("W");
						result.setDescription("탈퇴한 계정입니다.");
					}else {
						if (!user.getPassword().equals(passwd)) {
							result.setCode("W");
							result.setDescription("비밀번호가 맞지 않습니다.");
						}else {
							
							// 회원 확인 되었을 경우 탈퇴처리
							
							Users UptUsers = new Users(); //데이터 타입
							
							if ((ynwithdraw1 == null) || (ynwithdraw1 == "")) {
								ynwithdraw1 = "N";
							}
							if ((ynwithdraw2 == null) || (ynwithdraw2 == "")) {
								ynwithdraw2 = "N";
							}
							if ((ynwithdraw3 == null) || (ynwithdraw3 == "")) {
								ynwithdraw3 = "N";
							}
							if ((ynwithdraw4 == null) || (ynwithdraw4 == "")) {
								ynwithdraw4 = "N";
							}
							if ((ynwithdraw5 == null) || (ynwithdraw5 == "")) {
								ynwithdraw5 = "N";
							}
					        
							UptUsers.setUsername(request.getParameter("email"));                        // userid
					        UptUsers.setYnWithdraw("Y");                                                // 탈퇴여부 ( 'Y' 세팅 ) // request.getParameter("yn_withdraw") 
					        UptUsers.setYnWithdrawReason1(ynwithdraw1); // 선택사유1~5
					        UptUsers.setYnWithdrawReason2(ynwithdraw2);
					        UptUsers.setYnWithdrawReason3(ynwithdraw3);
					        UptUsers.setYnWithdrawReason4(ynwithdraw4);
					        UptUsers.setYnWithdrawReason5(ynwithdraw5);
					        UptUsers.setDsWithdraw(request.getParameter("dswithdraw"));                // 사유내용
					        
					        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					        //Calendar c1 = Calendar.getInstance();
					        //String strToday = sdf.format(c1.getTime());
					        Date dt = new Date();
					        
					        // 현재일자
					        UptUsers.setDtWithdraw(dt); // request.getParameter("dt_withdraw"));                // 탈퇴일자(현재일자처리:8자리)
							
							irst = userMapper.updateByWithdrawKey(UptUsers);
							
							if (irst == 1) {
								result.setCode("00");
								result.setDescription("탈퇴성공");
								// result.setUserInfo(user);
							} else {
								result.setCode("E");
								result.setDescription("탈퇴처리 중 오류가 발생하였습니다.");
								//result.setUserInfo(user);
							}
						}
					}
				}
			}
		}
		
		return result;
	}


	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Users GetUserInfo(String arg) {
		
		String idUser = arg;
		
		System.out.println("GetUserInfo idUser : " + idUser);
			
		Users user = userMapper.selectByPrimaryKey(idUser);
		
		return user;
	}

	

	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result GetAuthId(String arg1, String arg2, String arg3) {
	
		long irst = 0; 
		Result result = new Result();
		
        // 이름항목 입력 체크
		String idUser = arg1;
		String notel = arg2;
		String noCountry = arg3;
		
		System.out.println("regSmsRandom in idUser : " +  idUser );		
		System.out.println("regSmsRandom in notel : " +  notel );		
		System.out.println("regSmsRandom in noCountry : " +  noCountry );		
		
        if (notel.length() < 10) {
        	// 정규식 불일치
        	result.setCode("T");
			result.setDescription("휴대전화 번호를 확인하세요.");
			// return result;
		} else {

			// 등록처리

			AuthCode InsAhcd = new AuthCode(); // 데이터 타입

			long idAuthcd = 0;

			InsAhcd.setIdAuthCode(idAuthcd); // CD_AUTH
			InsAhcd.setCdAuth(""); // CD_AUTH
			InsAhcd.setTyAuth("1"); // 인증타입코드 1.SMS 2.Email

			// 현재일자 ( 사용하지 않음 : db에서 now 처리 )
			Date dt = new Date();
			InsAhcd.setDtReg(dt); // request.getParameter("dt_withdraw")); // 탈퇴일자(현재일자처리:8자리)

			System.out.println("authcodeMapper.insertAfterGetKey : " + " / " + InsAhcd);

			// 인증번호가 4자리용
			irst = authcodeMapper.insertAfterGetKey2(InsAhcd);
			// AuthCode rtnAhcd = authcodeMapper.insertAfterGetKey(InsAhcd);

			irst = InsAhcd.getIdAuthCode(); // 추출 가능

			System.out.println("find( return : irst ) : " + " / " + irst);
			// 7월17일-- 비번 찾기 Start------------------------------
			AuthCode authCode = authcodeMapper.selectByPrimaryKey(irst);
			String randomCode = authCode.getCdAuth();

			
			AuthCode msgAuthCode = new AuthCode(); // 보내려는 메시지 코드
			msgAuthCode.setCdAuth("인증번호는 ["+randomCode+"] 입니다");	// 인증번호 문자마세지내용으로 임시사용 
			msgAuthCode.setTyAuth(notel);	// 인증번호 보내는 대상자번호 임시사용
			// irst (AUTH_CODE code 값을 가지고 해당코드로 MSG_DATA에 Insert)
			authcodeMapper.insertSendMsg(msgAuthCode);
			//
			// 7월17일 -- 비번 찾기 End------------------------------
			
			if (irst > 0) {
				result.setCode(String.valueOf(irst));
				result.setDescription("신규등록성공");
				//result.setUserInfo(user);
				// return result;
			} else {
				result.setCode("C");
				result.setDescription("인증번호 등록처리 중 오류가 발생하였습니다.");
				//result.setUserInfo(user);
				// return result;
			}		
        }
		
		return result;
	}
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result SetDelivery1(HttpServletRequest request) {
	
		Result result = new Result();
	
		// 등록처리 
	    Users deliveryUser = new Users(); //데이터 타입
		
		String email = request.getParameter("email");	// 유저 id
		String name = request.getParameter("name");			// 이름
		String phone = request.getParameter("phone");			// 전화번호
		String address = request.getParameter("address");			// 주소
	    
		deliveryUser.setUsername(email);
	    deliveryUser.setNmUserDelivery1(name);
	    deliveryUser.setNoTelDelivery1(phone);
	    deliveryUser.setDsAddrDelivery1(address);
		
	    int irst = userMapper.updateDelivery1(deliveryUser);
	    
		
		if (irst > 0) {
			result.setCode("00");
			result.setDescription("변경성공");
			result.setUserInfo(deliveryUser);
		} else {
			result.setCode("E");
			result.setDescription("배송지 입력 중 오류가 발생하였습니다.");
			result.setUserInfo(deliveryUser);
		}
		
		return result;
	}
	
	@Override
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public Result SetDelivery100(HttpServletRequest request) {
	
		Result result = new Result();
	
		// 등록처리 
	    Users deliveryUser = new Users(); //데이터 타입
		
		String email = request.getParameter("email");	// 유저 id
		String name = request.getParameter("name");			// 이름
		String phone = request.getParameter("phone");			// 전화번호
		String address = request.getParameter("address");			// 주소
	    
		deliveryUser.setUsername(email);
	    deliveryUser.setNmUserDelivery100(name);
	    deliveryUser.setNoTelDelivery100(phone);
	    deliveryUser.setDsAddrDelivery100(address);
		
	    int irst = userMapper.updateDelivery100(deliveryUser);
	    
		
		if (irst > 0) {
			result.setCode("00");
			result.setDescription("변경성공");
			result.setUserInfo(deliveryUser);
		} else {
			result.setCode("E");
			result.setDescription("배송지 입력 중 오류가 발생하였습니다.");
			result.setUserInfo(deliveryUser);
		}

		return result;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Result UpdateGender(Users user) {
		Result result = new Result();
		
		userMapper.updateGender(user);
		
		return result;
	}

	
}
