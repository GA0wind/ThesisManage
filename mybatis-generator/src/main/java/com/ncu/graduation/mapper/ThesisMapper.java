package com.ncu.graduation.mapper;

import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ThesisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    long countByExample(ThesisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int deleteByExample(ThesisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int insert(Thesis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int insertSelective(Thesis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<Thesis> selectByExampleWithRowbounds(ThesisExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    List<Thesis> selectByExample(ThesisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    Thesis selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByExampleSelective(@Param("record") Thesis record, @Param("example") ThesisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByExample(@Param("record") Thesis record, @Param("example") ThesisExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByPrimaryKeySelective(Thesis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thesis
     *
     * @mbg.generated Thu Apr 16 21:08:57 CST 2020
     */
    int updateByPrimaryKey(Thesis record);
}