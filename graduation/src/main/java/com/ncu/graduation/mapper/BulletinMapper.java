package com.ncu.graduation.mapper;

import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.model.BulletinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BulletinMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    long countByExample(BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int deleteByExample(BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int insert(Bulletin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int insertSelective(Bulletin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    List<Bulletin> selectByExampleWithBLOBsWithRowbounds(BulletinExample example,
        RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    List<Bulletin> selectByExampleWithBLOBs(BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    List<Bulletin> selectByExampleWithRowbounds(BulletinExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    List<Bulletin> selectByExample(BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    Bulletin selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByExampleSelective(@Param("record") Bulletin record,
        @Param("example") BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Bulletin record,
        @Param("example") BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByExample(@Param("record") Bulletin record, @Param("example") BulletinExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByPrimaryKeySelective(Bulletin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Bulletin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulletin
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    int updateByPrimaryKey(Bulletin record);
}