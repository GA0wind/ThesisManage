package com.ncu.graduation.mapper;

import com.ncu.graduation.dto.UserSearchDTO;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.vo.ProAndStuNumVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StudentExtMapper {

  List<ProAndStuNumVO> countByCollege(String schoolYear);

  List<Student> searchStuList(@Param("schoolYear") String schoolYear, @Param("userSearchDTO")
      UserSearchDTO userSearchDTO);
}