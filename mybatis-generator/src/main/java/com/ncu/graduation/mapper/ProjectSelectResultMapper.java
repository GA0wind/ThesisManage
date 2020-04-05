package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectSelectResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    long countByExample(ProjectSelectResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int deleteByExample(ProjectSelectResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int insert(ProjectSelectResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int insertSelective(ProjectSelectResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    List<ProjectSelectResult> selectByExampleWithRowbounds(ProjectSelectResultExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    List<ProjectSelectResult> selectByExample(ProjectSelectResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    ProjectSelectResult selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") ProjectSelectResult record, @Param("example") ProjectSelectResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int updateByExample(@Param("record") ProjectSelectResult record, @Param("example") ProjectSelectResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int updateByPrimaryKeySelective(ProjectSelectResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select_result
     *
     * @mbg.generated Fri Apr 03 22:33:14 CST 2020
     */
    int updateByPrimaryKey(ProjectSelectResult record);
}