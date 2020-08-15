package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.CdCategory;
import java.util.List;

public interface CdCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_CATEGORY
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(Integer cdCategory);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_CATEGORY
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(CdCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_CATEGORY
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    CdCategory selectByPrimaryKey(Integer cdCategory);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_CATEGORY
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<CdCategory> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_CATEGORY
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(CdCategory record);
}