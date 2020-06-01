package com.ncu.graduation.mapper;

import com.ncu.graduation.model.College;
import com.ncu.graduation.model.CollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CollegeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    long countByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int deleteByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int insert(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int insertSelective(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    List<College> selectByExampleWithRowbounds(CollegeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    List<College> selectByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    College selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByExampleSelective(@Param("record") College record,
        @Param("example") CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByPrimaryKeySelective(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByPrimaryKey(College record);
}