package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.model.ThesisRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ThesisRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    long countByExample(ThesisRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByExample(ThesisRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insert(ThesisRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insertSelective(ThesisRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ThesisRecord> selectByExampleWithRowbounds(ThesisRecordExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ThesisRecord> selectByExample(ThesisRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    ThesisRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") ThesisRecord record, @Param("example") ThesisRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExample(@Param("record") ThesisRecord record, @Param("example") ThesisRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKeySelective(ThesisRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis_record
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKey(ThesisRecord record);
}