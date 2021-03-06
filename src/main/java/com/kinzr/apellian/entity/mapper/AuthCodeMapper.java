package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.AuthCode;
import java.util.List;

public interface AuthCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(Long idAuthCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(AuthCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    AuthCode selectByPrimaryKey(Long idAuthCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<AuthCode> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(AuthCode record);
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insertAfterGetKey(AuthCode record);   
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AUTH_CODE
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insertAfterGetKey2(AuthCode record);   
        
    
    int insertSendMsg(AuthCode record);
    
}