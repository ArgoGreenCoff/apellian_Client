package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.CdWedOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CdWedOptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_WED_OPTION
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(@Param("idWeddingHall") Integer idWeddingHall, @Param("idOption") Integer idOption);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_WED_OPTION
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(CdWedOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_WED_OPTION
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    CdWedOption selectByPrimaryKey(@Param("idWeddingHall") Integer idWeddingHall, @Param("idOption") Integer idOption);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_WED_OPTION
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<CdWedOption> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_WED_OPTION
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(CdWedOption record);
}