package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignLiteratureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ForeignLiteratureMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    long countByExample(ForeignLiteratureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByExample(ForeignLiteratureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insert(ForeignLiterature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int insertSelective(ForeignLiterature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ForeignLiterature> selectByExampleWithRowbounds(ForeignLiteratureExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    List<ForeignLiterature> selectByExample(ForeignLiteratureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    ForeignLiterature selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") ForeignLiterature record, @Param("example") ForeignLiteratureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByExample(@Param("record") ForeignLiterature record, @Param("example") ForeignLiteratureExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKeySelective(ForeignLiterature record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_literature
     *
     * @mbg.generated Tue May 12 21:57:39 CST 2020
     */
    int updateByPrimaryKey(ForeignLiterature record);
}