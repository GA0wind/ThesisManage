package com.ncu.graduation.mapper;

import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.model.TaskBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskBookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    long countByExample(TaskBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int deleteByExample(TaskBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int insert(TaskBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int insertSelective(TaskBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    List<TaskBook> selectByExampleWithRowbounds(TaskBookExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    List<TaskBook> selectByExample(TaskBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    TaskBook selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int updateByExampleSelective(@Param("record") TaskBook record, @Param("example") TaskBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int updateByExample(@Param("record") TaskBook record, @Param("example") TaskBookExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int updateByPrimaryKeySelective(TaskBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task_book
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    int updateByPrimaryKey(TaskBook record);
}