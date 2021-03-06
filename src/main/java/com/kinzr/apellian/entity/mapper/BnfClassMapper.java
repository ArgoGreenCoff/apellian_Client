package com.kinzr.apellian.entity.mapper;

import com.kinzr.apellian.entity.model.BnfClass;
import java.util.List;

public interface BnfClassMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BNF_CLASS
	 * @mbg.generated  Tue Apr 28 23:42:08 KST 2020
	 */
	int deleteByPrimaryKey(Integer idBnfClass);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BNF_CLASS
	 * @mbg.generated  Tue Apr 28 23:42:08 KST 2020
	 */
	int insert(BnfClass record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BNF_CLASS
	 * @mbg.generated  Tue Apr 28 23:42:08 KST 2020
	 */
	BnfClass selectByPrimaryKey(Integer idBnfClass);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BNF_CLASS
	 * @mbg.generated  Tue Apr 28 23:42:08 KST 2020
	 */
	List<BnfClass> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BNF_CLASS
	 * @mbg.generated  Tue Apr 28 23:42:08 KST 2020
	 */
	int updateByPrimaryKey(BnfClass record);
}