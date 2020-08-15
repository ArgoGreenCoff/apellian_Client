package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.BnfClassBnfImages;
import com.kinzr.apellian.entity.model.BnfClassImages;
import java.util.List;

public interface BnfClassImagesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_CLASS_IMAGES
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(Integer idBnfClassImage);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_CLASS_IMAGES
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(BnfClassImages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_CLASS_IMAGES
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    BnfClassImages selectByPrimaryKey(Integer idBnfClassImage);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_CLASS_IMAGES
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<BnfClassImages> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BNF_CLASS_IMAGES
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(BnfClassImages record);
    
    
    // 클래스에 해당하는 이미지 리스트
    List<BnfClassImages> selectClassImages(Integer idClass);
    
    
}