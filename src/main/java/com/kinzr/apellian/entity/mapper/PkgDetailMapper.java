package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.PkgDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PkgDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int deleteByPrimaryKey(@Param("idPkg") Integer idPkg, @Param("cdCategory") Integer cdCategory);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int insert(PkgDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    PkgDetail selectByPrimaryKey(@Param("idPkg") Integer idPkg, @Param("cdCategory") Integer cdCategory);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<PkgDetail> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    int updateByPrimaryKey(PkgDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<PkgDetail> selectByidPkg(@Param("idPkg") Integer idPkg);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<PkgDetail> selectByCdtheme(Integer cdtheme);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PKG_DETAIL
     *
     * @mbg.generated Mon Apr 27 15:07:22 KST 2020
     */
    List<PkgDetail> selectBySDMtheme(Integer cdtheme);
       
    
    
}