package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.MyWedCategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyWedCategoryMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	int deleteByPrimaryKey(@Param("idWedding") Long idWedding, @Param("cdCategory") Integer cdCategory);
			// ,@Param("idWeddingHall") Integer idWeddingHall);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	int insert(MyWedCategory record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	MyWedCategory selectByPrimaryKey(@Param("idWedding") Long idWedding, @Param("cdCategory") Integer cdCategory); 
			// ,	@Param("idWeddingHall") Integer idWeddingHall);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	List<MyWedCategory> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	int updateByPrimaryKey(MyWedCategory record);
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	List<MyWedCategory> selectByidWedding(@Param("idWedding") Long idWedding);
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	MyWedCategory selectSumByCtgyidWedding(@Param("idWedding") Long idWedding);	

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MY_WED_CATEGORY
	 * @mbg.generated  Tue Apr 28 16:45:02 KST 2020
	 */
	int updateByAmPrice(MyWedCategory record);	
	  
}