package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectPlanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    long countByExample(ProjectPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByExample(ProjectPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insert(ProjectPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insertSelective(ProjectPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ProjectPlan> selectByExampleWithRowbounds(ProjectPlanExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ProjectPlan> selectByExample(ProjectPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    ProjectPlan selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") ProjectPlan record, @Param("example") ProjectPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExample(@Param("record") ProjectPlan record, @Param("example") ProjectPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKey(ProjectPlan record);
}