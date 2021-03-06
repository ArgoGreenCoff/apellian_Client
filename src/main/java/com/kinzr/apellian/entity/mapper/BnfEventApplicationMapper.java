package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.BnfClassApplication;
import com.kinzr.apellian.entity.model.BnfEventApplication;
import java.util.List;

public interface BnfEventApplicationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_APPLICATION
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int deleteByPrimaryKey(Integer idEventApplication);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_APPLICATION
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int insert(BnfEventApplication record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_APPLICATION
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    BnfEventApplication selectByPrimaryKey(Integer idEventApplication);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_APPLICATION
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    List<BnfEventApplication> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_APPLICATION
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int updateByPrimaryKey(BnfEventApplication record);
    
    
    // 해당 이벤트의 신청한 인원수 가져오기
    int selectByApplyCntByEvent(Integer idEventApplication);

    // 해당 유저의 이벤트 신청횟수
    int selectByApplyCntByUser(String idUser);

    // 해당 유저의 이벤트 신청여부
    Boolean selectApplyRequestByUser(BnfEventApplication eventApply);

    // 유저 당첨 여부 확인
    BnfEventApplication selectApplyInfo(BnfEventApplication bnfApply);

}