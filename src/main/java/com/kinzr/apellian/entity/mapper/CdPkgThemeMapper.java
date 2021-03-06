package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.CdPkgTheme;
import java.util.List;

public interface CdPkgThemeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(Integer cdPkgTheme);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(CdPkgTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    CdPkgTheme selectByPrimaryKey(Integer cdPkgTheme);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<CdPkgTheme> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(CdPkgTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CD_PKG_THEME
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<CdPkgTheme> selectFirstFive();    
    
}