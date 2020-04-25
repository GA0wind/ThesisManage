package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectSelectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    long countByExample(ProjectSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int deleteByExample(ProjectSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int insert(ProjectSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int insertSelective(ProjectSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    List<ProjectSelect> selectByExampleWithRowbounds(ProjectSelectExample example,
        RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    List<ProjectSelect> selectByExample(ProjectSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    ProjectSelect selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int updateByExampleSelective(@Param("record") ProjectSelect record,
        @Param("example") ProjectSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int updateByExample(@Param("record") ProjectSelect record,
        @Param("example") ProjectSelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectSelect record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    int updateByPrimaryKey(ProjectSelect record);
}