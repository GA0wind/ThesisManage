package com.ncu.graduation.mapper;

import com.ncu.graduation.dto.UserSearchDTO;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.util.BlindDistribution;
import com.ncu.graduation.util.BlindDistribution.TeacherTwotuple;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 18:49
 * @description：自定义TeacherMapper接口
 * @modified By：
 * @version: 0.0.1
 */
public interface TeacherExtMapper {

  List<TeacherTwotuple> selectTnoAndStudentNum(@Param("schoolYear") String schoolYear,
      @Param("college") String college);

  int updateByTno(Teacher teacher);


  List<Teacher> searchTeaList(@Param("schoolYear") String schoolYear, @Param("userSearchDTO")
      UserSearchDTO userSearchDTO);
}
