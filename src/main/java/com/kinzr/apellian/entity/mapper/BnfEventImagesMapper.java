package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.BnfClassImages;
import com.kinzr.apellian.entity.model.BnfEventImages;
import java.util.List;

public interface BnfEventImagesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_IMAGES
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int deleteByPrimaryKey(Integer idBnfEventImage);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_IMAGES
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int insert(BnfEventImages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_IMAGES
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    BnfEventImages selectByPrimaryKey(Integer idBnfEventImage);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_IMAGES
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    List<BnfEventImages> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_EVENT_IMAGES
     *
     * @mbg.generated Mon May 18 06:33:12 KST 2020
     */
    int updateByPrimaryKey(BnfEventImages record);
    
    // 클래스에 해당하는 이미지 리스트
    List<BnfEventImages> selectEventImages(Integer idClass);

}