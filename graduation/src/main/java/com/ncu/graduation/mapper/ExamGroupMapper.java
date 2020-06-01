package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ExamGroup;
import com.ncu.graduation.model.ExamGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ExamGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    long countByExample(ExamGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int deleteByExample(ExamGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int insert(ExamGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int insertSelective(ExamGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    List<ExamGroup> selectByExampleWithRowbounds(ExamGroupExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    List<ExamGroup> selectByExample(ExamGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    ExamGroup selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByExampleSelective(@Param("record") ExamGroup record,
        @Param("example") ExamGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByExample(@Param("record") ExamGroup record,
        @Param("example") ExamGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByPrimaryKeySelective(ExamGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam_group
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    int updateByPrimaryKey(ExamGroup record);
}