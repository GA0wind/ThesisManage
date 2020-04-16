package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectApplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    long countByExample(ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int deleteByExample(ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int insert(ProjectApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int insertSelective(ProjectApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<ProjectApply> selectByExampleWithBLOBsWithRowbounds(ProjectApplyExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<ProjectApply> selectByExampleWithBLOBs(ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<ProjectApply> selectByExampleWithRowbounds(ProjectApplyExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<ProjectApply> selectByExample(ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    ProjectApply selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByExampleSelective(@Param("record") ProjectApply record, @Param("example") ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") ProjectApply record, @Param("example") ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByExample(@Param("record") ProjectApply record, @Param("example") ProjectApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(ProjectApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_apply
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByPrimaryKey(ProjectApply record);
}